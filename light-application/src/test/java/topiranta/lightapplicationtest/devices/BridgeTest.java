package topiranta.lightapplicationtest.devices;


import topiranta.lightapplication.devices.Bridge;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class BridgeTest {
    
    Bridge bridgeNoAppId;
    Bridge bridgeAllVariables;
    
    public BridgeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        bridgeNoAppId = new Bridge("3.3.3.3", "testBridge");
        bridgeAllVariables = new Bridge("4.4.4.4", "testBridge2", "testID");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void twoArgumentConstructorSetsIp() {
        
        assertEquals("3.3.3.3", bridgeNoAppId.getIp());
        
    }
    
    @Test
    public void twoArgumentConstructorSetsName() {
        
        assertEquals("testBridge", bridgeNoAppId.getName());
        
    }
    
    @Test
    public void threeArgumentConstructorSetsThreeValues() {
        
        assertTrue(bridgeAllVariables.getName() != null && bridgeAllVariables.getIp() != null && bridgeAllVariables.getAppId() != null);
    }
    
    @Test
    public void noAppIdConfiguredIsReportedInToString() {
        
        assertEquals("Bridge testBridge, IP Address 3.3.3.3, authentication ID for this application not configured.", bridgeNoAppId.toString());
        
    }

    
}
