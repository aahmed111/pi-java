/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.utils;


import edu.projetpi.entities.Article;
import edu.projetpi.entities.Article;
import edu.projetpi.entities.User;
import edu.projetpi.entities.User;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
    static private String smtpHost = "smtp.mailgun.org";
    static private String smtpUser = "postmaster@sandbox85795f02678647fe99988fe7f4af21e3.mailgun.org";
    static private String smtpPassword = "71c26a3e246c1f134d793f9a9af61dd9-52d193a0-0742abfb";

    public static void sendMail(String to, String subject, String body) {
        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpUser, smtpPassword);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(smtpUser));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendSwapConfirmations(User user, User counterparty, Article myArticle, Article otherArticle) {
        // Send confirmation to user
        String subject = "Confirmation d'échange";
        String body = "Bonjour,\n\nVotre échange a bien été enregistré.\n\n"
                + "Vous avez proposé l'article " + myArticle.getDesignation() + " à " + counterparty.getPrenom() + " " + counterparty.getNom() + ".\n"
                + "Vous avez accepté l'article " + otherArticle.getDesignation() + " de " + counterparty.getPrenom() + " " + counterparty.getNom() + ".\n\n"
                + "Cordialement,\n"
                + "L'équipe de ProjetPI";
        sendMail(user.getGmail(), subject, body);

        // Send alert to counterparty
        subject = "Nouvel échange";
        body = "Bonjour,\n\n"
                + "Vous avez reçu une nouvelle proposition d'échange.\n"
                + "Vous avez proposé l'article " + otherArticle.getDesignation() + " à " + user.getPrenom() + " " + user.getNom() + ".\n"
                + "Vous avez accepté l'article " + myArticle.getDesignation() + " de " + user.getPrenom() + " " + user.getNom() + ".\n\n"
                + "Cordialement,\n"
                + "L'équipe de ProjetPI";
        sendMail(counterparty.getGmail(), subject, body);
    }
}

