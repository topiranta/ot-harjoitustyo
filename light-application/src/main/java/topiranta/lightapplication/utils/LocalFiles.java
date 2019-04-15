package topiranta.lightapplication.utils;

import java.io.*;
import java.util.*;

public class LocalFiles {
    
    public static void writeNewFile(String filename, String text) throws Exception {
        
        PrintWriter writer = new PrintWriter(filename);
        writer.println(text);
        writer.close();
        
    }
    
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
