
package topiranta.lightapplication.devices;

import java.util.*;

/**
 * Luokka kuvaa Philips Hue -valo-ohjainta sovelluksessa
 * 
 */

public class Bridge {
    
    private String ip;
    private String name;
    private String appId;
    private String lat;
    private String lng;
    
    /**
     * Lyhyt konstruktori, joka mahdollistaa valo-ohjainolion perustamisen sovellukseen ilman, että autentikointiavaimen hakeminen fyysiseltä laitteelta on onnistunut
     * @param ip    ohjaimen ip-osoite sisäverkossa
     * @param name  ohjaimen nimi sovelluksessa 
     */
    
    public Bridge(String ip, String name) {
        
        this.ip = ip;
        this.name = name;
        
    }
    
    /**
     * Pitkä konstruktori, jolla myös onnistuneesti saatu autentikointiavain voidaan tallentaa olion tietoihin
     * @param ip    ohjaimen ip-osoite sisäverkossa
     * @param name  ohjaimen nimi sovelluksessa
     * @param appId fyysiseltä laitteelta saatu autentikointiavain
     */
    
    public Bridge(String ip, String name, String appId) {
        
        this.ip = ip;
        this.name = name;
        this.appId = appId;
        
    }
    
    public void setIp(String ip) {
        
        this.ip = ip;
        
    }
    
    public void setName(String name) {
        
        this.name = name;
        
    }
    
    public void setAppId(String appId) {
        
        this.appId = appId;
        
    }
    
    public void setLat(String lat) {
        
        this.lat = lat;
        
    }
    
    public void setLng(String lng) {
        
        this.lng = lng;
        
    }
    
    public String getIp() {
        
        return this.ip;
        
    }
    
    public String getName() {
        
        return this.name;
        
    }
    
    public String getAppId() {
        
        return this.appId;
        
    }
    
    public String getLat() {
        
        return this.lat;
        
    }
    
    public String getLng() {
        
        return this.lng;
        
    }
    
    @Override
    public String toString() {
        
        String msgBody = "Bridge " + this.name + ", IP Address " + this.ip + ", authentication ID for this application ";
        
        if (this.appId == null || this.appId.equals("")) {
            
            return msgBody + "not configured.";
            
        }
        
        return msgBody + this.appId;
        
    }
}
