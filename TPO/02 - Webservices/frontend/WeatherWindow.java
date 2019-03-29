package zad1.frontend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherWindow extends Stage {

    Map<String, Object>  weatherMap = parseJson();
    VBox weatherInfo = new VBox();

    public WeatherWindow() {
        setTitle("Weather info");
        setUpWeatherLabels();
        setScene(new Scene(weatherInfo));
    }

    private void setUpWeatherLabels() {
        Label descriptionLabel = new Label(
                "Weather description:\t" +
                        ((Map)((ArrayList)weatherMap
                                .get("weather"))
                                .get(0))
                                .get("description")
        );

        Label temperatureLabel = new Label(
                "Temperature:\t\t\t" +
                        ((Map)weatherMap
                                .get("main"))
                                .get("temp")
        );

        Label pressureLabel = new Label(
                "Pressure:\t\t\t\t" +
                        ((Map)weatherMap
                                .get("main"))
                                .get("pressure")
        );

        Label windSpeedLabel = new Label(
                "Wind speed:\t\t\t" +
                        ((Map)weatherMap
                                .get("wind"))
                                .get("speed")
        );

        weatherInfo.setPadding(new Insets(20,20,20,20));
        weatherInfo.setSpacing(10);
        weatherInfo.getChildren().addAll(
                descriptionLabel,
                temperatureLabel,
                pressureLabel,
                windSpeedLabel
        );
    }

    private Map<String, Object> parseJson() {
        String JsonString = MainWindow.userService.getWeather();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> parsedJSON = new HashMap<>();
        try {
            parsedJSON = mapper.readValue(JsonString, new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedJSON;
    }

}
