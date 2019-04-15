
package topiranta.lightapplication.logics;

import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.Connections;
import java.util.*;
import topiranta.lightapplication.utils.DeviceOperations;

public class Application {
    
    private Bridge bridge;
    private ArrayList<Lamp> lamps;
    
    public String setBridge(String ip, String name) {
        
        this.bridge = new Bridge(ip, name);
        
        
        try {
            
            String appId = DeviceOperations.authenticateApplication(ip);
            this.bridge.setAppId(appId);
            
        
        } catch (Exception e) {
            
            return e.getMessage();
            
        }
        
        return "Authentication successful";
        
    }
    
    public String getAllLamps() {
        
        if (this.bridge != null && this.bridge.getAppId() != null) {
            
            try {
                
                this.lamps = DeviceOperations.getAllLamps(this.bridge);
            
            } catch (Exception e) {
                
                return "Error: " + e;
                
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
    
    
    @Override
    public String toString() {
        
        if (this.bridge == null) {
            
            return "No bridge configured";
            
        }
        
        return this.bridge.toString();
    }
    
}
