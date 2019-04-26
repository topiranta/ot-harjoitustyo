package topiranta.lightapplication.utils;

import java.io.*;
import java.util.*;

/**
 * Luokka sisältää metodit, jotka suorittavat tiedostojen kirjoittamisen ja lukemisen pysyväistallennukseen
 * 
 */

public class LocalFiles {
    
    /**
     * Tiedoston kirjoittaminen
     * @param filename kirjoitettavan tiedoston nimi. Metodi kirjoittaa aina uuden tiedoston.
     * @param text  kirjoitettava sisältö
     * @throws Exception    metodi heittää virheen, mikäli kirjoittaminen epäonnistuu
     */
    
    public static void writeNewFile(String filename, String text) throws Exception {
        
        PrintWriter writer = new PrintWriter(filename);
        writer.println(text);
        writer.close();
        
    }
    
    /**
     * Tiedoston lukeminen
     * @param filename  luettavan tiedoston nimi
     * @return  tiedostosta luettu sisältö
     * @throws Exception    metodi heittää virheen, mikäli lukeminen epäonnistuu
     */
    
    public static String readFile(String filename) throws Exception {
        
        StringBuilder sBuilder = new StringBuilder();
        
        try (Scanner reader = new Scanner(new File(filename))) {
            
            while (reader.hasNextLine()) {
                
                sBuilder.append(reader.nextLine());
                
            }
            
        } catch (Exception e) {
            
            throw new Exception("File could not be read: " + e);
            
        }
        
        return sBuilder.toString();
        
    }
    
}
