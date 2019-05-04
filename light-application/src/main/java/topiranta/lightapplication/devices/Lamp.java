package topiranta.lightapplication.devices;

import java.net.*;
import topiranta.lightapplication.utils.Connections;
import org.json.simple.*;

/**
 * Luokka kuvaa yhtä Philips Hue -älyvaloa sekä sen toimintoja sovelluksen näkökulmasta.
 * 
 */


public class Lamp {
    
    private int id;
    private Bridge bridge;
    private String name;
    
    /**
     * Konstruktori
     * @param id    Lampun id-numero valo-ohjaimessa
     * @param bridge    Bridge-olio, johon lamppu liittyy
     */
    
    public Lamp(int id, Bridge bridge) {
        
        this.id = id;
        this.bridge = bridge;
        
    }
    
    public Lamp(int id, Bridge bridge, String name) {
        
        this.id = id;
        this.bridge = bridge;
        this.name = name;
    }
    
    public int getId() {
        
        return this.id;
        
    }
    
    /**
     * Metodi, joka palauttaa lamppukohtaisen URL:n PUT-operaatiolle valo-ohjaimen REST-rajapinnassa
     * @return URL-olio
     * @throws Exception    virhe, mikäli URL on väärin muodostettu 
     */
    
    public URL getPutUrl() throws Exception {
        
        URL putUrl = new URL("http://" + this.bridge.getIp() + "/api/" + this.bridge.getAppId() + "/lights/" + id + "/state");
        return putUrl;
        
    }
    
    public URL getGetUrl() throws Exception {
        
        URL getUrl = new URL("http://" + this.bridge.getIp() + "/api/" + this.bridge.getAppId() + "/lights/" + id);
        return getUrl;
        
    }
    
    /**
     * Metodi lampun sammuttamiseksi
     * @throws Exception    metodi osaa heittää virheen, mikäli viestin lähettäminen valo-ohjaimeen epäonnistuu 
     * tai valo-ohjain ei osaa käsitellä viestiä oikein
     */
    
    public void turnOff() throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"on\": false}");
        
    }
    
    public void turnOn() throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"on\": true}");
        
    }
    
    public String getName() {
        
        return this.name;
        
    }
    
    public boolean lampOn() throws Exception {
        
        JSONObject response = (JSONObject) Connections.getJSON(this.getGetUrl());
        JSONObject state = (JSONObject) response.get("state");
        
        if (state.get("on").toString().equals("true")) {
            
            return true;
            
        }
        
        return false;
        
    }
    
    public void setValues(int ct, int bri) throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"ct\":" + ct + ", \"bri\":" + bri + ", \"on\":true}");
        
    }
    
}
