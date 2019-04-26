package zad1.admin.frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zad1.admin.backend.Admin;

public class AdminUI extends Application {

    private Admin admin = new Admin("127.0.0.1", 9001);
    private ObservableList<String> topics;

    public static void startUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();

        TextArea ta = new TextArea();
        ta.setMinSize(300,250);
        grid.add(ta, 0, 0, 1, 2);

        topics = FXCollections.observableArrayList(admin.getTopicsList());
        ListView<String> lw = new ListView<>();
        lw.setItems(topics);
        grid.add(lw, 1,0);

        VBox buttons = new VBox();

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(3);
        buttons.setPadding(new Insets(5, 0, 5, 0));

        Button addTopicButton = new Button("Add topic");
        addTopicButton.setOnAction(event -> {
            TextInputDialog td = new TextInputDialog();
            td.setHeaderText("Enter new topic");
            td.showAndWait();
            String newTopic = td.getEditor().getText();
            admin.addTopic(newTopic);
            topics.setAll(admin.getTopicsList());
        });
        addTopicButton.setPrefWidth(150);

        Button removeTopicButton = new Button("Remove topic");
        removeTopicButton.setOnAction(event -> {
            String selectedTopic = lw.getSelectionModel().getSelectedItem();
            admin.removeTopic(selectedTopic);
            topics.setAll(admin.getTopicsList());
        });
        removeTopicButton.setPrefWidth(150);

        Button sendMessageButton = new Button("Send message");
        sendMessageButton.setOnAction(event -> {
            String selectedTopic = lw.getSelectionModel().getSelectedItem();
            String message = ta.getText();
            admin.sendMessage(selectedTopic, message);
            ta.setText("");
        });
        sendMessageButton.setPrefWidth(150);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> primaryStage.close());
        exitButton.setPrefWidth(150);

        buttons.getChildren().addAll(addTopicButton, removeTopicButton, sendMessageButton, exitButton);
        grid.add(buttons, 1, 1);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("TPO4 - Admin");
        primaryStage.show();
    }

}
