package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;

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
        this.genre = genre;
    }
}
