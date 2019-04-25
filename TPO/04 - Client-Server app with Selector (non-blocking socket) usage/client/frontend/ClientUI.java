package zad1.client.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientUI extends Application {

    private GridPane grid = new GridPane();
    private TabPane tabPane;

    public static void startUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        VBox buttons = new VBox();
        buttons.setSpacing(5);
        Button topicButton = new Button("Add topic");
        topicButton.setMinWidth(100);
        topicButton.setOnAction(event -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("Enter topic to follow:");
            td.showAndWait();
            String subject = td.getEditor().getText();
            Tab newTab = new Tab(subject);
            newTab.setOnCloseRequest(event1 -> System.out.println("[Client] closing tab " + newTab.getText()));
            tabPane.getTabs().add(newTab);
            tabPane.getTabs().forEach((tab) -> System.out.println("[Client] new tab added: " + tab.getText()));
        });
        Button exitButton = new Button("Exit");
        exitButton.setMinWidth(100);
        exitButton.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });
        buttons.getChildren().addAll(topicButton, exitButton);

        tabPane = new TabPane();
        tabPane.setPadding(new Insets(0,0,0,10));
        tabPane.setMinSize(400,600);

        grid.add(buttons,0,0);
        grid.add(tabPane, 1, 0);
        grid.setPadding(new Insets(10));

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
