package TestClasses;

import interfaces.IDateFormatter;
import interfaces.IFetcherFactory;
import interfaces.IJokeFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import jokeClasses.ChuckNorris;
import jokeClasses.EduJoke;
import jokeClasses.FetcherFactory;
import jokeClasses.Moma;
import jokeClasses.Tambal;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import testex.Joke;
import testex.JokeException;
import testex.JokeFetcher;

@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherIT {

    private JokeFetcher jokeFetcher;

    @Mock
    IDateFormatter dateFormatter;

    @Mock
    IFetcherFactory factory;
    
    @Mock
    List<IJokeFetcher> results;

    @Mock
    EduJoke eduJoke;

    @Mock
    ChuckNorris chuckNorris;

    @Mock
    Moma moma;

    @Mock
    Tambal tambal;

    @Before
    public void before() {
        List<IJokeFetcher> fetchers = Arrays.asList(eduJoke, chuckNorris, moma, tambal);
        when(factory.getJokeFetchers("eduprog,chucknorris,moma,tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");
        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(dateFormatter, factory);
    }

    @Test
    public void getAvailableTypesTest() {
        List<String> list = factory.getAvailableTypes();
        List<String> expected = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");

        assertThat(list.size(), is(4));
        assertThat(list, is(expected));
    }

    @Test
    public void isStringValidTest() {
        boolean actualLegal = jokeFetcher.isStringValid("eduprog,chucknorris,moma,tambal");
        boolean actualNotLegal = jokeFetcher.isStringValid("edupfewrog,chucvfcknorris,moddma,,tambal");

        boolean expectedLegal = true;
        boolean expectedNotLegal = false;

        assertThat(actualLegal, is(expectedLegal));
        assertThat(actualNotLegal, is(expectedNotLegal));
    }

    @Test(expected = JokeException.class)
    public void exceptionTest() throws JokeException {
        jokeFetcher.getJokes("yourmama", "Europe/Copenhagen");
    }

    @Test
    public void getJokesTest() throws JokeException {
        List<IJokeFetcher> result = factory.getJokeFetchers("eduprog,chucknorris,moma,tambal");

        //getting instance of first token.
        assertThat(result.get(0), instanceOf(EduJoke.class));
        //getting instance of second token.
        assertThat(result.get(1), instanceOf(ChuckNorris.class));
        //getting instance of third token.
        assertThat(result.get(2), instanceOf(Moma.class));
        //getting instance of last token.
        assertThat(result.get(3), instanceOf(Tambal.class));

        //Getting size of the list
        assertThat(result.size(), is(4));
    }

    //State based mockito test.
    //Not sure about the verify on this one.
    @Test
    public void getJokesTestStateBasedMock() throws JokeException {
        Date date = new Date();
        String expected = "27 mar. 2018 01:40 AM";

        //given
        when(dateFormatter.getFormattedDate(date, "Europe/Copenhagen"))
                .thenReturn(expected);
        assertThat(dateFormatter.getFormattedDate(date, "Europe/Copenhagen"), 
                equalTo(expected));
        
          //verifying that getFormattedDate is only used once
//        verify(dateFormatter).getFormattedDate(date, "Europe/Copenhagen"); //virker ikke, ved ikke hvorfor
    }
    
    @Test
    public void eduJokeMockTest() {
        String educJoke = "Teacher asked George: 'How can you prove the earth is round?' "
                + "George replied: 'I can’t. Besides, I never said it was.'";
        when(eduJoke.getJoke()).thenReturn(new Joke(educJoke, "http://jokes-plaul.rhcloud.com/api/joke"));
        assertThat(eduJoke.getJoke().getReference(), equalTo("http://jokes-plaul.rhcloud.com/api/joke"));
        results.add(eduJoke);
        verify(results).add(eduJoke);
        
        when(results.size()).thenReturn(1);
        assertThat(results.size(), equalTo(1));
    }

    @Test
    public void chuckJokeMockTest() {
        String chuckJoke = "Chuck Norris can make a class that is both abstract and final.";
        when(chuckNorris.getJoke()).thenReturn(new Joke(chuckJoke, "http://api.icndb.com/jokes/random"));
        assertThat(chuckNorris.getJoke().getReference(), equalTo("http://api.icndb.com/jokes/random"));
        results.add(chuckNorris);
        verify(results).add(chuckNorris);
        
        when(results.size()).thenReturn(1);
        assertThat(results.size(), equalTo(1));
    }

    @Test
    public void momaJokeMockTest() {
        String momaJoke = "Yo mamas so fat everytime she turns around its her birthday";
        when(moma.getJoke()).thenReturn(new Joke(momaJoke, "http://api.yomomma.info/"));
        assertThat(moma.getJoke().getReference(), equalTo("http://api.yomomma.info/"));
        results.add(moma);
        verify(results).add(moma);
        
        when(results.size()).thenReturn(1);
        assertThat(results.size(), equalTo(1));
    }

    @Test
    public void tambalJokeMockTest() {
        String tambalJoke = "If you reverse tambal you get labmat, and if you swap places "
                + "with lab and mat you get the programming platform matlab!";
        when(tambal.getJoke()).thenReturn(new Joke(tambalJoke, "http://tambal.azurewebsites.net/joke/random"));
        assertThat(tambal.getJoke().getReference(), equalTo("http://tambal.azurewebsites.net/joke/random"));
        results.add(tambal);
        verify(results).add(tambal);
        
        when(results.size()).thenReturn(1);
        assertThat(results.size(), equalTo(1));
    }
    
    @Test
    public void availableTypesMockTest() {
        when(factory.getAvailableTypes()).thenReturn(Arrays.asList("eduprog","chucknorris","moma","tambal"));
        assertThat(factory.getAvailableTypes().size(), equalTo(4));
    }
}
