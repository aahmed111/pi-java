/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.entities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.scene.control.Alert;

public class ConfirmationMail {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";
    
    private static final String EMAIL_HOST = "smtp.gmail.com"; // ou autre serveur SMTP
    private static final String EMAIL_USERNAME = "your_email@example.com";
    private static final String EMAIL_PASSWORD = "your_email_password";
    private static final int EMAIL_PORT = 587;

    public static void sendConfirmationMail(int client1Id, int client2Id) {
        // Récupération des adresses e-mail des deux clients à partir de la base de données
        String client1Email = getEmailById(client1Id);
        String client2Email = getEmailById(client2Id);
        
        // Envoi d'un e-mail de confirmation à chaque client
        sendMail(client1Email);
        sendMail(client2Email);
    }

    private static String getEmailById(int userId) {
        String email = null;
        try {
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Requête SQL pour récupérer l'adresse e-mail de l'utilisateur avec l'ID correspondant
            String sql = "SELECT email FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            // Récupération de l'adresse e-mail de l'utilisateur
            if (result.next()) {
                email = result.getString("email");
            }
            
            // Fermeture de la connexion à la base de données
            result.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }
    
    private static void sendMail(String to) {
        try {
            // Création de la session pour envoyer le mail
            Properties props = new Properties();
            props.put("mail.smtp.host", EMAIL_HOST);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", EMAIL_PORT);
            
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });
            
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Confirmation d'échange");
            message.setText("Bonjour,\n\nVotre échange a bien été enregistré.");
            
            // Envoi du message
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erreur lors de l'envoi de l'email de confirmation.").showAndWait();
        }
    }
}
