package data;

import java.util.ArrayList;
import java.util.List;

public class SavedMovies {
    private static final SavedMovies instance = new SavedMovies();
    private List<Result> movieList = null;

    // Constructor
    private SavedMovies() {
        movieList = new ArrayList<Result>();
    }

    public static SavedMovies getInstance() {return instance;}


    public List<Result> getList() {
        return movieList;
    }

    public void add(Result movie) {
        System.out.println("Added movie");
        movieList.add(movie);
    }

    public void addAll(List<Result> movies) {
        movieList.addAll(movies);
        System.out.println("Added movies");

    }

    public void printAllTitles() {
        System.out.println("Trying to print movies");
        for (Result movie : movieList) {
            System.out.println(movie.getTitle());
        }
    }

}
