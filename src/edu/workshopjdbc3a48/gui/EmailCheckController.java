/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class EmailCheckController implements Initializable {

    private String password;

    @FXML
    private TextField email;
    private TextField passwordEmail;
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public String decrypt(String ciphertextHex, String keyHex) throws Exception {
        byte[] key = Hex.decode(keyHex);
        byte[] input = Hex.decode(ciphertextHex);
        BlockCipher engine = new AESFastEngine();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(false, new KeyParameter(key));
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);

        return new String(output, "UTF-8");
    }

    public String encrypt(String plaintext, String keyHex) throws Exception {
        byte[] key = Hex.decode(keyHex);
        byte[] input = plaintext.getBytes("UTF-8");

        BlockCipher engine = new AESFastEngine();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(true, new KeyParameter(key));

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);

        return Hex.toHexString(output);
    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLException, MessagingException, Exception {
        String MyEmail = "abdelwahed.souid@esprit.tn";
        String Email = email.getText();
        String motDePasse = "223JMT7042";
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
                String keyHex = "00112233445566778899AABBCCDDEEFF";
                String NewpASS = decrypt(passwordCompte, keyHex);

         /*       Properties props = new Properties();
                props.put("mail.smtp.auth", "true");         
                props.put("mail.smtp.host", "smtp@esprit.tn");
                props.put("mail.smtp.port","578 " );
                props.put("mail.smtp.starttls.enable", true);
                props.put("mail.transport.protocl", "smtp");
                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Email, motDePasse);
                    }
                });
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Email ));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getText()));
                message.setSubject("Confirmation d'inscription");
                message.setText("Bonjour,\n"
                          + "Votre inscription a été confirmée. Veuillez trouver ci-dessous votre mot de passe de connexion dans l'application swapify :\n"
                          + NewpASS);
                
                Transport.send(message);  */
                Alert alertName = new Alert(Alert.AlertType.CONFIRMATION, "votre mt de passe est :" + NewpASS);

                alertName.show();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSinup.fxml"));
                    Parent rootU = loader.load();
                    Scene sceneU = new Scene(rootU);
                    Node node = (Node) event.getSource();
                    Stage appStage = (Stage) node.getScene().getWindow();
                    sceneU.setFill(Color.TRANSPARENT);
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
