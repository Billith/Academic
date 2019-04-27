package zad1.client.frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import zad1.client.backend.Client;

import java.util.Hashtable;
import java.util.Map;

public class ClientUI extends Application {

    private Client client = new Client("127.0.0.1", 9001);
    public static Map<String, ObservableList<String>> messagesMap = new Hashtable<>();
    public static ObservableList<String> topicsList = FXCollections.observableArrayList();

    private GridPane grid = new GridPane();
    private TabPane tabPane;

    public static void startUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        HBox buttons = new HBox();
        buttons.setSpacing(5);

        Button exitButton = new Button("Exit");
        exitButton.setMinWidth(100);
        exitButton.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });
        buttons.getChildren().addAll(exitButton);

        tabPane = new TabPane();
        tabPane.setPadding(new Insets(10,0,0,0));
        tabPane.setMinSize(400,600);

        ListView<String> topicListView = new ListView<>();
        topicListView.setItems(topicsList);
        topicListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedTopic = topicListView.getSelectionModel().getSelectedItem();
                Tab newTab = new Tab(selectedTopic);
                newTab.setOnCloseRequest(event1 -> {
                    client.unsubscribeToTopic(selectedTopic);
                });
                ListView<String> lw = new ListView<>();
                messagesMap.put(selectedTopic, FXCollections.observableArrayList());
                lw.setItems(messagesMap.get(selectedTopic));
                newTab.setContent(lw);
                client.subscribeToTopic(selectedTopic);
                tabPane.getTabs().add(newTab);
            }
        });
        grid.add(topicListView, 1, 0);

        grid.add(buttons,0,1);
        grid.add(tabPane, 0, 0);
        grid.setPadding(new Insets(10));

        runTopicsProbeThread();

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TPO4 - Client");
        primaryStage.show();
    }

    private void runTopicsProbeThread() {
        new Thread(() -> {
            while (true) {
                System.out.println("[Client] Probing server for topics");
                client.getTopicsList();
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
