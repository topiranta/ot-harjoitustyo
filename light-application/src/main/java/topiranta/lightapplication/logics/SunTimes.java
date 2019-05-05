
package topiranta.lightapplication.logics;

import topiranta.lightapplication.utils.*;
import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.logics.DefaultAndTestValues;
import java.util.*;
import java.time.*;
import java.net.*;
import org.json.simple.*;

/**
 * Luokka, joka huolehtii auringonlasku- ja huippuaikatiedon saamisesta rajapinnasta.
 * 
 */

public class SunTimes {
    
    /**
     * Metodi hakee rajapinnasta tiedot siitä, milloin aurinko on korkeimmillaan ja milloin se laskee valo-ohjaimen sijannissa. Mikäli valo-ohjaimelle
     * ei ole asetettu sijaintitietoja, käyttää metodi Helsingin sijaintia rajapintakutsussa.
     * @param bridge valo-ohjain, jonka sijainnille tiedot haetaan
     * @return lista, jossa LocalDateTime-muotoiset arvot auringon huipp- ja laskuajoista
     * @throws Exception metodi heittää virheen, mikäli toiminto epäonnistuu
     */
    
    public static ArrayList<LocalDateTime> getSunTimes(Bridge bridge) throws Exception {
        
        String lat = bridge.getLat();
        String lng = bridge.getLng();
        
        if (lat == null || lng == null) {
            
            lat = DefaultAndTestValues.getHelsinkiLat();
            lng = DefaultAndTestValues.getHelsinkiLng();
        }
        
        URL getUrl = new URL("https://api.sunrise-sunset.org/json?lat=" + lat + "&lng=" + lng + "&formatted=0");
        
        JSONObject received = (JSONObject) Connections.getJSON(getUrl);
        JSONObject results = (JSONObject) received.get("results");
        
        ArrayList<LocalDateTime> toReturn = new ArrayList<>();
        
        toReturn.add(Time.getTimeInSystemTimeZone(results.get("solar_noon").toString()));
        toReturn.add(Time.getTimeInSystemTimeZone(results.get("sunset").toString()));
        
        return toReturn;
        
    }
    
}
