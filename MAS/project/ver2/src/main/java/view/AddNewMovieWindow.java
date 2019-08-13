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

/**
 * Class is responsible for spawning main window of GUI and setting up first displayed scene of adding new movie to the
 * system
 */
public class AddNewMovieWindow extends Application {

    public static JMetro.Style theme = JMetro.Style.LIGHT;

    private ObservableList genresList = FXCollections.observableArrayList();

    /**
     * Start function which is automatically called on window creation
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        TextField movieTitle = new PersistentPromptTextField("", "tytuł filmu");
        TextField movieDirector = new PersistentPromptTextField("", "reżyser");
        TextField productionCountry = new PersistentPromptTextField("","kraj produkcji");
        TextArea movieDescription = new PersistentPromptTextArea("", "opis");
        movieDescription.setMaxWidth(450);
        // https://stackoverflow.com/questions/36612545/javafx-textarea-limit
        movieDescription.setTextFormatter(
                new TextFormatter<String>(change -> change.getControlNewText().length() <= 200 ? change : null)
        );
        movieDescription.setWrapText(true);

        Label productionYear = new Label("Rok produkcji: ");
        Label duration = new Label("Czas trwania filmu: ");
        Label minimalAge = new Label("Minimalny wiek widza: ");

        //TextArea genres = new PersistentPromptTextArea("", "gatunki filmowe");
        //genres.setPrefHeight(50);

        Spinner<Integer> productionYearPicker = new Spinner<>(1950, 2050, LocalDate.now().getYear());
        productionYearPicker.setEditable(true);
        Spinner<Integer> durationPicker = new Spinner<>(0, 300, 0);
        durationPicker.setEditable(true);
        Spinner<Integer> minimalAgePicker = new Spinner<>(1, 25, 12);
        minimalAgePicker.setEditable(true);

        VBox genresBox = new VBox();
        HBox buttons = new HBox();

        setupGenresBox(genresBox);
        setupButtons(buttons, movieTitle, movieDirector, productionCountry, productionYearPicker, movieDescription,
                durationPicker, minimalAgePicker, primaryStage);
        setupGrid(grid, movieTitle, movieDirector, productionCountry, productionYear, productionYearPicker,
                movieDescription, duration, durationPicker, minimalAge, minimalAgePicker, genresBox, buttons);

        Scene scene = new Scene(grid);
        new JMetro(theme).applyTheme(scene);
        Platform.runLater(() -> genresBox.requestFocus());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dodaj nowy film");
        primaryStage.show();
    }

    /**
     * Function setup behaviour and layout of the buttons.
     * @param buttons
     * @param movieTitle
     * @param movieDirector
     * @param productionCountry
     * @param productionYearPicker
     * @param movieDescription
     * @param durationPicker
     * @param minimalAgePicker
     * @param primaryStage
     */
    private void setupButtons(HBox buttons, TextField movieTitle, TextField movieDirector, TextField productionCountry, Spinner<Integer> productionYearPicker, TextArea movieDescription, Spinner<Integer> durationPicker, Spinner<Integer> minimalAgePicker, Stage primaryStage) {
        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> createMovie(movieTitle, movieDirector, productionCountry, productionYearPicker,
                movieDescription, durationPicker, minimalAgePicker, genresList, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());
    }

    /**
     * Function setup behaviour and layout of the genres controls.
     * @param genresBox
     */
    private void setupGenresBox(VBox genresBox) {
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
    }

    /**
     * Function adds every node to the main grid.
     * @param grid
     * @param movieTitle
     * @param movieDirector
     * @param productionCountry
     * @param productionYear
     * @param productionYearPicker
     * @param movieDescription
     * @param duration
     * @param durationPicker
     * @param minimalAge
     * @param minimalAgePicker
     * @param genresBox
     * @param buttons
     */
    private void setupGrid(GridPane grid, TextField movieTitle, TextField movieDirector, TextField productionCountry,
                           Label productionYear, Spinner<Integer> productionYearPicker, TextArea movieDescription,
                           Label duration, Spinner<Integer> durationPicker, Label minimalAge,
                           Spinner<Integer> minimalAgePicker, VBox genresBox, HBox buttons) {
        grid.add(movieTitle, 0, 0, 2, 1);
        grid.add(movieDirector, 0, 1, 2 ,1);
        grid.add(productionCountry, 0, 2, 2, 1);
        grid.add(productionYear, 0, 3);
        grid.add(productionYearPicker, 1, 3);
        grid.add(movieDescription, 0, 6, 2 ,1);
        grid.add(duration, 0, 4);
        grid.add(durationPicker, 1, 4);
        grid.add(minimalAge, 0, 5);
        grid.add(minimalAgePicker, 1, 5);
        grid.add(genresBox, 0 ,7, 2 ,1);
        grid.add(buttons, 0, 8, 2 ,1);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
    }

    /**
     * Function creates new movie with data provided through GUI
     * @param movieTitle
     * @param movieDirector
     * @param productionCountry
     * @param prodYear
     * @param movieDescription
     * @param duration
     * @param minimalAge
     * @param genres
     * @param primaryStage
     */
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

    /**
     * Function display prompt for user after creation of new movie
     * @param movie
     * @param primaryStage
     */
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
                   displayNewReservationPane(movie, primaryStage);
               }
               else if (response == no) {
                   primaryStage.close();
               }
           });
    }

    /**
     * Function redirect window to the new scene of adding new reservation
     * @param movie
     * @param primaryStage
     */
    private void displayNewReservationPane(Movie movie, Stage primaryStage) {
        Scene scene = new Scene(new AddNewReservationPane(movie, primaryStage));
        new JMetro(theme).applyTheme(scene);
        primaryStage.setTitle("Dodaj rezerwacje sali");
        primaryStage.setScene(scene);
    }

    /**
     * Function launches the GUI
     */
    public static void startUI() {
        launch();
    }
}
