
package topiranta.lightapplication;


import topiranta.lightapplication.logics.Application;
import topiranta.lightapplication.ui.TextUi;
import topiranta.lightapplication.devtests.*;
import java.util.*;


public class Main {
    
    public static void main(String[] args) {
        
        Application application = new Application();
        
        TextUi textui = new TextUi(application, new Scanner(System.in));
        
        textui.start();
        


        
    }
    
}
