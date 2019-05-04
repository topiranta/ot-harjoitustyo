
package topiranta.lightapplication.logics;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import topiranta.lightapplication.devices.*;

public class LightCalculator {
    
    private LocalDateTime sunTimesFetched;
    private LocalDateTime dimStart;
    private LocalDateTime dimStop;
    private Bridge bridge;
    
    public LightCalculator(Bridge bridge) throws Exception {
        
        this.bridge = bridge;
        this.refresh();
        
    }
    
    public void refresh() throws Exception {
        
        ArrayList<LocalDateTime> receivedTimes = SunTimes.getSunTimes(this.bridge);
        
        if (receivedTimes.size() == 0) {
            
            return;
            
        }
        
        this.sunTimesFetched = LocalDateTime.now();
        this.dimStart = receivedTimes.get(0);
        
        if (receivedTimes.get(1).getHour() > 19 || receivedTimes.get(1).toLocalDate().compareTo(LocalDate.now()) > 0 || receivedTimes.get(1).getYear() == 1970) {
            
            this.dimStop = LocalDateTime.parse(LocalDate.now().toString() + "T20:00:00");
            
        } else {
            
            this.dimStop = receivedTimes.get(1);
            
        }
        
    }
    
    public int[] getLightValues() throws Exception {
        
        int[] values = new int[2];
        
        if (LocalDate.now().compareTo(this.sunTimesFetched.toLocalDate()) > 0 && LocalDateTime.now().getHour() >= 5) {
            
            this.refresh();
            
        }
        
        int colorTemperature = (int) (this.getDimCompleteness() * 347) + 153;
        int brightness = 254 - (int) (this.getDimCompleteness() * 154);
        
        values[0] = colorTemperature;
        values[1] = brightness;
        
        return values;
        
    }
    
    public Double getDimCompleteness() {
        
        long minutesPassed = (long) (this.dimStart.until(LocalDateTime.now(), ChronoUnit.MINUTES));
        
        double completeness = (double) minutesPassed / this.getDimmingLength();
        
        if (completeness < 0) {
            
            return 0.0;
            
        }
        
        if (completeness > 1.0) {
            
            return 1.0;
            
        }
        
        return completeness;
        
    }
    
    public long getDimmingLength() {
        
        return (long) (this.dimStart.until(dimStop, ChronoUnit.MINUTES));
        
    }
    
}
