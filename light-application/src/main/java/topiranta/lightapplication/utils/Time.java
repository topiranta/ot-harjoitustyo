
package topiranta.lightapplication.utils;

import java.time.*;
import java.util.*;


public class Time {
    
    public static int getTimeZoneOffSet() {
        
        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getOffset(System.currentTimeMillis()) / (1000 * 60 * 60);
        
        return offset;
        
    }
    
    public static LocalDateTime getTimeInSystemTimeZone(String dateTime) {
        
        
        String[] pieces = dateTime.split("\\+");
        
        LocalDateTime parsed = LocalDateTime.parse(pieces[0]);
        
        return parsed.plusHours((long) getTimeZoneOffSet());
        
    }
    
}
