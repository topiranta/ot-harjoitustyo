
package tr.ot.light.application;


import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        
        Application application = new Application();
        
        TextUi textui = new TextUi(application, new Scanner(System.in));
        
        textui.start();
        

        
    }
    
}
