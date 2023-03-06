package edu.workshopjdbc3a48.gui;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.Queue;
 

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

 
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;

import javafx.scene.input.KeyCode;

public class ahhhh extends Application {
    private MessageProducer messageProducer;
    private Session session;
    private String codeUser;
    private ObservableList<String> observableListMessages = FXCollections.observableArrayList();
    private ListView<String> listViewMessage = new ListView<>(observableListMessages);
    private ObservableList<String> observableListMessagesEnvoyes = FXCollections.observableArrayList();
    private ListView<String> listViewMessageEnvoye = new ListView<>(observableListMessagesEnvoyes);
    private TemporaryQueue temporaryQueue; // file d'attente temporaire pour stocker les messages pour les utilisateurs déconnectés

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

        vBox.getChildren().addAll(listViewMessage, listViewMessageEnvoye, hBox2);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        double dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        primaryStage.setMinWidth(25 * dpi / 2.54);
        primaryStage.setMaxWidth(25 * dpi / 2.54);
        primaryStage.setMinHeight(15 * dpi / 2.54);
        primaryStage.setMaxHeight(15 * dpi / 2.54);

        buttonConnecter.setOnAction(e -> {
            try {
                codeUser = textFieldCode.getText().trim();

                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                        "tcp://" + textFieldHost.getText().trim() + ":" + textFieldPort.getText().trim());
                Connection connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // créer une file d'attente temporaire pour les messages des utilisateurs déconnectés
                temporaryQueue = session.createTemporaryQueue();
                            Destination destination = session.createTopic("chatTopic");
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer messageConsumer = session.createConsumer(destination, "dest='" + codeUser + "'");
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            String text = textMessage.getText();
                            Platform.runLater(() -> {
                                observableListMessages.add(text);
                            });
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            // récupérer les messages stockés temporairement pour cet utilisateur et les afficher
            MessageConsumer temporaryMessageConsumer = session.createConsumer(temporaryQueue, "dest='" + codeUser + "'");
            temporaryMessageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            String text = textMessage.getText();
                            Platform.runLater(() -> {
                                observableListMessages.add(text);
                            });
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            buttonEnvoyer.setDisable(false);
            textFieldCode.setDisable(true);
            textFieldHost.setDisable(true);
            textFieldPort.setDisable(true);
        } catch (JMSException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de connexion");
            alert.setContentText("Impossible de se connecter au serveur. Vérifiez les informations de connexion et réessayez.");
            alert.showAndWait();
        }
    });

    buttonEnvoyer.setOnAction(e -> {
        try {
            String dest = textFieldTo.getText().trim();
            String text = textAreaMessage.getText().trim();

            if (!dest.isEmpty() && !text.isEmpty()) {
                TextMessage message = session.createTextMessage(text);
                message.setStringProperty("dest", dest);
                message.setStringProperty("source", codeUser);
                messageProducer.send(message);

                Platform.runLater(() -> {
                    observableListMessagesEnvoyes.add("To: " + dest + " | Message: " + text);
                });

                // stocker temporairement le message pour les utilisateurs déconnectés
                TemporaryQueue temporaryQueueDest = session.createTemporaryQueue();
                MessageProducer temporaryMessageProducer = session.createProducer(temporaryQueueDest);
                temporaryMessageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                message.setJMSReplyTo(temporaryQueueDest);
                temporaryMessageProducer.send(message);
            }

            textAreaMessage.clear();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    });
}

public static void main(String[] args) {
    launch(args);
}
}
