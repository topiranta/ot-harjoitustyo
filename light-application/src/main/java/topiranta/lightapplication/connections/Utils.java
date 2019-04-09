
package topiranta.lightapplication.connections;

import java.io.*;
import java.util.*;
import java.net.*;
import org.json.simple.*;
import topiranta.lightapplication.devices.*;


public class Utils {
    

    public static ArrayList<Lamp> getAllLamps(Bridge bridge) throws Exception {
        
        ArrayList<Lamp> lamps = new ArrayList<>();
        
        URL getURL = new URL("http://" + bridge.getIp() + "/api/" + bridge.getAppId() + "/lights");
        JSONObject response = getJSON(getURL);
        
        for (Object object : response.keySet()) {
            
            Lamp lamp = new Lamp(Integer.valueOf(object.toString()), bridge);
            lamps.add(lamp);
            
        }
        
        return lamps;
        
    }
    
    public static String authenticateApplication(String bridgeIp) throws Exception {
        
        URL postURL = new URL("http://" + bridgeIp + "/api");
        String message = "{\"devicetype\":\"ot-lamp-application\"}";
        
        JSONArray response = Utils.postMessage(postURL, message);
        JSONObject responseObject = (JSONObject) response.get(0);
        JSONObject success = (JSONObject) responseObject.get("success");
        
        if (success == null || success.toString().equals("")) {
            
            throw new Exception("Authentication failed.");
            
        }
        
        return success.get("username").toString();
        
        
        
    }
    
    public static JSONArray postMessage(URL url, String message) throws Exception {
        
        HttpURLConnection connection = openNewConnection(url, "POST");
        writeMessage(connection, message);
        int responseCode = connection.getResponseCode();
        
        if (responseCode != 200) {
            
            connection.disconnect();
            throw new Exception("" + responseCode);
            
        } else {
            
            String response = getResponse(connection);
            connection.disconnect();
            Object receivedMessage = JSONValue.parse(response);
            
            return (JSONArray) receivedMessage;  
        }
    }
    
    public static JSONObject getJSON(URL url) throws Exception {
        
        HttpURLConnection connection = openNewConnection(url, "GET");
        int responseCode = connection.getResponseCode();
        
        if (responseCode != 200) {
            
            connection.disconnect();
            throw new Exception("" + responseCode);
            
        } else {
            
            String response = getResponse(connection);
            connection.disconnect();
            Object receivedMessage = JSONValue.parse(response);
            
            return (JSONObject) receivedMessage;
        }
        
    }
    
    public static void putJSON(URL url, String message) throws Exception {
        
        HttpURLConnection connection = openNewConnection(url, "PUT");
        writeMessage(connection, message);
        
        try {
            
            getResponse(connection);
            
        } catch (Exception e) {
            
            connection.disconnect();
            
            throw new Exception("Put failed: " + e);
            
        }
        
        connection.disconnect();
        
    }
    
    public static HttpURLConnection openNewConnection(URL url, String requestMethod) throws Exception {
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        if (!requestMethod.equals("GET")) {
            
            connection.setDoOutput(true);     
            
        }

        connection.setRequestMethod(requestMethod);
        
        return connection;
   
    }
    
    public static void writeMessage(HttpURLConnection connection, String message) throws Exception {
        
        OutputStreamWriter messageWriter = new OutputStreamWriter(connection.getOutputStream());
        messageWriter.write(message);
        messageWriter.close();
        
    }
    
    public static String getResponse(HttpURLConnection connection) throws Exception {
        
        StringBuilder sBuilder = new StringBuilder();
        Scanner reader = new Scanner(connection.getInputStream());
            
        while (reader.hasNext()) {
                
            sBuilder.append(reader.nextLine());
                
        }
            
        reader.close();
            
        return sBuilder.toString();
        
    }
    
       
    
}
