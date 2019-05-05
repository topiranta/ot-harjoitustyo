
package topiranta.lightapplication.logics;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import topiranta.lightapplication.devices.*;

/**
 * Värilaskinolio, joka osaa laskea lampuille sopivat väriarvot niiden sijainnin sekä kellonajan mukaan. Laskin hakee REST-rajapinnasta
 * tiedon siitä, milloin aurinko on korkeimmillaan ja milloin se laskee valo-ohjaimen sijannissa. Näiden sekä kellonajan
 * perusteella perusteella se laskee oikean sävyn ja kirkkauden valoille.
 * 
 */

public class LightCalculator {
    
    private LocalDateTime sunTimesFetched;
    private LocalDateTime sunsetTime;
    private LocalDateTime dimStart;
    private LocalDateTime dimStop;
    private Bridge bridge;
    
    public LightCalculator(Bridge bridge) throws Exception {
        
        this.bridge = bridge;
        this.refresh();
        
    }
    
    /**
     * Metodi laskimen päivittämiseksi. Metodi hakee uudet ajat auringon huippukorkeudelle sekä auringonlaskulle. Auringon huipputunti asetetaan himmennyksen alkuajankohdaksi.
     * Loppuajankohdaksi asetetaan auringonlasku, kuitenkin niin, että himmennys päättyy viimeistään klo 20. Metodi tallentaa tiedon, milloin ajat on rajapinnasta
     * haettu.
     * @throws Exception metodi heittää virheen, mikäli toiminto epäonnistuu
     */
    
    public void refresh() throws Exception {
        
        ArrayList<LocalDateTime> receivedTimes = SunTimes.getSunTimes(this.bridge);
        
        if (receivedTimes.size() == 0) {
            
            throw new Exception("Fetching sunset time failed.");
            
        }
        
        this.sunTimesFetched = LocalDateTime.now();
        this.sunsetTime = receivedTimes.get(1);
        this.dimStart = receivedTimes.get(0);
        
        if (receivedTimes.get(1).getHour() > 19 || receivedTimes.get(1).toLocalDate().compareTo(LocalDate.now()) > 0 || receivedTimes.get(1).getYear() == 1970) {
            
            this.dimStop = LocalDateTime.parse(LocalDate.now().toString() + "T20:00:00");
            
        } else {
            
            this.dimStop = receivedTimes.get(1);
            
        }
        
    }
    
    /**
     * Metodi valoarvojen laskemiseksi. Metodi tarkistaa, ovatko ajat auringonlaskulle ja huipputunnille ajantasaiset vai tuleeko uudet hakea rajapinnasta.
     * @return taulukko, jossa uudet arvot värisävylle sekä kirkkaudelle
     * @throws Exception metodi heittää virheen, mikäli toiminto epäonnistuu
     */
    
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
    
    
    private Double getDimCompleteness() {
        
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
    
    private long getDimmingLength() {
        
        return (long) (this.dimStart.until(dimStop, ChronoUnit.MINUTES));
        
    }
    
    @Override
    public String toString() {
        
        int[] values = new int[2];
        
        try {
        
            values = this.getLightValues();
            
        } catch (Exception e) {
            
            return "Values could not be calculated: " + e.getMessage();
            
        }
        
        return "Solar noon is at " + dimStart.toLocalTime().toString() + " and sunset at " + sunsetTime.toLocalTime().toString() + ". \n"
                + "At " + LocalDateTime.now().toLocalTime().toString() + " calculated color temperature is " + values[0] + " and brightness " + values[1];
    }
    
}
