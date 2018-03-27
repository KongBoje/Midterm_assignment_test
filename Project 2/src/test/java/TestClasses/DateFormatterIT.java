package TestClasses;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import testex.DateFormatter;
import testex.JokeException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;

public class DateFormatterIT {

    @Test(expected = JokeException.class)
    public void getFormattedDateTest() throws JokeException {
        Date date = new Date();
        DateFormatter df = new DateFormatter();
        df.getFormattedDate(date, "ImNotLegal");
    }
    
    @Test
    public void testGetFormattedDate() throws JokeException {
        String testStrings[] = {"Europe/Copenhagen", "Asia/Kolkata"};
        Date date = new Date();
        DateFormatter df = new DateFormatter();
        
        for (String tmp : testStrings)
            df.getFormattedDate(date, tmp);
    }
    
    @Test
    public void testGetFormattedDate2() throws JokeException {
        String testStrings[] = {"Europe/Copenhagen", "NST"};
        ArrayList<String> ar = new ArrayList();
        Date date = new Date();
        DateFormatter df = new DateFormatter();
        
        for (String tmp : testStrings) {
            ar.add(df.getFormattedDate(date, tmp));
        }
        for (String string : ar) {
            assertNotNull(string);
            Assert.assertThat(string.length() > 1, is(true));
        }
    }
}
