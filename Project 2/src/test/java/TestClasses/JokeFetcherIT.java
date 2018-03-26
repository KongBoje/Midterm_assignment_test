package TestClasses;

import interfaces.IDateFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import testex.DateFormatter;
import testex.JokeException;
import testex.JokeFetcher;
import testex.Jokes;

@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherIT {

    @Mock
    private IDateFormatter dateFormatter;

    @Test
    public void getAvailableTypesTest() {
        JokeFetcher jf = new JokeFetcher(dateFormatter);
        List<String> list = jf.getAvailableTypes();
        List<String> expected = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");

        assertThat(list.size(), is(4));
        assertThat(list, is(expected));
    }

    @Test
    public void isStringValidTest() {
        JokeFetcher jf = new JokeFetcher(dateFormatter);

        boolean actualLegal = jf.isStringValid("eduprog,chucknorris,moma,tambal");
        boolean actualNotLegal = jf.isStringValid("edupfewrog,chucvfcknorris,moddma,,tambal");

        boolean expectedLegal = true;
        boolean expectedNotLegal = false;

        assertThat(actualLegal, is(expectedLegal));
        assertThat(actualNotLegal, is(expectedNotLegal));
    }

    @Test(expected = JokeException.class)
    public void exceptionTest() throws JokeException {
        JokeFetcher jf = new JokeFetcher(dateFormatter);
        jf.getJokes("yourmama", "Europe/Copenhagen");
    }

    @Test
    public void getJokesTest() throws JokeException {
        JokeFetcher jf = new JokeFetcher(dateFormatter);
        String tz = "Europe/Copenhagen";
        String jokes = "tambal,tambal,chucknorris";

        assertEquals(jf.getJokes(jokes, tz).getJokes().size(), 3);
    }

    //State based mockito test.
    @Test
    public void getJokesTestMock() throws JokeException {
        Date date = new Date();
        String expected = "27 mar. 2018 01:40 AM";
        
        JokeFetcher jf = new JokeFetcher(dateFormatter);

        //given
        given(dateFormatter.getFormattedDate(date, "Europe/Copenhagen"))
                .willReturn(expected);

//        //when
        jf.getJokes("tambal,tambal,chucknorris", "Europe/Copenhagen");
        
//        //verifying that getFormattedDate is only used once
//        verify(dateFormatter).getFormattedDate(date, "Europe/Copenhagen");
    }

}
