
package tr.ot.light.application;

import java.io.*;
import java.util.*;
import java.net.*;
import org.json.simple.*;


public class Utils {
    
    public static String authenticateApplication(String bridgeIp) throws Exception {
        
        URL postURL = new URL("http://" + bridgeIp + "/api");
        String message = "{\"devicetype\":\"ot-lamp-application\"}";
        
        JSONArray response = Utils.postMessage(postURL, message);
        
        JSONObject responseObject = (JSONObject)response.get(0);
        JSONObject success = (JSONObject) responseObject.get("success");
        
        if (success == null || success.toString().equals("")) {
            
            throw new Exception("Authentication failed.");
            
        }
        
        return success.get("username").toString();
        
        
        
    }
    
    public static JSONArray postMessage(URL url, String message) throws Exception {
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        
        
        OutputStreamWriter messageWriter = new OutputStreamWriter(connection.getOutputStream());
        messageWriter.write(message);
        messageWriter.close();
        
        int responseCode = connection.getResponseCode();
        
        
        StringBuilder sBuilder = new StringBuilder();
        
        if(responseCode != 200) {
            
            connection.disconnect();
            throw new Exception("" + responseCode);
            
        } else {
            
            Scanner reader = new Scanner(connection.getInputStream());
            
            while (reader.hasNext()) {
                
                sBuilder.append(reader.nextLine());
                
            }
            
            reader.close();
            
            connection.disconnect();
            
            Object receivedMessage = JSONValue.parse(sBuilder.toString());
            
            return (JSONArray) receivedMessage;
            
        }
        
        
        
        
        
    }
       
    
}
