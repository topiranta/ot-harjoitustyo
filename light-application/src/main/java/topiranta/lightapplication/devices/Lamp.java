package topiranta.lightapplication.devices;

import java.net.*;
import topiranta.lightapplication.utils.Connections;


public class Lamp {
    
    private int id;
    private Bridge bridge;
    
    public Lamp(int id, Bridge bridge) {
        
        this.id = id;
        this.bridge = bridge;
        
    }
    
    public URL getPutUrl() throws Exception {
        
        URL putUrl = new URL("http://" + this.bridge.getIp() + "/api/" + this.bridge.getAppId() + "/lights/" + id + "/state");
        return putUrl;
        
    }
    
    public void turnOff() throws Exception {
        
        Connections.putJSON(this.getPutUrl(), "{\"on\": false}");
        
    }
    
}
