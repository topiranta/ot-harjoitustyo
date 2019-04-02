
package tr.ot.light.application;

public class Application {
    
    private Bridge bridge;
    
    public String setBridge(String ip, String name) {
        
        this.bridge = new Bridge(ip, name);
        
        
        try {
            
            String appId = Utils.authenticateApplication(ip);
            this.bridge.setAppId(appId);
            
        
        } catch (Exception e) {
            
            return e.getMessage();
            
        }
        
        return "Authentication successful";
        
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
