package view;

import controller.MovieValidator;
import controller.ValidateDataException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Movie;

import java.util.List;
import java.util.Optional;

public class AddNewMovieWindow extends Application {

    private ObservableList genresList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);

        TextField movieTitle = new PersistentPromptTextField("", "tytuł filmu");
        TextField movieDirector = new PersistentPromptTextField("", "reżyser");
        TextField productionCountry = new PersistentPromptTextField("","kraj produkcji");
        TextField productionYear = new PersistentPromptTextField("", "rok produkcji");
        TextArea movieDescription = new PersistentPromptTextArea("", "opis");
        TextField duration = new PersistentPromptTextField("", "czas trwania filmu");
        TextField minimalAge = new PersistentPromptTextField("", "minimalny wiek widza");
        TextArea genres = new PersistentPromptTextArea("", "gatunki filmowe");
        genres.setPrefHeight(50);

        VBox genresBox = new VBox();
        HBox addAndRemoveBox = new HBox();
        ListView<String> genresLV = new ListView<>();
        Button genresAdd = new Button("+");
        Button genresRemove = new Button("-");
        genresLV.setPrefHeight(100);
        genresLV.setItems(genresList);
        genresAdd.setMaxWidth(Double.MAX_VALUE);
        genresAdd.setOnAction(event -> {
            TextInputDialog newGenre = new TextInputDialog();
            newGenre.setTitle("Dodaj nową kategorię");
            newGenre.setContentText("Podaj nową kategorię:");
            Optional<String> result = newGenre.showAndWait();
            if(result.isPresent() && !result.get().trim().equals("")) {
                genresList.add(result.get());
            }
        });
        genresRemove.setMaxWidth(Double.MAX_VALUE);
        genresRemove.setOnAction(event -> {
            String selectedGenre = genresLV.getSelectionModel().getSelectedItem();
            genresList.remove(selectedGenre);
        });
        addAndRemoveBox.setHgrow(genresAdd, Priority.ALWAYS);
        addAndRemoveBox.setHgrow(genresRemove, Priority.ALWAYS);
        addAndRemoveBox.getChildren().addAll(genresAdd, genresRemove);
        genresBox.getChildren().addAll(genresLV, addAndRemoveBox);

        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> createMovie(movieTitle, movieDirector, productionCountry, productionYear, movieDescription, duration, minimalAge, genresList, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());

        grid.add(movieTitle, 0, 0);
        grid.add(movieDirector, 0, 1);
        grid.add(productionCountry, 0, 2);
        grid.add(productionYear, 0, 3);
        grid.add(movieDescription, 0, 4);
        grid.add(duration, 0, 5);
        grid.add(minimalAge, 0, 6);
        grid.add(genresBox, 0 ,7);
        grid.add(buttons, 0, 8);

        Platform.runLater(() -> genresBox.requestFocus());

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodaj nowy film");
        primaryStage.setMaxHeight(550);
        primaryStage.setMaxWidth(525);
        primaryStage.show();
    }

    private void createMovie(TextField movieTitle, TextField movieDirector, TextField productionCountry,
                             TextField productionYear, TextArea movieDescription, TextField duration,
                             TextField minimalAge, List<String> genres, Stage primaryStage) {

        try {
            MovieValidator.validateInput(movieTitle, movieDirector, productionCountry, productionYear, movieDescription, duration, minimalAge, genres);
        } catch (ValidateDataException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
            return;
        }

        Movie movie = new Movie(movieTitle.getText(), movieDirector.getText(), productionCountry.getText(),
                Integer.parseInt(productionYear.getText()), movieDescription.getText(), Integer.parseInt(duration.getText()),
                Integer.parseInt(minimalAge.getText()), genres);

        promptForReservationCreation(movie, primaryStage);

    }

    private void promptForReservationCreation(Movie movie, Stage primaryStage) {
        ButtonType yes = new ButtonType("Tak");
        ButtonType no = new ButtonType("Nie");
        String msg = "Film został pomyślnie dodany do sysemu.\nCzy chcesz zarezerwować salę na projekcje tego filmu?";
        new Alert(Alert.AlertType.CONFIRMATION, msg, yes, no)
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
        Scene scene = new Scene(new AddNewReservationWindow(movie, primaryStage));
        primaryStage.setTitle("Dodaj rezerwacje sali");
        primaryStage.setScene(scene);
    }

    public static void startUI() {
        launch();
    }
}
