/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ChatController implements Initializable {

    private MessageProducer messageProducer;
    private Session session;
    private String codeUser;
    private ObservableList<String> observableListMessages = FXCollections.observableArrayList();
    // private ListView<String> listViewMessage = new ListView<>(observableListMessages);
    private ObservableList<String> observableListMessagesEnvoyes = FXCollections.observableArrayList();
    private int id_user;
    @FXML
    private Label id;
    @FXML
    private ListView<String> listChat;
    @FXML
    private ListView<String> listMessage;
    @FXML
    private TextArea message;
    @FXML
    private ListView<String> listMessageEnovoye;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void envoyer(ActionEvent event) throws JMSException, SQLException {
        //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // ajout du date
        ////   Date now = new Date();
        //    String timestamp = dateFormat.format(now);

        //  Message t = new edu.workshopjdbc3a48.entities.Message(Message);
        //  ServiceMessage Rs = new ServiceMessage();
        // Rs.ajouter(t);
        // create ActiveMQ connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://" + "localhost" + ":" + 61616);

        // create ActiveMQ connection
        javax.jms.Connection connection = connectionFactory.createConnection();
        connection.start();

        // create ActiveMQ session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create ActiveMQ destination
        Destination destination = session.createQueue("JMSchat");

        // create ActiveMQ producer )(pour l'envoie des message)
        messageProducer = session.createProducer(destination);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // MessageConsumer messageConsumer = session.createConsumer(destination, "to='" + codeUser + "'");
        MessageConsumer messageConsumer = session.createConsumer(session.createQueue("JMSchat"), "to='" + codeUser + "'"); //ajout le destination a la file d'attente 
        messageConsumer.setMessageListener(message -> {

         
            try {
                String text = ((TextMessage) message).getText();
                Platform.runLater(() -> observableListMessages.add(text));
            } catch (JMSException ex) {
                Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        });      
            ServiceUser su = new ServiceUser();
            User userReciver = su.getOneById(105);
            User userSender = su.getOneById(id_user);
            String to = userReciver.getUsername();
            // String text = "[" + timestamp + "] " + textAreaMessage.getText().trim(); //pour la date

            observableListMessagesEnvoyes.add("To " + to + ": " + text);
            //liste des message envoyeer 
            listMessageEnovoye.setItems(observableListMessagesEnvoyes);
            //    if (!to.isEmpty() && !text.isEmpty()) {
            if (!to.isEmpty()) {
                TextMessage messageEnvoye = session.createTextMessage(message.getText());
                messageEnvoye.setStringProperty("to", to);
                messageEnvoye.setStringProperty("from", userSender.getUsername()); // envoye des message par code 
                messageProducer.send(messageEnvoye);
                message.clear(); 
            }   
    }
        // allow sending messages by pressing Enter key
    /*textAreaMessage.setOnKeyPressed (e  
         
        -> {
            if (e.getCode() == KeyCode.ENTER && !e.isShiftDown()) {
            e.consume();
            buttonEnvoyer.fire();
        }
    }
);
    }*/
   
    
}
