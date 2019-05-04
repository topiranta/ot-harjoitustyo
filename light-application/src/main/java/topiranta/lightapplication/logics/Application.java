
package topiranta.lightapplication.logics;

import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.Connections;
import java.util.*;

public class Application {
    
    private Bridge bridge;
    private ArrayList<Lamp> allLamps;
    private ArrayList<Lamp> lampsToUpdate = new ArrayList<>();
    private LightCalculator lightCalculator;
    private int[] previouslyUpdatedValues;
    
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
    
    public String setLocation(String lat, String lng) {
        
        if (this.bridge == null) {
            
            return "Bridge must be configured before setting the location";
            
        }
        
        this.bridge.setLat(lat);
        this.bridge.setLng(lng);
        
        try {
        
            this.lightCalculator = new LightCalculator(this.bridge);
        
        } catch (Exception e) {
            
            return "Error: " + e.getMessage();
            
        }
        
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
            
        
        } catch (Exception e) {
            
            return "Failed to load configurations: " + e;
            
        }
        
        this.setLocation(bridge.getLat(), bridge.getLng());
        
        return "Successfully loaded bridge configurations";
        
    }
    
    public String addAllLampsToBeUpdatedAutomatically() {
        
        this.previouslyUpdatedValues = null;
        
        for (Lamp lamp : allLamps) {
            
            this.lampsToUpdate.add(lamp);
            
        }
        
        return "All lamps added to the list of automatically updated lamps";
        
    }
    
    public String removeLampFromBeingUpdatedAutomatically(String lampName) {
        
        int index = -1;
        
        for (Lamp lamp : lampsToUpdate) {
            
            if (lamp.getName().equals(lampName)) {
                
                index = lampsToUpdate.indexOf(lamp);
                
            }
            
        }
        
        if (index != -1) {
            
            lampsToUpdate.remove(index);
            
            return lampName + " successfully removed from automatically updated lamps";
            
        }
        
        return "No lamp removed: lamp name not found";
        
    }
    
    public String removeAllLampsFromBeingUpdatedAutomatically() {
        
        this.lampsToUpdate.clear();
        
        return  "All lamps removed from being updated automatically";
        
    }
    
    public String updateLamps() {
        
        if (this.previouslyUpdatedValues != null) {
            
            try {
            
                this.lampsToUpdate = DeviceOperations.lampsStillToBeUpdated(previouslyUpdatedValues, lampsToUpdate, bridge);
            
            } catch (Exception e) {
                
                return "Error: " + e.getMessage();
                
            }
        }
        
        if (this.lampsToUpdate == null || this.lampsToUpdate.size() == 0) {
            
            return "No lamps to be updated";
        }
        
        int[] valuesToUpdate = new int[2];
        
        try {
            
            valuesToUpdate = this.lightCalculator.getLightValues();
            
        
        } catch (Exception e) {
            
            return "Error while calculating color and brightness values: " + e.getMessage();
        }
        
        for (Lamp lamp : this.lampsToUpdate) {
            
            try {
                
                lamp.setValues(valuesToUpdate[0], valuesToUpdate[1]);
                
            } catch (Exception e) {
                
                return "Error while updating lamp values: " + e.getMessage();
                
            }
            
        }
        
        this.previouslyUpdatedValues = valuesToUpdate;
        
        return "Lamps successfully updated";
    }
    
    
    
    @Override
    public String toString() {
        
        if (this.bridge == null) {
            
            return "No bridge configured";
            
        }
        
        return this.bridge.toString();
    }
    
}
