package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Movie;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewMovieWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);

        TextField movieTitle = new TextField("tytuł filmu");
        TextField movieDirector = new TextField("reżyser");
        TextField productionCountry = new TextField("kraj produkcji");
        TextField productionYear = new TextField("rok produkcji");
        TextArea movieDescription = new TextArea("opis");
        TextField duration = new TextField("czas trwania filmu");
        TextField minimalAge = new TextField("minimalny wiek widza");
        TextArea genres = new TextArea("gatunki filmowe");
        genres.setPrefHeight(50);

        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> validateInputAndCreateReservation(movieTitle, movieDirector, productionCountry, productionYear, movieDescription, duration, minimalAge, genres, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());

        grid.add(movieTitle, 0, 0);
        grid.add(movieDirector, 0, 1);
        grid.add(productionCountry, 0, 2);
        grid.add(productionYear, 0, 3);
        grid.add(movieDescription, 0, 4);
        grid.add(duration, 0, 5);
        grid.add(minimalAge, 0, 6);
        grid.add(genres, 0, 7);
        grid.add(buttons, 0, 8);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodaj nowy film");
        primaryStage.setHeight(500);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    private void validateInputAndCreateReservation(TextField movieTitle, TextField movieDirector, TextField productionCountry,
                                                   TextField productionYear, TextArea movieDescription, TextField duration,
                                                   TextField minimalAge, TextArea genres, Stage primaryStage) {

        // https://stackoverflow.com/questions/8923398/regex-doesnt-work-in-string-matches
        Pattern p = Pattern.compile("^\\d+$");
        Matcher mYear = p.matcher(productionYear.getText());
        Matcher mDuration = p.matcher(duration.getText());
        Matcher mAge = p.matcher(minimalAge.getText());

        if(!mDuration.matches() || !mAge.matches() || !mYear.matches()) {
            new Alert(Alert.AlertType.ERROR, "Niepoprawne dane. Czas trwania filmu, minimalny wiek widza i rok produkcji muszą być liczbą.").showAndWait();
            return;
        }

        Movie movie = new Movie(movieTitle.getText(), movieDirector.getText(), productionCountry.getText(),
                Integer.parseInt(productionYear.getText()), movieDescription.getText(), Integer.parseInt(duration.getText()),
                Integer.parseInt(minimalAge.getText()), Arrays.asList(genres.getText().split(",")));

        ButtonType yes = new ButtonType("Tak");
        ButtonType no = new ButtonType("Nie");

        new Alert(
                Alert.AlertType.CONFIRMATION,
                "Film został pomyślnie dodany do sysemu.\nCzy chcesz zarezerwować salę na projekcje tego filmu?",
                yes, no
        )
        .showAndWait()
        .ifPresent(response -> {
            if (response == yes) {
                displayNewReservationWindow(movie, primaryStage);
            }
            else if (response == no) {
                primaryStage.close();
            }
        });

    }

    private void displayNewReservationWindow(Movie movie, Stage primaryStage) {
        Scene scene = new Scene(new AddNewReservationWindow(movie));
        primaryStage.setScene(scene);
    }
}
