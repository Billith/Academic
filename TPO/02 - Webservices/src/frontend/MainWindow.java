package zad1.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import zad1.Service;

import java.util.Locale;


public class MainWindow extends Application {

    protected static Service userService;
    private static double rateOfCountryCurrency;
    private static double NBPRateOfPolishCurrency;

    private GridPane grid = new GridPane();
    private WebView webView;
    private Label rateOfCountryCurrencyLabel;
    private Label NBPRateOfPolishCurrencyLabel;
    private Label countryLabel;
    private Label cityLabel;
    private Label currencyLabel;


    @SuppressWarnings("Duplicates")
    @Override
    public void start(Stage stage) {
        stage.setTitle("TPO02 - s15599");
        stage.setResizable(false);

        setUpResultsLabelsBox();
        setUpLocaleLabelsBox();
        setUpButtonsBox();
        setUpBrowserScene();

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }


    static public void startUI(Service service , double rate1, double rate2) {
        Locale.setDefault(Locale.ENGLISH);
        userService = service;
        rateOfCountryCurrency = rate1;
        NBPRateOfPolishCurrency = rate2;
        launch();
    }

    private void setUpResultsLabelsBox() {
        rateOfCountryCurrencyLabel   = new Label(String.format(String.format("Rate of country currency to user's currency:\t%s", rateOfCountryCurrency)));
        NBPRateOfPolishCurrencyLabel = new Label(String.format(String.format("Rate of PLN to country currency:\t\t\t%s", NBPRateOfPolishCurrency)));
        HBox weatherBox = setUpWeatherBox();
        VBox resultsLabel = new VBox();
        resultsLabel.getChildren().addAll(weatherBox, rateOfCountryCurrencyLabel, NBPRateOfPolishCurrencyLabel);
        resultsLabel.setPadding(new Insets(10,10,10,10));
        resultsLabel.setSpacing(10);
        grid.add(resultsLabel, 0,0,1,1);
    }

    private HBox setUpWeatherBox() {
        Label weatherLabel = new Label(String.format("Weather: "));
        Button weatherButton = new Button(">Click<");
        weatherButton.setMaxHeight(15);
        weatherButton.setMinHeight(15);
        weatherButton.setFont(new Font("Verdana", 8));
        setWeatherButtonAction(weatherButton);
        HBox weatherBox = new HBox();
        weatherBox.getChildren().addAll(weatherLabel, weatherButton);
        return weatherBox;
    }

    private void setUpLocaleLabelsBox() {
        countryLabel  = new Label(String.format("Current country:\t%s", userService.getCountry()));
        cityLabel     = new Label(String.format("Current city:\t\t%s", userService.getCity()));
        currencyLabel = new Label(String.format("Current currency:\t%s", userService.getUsersProvidedCurrency()));

        VBox settingsLabels = new VBox();
        settingsLabels.getChildren().addAll(countryLabel, cityLabel, currencyLabel);
        settingsLabels.setPadding(new Insets(10,10,10,50));
        settingsLabels.setSpacing(10);
        grid.add(settingsLabels, 1,0,1,1);
    }

    private void setUpButtonsBox() {
        Button cityButton = new Button("Change city");
        setCityButtonAction(cityButton);

        Button countryButton = new Button("Change country");
        setCountryButtonAction(countryButton);

        Button currencyButton = new Button("Change currency");
        setCurrencyButtonAction(currencyButton);

        HBox buttons = new HBox();
        buttons.setPadding(new Insets(5, 10, 10, 10));
        buttons.setSpacing(10);
        buttons.getChildren().addAll(cityButton, countryButton, currencyButton);
        grid.add(buttons, 0,1,2,1);
    }

    private void setUpBrowserScene() {
        webView = new WebView();
        webView.setMinSize(1200, 800);
        WebEngine webEngine = webView.getEngine();
        webEngine.load(String.format("https://en.wikipedia.org/wiki/%s", userService.getCity()));
        VBox browser = new VBox(webView);
        grid.add(browser, 0,2,2,1);
    }

    private void setCityButtonAction(Button cityButton) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new city: ");
        cityButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCity = td.getEditor().getText();
            cityLabel.setText(String.format("Current city:\t\t%s", newCity));
            webView.getEngine().load(String.format("https://en.wikipedia.org/wiki/%s", td.getEditor().getText()));
            userService.setCity(newCity);
        });
    }

    private void setCountryButtonAction(Button countryButton) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new country: ");
        countryButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCountry = td.getEditor().getText();
            userService.setCountry(newCountry);
            countryLabel.setText(String.format("Current country:\t%s", newCountry));
            rateOfCountryCurrencyLabel.setText(String.valueOf(String.format("Rate of country currency to current currency: \t%s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
            NBPRateOfPolishCurrencyLabel.setText(String.valueOf(String.format("Rate of PLN to country currency: \t\t\t\t%s", userService.getNBPRate())));
        });
    }

    private void setCurrencyButtonAction(Button currencyButton) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new currency: ");
        currencyButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCurrency = td.getEditor().getText();
            userService.setUsersProvidedCurrency(newCurrency);
            currencyLabel.setText(String.format("Current currency:\t%s", newCurrency));
            rateOfCountryCurrencyLabel.setText(String.valueOf(String.format("Rate of country currency to current currency: \t%s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
        });
    }

    private void setWeatherButtonAction(Button weatherButton) {
        weatherButton.setOnAction(actionEvent -> new WeatherWindow().showAndWait());
    }

}
