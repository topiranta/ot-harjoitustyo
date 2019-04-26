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
    
    @Test
    public void postJSONReturnsCorrectMessage() {
        
        JSONObject receivedJSON = new JSONObject();
        
        try {
            
            receivedJSON = (JSONObject) Connections.postJSON((new URL("https://jsonplaceholder.typicode.com/posts")), "");
            
        } catch (Exception e) {
            

        }
        
        
        assertEquals((long) 101, receivedJSON.get("id"));
        
    }
    
    @Test
    public void postJSONThrowsExceptionWithFaultyURL() {
        
        String message = "";
        
        try {
            
            Connections.postJSON((new URL("https://topiranta.com/posts")), "{\"device-type\":\"test\"}");
            
        } catch (Exception e) {
            
            message = e.getMessage();
            
        }
        
        assertEquals("404", message);
        
    }
    
    @Test
    public void putJSONSucceeds() {
        
        int result = 1;
        
        try {
            
            Connections.putJSON((new URL("https://jsonplaceholder.typicode.com/posts/1")), "{\"on\":\"false\"}");
            
            result = 2;
            
        } catch (Exception e) {
            
            result = 0;
            
        }
        
        assertEquals(2, result);
    }
    
    
    

    
}
