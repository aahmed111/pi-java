/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSchat extends Application {
    private MessageProducer messageProducer;
    private Session session;
    private String codeUser;
    private ObservableList<String> observableListMessages = FXCollections.observableArrayList();
    private ListView<String> listViewMessage = new ListView<>(observableListMessages);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JMS chat");

        BorderPane borderPane = new BorderPane();

        HBox hBox = new HBox();

        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        Label labelCode = new Label("Code:");
        TextField textFieldCode = new TextField("c1");
        textFieldCode.setPromptText("Code");

        Label labelHost = new Label("Host:");
        TextField textFieldHost = new TextField("localhost");
        textFieldHost.setPromptText("Host");

        Label labelPort = new Label("Port:");
        TextField textFieldPort = new TextField("61616");
        textFieldPort.setPromptText("Port");

        Button buttonConnecter = new Button("Connecter");
        hBox.getChildren().addAll(labelCode, textFieldCode, labelHost, textFieldHost, labelPort, textFieldPort,
                buttonConnecter);

        borderPane.setTop(hBox);

        VBox vBox = new VBox();
        HBox hBox2 = new HBox();
        hBox2.setSpacing(10);

        Label labelTo = new Label("To:");
        TextField textFieldTo = new TextField("c1");
        textFieldTo.setPrefWidth(150);
        Label labelMessage = new Label("Message:");
        TextArea textAreaMessage = new TextArea();
        textAreaMessage.setPrefRowCount(3);

        Button buttonEnvoyer = new Button("Envoyer");
        buttonEnvoyer.setDefaultButton(true);

        hBox2.getChildren().addAll(labelTo, textFieldTo, labelMessage, textAreaMessage, buttonEnvoyer);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(textAreaMessage, Priority.ALWAYS);
        VBox.setVgrow(listViewMessage, Priority.ALWAYS);

        vBox.getChildren().addAll(listViewMessage, hBox2);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        // connect to ActiveMQ broker
        buttonConnecter.setOnAction(e -> {
            try {
                codeUser = textFieldCode.getText().trim();
       




            // create ActiveMQ connection factory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://" + textFieldHost.getText().trim() + ":" + textFieldPort.getText().trim());

            // create ActiveMQ connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // create ActiveMQ session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create ActiveMQ destination
            Destination destination = session.createTopic("JMSchat");

            // create ActiveMQ producer
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // create ActiveMQ consumer
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(message -> {
                try {
                    String text = ((TextMessage) message).getText();
                    Platform.runLater(() -> observableListMessages.add(text));
                } catch (JMSException ex) {
                    Logger.getLogger(JMSchat.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (JMSException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de se connecter à l'hôte et au port spécifiés");
            alert.showAndWait();
        }
    });

    // send message
    buttonEnvoyer.setOnAction(e -> {
        try {
            String to = textFieldTo.getText().trim();
            String text = textAreaMessage.getText().trim();

            if (!to.isEmpty() && !text.isEmpty()) {
                TextMessage message = session.createTextMessage(text);
                message.setStringProperty("to", to);
                message.setStringProperty("from", codeUser);
                messageProducer.send(message);
                textAreaMessage.clear();
            }
        } catch (JMSException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur d'envoi");
            alert.setHeaderText(null);
            alert.setContentText("Impossible d'envoyer le message");
            alert.showAndWait();
        }
    });

    // allow sending messages by pressing Enter key
    textAreaMessage.setOnKeyPressed(e -> {
        if (e.getCode() == KeyCode.ENTER && !e.isShiftDown()) {
            e.consume();
            buttonEnvoyer.fire();
        }
    });

    // allow editing "To" field in ListView
    listViewMessage.setEditable(true);
    listViewMessage.setCellFactory(TextFieldListCell.forListView());
    listViewMessage.setOnEditCommit(e -> {
        int index = e.getIndex();
        String newText = e.getNewValue();
        observableListMessages.set(index, newText);
    });
}

@Override
public void stop() {
    try {
        session.close();
    } catch (JMSException ex) {
        Logger.getLogger(JMSchat.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public static void main(String[] args) {
    launch(args);
}
}


