
package topiranta.lightapplication.logics;

import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.Connections;
import java.util.*;

public class Application {
    
    private Bridge bridge;
    private ArrayList<Lamp> lamps;
    
    public String setBridge(String ip, String name) {
        
        this.bridge = new Bridge(ip, name);
        
        if (!ip.equals("0.0.0.0")) {
        
            try {
            
                String appId = DeviceOperations.authenticateApplication(ip);
                this.bridge.setAppId(appId);
            
        
            } catch (Exception e) {
            
                return e.getMessage();
            
            }
        
        } else {
            
            this.bridge.setAppId("testMode");
            
        }
        
        return "Authentication successful";
        
    }
    
    public String getAllLampsFromBridge() {
        
        if (this.bridge != null && this.bridge.getAppId() != null) {
            
            if (!this.bridge.getIp().equals("0.0.0.0")) {
            
                try {

                    this.lamps = DeviceOperations.getAllLamps(this.bridge);

                } catch (Exception e) {

                    return "Error: " + e;

                }
            
            } else {
                
                this.lamps = UserTestMode.getLampTestSet(this.bridge);
                
            }
            
            return "All lamps fetched";
            
        }
        
        return "Bridge not configured properly";
        
    }
    
    public String turnOffAllLamps() {
        
        if (this.lamps != null && this.lamps.size() > 0) {
            
            for (Lamp lamp : this.lamps) {
                
                try {
                    
                    lamp.turnOff();
                    
                } catch (Exception e) {
                    
                    return "Error: " + e;
                    
                }
                
            }
            
            return "All lamps turned off";
            
        }
        
        return "No lamps configured";
        
    }
    
    public void setBridgeName(String name) {
        
        if (this.bridge != null) {
            
            this.bridge.setName(name);
            
        }
           
        
    }
    
    public void setBridgeIp(String ip) {
        
        if (this.bridge != null) {
            
            this.bridge.setIp(ip);
            
        }
    }
    
    public ArrayList<Lamp> getAllLamps() {
        
        return this.lamps;
        
    }
    
    public String saveBridgeConfiguration() {
        
        if (this.bridge != null) {
            
            try {
            
                ConfigurationOperations.saveBridgeConfig(this.bridge);
                
                return "Bridge configuration saved";
            
            } catch (Exception e) {
                
                return "Error while trying to save configuration: " + e;
                
            }
        }
        
        return "No bridge configured. Please configure a bridge before saving.";
        
    }
    
    public String loadBridgeConfiguration() {
        
        if (this.bridge == null) {
            
            this.bridge = new Bridge(" ", " ");
            
        }
        
        try {
        
            ConfigurationOperations.loadBridgeConfig(this.bridge);
            
            return "Successfully loaded bridge configurations";
        
        } catch (Exception e) {
            
            return "Failed to load configurations: " + e;
            
        }
        
    }
    
    
    @Override
    public String toString() {
        
        if (this.bridge == null) {
            
            return "No bridge configured";
            
        }
        
        return this.bridge.toString();
    }
    
}
