package topiranta.lightapplication.utils;

import java.io.*;

public class LocalFileOperations {
    
    public static void writeNewFile(String filename, String text) throws Exception {
        
        PrintWriter writer = new PrintWriter(filename);
        writer.println(text);
        writer.close();
        
    }
    
}
