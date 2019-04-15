package topiranta.lightapplicationtest.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import topiranta.lightapplication.utils.*;
import org.json.simple.*;
import java.net.*;

public class ConnectionsTest {
    
    public ConnectionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getJSONReturnsJSONObjectWithCorrectContents() {
        
        JSONObject receivedJSON = new JSONObject();
        
        try {
        
            receivedJSON = Connections.getJSON(new URL("https://jsonplaceholder.typicode.com/todos/1"));
        
        } catch (Exception e) {
            
            
        }
        
        assertEquals("delectus aut autem", receivedJSON.get("title"));
        
    }

    
}
