package topiranta.lightapplication.devices;

import java.net.*;
import topiranta.lightapplication.utils.Connections;

/**
 * Luokka kuvaa yhtä Philips Hue -älyvaloa sekä sen toimintoja sovelluksen näkökulmasta.
 * 
 */


public class Lamp {
    
    private int id;
    private Bridge bridge;
    
    /**
     * Konstruktori
     * @param id    Lampun id-numero valo-ohjaimessa
     * @param bridge    Bridge-olio, johon lamppu liittyy
     */
    
    public Lamp(int id, Bridge bridge) {
        
        this.id = id;
        this.bridge = bridge;
        
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
     * Metodi lampun sammuttamiseksi
     * @throws Exception    metodi osaa heittää virheen, mikäli viestin lähettäminen valo-ohjaimeen epäonnistuu tai valo-ohjain ei osaa käsitellä viestiä oikein
     */
    
    public void turnOff() throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"on\": false}");
        
    }
    
}
