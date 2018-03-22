/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import testex.JokeException;
import testex.JokeFetcher;

/**
 *
 * @author Micha
 */
public class JokeFetcherIT {
    
    public JokeFetcherIT() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void getAvailableTypesTest() {
        JokeFetcher jf = new JokeFetcher();
        List<String> big = jf.getAvailableTypes();
        List<String> expected = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");
        
        assertThat(big.size(), is(4));
        assertThat(big, is(expected));
    }
    
    
    
    @Test
    public void testGetJokes() throws JokeException {
        JokeFetcher jf = new JokeFetcher();
        String tz = "Europe/Copenhagen";
        String jokes = "tambal,tambal,chucknorris";
        
        assertEquals(jf.getJokes(jokes, tz).getJokes().size(), 3);
    }
    
    
    @Test(expected = JokeException.class)
    public void testException() throws JokeException{
        JokeFetcher jf = new JokeFetcher();
        jf.getJokes("yourmama","Europe/Copenhagen");
    }
    
    
}
