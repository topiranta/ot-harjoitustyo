package topiranta.lightapplication.ui;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import topiranta.lightapplication.logics.Application;
import topiranta.lightapplication.logics.DefaultAndTestValues;

public class TextUi {
    
    private Application application;
    private Scanner reader;
    
    public TextUi(Application application, Scanner reader) {
        
        this.application = application;
        this.reader = reader;
        
    }
    
    public void start() {
        
        System.out.println("Welcome!");
        System.out.println("Current status: " + this.application.toString());
        System.out.println("Choose command: ");
        this.printCommands();
        
        while(true) {
            
            System.out.print("> ");
            String input = reader.nextLine();
            
            if (input.equals("0")) {
                
                System.out.println("See you again!");
                break;
                
            } else if (input.equals("1")) {
                
                this.configureBridge();
                this.setCoordinates();
                    
            } else if (input.equals("2")) {
                
                System.out.print("Insert new name: ");
                
                String nameInput = reader.nextLine();
                
                this.application.setBridgeName(nameInput);
                
                
            } else if (input.equals("3")) {
                
                System.out.print("Insert new IP address: ");
                
                String ipInput = reader.nextLine();
                
                this.application.setBridgeIp(ipInput);
                
                
            } else if (input.equals("4")) {
                
                System.out.println(this.application.toString());
                
                
            } else if (input.equals("5")) {
                
                this.printCommands();
                
                
            } else if (input.equals("6")) {
                
                System.out.println(this.application.getAllLampsFromBridge());
                
            } else if (input.equals("7")) {
                
                System.out.println(this.application.turnOffAllLamps());
                
            } else if (input.equals("8")) {
                
                System.out.println(this.application.saveBridgeConfiguration());
                
            } else if (input.equals("9")) {
                
                System.out.println(this.application.loadBridgeConfiguration());
                
            } else if (input.equals("10")) {
                
                for (String string : this.application.getAllLampNames()) {
                    
                    System.out.println(string);
                    
                }
            
            } else if (input.equals("11")) {
                
                System.out.println(this.application.addAllLampsToBeUpdatedAutomatically());
            
            } else if (input.equals("12")) {
                
                System.out.println(this.application.updateLamps());
            
            } else if (input.equals("99")) {
                
                System.out.println("Oh you! You found a secret testing command. No testing was, however, conducted.");
                
            } else {
                
                System.out.println("Unknown command! Insert 5 to print all commands.");
                
            }
            
            
            
        }
        
    }
    
    public void configureBridge() {
        
        System.out.println("What is the IP address of your bridge? (insert 0.0.0.0 to use application in user test mode)");
                System.out.print("> ");
                String ip = reader.nextLine();
                
                System.out.println("What do you want to call your bridge?");
                System.out.print("> ");
                String name = reader.nextLine();
                
                System.out.println("Please press you bridge connection button before proceeding. Insert \"c\" when you are ready to continue or \"x\" to cancel.");
                
                while(true) {
                    
                    System.out.print("> ");
                    
                    String command = reader.nextLine();
                    
                    if (command.equals("x")) {
                        
                        break;
                        
                    } else if (command.equals("c")) {
                        
                        System.out.println(this.application.setBridge(ip, name));
                        
                    }
                    
                    System.out.println("");
                    
                    break;
                }
        
    }
    
    public void setCoordinates() {
        
        String lat;
        String lng;
        System.out.print("Insert bridge latitude coordinate, for example 60.1718665 (inserting empty latitude sets Helsinki coordinates for the bridge): ");
        String input = reader.nextLine();
        
        if (input.equals("")) {
            
            lat = DefaultAndTestValues.getHelsinkiLat();
            lng = DefaultAndTestValues.getHelsinkiLng();
            
        } else {
            
            lat = input;
            System.out.print("Insert bridge longitude coordinate: ");
            lng = reader.nextLine();
            
        }
        
        System.out.println(this.application.setLocation(lat, lng));
    }
    
    public void configureAutomaticUpdate() {
        
        LocalDateTime stop;
        
        System.out.println("Insert time for automatic update to stop, for example 18:30 (inserting no time will run the program indefinitely and thus the program must be stopped by the OS)");
        System.out.print("> ");
        
        String time = reader.nextLine();
        
        if (!time.equals("")) {
            
            stop = LocalDateTime.parse(LocalDate.now().toString() + "T" + time + "00");
            
        } else {
            
            stop = LocalDateTime.parse("2050-01-01T00:00:00");
        }
        
    }
    
    public void startAutomaticUpdate(LocalDateTime stop) {
        
        LocalDateTime lastUpdate = LocalDateTime.now();
        
        while (LocalDateTime.now().compareTo(stop) < 0) {
            
            if (LocalDateTime.now().until(lastUpdate, ChronoUnit.SECONDS) < -60) {
                
                System.out.println(LocalDateTime.now().toString() + ": " + this.application.updateLamps());
                
            }
            
        }
        
    }
    
    public void printCommands() {
        
        System.out.println("");
        System.out.println("1 - configure new bridge");
        System.out.println("2 - change bridge name");
        System.out.println("3 - change bridge IP address");
        System.out.println("4 - print current status");
        System.out.println("5 - print commands");
        System.out.println("6 - fetch all lights configured to the bridge");
        System.out.println("7 - turn off all lights");
        System.out.println("8 - save current bridge configuration");
        System.out.println("9 - load existing bridge configuration");
        System.out.println("0 - exit");
        System.out.println("");
    }
    
}
