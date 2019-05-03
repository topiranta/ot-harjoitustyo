
package topiranta.lightapplication.logics;

import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.Connections;
import java.util.*;

public class Application {
    
    private Bridge bridge;
    private ArrayList<Lamp> allLamps;
    private ArrayList<Lamp> lampsToUpdate = new ArrayList<>();
    private LightCalculator lightCalculator;
    
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
    
    public String setLocation(String lat, String lng) throws Exception {
        
        if (this.bridge == null) {
            
            return "Bridge must be configured before setting the location";
            
        }
        
        this.bridge.setLat(lat);
        this.bridge.setLng(lng);
        
        this.lightCalculator = new LightCalculator(this.bridge);
        
        return "Location set successfully";
        
    }
    
    public String getAllLampsFromBridge() {
        
        if (this.bridge != null && this.bridge.getAppId() != null) {
            
            if (!this.bridge.getIp().equals("0.0.0.0")) {
            
                try {

                    this.allLamps = DeviceOperations.getAllLamps(this.bridge);

                } catch (Exception e) {

                    return "Error: " + e;

                }
            
            } else {
                
                this.allLamps = DefaultAndTestValues.getLampTestSet(this.bridge);
                
            }
            
            return "All lamps fetched";
            
        }
        
        return "Bridge not configured properly";
        
    }
    
    public String turnOffAllLamps() {
        
        if (this.allLamps != null && this.allLamps.size() > 0) {
            
            if (!this.bridge.getIp().equals("0.0.0.0")) {
            
                for (Lamp lamp : this.allLamps) {

                    try {

                        lamp.turnOff();

                    } catch (Exception e) {

                        return "Error: " + e;

                    }

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
        
        return this.allLamps;
        
    }
    
    public ArrayList<String> getAllLampNames() {
        
        ArrayList<String> names = new ArrayList<>();
        
        for (Lamp lamp : this.getAllLamps()) {
            
            names.add(lamp.getName());
            
        }
        
        return names;
        

        
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
    
    public void addAllLampsToBeUpdatedAutomatically() {
        
        for (Lamp lamp : allLamps) {
            
            this.lampsToUpdate.add(lamp);
            
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
