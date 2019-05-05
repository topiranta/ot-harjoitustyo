
package topiranta.lightapplication.logics;

import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.Connections;
import java.util.*;
import java.time.*;

/**
 * Varsinainen sovellus, joka huolehtii käyttäjän haluamien toimintojen suorittamisesta
 * Sovellusoliolla on oliomuuttujana valo-ohjain, jota se käyttää, lista kaikista saatavilla olevista valoista,
 * lista käyttäjän määrittämistä päivitettävistä valoista ja värilaskin. Lisäksi se pitää kirjaa siitä, milloin valoja on
 * viimeksi automaattisesti päivitetty
 * 
 */

public class Application {
    
    private Bridge bridge;
    private ArrayList<Lamp> allLamps;
    private ArrayList<Lamp> userListOfLampsToUpdate = new ArrayList<>();
    private LightCalculator lightCalculator;
    private int[] previouslyUpdatedValues;
    
    /**
     * Valo-ohjaimen luomiseen tarkoitettu metodi
     * @param ip
     * @param name
     * @return 
     */
    
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
    
    /**
     * Metodi koordinaattien asettamiseksi valo-ohjainoliolle. Koordinaatteja tarvitaan, jotta ajat auringon huippukorkeudelle ja auringolaskulle
     * saadaan rajapinnasta
     * @param lat leveyskoordinaatti
     * @param lng pituuskoordinaatti
     * @return palauttaa tekstikäyttöliittymälle tiedon koordinaattien asettamisen onnistumisesta tai epäonnistumisesta
     */
    
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
    
    /**
     * Metodi fyysisen valo-ohjaimen tarjoamien lamppujen hakemiseksi ja tuomiseksi ajonaikaiseen muistiin
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
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
    
    /**
     * Metodi kaikkien tiedossa olevien lamppujen sammuttamiseksi
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
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
    
    /**
     * Metodi lampun nimien listaamiseksi
     * @return lista tiedossa olevien lamppujen nimistä fyysisellä valo-ohjaimella
     */
    
    public ArrayList<String> getAllLampNames() {
        
        ArrayList<String> names = new ArrayList<>();
        
        for (Lamp lamp : this.getAllLamps()) {
            
            names.add(lamp.getName());
            
        }
        
        return names;
        
    }
    
    /**
     * Metodi, jolla valo-ohjaimen nykyiset asetukset tallennetaan pysyväistallennukseen
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
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
    
    /**
     * Metodi, jolla luetaan valo-ohjaimen asetukset pysyväistallennuksesta sovelluksen käyttöön
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
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
    
    /**
     * Lisää kaikki tiedossa olevat lamput automaattisesti päivitettävien lamppujen listaan
     * @return 
     */
    
    public String addAllLampsToBeUpdatedAutomatically() {
        
        this.previouslyUpdatedValues = null;
        
        this.userListOfLampsToUpdate.clear();
        
        for (Lamp lamp : allLamps) {
            
            this.userListOfLampsToUpdate.add(lamp);
            
        }
        
        return "All lamps added to the list of automatically updated lamps";
        
    }
    
    /**
     * Metodi poistaa yksittäisen lampun automaattisesti päivitettävien lamppujen listasta
     * @param lampName poistettavan lampun nimi
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
    public String removeLampFromBeingUpdatedAutomatically(String lampName) {
        
        int index = -1;
        
        for (Lamp lamp : userListOfLampsToUpdate) {
            
            if (lamp.getName().equals(lampName)) {
                
                index = userListOfLampsToUpdate.indexOf(lamp);
                
            }
            
        }
        
        if (index != -1) {
            
            userListOfLampsToUpdate.remove(index);
            
            return lampName + " successfully removed from automatically updated lamps";
            
        }
        
        return "No lamp removed: lamp name not found";
        
    }
    
    /**
     * Tyjentää automaattisesti päivitettävien lamppujen listan
     * @return 
     */
    
    public String removeAllLampsFromBeingUpdatedAutomatically() {
        
        this.userListOfLampsToUpdate.clear();
        
        return  "All lamps removed from being updated automatically";
        
    }
    
    /**
     * Metodi hakee värilaskimelta uudet sävy- ja kirkkausarvot, jotka päivitetään ehdot täyttäviin lamppuihin.
     * DeviceOperations-luokan metodi lampsToBeUpdated palauttaa tämän metodin käyttöön listan niistä käyttäjän haluamista lampuista,
     * joiden asetuksia toiset sovellukset eivät ole muuttaneet sitten edellisen päivityksen. 
     * Tämän jälkeen metodi päivittää näihin lamppuihin lasketut arvot.
     * @return tieto tekstikäyttöliittymälle toiminnon onnistumisesta tai epäonnistumisesta
     */
    
    public String updateLamps() {
        
        ArrayList<Lamp> lampsToUpdate = new ArrayList<>();
        
        if (this.previouslyUpdatedValues != null) {
            
            try {
            
                lampsToUpdate = DeviceOperations.lampsToBeUpdated(previouslyUpdatedValues, userListOfLampsToUpdate, bridge);
            
            } catch (Exception e) {
                
                return "Error: " + e.getMessage();
                
            }
            
        } else {
            
            lampsToUpdate = this.userListOfLampsToUpdate;
            
        }
        
        if (lampsToUpdate == null || lampsToUpdate.size() == 0) {
            
            return "No lamps to be updated";
        }
        
        int[] valuesToUpdate = new int[2];
        
        try {
            
            valuesToUpdate = this.lightCalculator.getLightValues();
            
        
        } catch (Exception e) {
            
            return "Error while calculating color and brightness values: " + e.getMessage();
        }
        
        for (Lamp lamp : lampsToUpdate) {
            
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
