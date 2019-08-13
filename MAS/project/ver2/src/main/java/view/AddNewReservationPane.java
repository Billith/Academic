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

/**
 * Class i responsible for setting up new grid
 */
public class AddNewReservationPane extends GridPane {

    private Movie movie;
    private ObservableList<Room> roomList = FXCollections.observableArrayList();
    private ObservableList<RoomReservation> nextWeekReservations = FXCollections.observableArrayList(RoomReservation.getReservationsForNextWeek());

    /**
     * The constructor
     * @param movie
     * @param primaryStage
     */
    public AddNewReservationPane(Movie movie, Stage primaryStage) {
        this.movie = movie;
        setUpLayOut(primaryStage);
    }

    /**
     * Function setup grid and new scene in main window
     * @param primaryStage
     */
    private void setUpLayOut(Stage primaryStage) {

        Label projectionForm = new Label("Format projekcji");
        Label reservationStart = new Label("Początek rezerwacji");
        Label reservationEnd = new Label("Koniec rezerwacji");
        Label room = new Label("Sala");
        Label price = new Label("Bazowa cena biletu");
        Label otherReservations = new Label("Rezerwacje na przyszły tydzień:");

        ComboBox<Room> availableRooms = new ComboBox<>();
        availableRooms.setItems(roomList);
        availableRooms.valueProperty().addListener((observable, oldValue, newValue) -> {
            //nextWeekReservations.setAll(RoomReservation.getReservationsForNextWeek(newValue));
        });

        HBox radioButtons = new HBox();
        ToggleGroup group = new ToggleGroup();

        HBox startTimePicker = new HBox();
        DatePicker start = new FormattedDataPicker(LocalDate.now());
        TextField startTime = new TextField("00:00");

        HBox endTimePicker = new HBox();
        DatePicker end = new FormattedDataPicker(LocalDate.now());
        TextField endTime = new TextField("00:00");

        TextField ticketPrice = new PersistentPromptTextField("", "cena biletu (np. 22.99)");

        TableView table = new TableView();
        HBox buttons = new HBox();

        setupRadioButtons(radioButtons, group, availableRooms);
        setupStartTimePicker(startTimePicker, start, startTime);
        setupEndTimePicker(endTimePicker, end, endTime);
        setupTable(table);
        setupButtons(buttons, group, start, startTime, end, endTime, availableRooms, ticketPrice, primaryStage);
        setupGrid(this, projectionForm, radioButtons, room, availableRooms, reservationStart, startTimePicker,
                reservationEnd, endTimePicker, price, ticketPrice, otherReservations, table, buttons);

    }

    /**
     * Function setup behaviour and layout of buttons
     * @param buttons
     * @param group
     * @param start
     * @param startTime
     * @param end
     * @param endTime
     * @param availableRooms
     * @param ticketPrice
     * @param primaryStage
     */
    private void setupButtons(HBox buttons, ToggleGroup group, DatePicker start, TextField startTime, DatePicker end, TextField endTime, ComboBox<Room> availableRooms, TextField ticketPrice, Stage primaryStage) {
        Button confirm = new Button("Zatwierdź");
        Button cancel = new Button("Anuluj");
        buttons.getChildren().addAll(confirm, cancel);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        confirm.setOnAction(event -> createReservation(group, start, startTime, end, endTime, availableRooms, ticketPrice, primaryStage));
        cancel.setOnAction(event -> primaryStage.close());
    }

    /**
     * Function setup table layout and assign data source
     * @param table
     */
    private void setupTable(TableView table) {
        TableColumn eventCol = new TableColumn("Wydarzenie");
        TableColumn roomCol = new TableColumn("Sala");
        TableColumn startCol = new TableColumn("Od");
        TableColumn endCol = new TableColumn("Do");
        eventCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("eventString"));
        eventCol.setSortable(false);
        roomCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, Room>("room"));
        roomCol.setSortable(false);
        startCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("startString"));
        startCol.setSortable(false);
        endCol.setCellValueFactory(new PropertyValueFactory<RoomReservation, String>("endString"));
        endCol.setSortable(false);
        table.setItems(nextWeekReservations);
        table.getColumns().addAll(eventCol, roomCol, startCol, endCol);
        table.setPrefWidth(650);
        table.setEditable(false);
        table.getSortOrder().add(startCol);
    }

    /**
     * Function setup reservation end time controls
     * @param endTimePicker
     * @param end
     * @param endTime
     */
    private void setupEndTimePicker(HBox endTimePicker, DatePicker end, TextField endTime) {
        endTimePicker.getChildren().addAll(end, endTime);
        endTimePicker.setMaxWidth(225);
        endTimePicker.setSpacing(5);
    }

    /**
     * Function setup reservation start time controls
     * @param startTimePicker
     * @param start
     * @param startTime
     */
    private void setupStartTimePicker(HBox startTimePicker, DatePicker start, TextField startTime) {
        startTime.setMinHeight(15);
        startTimePicker.getChildren().addAll(start, startTime);
        startTimePicker.setMaxWidth(225);
        startTimePicker.setSpacing(5);
    }

    /**
     * Function setup movie projection type controls behaviour and layout
     * @param radioButtons
     * @param group
     * @param availableRooms
     */
    private void setupRadioButtons(HBox radioButtons, ToggleGroup group, ComboBox<Room> availableRooms) {
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
                    availableRooms.setValue(null);
                    nextWeekReservations.setAll(RoomReservation.getReservationsForNextWeek());
                } else {
                    roomList.setAll(Room.getRoomList(RoomType.THREE_D));
                    availableRooms.setValue(null);
                    nextWeekReservations.setAll(RoomReservation.getReservationsForNextWeek());
                }
            }
        });
    }

    /**
     * Function adds every control to the main grid
     * @param addNewReservationPane
     * @param projectionForm
     * @param radioButtons
     * @param room
     * @param availableRooms
     * @param reservationStart
     * @param startTimePicker
     * @param reservationEnd
     * @param endTimePicker
     * @param price
     * @param ticketPrice
     * @param otherReservations
     * @param table
     * @param buttons
     */
    private void setupGrid(AddNewReservationPane addNewReservationPane, Label projectionForm, HBox radioButtons, Label room, ComboBox<Room> availableRooms, Label reservationStart, HBox startTimePicker, Label reservationEnd, HBox endTimePicker, Label price, TextField ticketPrice, Label otherReservations, TableView table, HBox buttons) {
        addNewReservationPane.add(projectionForm, 0, 0);
        addNewReservationPane.add(radioButtons, 1, 0);
        addNewReservationPane.add(room, 0, 1);
        addNewReservationPane.add(availableRooms, 1, 1);
        addNewReservationPane.add(reservationStart, 0, 2);
        addNewReservationPane.add(startTimePicker, 1, 2);
        addNewReservationPane.add(reservationEnd, 0, 3);
        addNewReservationPane.add(endTimePicker, 1, 3);
        addNewReservationPane.add(price, 0, 4);
        addNewReservationPane.add(ticketPrice, 1, 4);
        addNewReservationPane.add(otherReservations, 0 ,5, 2, 1);
        addNewReservationPane.add(table, 0, 6, 2, 1);
        addNewReservationPane.add(buttons, 0, 7, 2, 1);

        addNewReservationPane.setPadding(new Insets(10, 10, 10 ,10));
        addNewReservationPane.setVgap(10);
        addNewReservationPane.setHgap(15);
    }

    /**
     * Function creates new reservation with data provided through GUI
     * @param group
     * @param start
     * @param startTime
     * @param end
     * @param endTime
     * @param availableRooms
     * @param ticketPrice
     * @param primaryStage
     */
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
