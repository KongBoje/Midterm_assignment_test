package jokeClasses;

import static com.jayway.restassured.RestAssured.given;
import interfaces.IJokeFetcher;
import testex.Joke;

public class ChuckNorris implements IJokeFetcher {

    @Override
    public Joke getJoke() {
        try {
            String joke = given().get("http://api.icndb.com/jokes/random").path("value.joke");
            return new Joke(joke, "http://api.icndb.com/");
        } catch (Exception e) {
            return null;
        }
    }
}
