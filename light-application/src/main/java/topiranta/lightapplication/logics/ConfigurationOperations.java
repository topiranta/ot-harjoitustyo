package topiranta.lightapplication.logics;

import topiranta.lightapplication.utils.LocalFiles;
import topiranta.lightapplication.devices.*;

/**
 * Staattiset metodit, joilla kirjoitetaan ja luetaan konfigurointitietoa pysyväistallennuksesta
 * 
 */

public class ConfigurationOperations {
    
    /**
     * Metodi tallentaa valo-ohjaimen konfigurointitiedot pysyväistallennukseen
     * @param bridge    valo-ohjain, jonka tiedot tallennetaan
     * @throws Exception    metodi heittää virheen, mikäli tallennustiedoston luomisessa tai kirjoittamisessa tulee ongelmia
     */
    
    public static void saveBridgeConfig(Bridge bridge) throws Exception {
        
        try {
        
            LocalFiles.writeNewFile("config.txt", bridge.getIp() + ", " + bridge.getName() + ", " + bridge.getAppId());
        
        } catch (Exception e) {
            
            throw new Exception("Saving configuration failed: " + e);
            
        }
    }
    
    /**
     * Metodi lataa valo-ohjaimen konfigurointitiedot pysyväistallennuksesta
     * @param bridge    valo-ohjain, jolle tiedot sovelluksessa annetaan
     * @throws Exception    metodi heittää virheen, mikäli tiedoston lukeminen epäonnistuu
     */
    
    public static void loadBridgeConfig(Bridge bridge) throws Exception {
        
        try {
            
            String[] config = LocalFiles.readFile("config.txt").split(", ");
            bridge.setIp(config[0]);
            bridge.setName(config[1]);
            bridge.setAppId(config[2]);
            
        } catch (Exception e) {
            
            throw new Exception("Loading configuration failed: " + e);
            
        }
        
    }
    
}
