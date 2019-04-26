
package topiranta.lightapplication.logics;

import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import topiranta.lightapplication.devices.Bridge;
import topiranta.lightapplication.devices.Lamp;
import topiranta.lightapplication.utils.Connections;

/**
 * Staattiset metodit yleisten operaatioiden suorittamiseksi fyysisen valo-ohjaimen kanssa.
 * 
 */

public class DeviceOperations {
    
    /**
     * Kaikkien lamppujen hakeminen valo-ohjaimelta. Metodi pyytää ohjaimelta tiedon kaikista siihen kytketyistä valoista.
     * @param bridge    ohjain, jonka lamput halutaan hakea
     * @return  lista lamppuolioita, jotka on luotu sovellukseen fyysiseltä ohjaimelta saadun tiedon perusteella
     * @throws Exception    metodi osaa heittää virheen, mikäli tiedon hakeminen epäonnistuu
     */

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
    
    /**
     * Metodi lähettää tiedon uudesta sovelluksesta valo-ohjaimelle, ja palauttaa autentikointiavaimen, jota käytetään osana REST-rajapintakutsujen osoitetta
     * @param bridgeIp  fyysisen valo-ohjaimen IP-osoite lähiverkossa
     * @return  fyysiseltä valo-ohjaimelta saatu autentikointiavain
     * @throws Exception    metodi osaa heittää virheen, mikäli autentikointi epäonnistuu esimerkiksi yhteysvirheen vuoksi
     */

    public static String authenticateApplication(String bridgeIp) throws Exception {
        
        URL postURL = new URL("http://" + bridgeIp + "/api");
        String message = "{\"devicetype\":\"ot-lamp-application\"}";
        
        JSONArray response;
        
        try {
        
            response = (JSONArray) Connections.postJSON(postURL, message);
        
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
