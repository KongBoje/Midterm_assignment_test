/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import java.text.SimpleDateFormat;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import testex.DateFormatter;
import testex.JokeException;
import java.util.TimeZone;

/**
 *
 * @author Micha
 */
public class DateFormatterIT {
    
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
    public void testGetFormattedDAte() throws JokeException {
        String testStrings[] = {"Europe/Copenhagen", "Asia/Kolkata"};
        
        for (String tmp : testStrings)
            DateFormatter.getFormattedDate(tmp);
    }
}
