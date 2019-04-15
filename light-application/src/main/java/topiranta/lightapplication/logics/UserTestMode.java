package topiranta.lightapplication.logics;

import java.util.*;
import topiranta.lightapplication.devices.*;


public class UserTestMode {
    
    public static ArrayList<Lamp> getLampTestSet(Bridge bridge) {
        
        ArrayList<Lamp> lampList = new ArrayList<>();
        
        for (int i = 1; i < 5; i++) {
            
            lampList.add(new Lamp(i, bridge));
            
        }
        
        return lampList;
        
    }
    
}
