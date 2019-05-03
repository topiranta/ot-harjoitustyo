
package topiranta.lightapplication;


import topiranta.lightapplication.logics.Application;
import topiranta.lightapplication.ui.TextUi;
import java.util.*;






import topiranta.lightapplication.devtests.*;


public class Main {
    
    public static void main(String[] args) {
        
        Application application = new Application();
        
        TextUi textui = new TextUi(application, new Scanner(System.in));
        
        textui.start();
        
        //BasicTests.printCurrentValues();

        


        
    }
    
}
