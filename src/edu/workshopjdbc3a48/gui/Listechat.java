package edu.workshopjdbc3a48.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Listechat {
    private final ObservableList<String> chats = FXCollections.observableArrayList();
    private final ListView<String> chatListView = new ListView<>(chats);

    public Listechat() {
        // Ajouter les chats initiaux ici si besoin
    }

    public void addChat(String user1, String user2) {
    String chat = user1 + " -> " + user2;
    String chat2 = user2 + " -> " + user1;
    if (!chats.contains(chat) && !chats.contains(chat2)) {
        chats.add(chat);
    }
}


    public void removeChat(String user1, String user2) {
        String chat = user1 + " -> " + user2;
        chats.remove(chat);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Liste des chats");

        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label("Liste des chats :");
        hBox.getChildren().add(label);

        borderPane.setTop(hBox);

        chatListView.setPrefSize(300, 500);
        borderPane.setCenter(chatListView);

        Scene scene = new Scene(borderPane, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Listechat listechat = new Listechat();

            // Ajouter des chats pour tester
            listechat.addChat("a", "b");
            listechat.addChat("c", "d");
            

            // Afficher la liste des chats
            listechat.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}
