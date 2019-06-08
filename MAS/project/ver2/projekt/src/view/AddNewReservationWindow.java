package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Movie;
import model.Room;


public class AddNewReservationWindow extends GridPane {

    private Movie movie;

    public AddNewReservationWindow(Movie movie) {
        this.movie = movie;
        setUpLayOut();
    }

    private void setUpLayOut() {

        Label projectionForm = new Label("Format projekcji");
        Label reservationStart = new Label("Początek rezerwacji");
        Label reservationEnd = new Label("Koniec rezerwacji");
        Label room = new Label("Sala");
        Label otherReservations = new Label("Rezerwacje na przyszły tydzień:");

        HBox radioButtons = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton twoD = new RadioButton("2D");
        RadioButton threeD = new RadioButton("3D");
        twoD.setToggleGroup(group);
        threeD.setToggleGroup(group);
        radioButtons.getChildren().addAll(twoD, threeD);
        radioButtons.setSpacing(5);

        HBox startTimePicker = new HBox();
        DatePicker start = new DatePicker();
        TextField startTime = new TextField("00:00");
        startTimePicker.getChildren().addAll(start, startTime);
        startTimePicker.setMaxWidth(200);

        HBox endTimePicker = new HBox();
        DatePicker end = new DatePicker();
        TextField endTime = new TextField("00:00");
        endTimePicker.getChildren().addAll(end, endTime);
        endTimePicker.setMaxWidth(200);

        ComboBox<Room> availableRooms = new ComboBox<>(FXCollections.observableArrayList());

        TableView table = new TableView();
        TableColumn roomCol = new TableColumn("Sala");
        TableColumn startCol = new TableColumn("Od");
        TableColumn endCol = new TableColumn("Do");
        table.getColumns().addAll(roomCol, startCol, endCol);

        this.add(projectionForm, 0, 0);
        this.add(radioButtons, 1, 0);
        this.add(reservationStart, 0, 1);
        this.add(startTimePicker, 1, 1);
        this.add(reservationEnd, 0, 2);
        this.add(endTimePicker, 1, 2);
        this.add(room, 0, 3);
        this.add(availableRooms, 1, 3);
        this.add(otherReservations, 0 ,4, 2, 1);
        this.add(table, 0, 5, 2, 1);

        this.setPadding(new Insets(10, 10, 10 ,10));
        this.setVgap(10);
        this.setHgap(15);

    }


}
