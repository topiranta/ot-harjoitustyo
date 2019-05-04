
package topiranta.lightapplication.logics;

import java.net.URL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
        JSONObject response = (JSONObject) Connections.getJSON(getURL);
        
        for (Object object : response.keySet()) {
            
            int id = Integer.valueOf(object.toString());
            
            JSONObject object2 = (JSONObject) response.get("" + id);
            
            String name = object2.get("name").toString();
            
            Lamp lamp = new Lamp(id, bridge, name);
            lamps.add(lamp);
            
        }
        
        return lamps;
    }
    
    /**
     * Metodi lähettää tiedon uudesta sovelluksesta valo-ohjaimelle, ja palauttaa autentikointiavaimen, 
     * jota käytetään osana REST-rajapintakutsujen osoitetta
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
    
    public static ArrayList<Lamp> lampsToBeUpdated(int[] previousValues, ArrayList<Lamp> userListOfLampsToUpdate, Bridge bridge) throws Exception {
        
        ArrayList<Lamp> toReturn = new ArrayList<>();
        ArrayList<Lamp> allLamps = getAllLamps(bridge);
        
        URL getURL = new URL("http://" + bridge.getIp() + "/api/" + bridge.getAppId() + "/lights");
        JSONObject response = (JSONObject) Connections.getJSON(getURL);
        
        
        for (Lamp lamp : allLamps) {
            
            if (userListOfLampsToUpdate.contains(lamp)) {
            
                JSONObject lampInformation = (JSONObject) response.get("" + lamp.getId());
                String lampType = lampInformation.get("type").toString();
                JSONObject state = (JSONObject) lampInformation.get("state");


                int[] values = new int[2];

                if (!lampType.equals("Dimmable light")) {

                    values[0] = Integer.valueOf(state.get("ct").toString());

                } else {

                    values[0] = previousValues[0];

                }

                values[1] = Integer.valueOf(state.get("bri").toString());
                String on = state.get("on").toString();
                String reachable = state.get("reachable").toString();


                if (on.equals("true") && reachable.equals("true") && (values[0] == previousValues[0] || values[0] == 211) && (values[1] == previousValues[1] || values[1] == 211)) {

                    toReturn.add(lamp);

                }
            
            }
            
            
        }
        
        return toReturn;
        
    }
    
    
    
}
