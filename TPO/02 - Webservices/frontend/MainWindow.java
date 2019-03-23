package zad1.frontend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainWindow extends Application {

    private static Service userService;
    private static double rateAtStart;
    private static double NBPRateAtStart;
    private WebView webView = setUpBrowser();
    private static Label rateForLabel;
    private static Label rateNbpLabel;
    private static Label countryLabel;
    private static Label cityLabel;
    private static Label currencyLabel;


    @SuppressWarnings("Duplicates")
    @Override
    public void start(Stage stage) {
        stage.setTitle("TPO02 - s15599");
        stage.setResizable(false);

        GridPane grid = new GridPane();

        VBox browser = new VBox(webView);
        grid.add(browser, 0,2,2,1);

        Label weatherLabel = new Label(String.format("Weather: "));
        Button weatherButton = new Button(">Click<");
        weatherButton.setMaxHeight(15);
        weatherButton.setMinHeight(15);
        weatherButton.setFont(new Font("Verdana", 8));
        weatherButton.setOnAction(actionEvent -> showWeatherInfo());
        HBox weatherBox = new HBox();
        weatherBox.getChildren().addAll(weatherLabel, weatherButton);

        rateForLabel = new Label(String.format(String.format("Rate of country currency to current currency: \t%s", rateAtStart)));
        rateNbpLabel = new Label(String.format(String.format("Rate of PLN to country currency: \t\t\t\t%s", NBPRateAtStart)));
        VBox resultsLabel = new VBox();
        resultsLabel.getChildren().addAll(weatherBox, rateForLabel, rateNbpLabel);
        resultsLabel.setPadding(new Insets(10,10,10,30));
        resultsLabel.setSpacing(10);
        grid.add(resultsLabel, 0,0,1,1);

        countryLabel = new Label(String.format("Current country:\t%s", userService.getCountry()));
        cityLabel = new Label(String.format("Current city:\t\t%s", userService.getCity()));
        currencyLabel = new Label(String.format("Current currency:\t%s", userService.getUsersProvidedCurrency()));

        VBox settingsLabels = new VBox();
        settingsLabels.getChildren().addAll(countryLabel, cityLabel, currencyLabel);
        settingsLabels.setPadding(new Insets(10,10,10,30));
        settingsLabels.setSpacing(10);
        grid.add(settingsLabels, 1,0,1,1);

        Button cityButton = new Button("Change city");
        setCityButtonAction(cityButton, cityLabel);

        Button countryButton = new Button("Change country");
        setCountryButtonAction(countryButton, countryLabel);

        Button currencyButton = new Button("Change currency");
        setCurrencyButtonAction(currencyButton, currencyLabel);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(cityButton, countryButton, currencyButton);
        grid.add(buttons, 0,1,2,1);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    static public void startUI(Service service , double rate1, double rate2) {
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

        Map<String, Object> weatherMap = parseJson();

        Label descriptionLabel = new Label(
                "Weather description:\t" +
                ((Map)((ArrayList)weatherMap.get("weather"))
                        .get(0))
                        .get("description")
        );

        Label temperatureLabel = new Label(
                "Temperature:\t\t\t" +
                ((Map)weatherMap.get("main"))
                        .get("temp")
        );

        Label pressureLabel = new Label(
                "Pressure:\t\t\t\t" +
                ((Map)weatherMap.get("main"))
                        .get("pressure")
        );

        Label visibilityLabel = new Label("Visibility:\t\t\t\t" + weatherMap.get("visibility"));

        Label windSpeedLabel = new Label(
                "Wind speed:\t\t\t" +
                ((Map)weatherMap.get("wind"))
                        .get("speed")
        );

        Stage weatherWindow = new Stage();
        weatherWindow.setTitle("Weather info");
        VBox weatherInfo = new VBox();
        weatherInfo.setPadding(new Insets(20,20,20,20));
        weatherInfo.setSpacing(10);
        weatherInfo.getChildren().addAll(
                descriptionLabel,
                temperatureLabel,
                pressureLabel,
                visibilityLabel,
                windSpeedLabel
        );

        weatherWindow.setScene(new Scene(weatherInfo));
        weatherWindow.showAndWait();
    }

    private Map<String, Object> parseJson() {
        String JsonString = userService.getWeather();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonParsed = new HashMap<>();
        try {
            jsonParsed = mapper.readValue(JsonString, new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonParsed;
    }

    private void setCityButtonAction(Button cityButton, Label cityLabel) {
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

    private void setCountryButtonAction(Button countryButton, Label countryLabel) {
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter new country: ");
        countryButton.setOnAction(actionEvent -> {
            td.showAndWait();
            String newCountry = td.getEditor().getText();
            userService.setCountry(newCountry);
            countryLabel.setText(String.format("Current country:\t%s", newCountry));
            rateForLabel.setText(String.valueOf(String.format("Rate of country currency to current currency: \t%s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
            rateNbpLabel.setText(String.valueOf(String.format("Rate of PLN to country currency: \t\t\t\t%s", userService.getNBPRate())));
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
            rateForLabel.setText(String.valueOf(String.format("Rate of country currency to current currency: \t%s", userService.getRateFor(userService.getUsersProvidedCurrency()))));
        });
    }

}
