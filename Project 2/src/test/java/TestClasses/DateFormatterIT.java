/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import testex.DateFormatter;
import testex.JokeException;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Micha
 */
public class DateFormatterIT {

    private static final Logger logger = Logger.getLogger(DateFormatterIT.class.getName());

    ;
    
    public DateFormatterIT() {
    }

    @Before
    public void setUp() {

    }

    @Test(expected = JokeException.class)
    public void getFormattedDateTest() throws JokeException {
        DateFormatter.getFormattedDate("ImNotLegal");
    }

    
    @Test
    public void testGetFormattedDate() throws JokeException {
        String testStrings[] = {"Europe/Copenhagen", "Asia/Kolkata"};
        
        for (String tmp : testStrings)
            DateFormatter.getFormattedDate(tmp);
    }
    
    @Test
    public void testGetFormattedDate2() throws JokeException {
        String testStrings[] = {"Europe/Copenhagen", "NST"};
        ArrayList<String> ar = new ArrayList();
        for (String tmp : testStrings) {
            ar.add(DateFormatter.getFormattedDate(tmp));
        }
        for (String string : ar) {
            assertNotNull(string);
            Assert.assertThat(string.length() > 1, is(true));
        }
    }
}
