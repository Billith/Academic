package zad1.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import zad1.Service;

import java.util.Locale;


public class MainWindow extends Application {

    private static Service userService;
    private static String weatherAtStart;
    private static double rateAtStart;
    private static double NBPRateAtStart;
    private WebView webView = setUpBrowser();

    private static Label rateForLabel;
    private static Label rateNbpLabel;
    private static Label countryLabel;
    private static Label cityLabel;
    private static Label currencyLabel;


    @Override
    public void start(Stage stage) {
        stage.setTitle("TPO02 - s15599");
        stage.setResizable(false);

        GridPane grid = new GridPane();

        VBox browser = new VBox(webView);
        grid.add(browser, 0,2,3,1);

        Label weatherLabel = new Label(String.format("Pogoda: "));
        Button weatherButton = new Button(">Click<");
        weatherButton.setOnAction(actionEvent -> showWeatherInfo());
        HBox weatherBox = new HBox();
        weatherBox.getChildren().addAll(weatherLabel, weatherButton);

        rateForLabel = new Label(String.format(String.format("Kurs1: %s", rateAtStart)));
        rateNbpLabel = new Label(String.format(String.format("Kurs2: %s", NBPRateAtStart)));
        VBox resultsLabel = new VBox();
        resultsLabel.getChildren().addAll(weatherBox, rateForLabel, rateNbpLabel);
        grid.add(resultsLabel, 0,0,1,1);

        countryLabel = new Label(String.format("Current country:\t%s", userService.getCountry()));
        cityLabel = new Label(String.format("Current city:\t\t%s", userService.getCity()));
        currencyLabel = new Label(String.format("Current currency:\t%s", userService.getUsersProvidedCurrency()));

        VBox settingsLabels = new VBox();
        settingsLabels.getChildren().addAll(countryLabel, cityLabel, currencyLabel);
        grid.add(settingsLabels, 1,0,1,1);

        Button cityButton = new Button("Change city");
        setCityButtonAction(cityButton, cityLabel);

        Button countryButton = new Button("Change country");
        setCountryButtonAction(countryButton, countryLabel);

        Button currencyButton = new Button("Change currency");
        setCurrencyButtonAction(currencyButton, currencyLabel);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(cityButton, countryButton, currencyButton);
        grid.add(buttons, 0,1,3,1);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    static public void startUI(Service service, String weatherJsonString, double rate1, double rate2) {
        Locale.setDefault(Locale.ENGLISH);
        userService = service;
        rateAtStart = rate1;
        NBPRateAtStart = rate2;
        launch();
    }

    private WebView setUpBrowser() {
        WebView webView = new WebView();
        webView.setMinSize(1200, 800);
        WebEngine webEngine = webView.getEngine();
        webEngine.load(String.format("https://en.wikipedia.org/wiki/%s", userService.getCity()));
        return webView;
    }

    private void showWeatherInfo() {
        Stage weatherWindow = new Stage();
        weatherWindow.setTitle("Weather info");
        HBox weatherInfo = new HBox();
        weatherWindow.setScene(new Scene(weatherInfo, 300,400));
        weatherWindow.showAndWait();
    }

    private void setCityButtonAction(Button cityButton, Label cityLabel) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new city: ");
        cityButton.setOnAction(actionEvent -> {
            td.showAndWait();
            cityLabel.setText(String.format("Current city:\t\t%s", td.getEditor().getText()));
            webView.getEngine().load(String.format("https://en.wikipedia.org/wiki/%s", td.getEditor().getText()));
        });
    }

    private void setCountryButtonAction(Button countryButton, Label countryLabel) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new country: ");
        countryButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCountry = td.getEditor().getText();
            userService.setCountry(newCountry);
            countryLabel.setText(String.format("Current country:\t%s", newCountry));
            System.out.println("setCountryButtonAction: " + userService.getRateFor(userService.getUsersProvidedCurrency()));
            rateForLabel.setText(String.valueOf(String.format("Kurs1: %s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
            System.out.println("setCountryButtonAction: " + userService.getNBPRate());
            rateNbpLabel.setText(String.valueOf(String.format("Kurs2: %s", userService.getNBPRate())));
        });
    }

    private void setCurrencyButtonAction(Button currencyButton, Label currencyLabel) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new currency: ");
        currencyButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCurrency = td.getEditor().getText();
            userService.setUsersProvidedCurrency(newCurrency);
            currencyLabel.setText(String.format("Current currency:\t%s", newCurrency));
            rateForLabel.setText(String.valueOf(String.format("Kurs1: %s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
        });
    }

}
