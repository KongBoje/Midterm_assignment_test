package jokeClasses;

import interfaces.IFetcherFactory;
import interfaces.IJokeFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetcherFactory implements IFetcherFactory{

    private final List<String> availableTypes 
            = Arrays.asList("eduprog","chucknorris","moma","tambal");
    
    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    //Polymorphism instead of Conditionals
    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        String[] tokens = jokesToFetch.split(",");
        List<IJokeFetcher> getJokes = new ArrayList<>();
        for(String token : tokens){
            switch(token){
                case "eduprog":
                    getJokes.add(new EduJoke());
                    break;
                case "chucknorris":
                    getJokes.add(new ChuckNorris());
                    break;
                case "moma":
                    getJokes.add(new Moma());
                    break;
                case "tambal":
                    getJokes.add(new Tambal());
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
        }
        return getJokes;
    }
    
}
