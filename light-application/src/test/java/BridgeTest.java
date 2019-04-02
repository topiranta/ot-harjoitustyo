
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tr.ot.light.application.*;;


public class BridgeTest {
    
    Bridge bridge;
    
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
        
        bridge = new Bridge("3.3.3.3", "testBridge");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void twoArgumentConstructorSetsIp() {
        
        assertEquals("3.3.3.3", bridge.getIp());
        
    }
    
    @Test
    public void twoArgumentConstructorSetsName() {
        
        assertEquals("testBridge", bridge.getName());
        
    }
    
}
