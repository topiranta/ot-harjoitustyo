package topiranta.lightapplication.logics;

import java.util.*;
import topiranta.lightapplication.devices.*;


/**
 * Luokassa on metodeja, jotka mahdollistavat sovelluksen käyttämisen testimoodissa, jos fyysistä valo-ohjainta ei ole saatavilla
 *
 */

public class DefaultAndTestValues {
    
    /**
     * Metodi palauttaa listan ohjelmallisesti generoituja lamppuolioita
     * @param bridge valo-ohjainolio, jolta lamput "haetaan"
     * @return  lista, jossa generoidut lamppuoliot ovat
     */
    
    public static ArrayList<Lamp> getLampTestSet(Bridge bridge) {
        
        ArrayList<Lamp> lampList = new ArrayList<>();
        
        for (int i = 1; i < 5; i++) {
            
            lampList.add(new Lamp(i, bridge));
            
        }
        
        return lampList;
        
    }
    
    public static String getHelsinkiLat() {
        
        return "60.1706664";
        
    }
    
    public static String getHelsinkiLng() {
        
        return "24.9416871";
        
    }
    
}
