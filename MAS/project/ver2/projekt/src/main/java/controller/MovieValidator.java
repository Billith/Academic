package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

/**
 * Class responsible for validation of users input entered in window which creates new Movie object
 */
public class MovieValidator {

    /**
     * Validates input from the GUI, provided by the end user. Function doesn't validate values from Spinner controls
     * since they can only return Integer values.
     * @param movieTitle title of the movie
     * @param movieDirector director of the movie
     * @param productionCountry production country of the movie
     * @param movieDescription description of the movie
     * @param genres list of genres of the movie
     * @throws ValidateDataException thrown when users input is illegal
     */
    public static void validateInput(TextField movieTitle, TextField movieDirector, TextField productionCountry,
            TextArea movieDescription, List<String> genres) throws ValidateDataException {

        String title = movieTitle.getText().trim();
        if(title.equals("") || title.equals("tytuł filmu")) {
            throw new ValidateDataException("Nie wprowadzono tytułu filmu");
        }

        String director = movieDirector.getText().trim();
        if(director.equals("") || director.equals("reżyser")) {
            throw new ValidateDataException("Nie wprowadzono reżysera");
        }

        String country = productionCountry.getText().trim();
        if(country.equals("") || country.equals("kraj produkcji")) {
            throw new ValidateDataException("Nie wprowadzono kraju produkcji");
        }

        String description = movieDescription.getText().trim();
        if(description.equals("") || description.equals("opis")) {
            throw new ValidateDataException("Nie wprowadzono opisu");
        }

        if(genres.isEmpty()) {
            throw new ValidateDataException("Nie wprowadzono kategorii filmowych");
        }

    }
}
