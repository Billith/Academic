package model;

import model.oplusplus.ObjectPlus;
import model.oplusplus.ObjectPlusPlus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The class represents movie in the system
 */
public class Movie extends ObjectPlusPlus {

    private String title;
    private String director;
    private String productionCountry;
    private int productionYear;
    private String description;
    private int duration;
    private int minimalAge;
    private List<String> genre;

    /**
     * The constructor
     * @param title
     * @param director
     * @param productionCountry
     * @param productionYear
     * @param description Description of the movie. Can contain at most 200 words.
     * @param duration Duration of the movie in minutes.
     * @param minimalAge
     * @param genre
     */
    public Movie(String title, String director, String productionCountry, int productionYear, String description,
                 int duration, int minimalAge, List<String> genre) {
        this.title = title;
        this.director = director;
        this.productionCountry = productionCountry;
        this.productionYear = productionYear;
        this.description = description;
        this.duration = duration;
        this.minimalAge = minimalAge;
        // https://stackoverflow.com/questions/36430727/whats-the-best-way-to-trim-all-elements-in-a-liststring
        this.genre = genre.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Function returns movie title
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    public static List<Movie> getMovieExtent() {
        List<ObjectPlus> allMovies = ObjectPlus.getClassExtent(Movie.class);
        return allMovies.stream()
                .map(e -> (Movie) e)
                .collect(Collectors.toList());
    }

    public String toString() {
        return title;
    }

}
