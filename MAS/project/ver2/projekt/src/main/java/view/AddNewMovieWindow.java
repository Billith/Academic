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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;
import model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AddNewMovieWindow extends Application {

    private ObservableList genresList = FXCollections.observableArrayList();
    public static JMetro.Style theme = JMetro.Style.LIGHT;

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);

        TextField movieTitle = new PersistentPromptTextField("", "tytuł filmu");
        TextField movieDirector = new PersistentPromptTextField("", "reżyser");
        TextField productionCountry = new PersistentPromptTextField("","kraj produkcji");
        Label productionYearL = new Label("Rok produkcji: ");
        TextArea movieDescription = new PersistentPromptTextArea("", "opis");
        Label durationL = new Label("Czas trwania filmu: ");
        Label minimalAgeL = new Label("Minimalny wiek widza: ");
        TextArea genres = new PersistentPromptTextArea("", "gatunki filmowe");
        genres.setPrefHeight(50);

        Spinner<Integer> prodYear = new Spinner<>(1950, 2050, LocalDate.now().getYear());
        prodYear.setEditable(true);
        Spinner<Integer> duration = new Spinner<>(0, 300, 0);
        duration.setEditable(true);
        Spinner<Integer> minimalAge = new Spinner<>(1, 25, 12);
        minimalAge.setEditable(true);

        VBox genresBox = new VBox();
        HBox addAndRemoveBox = new HBox();
        ListView<String> genresLV = new ListView<>();
        Button genresAdd = new Button("Dodaj kategorie");
        Button genresRemove = new Button("Usuń kategorie");
        genresLV.setPrefHeight(100);
        genresLV.setItems(genresList);
        genresAdd.setMaxWidth(Double.MAX_VALUE);
        genresAdd.setMaxHeight(5);
        genresAdd.setOnAction(event -> {
            TextInputDialog newGenre = new TextInputDialog();
            new JMetro(theme).applyTheme(newGenre.getDialogPane());
            newGenre.setTitle("Dodaj nową kategorię");
            newGenre.setHeaderText("Dodaj nową kategorię");
            newGenre.setContentText("Podaj nową kategorię:");
            Optional<String> result = newGenre.showAndWait();
            if(result.isPresent() && !result.get().trim().equals("")) {
                genresList.add(result.get());
            }
        });
        genresRemove.setMaxWidth(Double.MAX_VALUE);
        genresRemove.setMaxHeight(5);
        genresRemove.setOnAction(event -> {
            String selectedGenre = genresLV.getSelectionModel().getSelectedItem();
            genresList.remove(selectedGenre);
        });
        addAndRemoveBox.setHgrow(genresAdd, Priority.ALWAYS);
        addAndRemoveBox.setHgrow(genresRemove, Priority.ALWAYS);
        addAndRemoveBox.setSpacing(3);
        addAndRemoveBox.getChildren().addAll(genresAdd, genresRemove);
        genresBox.getChildren().addAll(genresLV, addAndRemoveBox);

        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> createMovie(movieTitle, movieDirector, productionCountry, prodYear, movieDescription, duration, minimalAge, genresList, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());

        movieDescription.setMaxWidth(450);

        grid.add(movieTitle, 0, 0, 2, 1);
        grid.add(movieDirector, 0, 1, 2 ,1);
        grid.add(productionCountry, 0, 2, 2, 1);
        grid.add(productionYearL, 0, 3);
        grid.add(prodYear, 1, 3);
        grid.add(movieDescription, 0, 6, 2 ,1);
        grid.add(durationL, 0, 4);
        grid.add(duration, 1, 4);
        grid.add(minimalAgeL, 0, 5);
        grid.add(minimalAge, 1, 5);
        grid.add(genresBox, 0 ,7, 2 ,1);
        grid.add(buttons, 0, 8, 2 ,1);

        Platform.runLater(() -> genresBox.requestFocus());

        Scene scene = new Scene(grid);
        new JMetro(theme).applyTheme(scene);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodaj nowy film");
        primaryStage.setMaxHeight(625);
        primaryStage.setMaxWidth(525);
        primaryStage.show();
    }

    private void createMovie(TextField movieTitle, TextField movieDirector, TextField productionCountry,
                             Spinner<Integer> prodYear, TextArea movieDescription, Spinner<Integer> duration,
                             Spinner<Integer> minimalAge, List<String> genres, Stage primaryStage) {

        try {
            MovieValidator.validateInput(movieTitle, movieDirector, productionCountry, movieDescription, genres);
        } catch (ValidateDataException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            new JMetro(theme).applyTheme(alert.getDialogPane());
            alert.showAndWait();
            return;
        }

        Movie movie = new Movie(movieTitle.getText(), movieDirector.getText(), productionCountry.getText(),
                prodYear.getValue(), movieDescription.getText(), duration.getValue(),
                minimalAge.getValue(), genres);

        promptForReservationCreation(movie, primaryStage);

    }

    private void promptForReservationCreation(Movie movie, Stage primaryStage) {
        ButtonType yes = new ButtonType("Tak");
        ButtonType no = new ButtonType("Nie");
        String msg = "Film został pomyślnie dodany do sysemu.\nCzy chcesz zarezerwować salę na projekcje tego filmu?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, yes, no);
        alert.setHeaderText("Potwierdzenie");
        alert.setTitle("Potwierdzenie");
        new JMetro(theme).applyTheme(alert.getDialogPane());
        alert.showAndWait()
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
        new JMetro(theme).applyTheme(scene);
        primaryStage.setTitle("Dodaj rezerwacje sali");
        primaryStage.setScene(scene);
    }

    public static void startUI() {
        launch();
    }
}
