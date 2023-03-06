/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class Chat extends Application {

    private MessageProducer messageProducer;
    private Session session;
    private String codeUser;

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
        GridPane gridPane = new GridPane();
        HBox hBox2 = new HBox();
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(hBox2);
        borderPane.setCenter(vBox);

        Label labelTo = new Label("To:");
        TextField textFieldTo = new TextField("c1");
        textFieldTo.setPrefWidth(250);

        Label labelMessage = new Label("Message:");
        TextArea textAreaMessage = new TextArea();
        textAreaMessage.setPrefWidth(250);

        Button buttonEnvoyer = new Button("Envoyer");

        gridPane.setPadding(new Insets(10));
        textAreaMessage.setPrefRowCount(2);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(labelTo, 0, 0);
        gridPane.add(textFieldTo, 1, 0);
        gridPane.add(labelMessage, 0, 1);
        gridPane.add(textAreaMessage, 1, 1);
        gridPane.add(buttonEnvoyer, 2, 1);

        ObservableList<String> observableListMessages = FXCollections.observableArrayList();
        ListView<String> listViewMessages = new ListView<>(observableListMessages);
        hBox2.getChildren().add(listViewMessages);

        hBox2.setPadding(new Insets(10));
        hBox2.setSpacing(10);

        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonEnvoyer.setOnAction(e -> {

            try {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(textAreaMessage.getText());
                textMessage.setStringProperty("code", textFieldTo.getText());
                messageProducer.send(textMessage);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }

        });

        buttonConnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    codeUser = textFieldCode.getText();
                    String host = textFieldHost.getText();
                    int port = Integer.parseInt(textFieldPort.getText());
                    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                            "tcp://" + host + ":" + port);
                    Connection connection = connectionFactory.createConnection();
                    connection.start();
                    // create ActiveMQ session
                    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    // create ActiveMQ destination
                    Destination destination = session.createTopic("enset.chat");
                    // create ActiveMQ consumer with a selector based on the "to" property
                    MessageConsumer messageConsumer = session.createConsumer(destination, "code='" + codeUser + "'");
                    messageProducer = session.createProducer(destination);
                    messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    messageConsumer.setMessageListener(message -> {

                        try {
                            if (message instanceof TextMessage) {
                                TextMessage textMessage = (TextMessage) message;
                                observableListMessages.add(textMessage.getText());
                            } else if (message instanceof StreamMessage) {
                            }
                        } catch (Exception ex) {

                        }

                    });
                    hBox.setDisable(true);  //verifier l'etablire de la connextion 
                } catch (JMSException ex) {

                }
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
