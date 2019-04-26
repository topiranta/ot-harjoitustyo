
package topiranta.lightapplication.utils;

import java.io.*;
import java.util.*;
import java.net.*;
import org.json.simple.*;
import topiranta.lightapplication.devices.*;

/**
 * Luokassa on staattiset metodit, joilla otetaan varsinainen yhteys REST-rajapintoihin ja haetaan tai lähetetään niihin tietoa
 * 
 */

public class Connections {
    
    /**
     * Metodi tekee POST-operaation sille parametrina annettuun URL-osoitteeseen myös parametrilla annettuna merkkijonolla
     * @param url   URL, johon POST tehdään
     * @param message   varsinainen POST-viesti
     * @return  operaation seurauksena rajapinnasta saatu viesti
     * @throws Exception    virhe annetaan, mikäli toiminto epäonnistuu
     */
    
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
    
    /**
     * Metodi hakee GET-operaatiolla JSON-muotoista tietoa rajapinnasta
     * @param url   URL-osoite, josta tieto haetaan
     * @return  saatu JSON-muotoinen tieto
     * @throws Exception    virhe heitetään, mikäli GET-operaatio epäonnistuu
     */
    
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
    
    /**
     * JSON-viestin lähettäminen haluttuun URL-osoitteeseen
     * @param url   URL, johon viesti lähetetään
     * @param message   lähetettävä JSON-viesti
     * @throws Exception    metodi heittää virheen, mikäli operaatio epäonnistuu
     */
    
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
    
    private static HttpURLConnection openNewConnection(URL url, String requestMethod) throws Exception {
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        if (!requestMethod.equals("GET")) {
            
            connection.setDoOutput(true);     
            
        }

        connection.setRequestMethod(requestMethod);
        
        return connection;
   
    }
    
    private static void writeMessage(HttpURLConnection connection, String message) throws Exception {
        
        OutputStreamWriter messageWriter = new OutputStreamWriter(connection.getOutputStream());
        messageWriter.write(message);
        messageWriter.close();
        
    }
    
    private static String getResponse(HttpURLConnection connection) throws Exception {
        
        StringBuilder sBuilder = new StringBuilder();
        Scanner reader = new Scanner(connection.getInputStream());
            
        while (reader.hasNext()) {
                
            sBuilder.append(reader.nextLine());
                
        }
            
        reader.close();
            
        return sBuilder.toString();
        
    }
    
       
    
}
