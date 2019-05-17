package client.ui;

import client.PhoneBookClient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class PhoneBookClientUI extends Application {

    PhoneBookClient pbc;

    @Override
    public void start(Stage stage) {

        try {
            pbc = new PhoneBookClient();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to connect to the server!\n" + e.getMessage()).showAndWait();
            System.exit(1);
        }

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);

        Label nameLabel = new Label("Name:        ");
        Label phoneLabel = new Label("PhoneNumber: ");

        TextField nameTextField = new TextField();
        TextField phoneNumberTextField = new TextField();
        TextField output = new TextField();
        output.setEditable(false);

        HBox buttons = new HBox();

        Button getButton = new Button("Get number");
        getButton.setOnAction(actionEvent -> {
            try {
                output.setText(pbc.getPhoneNumber(nameTextField.getText()));
            } catch (RemoteException e) {
                new Alert(Alert.AlertType.ERROR, "Getting phone number failed!\n" + e.getMessage()).showAndWait();
            }
        });
        Button addButton = new Button("Add number");
        addButton.setOnAction(actionEvent -> {
            try {
                output.setText(Boolean.toString(pbc.addPhoneNumber(nameTextField.getText(), phoneNumberTextField.getText())));
            } catch (RemoteException e) {
                new Alert(Alert.AlertType.ERROR, "Adding phone number failed!\n" + e.getMessage()).showAndWait();
            }
        });
        Button replaceButton = new Button("Replace number");
        replaceButton.setOnAction(actionEvent -> {
            try {
                output.setText(Boolean.toString(pbc.replacePhoneNumber(nameTextField.getText(), phoneNumberTextField.getText())));
            } catch (RemoteException e) {
                new Alert(Alert.AlertType.ERROR, "Replacing phone number failed!\n" + e.getMessage()).showAndWait();
            }
        });

        buttons.setSpacing(5);
        buttons.getChildren().addAll(getButton, addButton, replaceButton);

        grid.add(nameLabel, 0, 0);
        grid.add(nameTextField, 1,0);
        grid.add(phoneLabel,0,1);
        grid.add(phoneNumberTextField, 1, 1);
        grid.add(buttons, 0, 2, 2, 1);
        grid.add(output, 0, 3, 3, 1);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("TPO5 - Client - s15599");
        stage.show();

    }

}
