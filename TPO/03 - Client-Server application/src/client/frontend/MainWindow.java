package zad1.client.frontend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import zad1.client.backend.Client;

public class MainWindow extends Application {

    public static TextField outputTextField;

    private GridPane grid = new GridPane();
    private Label wordLabel = new Label("Word: ");
    private Label languageCodeLabel = new Label("Language code: ");
    private Label portLabel = new Label("Port: ");
    private TextField wordTextField = new TextField();
    private TextField languageCodeTextField = new TextField();
    private TextField portTextField = new TextField();
    private HBox buttons = new HBox();
    private Button translateButton = new Button("Translate");
    private Button quitButton = new Button("Quit");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        addLabelsToGrid();
        addTextBoxesToGrid();
        addButtonsToGrid();
        setUpGrid();
        setUpButtonsActions();
        setUpPrimaryStage(primaryStage);
    }

    public static void startUI() {
        launch();
    }

    private void setUpGrid() {
        grid.setPadding(new Insets(10,10,5,10));
        grid.setVgap(5);
    }

    private void addLabelsToGrid() {
        grid.add(wordLabel, 0, 0);
        grid.add(languageCodeLabel, 0, 1);
        grid.add(portLabel, 0, 2);
    }

    private void addTextBoxesToGrid() {
        grid.add(wordTextField, 1, 0);
        grid.add(languageCodeTextField, 1, 1);
        grid.add(portTextField, 1, 2);
        outputTextField = new TextField();
        outputTextField.setEditable(false);
        grid.add(outputTextField, 0, 3, 2, 1);
    }

    private void addButtonsToGrid() {
        buttons.setSpacing(5);
        buttons.getChildren().addAll(translateButton, quitButton);
        grid.add(buttons, 0, 4, 2, 1);
    }

    private void setUpPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("TPO 03");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpButtonsActions() {
        translateButton.setOnAction(event -> {
            if (isInputIncorrect()) {
                outputTextField.setText("Wrong or empty input value!");
            } else {
                Client.sendTranslationRequest(
                        wordTextField.getText(),
                        languageCodeTextField.getText(),
                        Integer.parseInt(portTextField.getText())
                );
            }
        });
        quitButton.setOnAction(event -> System.exit(0));

    }

    private boolean isInputIncorrect() {
        return wordTextField.getText().trim().isEmpty() || languageCodeTextField.getText().trim().isEmpty()
                || portTextField.getText().trim().isEmpty() || !isValidPortNumber(portTextField.getText().trim());
    }

    private boolean isValidPortNumber(String port) {
        double portNumber;
        try {
            portNumber = Double.parseDouble(port);
        } catch (NumberFormatException | NullPointerException n) {
            return false;
        }
        return portNumber > 0 && portNumber < 65536;
    }

}
