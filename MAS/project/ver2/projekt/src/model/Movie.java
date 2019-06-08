package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;
import java.util.stream.Collectors;

public class Movie extends ObjectPlusPlus {

    private String title;
    private String director;
    private String productionCountry;
    private int productionYear;
    private String description;
    private int duration;
    private int minimalAge;
    private List<String> genre;

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
        this.genre = genre.stream().map(String::trim).collect(Collectors.toList());
    }

    public String toString() {
        return String.format("[ %s title=%s, director=%s, country=%s, year=%s, desc=%s, duration=%s, age=%s, genres=%s ]",
                this.getClass().getSimpleName(),
                title,
                director,
                productionCountry,
                productionYear,
                description,
                duration,
                minimalAge,
                genre
        );
    }

}
