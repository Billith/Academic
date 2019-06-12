package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class MovieValidator {

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
