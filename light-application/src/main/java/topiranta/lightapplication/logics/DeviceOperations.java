
package topiranta.lightapplication.logics;

import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import topiranta.lightapplication.devices.Bridge;
import topiranta.lightapplication.devices.Lamp;
import topiranta.lightapplication.utils.Connections;


public class DeviceOperations {

    public static ArrayList<Lamp> getAllLamps(Bridge bridge) throws Exception {
        
        ArrayList<Lamp> lamps = new ArrayList<>();
        URL getURL = new URL("http://" + bridge.getIp() + "/api/" + bridge.getAppId() + "/lights");
        JSONObject response = Connections.getJSON(getURL);
        
        for (Object object : response.keySet()) {
            
            Lamp lamp = new Lamp(Integer.valueOf(object.toString()), bridge);
            lamps.add(lamp);
            
        }
        
        return lamps;
    }

    public static String authenticateApplication(String bridgeIp) throws Exception {
        
        URL postURL = new URL("http://" + bridgeIp + "/api");
        String message = "{\"devicetype\":\"ot-lamp-application\"}";
        
        JSONArray response;
        
        try {
        
            response = Connections.postMessage(postURL, message);
        
        } catch (Exception e) {
            
            throw new Exception("Authentication error: " + e);
            
        }
        
        JSONObject responseObject = (JSONObject) response.get(0);
        JSONObject success = (JSONObject) responseObject.get("success");
        
        if (success == null || success.toString().equals("")) {
            
            throw new Exception("Authentication failed.");
            
        }
        
        return success.get("username").toString();
    }
    
    
    
}
