package topiranta.lightapplication.logics;

import topiranta.lightapplication.utils.LocalFiles;
import topiranta.lightapplication.devices.*;

public class ConfigurationOperations {
    
    public static void saveBridgeConfig(Bridge bridge) throws Exception {
        
        try {
        
            LocalFiles.writeNewFile("config.txt", bridge.getIp() + ", " + bridge.getName() + ", " + bridge.getAppId());
        
        } catch (Exception e) {
            
            throw new Exception ("Saving configuration failed: " + e);
            
        }
    }
    
    public static void loadBridgeConfig(Bridge bridge) throws Exception {
        
        try {
            
            String[] config = LocalFiles.readFile("config.txt").split(", ");
            bridge.setIp(config[0]);
            bridge.setName(config[1]);
            bridge.setAppId(config[2]);
            
        } catch (Exception e) {
            
            throw new Exception ("Loading configuration failed: " + e);
            
        }
        
    }
    
}
