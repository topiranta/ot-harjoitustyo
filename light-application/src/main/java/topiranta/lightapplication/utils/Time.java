
package topiranta.lightapplication.utils;

import java.time.*;
import java.util.*;

/**
 * Yleiset ajan käsittelyyn liittyvät metodit sisältävä luokka
 * 
 */

public class Time {
    
    /**
     * Metodi hakee tiedon käyttöjärjestelmän kellon aikavyöhykkeestä
     * @return aikapoikkeama UTC:sta kokonaislukuna
     */
    
    public static int getTimeZoneOffSet() {
        
        TimeZone tz = TimeZone.getDefault();
        int offset = tz.getOffset(System.currentTimeMillis()) / (1000 * 60 * 60);
        
        return offset;
        
    }
    
    /**
     * Metodi UTC-ajan muuttamiseksi käyttöjärjestelmän mukaiseen aikaan
     * @param dateTime aika UTC:ssa merkkijonomuotoisena
     * @return aika käyttöjärjestelmän aikavyöhykkeessä
     */
    
    public static LocalDateTime getTimeInSystemTimeZone(String dateTime) {
        
        
        String[] pieces = dateTime.split("\\+");
        
        LocalDateTime parsed = LocalDateTime.parse(pieces[0]);
        
        return parsed.plusHours((long) getTimeZoneOffSet());
        
    }
    
}
