package model;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class MovieProjection extends Event {
    String title;
    String director;
    List<MovieProjectionGenre> genres;
    MovieProjectionTranslationType translationType;
    String country;
    int duration;
    MovieProjectionAgeCategory ageCategory;
    LocalDate projectionDate;

    public MovieProjection(
            String description,
            ScreeningRoomType requiredScreeningRoomType,
            String title,
            String director,
            List<MovieProjectionGenre> genres,
            MovieProjectionTranslationType translationType,
            String country,
            int duration,
            LocalDate projectionDate
    ) {
        this.description = description;
        this.requiredScreeningRoomType = requiredScreeningRoomType;
        this.title = title;
        this.director = director;
        this.genres = genres;
        this.translationType = translationType;
        this.country = country;
        this.duration = duration;
        this.projectionDate = projectionDate;
    }

    public List<MovieProjectionGenre> getGenres() {
        return genres;
    }
}
