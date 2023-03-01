/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailCheckController implements Initializable {

    private String password;

    @FXML
    private TextField email;
    @FXML
    private TextField passwordEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retour(ActionEvent event
    ) {
        try {
            // crée un objet FXMLLoader qui va permettre de charger les informations de la vue "Login.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            //charger l'interface graphique (Parent rootU)
            Parent rootU = loader.load();
            //crée une nouvelle scène (Scene sceneU)
            Scene sceneU = new Scene(rootU);
            //récupère l'objet Stage correspondant à la fenêtre principale
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //    String css =this.getClass().getResource("../GUI/style.css").toExternalForm();
            //     sceneU.getStylesheets().add(css);
            //remplacer la scene 
            appStage.setScene(sceneU);
            //voir la nouvelle scene
            appStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLException, MessagingException {
        String Email = email.getText();
        String motDePasse = passwordEmail.getText();
        ServiceUser sc = new ServiceUser();
        if (sc.isValidEmailAddress(Email)) {
            if (sc.mailExist(Email) == false) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "email non trouvé!");
                Optional<ButtonType> resultName = alertName.showAndWait();
                if (resultName.isPresent() && resultName.get() == ButtonType.OK) {
                    email.setText("");
                    passwordEmail.setText("");
                }
            } else {
                String passwordCompte = sc.getPasswordByEmail(Email);

                Properties props = new Properties();
                props.put("mail.smtp.auth", true);         
                props.put("mail.smtp.host", "smtp.esprit.tn");
                props.put("mail.smtp.port", 587 );
                props.put("mail.smtp.starttls.enable", true);
                props.put("mail.transport.protocl", "smtp");


                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Email, motDePasse);
                    }
                });
 
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("emailName@esprit.tn" ));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
                message.setSubject("Confirmation d'inscription");
                message.setText("Bonjour,\n"
                          + "Votre inscription a été confirmée. Veuillez trouver ci-dessous votre mot de passe de connexion dans l'application swapify :\n"
                          + passwordCompte);
                
                Transport.send(message);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent rootU = loader.load();
                    Scene sceneU = new Scene(rootU);
                    Node node = (Node) event.getSource();
                    Stage appStage = (Stage) node.getScene().getWindow();

                    appStage.setScene(sceneU);
                    appStage.show();
                    Alert alertMail = new Alert(Alert.AlertType.INFORMATION, "password envoyé avec succes !!verifier votre email");
                    alertMail.show();
                } catch (IOException ex) {
                    System.out.println("Erreur lors du chargement du fichier FXML : " + ex.getMessage());
                    ex.printStackTrace();
                }

            }
        } else {
            Alert alerttMail = new Alert(Alert.AlertType.ERROR, "email invalide !");
            alerttMail.show();
        }
    }

}
