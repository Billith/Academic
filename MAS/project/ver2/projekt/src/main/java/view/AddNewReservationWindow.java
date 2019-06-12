package view;

import controller.ReservationValidator;
import controller.ValidateDataException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AddNewReservationWindow extends GridPane {

    private Movie movie;
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<RoomReservation> nextWeekReservations = FXCollections.observableArrayList(RoomReservation.getReservationsForNextWeek());

    public AddNewReservationWindow(Movie movie, Stage primaryStage) {
        this.movie = movie;
        setUpLayOut(primaryStage);
    }

    private void setUpLayOut(Stage primaryStage) {

        Label projectionForm = new Label("Format projekcji");
        Label reservationStart = new Label("Początek rezerwacji");
        Label reservationEnd = new Label("Koniec rezerwacji");
        Label room = new Label("Sala");
        Label price = new Label("Bazowa cena biletu");
        Label otherReservations = new Label("Rezerwacje na przyszły tydzień:");

        HBox radioButtons = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton twoD = new RadioButton("2D");
        RadioButton threeD = new RadioButton("3D");
        twoD.setToggleGroup(group);
        twoD.setUserData("2D");
        threeD.setToggleGroup(group);
        threeD.setUserData("3D");
        radioButtons.getChildren().addAll(twoD, threeD);
        radioButtons.setSpacing(5);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(group.getSelectedToggle() != null) {
                if(group.getSelectedToggle().getUserData().toString().equals("2D")) {
                    roomList.setAll(Room.getRoomList(RoomType.TWO_D));
                } else {
                    roomList.setAll(Room.getRoomList(RoomType.THREE_D));
                }
            }
        });

        HBox startTimePicker = new HBox();
        DatePicker start = new DatePicker(LocalDate.now());
        TextField startTime = new TextField("00:00");
        startTime.setMinHeight(15);
        startTimePicker.getChildren().addAll(start, startTime);
        startTimePicker.setMaxWidth(225);
        startTimePicker.setSpacing(5);

        HBox endTimePicker = new HBox();
        DatePicker end = new DatePicker(LocalDate.now());
        TextField endTime = new TextField("00:00");
        endTimePicker.getChildren().addAll(end, endTime);
        endTimePicker.setMaxWidth(225);
        endTimePicker.setSpacing(5);

        ComboBox<Room> availableRooms = new ComboBox<>();
        availableRooms.setItems(roomList);

        TextField ticketPrice = new PersistentPromptTextField("", "cena biletu (np. 22.99)");

        TableView table = new TableView();
        TableColumn eventCol = new TableColumn("Wydarzenie");
        TableColumn roomCol = new TableColumn("Sala");
        TableColumn startCol = new TableColumn("Od");
        TableColumn endCol = new TableColumn("Do");
        eventCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("event"));
        eventCol.setSortable(false);
        roomCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, Room>("room"));
        roomCol.setSortable(false);
        startCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("startString"));
        startCol.setSortable(false);
        endCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("endString"));
        endCol.setSortable(false);
        table.setItems(nextWeekReservations);
        table.getColumns().addAll(eventCol, roomCol, startCol, endCol);
        table.setMinWidth(440);
        table.setEditable(false);
        table.getSortOrder().add(startCol);

        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        HBox buttons = new HBox();
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> createReservation(group, start, startTime, end, endTime, availableRooms, ticketPrice, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());

        this.add(projectionForm, 0, 0);
        this.add(radioButtons, 1, 0);
        this.add(reservationStart, 0, 1);
        this.add(startTimePicker, 1, 1);
        this.add(reservationEnd, 0, 2);
        this.add(endTimePicker, 1, 2);
        this.add(room, 0, 3);
        this.add(availableRooms, 1, 3);
        this.add(price, 0, 4);
        this.add(ticketPrice, 1, 4);
        this.add(otherReservations, 0 ,5, 2, 1);
        this.add(table, 0, 6, 2, 1);
        this.add(buttons, 0, 7, 2, 1);

        this.setPadding(new Insets(10, 10, 10 ,10));
        this.setVgap(10);
        this.setHgap(15);

    }

    private void createReservation(ToggleGroup group, DatePicker start, TextField startTime, DatePicker end,
                                   TextField endTime, ComboBox<Room> availableRooms, TextField ticketPrice, Stage primaryStage) {
        try {
            ReservationValidator.validateInput(group, start, startTime, end, endTime, availableRooms, ticketPrice);
            RoomType requiredRoom = (group.getSelectedToggle().getUserData().equals("2D")) ? RoomType.TWO_D : RoomType.THREE_D;
            String[] startHourAndMinute = startTime.getText().trim().split(":");
            String[] endHourAndMinute = endTime.getText().trim().split(":");
            new RoomReservation(
                    LocalDateTime.of(start.getValue(), LocalTime.of(Integer.parseInt(startHourAndMinute[0]), Integer.parseInt(startHourAndMinute[1]))),
                    LocalDateTime.of(end.getValue(), LocalTime.of(Integer.parseInt(endHourAndMinute[0]), Integer.parseInt(endHourAndMinute[1]))),
                    availableRooms.getValue(),
                    new MovieProjection(requiredRoom, new BigDecimal(Double.parseDouble(ticketPrice.getText())), movie)
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Rezerwacja została pomyślnie dodana do systemu.", ButtonType.OK);
            new JMetro(AddNewMovieWindow.theme).applyTheme(alert.getDialogPane());
            alert.setHeaderText("Informacja");
            alert.setTitle("Informacja");
            alert.showAndWait();
            primaryStage.close();
        } catch (ValidateDataException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            new JMetro(AddNewMovieWindow.theme).applyTheme(alert.getDialogPane());
            alert.showAndWait();
        }

    }

}
