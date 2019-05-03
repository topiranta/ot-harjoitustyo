
package topiranta.lightapplication.logics;

import topiranta.lightapplication.utils.*;
import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.logics.DefaultAndTestValues;
import java.util.*;
import java.time.*;
import java.net.*;
import org.json.simple.*;

public class SunTimes {
    
    
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
