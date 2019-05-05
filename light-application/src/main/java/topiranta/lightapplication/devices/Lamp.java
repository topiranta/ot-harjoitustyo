package topiranta.lightapplication.devices;

import java.net.*;
import java.util.Objects;
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
    
    /**
     * Metodi palauttaa lamppukohtaisen URL:n GET-opreaatiolle valo-ohjaimen REST-rajapinnassa
     * @return URL-olio
     * @throws Exception    virhe heitetään, mikäli URL on väärin muodostettu 
     */
    
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
    
    
    public String getName() {
        
        return this.name;
        
    }
    
    /**
     * Metodi lähettää värisävy- ja kirkkausarvot lähetettäväksi fyysiselle lampulle
     * @param ct color temperature, värin lämmön sävy välillä 153 - 500
     * @param bri brightness, kirkkaus, arvo välillä 1 - 254
     * @throws Exception    
     */
    
    public void setValues(int ct, int bri) throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"ct\":" + ct + ", \"bri\":" + bri + ", \"on\":true}");
        
    }

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            
            return true;
            
        }
        
        if (obj == null) {
            
            return false;
            
        }
        if (getClass() != obj.getClass()) {
            
            return false;
            
        }
        
        final Lamp other = (Lamp) obj;
        
        if (this.id != other.id) {
            
            return false;
            
        }
        
        if (!Objects.equals(this.name, other.name)) {
            
            return false;
            
        }
        
        return true;
    }
    
    
    
}
