package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.TicketType;

import java.util.Arrays;
import java.util.List;

public class SellTicketWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();

        Label typeL = new Label("Rodzaj biletu: ");
        Label discountsL = new Label("Zniżki");
        Label vipL = new Label("VIP:");
        Label durationL = new Label("dasdasd");
        Label eventL = new Label("Wydarzenie");
        Label seatL = new Label("Miejsce");

        ComboBox<TicketType> typeLV = new ComboBox<>();
        typeLV.setItems(FXCollections.observableArrayList(Arrays.asList(TicketType.values())));

        grid.add(typeL, 0, 1);
        grid.add(typeLV, 1, 1);
        grid.add(discountsL, 0, 2);
        grid.add(vipL, 0, 3);
        grid.add(durationL, 0, 4);
        grid.add(eventL, 0, 5);
        grid.add(seatL, 0, 6);
        grid.setPadding(new Insets(10, 10, 10 ,10));
        grid.setVgap(10);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sprzedaj bilet");
        primaryStage.setMaxHeight(550);
        primaryStage.setMaxWidth(525);
        primaryStage.show();

    }

}
