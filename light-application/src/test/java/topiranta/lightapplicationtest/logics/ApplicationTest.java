package topiranta.lightapplicationtest.logics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import topiranta.lightapplication.logics.Application;
import topiranta.lightapplication.devices.*;
import topiranta.lightapplication.utils.*;


public class ApplicationTest {
    
    Application application;
    
    public ApplicationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
       application = new Application();
       application.setBridge("0.0.0.0", "userTestBridge");
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void setBridgeRecognizesUserTestMode() {
        
        assertEquals("Bridge userTestBridge, IP Address 0.0.0.0, authentication ID for this application testMode", application.toString());
        
    }
    
    @Test
    public void faultyIpAddressCausesAnException() {
        
        assertEquals("Authentication error: java.net.UnknownHostException: notARealAddress", application.setBridge("notARealAddress", "errorTestBridge"));
        
    }
    
    @Test
    public void getAllLampsRecognizesUserTestMode() {
        
        application.getAllLampsFromBridge();
        assertEquals(4, application.getAllLamps().size());
        
    }
    
    @Test
    public void configurationsSavedCorrectly() {
        
        assertEquals("Bridge configuration saved", application.saveBridgeConfiguration());

    }
    
    @Test
    public void configurationsLoadedCorrectly() {
        
        try {
        
            LocalFiles.writeNewFile("config.txt", "loadTestIp, loadTestBridgeName, loadTestAppId");
        
        } catch (Exception e) {
            
            
        }
        
        application.loadBridgeConfiguration();
        
        assertEquals("Bridge loadTestBridgeName, IP Address loadTestIp, authentication ID for this application loadTestAppId", application.toString());
    }

    
}
