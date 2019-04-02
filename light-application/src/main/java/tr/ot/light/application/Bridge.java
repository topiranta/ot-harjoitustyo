
package tr.ot.light.application;

import java.util.*;

public class Bridge {
    
    private String ip;
    private String name;
    private String appId;
    private ArrayList<Lamp> allLamps = new ArrayList<>();
    private ArrayList<Lamp> lampsToUpdate = new ArrayList<>();
    
    public Bridge(String ip, String name) {
        
        this.ip = ip;
        this.name = name;
        
    }
    
    public Bridge(String ip, String name, String appId) {
        
        this.ip = ip;
        this.name = name;
        this.appId = appId;
        
    }
    
    public void setIp(String ip) {
        
        this.ip = ip;
        
    }
    
    public void setName(String name) {
        
        this.name = name;
        
    }
    
    public void setAppId(String appId) {
        
        this.appId = appId;;
        
    }
    
    public String getIp() {
        
        return this.ip;
        
    }
    
    public String getName() {
        
        return this.name;
        
    }
    
    public String getAppId() {
        
        return this.appId;
        
    }
    
    @Override
    public String toString() {
        
        String msgBody = "Bridge " + this.name + ", IP Address " + this.ip + ", authentication ID for this application ";
        
        if (this.appId == null || this.appId.equals("")) {
            
            return msgBody + "not configured.";
            
        }
        
        return msgBody + this.appId;
        
    }
}
