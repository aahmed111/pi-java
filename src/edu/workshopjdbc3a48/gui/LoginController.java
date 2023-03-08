/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;


import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.jms.MessageProducer;
import javax.jms.Session;


public class LoginController implements Initializable {

   
  
   
    
    private LocalDateTime date_login;

    public LocalDateTime getDate_login() {
        return date_login;
    }

    public void setDate_login(LocalDateTime date_login) {
        this.date_login = date_login;
    }
    private int id_Connector;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private Button forget;
    @FXML
    private Label message;
    private Parent fxml;
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public int getId_Connector() {
        return id_Connector;
    }

    public void setId_Connector(int id_Connector) {
        this.id_Connector = id_Connector;
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
    private void login(ActionEvent event) throws IOException, Exception {

        String USERNAME = username.getText();
        String PASSWORD = password.getText();

        String keyHex = "00112233445566778899AABBCCDDEEFF"; // la clé AES en hexadécimal
        String encryptedPassword = encrypt(PASSWORD, keyHex);
        boolean found = false;

        try {
            /* Connection cnx = DataSource.getInstance().getCnx();
           
            String req = "SELECT `username`,`password`,`type` FROM `user` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);*/
            ServiceUser su = new ServiceUser();
            List<User> list = su.getAll();

            for (User u : list) {

                String typeUser = u.getType();

              if (su.checkBlocked(USERNAME) == false) {
                if ((u.getUsername().equals(USERNAME)) && (u.getPassword().equals(encryptedPassword))) {
                    found = true;
                    username.setStyle("-fx-border-color: none;");
                    password.setStyle("-fx-border-color: none;");
                    switch (typeUser) {

                        case "Admin":
                            Admin adminLogin = su.getAdminByUsername(USERNAME);
                            id_Connector = adminLogin.getId_user();
                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ProfilAdmin.fxml"));
                            Parent root1 = loader1.load();
                            ProfilAdminController pa = loader1.getController();
                            pa.setId_admin(id_Connector);
                            Stage loginStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            loginStage1.close();
                            Scene scene1 = new Scene(root1);
                            Stage adminStage = new Stage();
                            adminStage.setScene(scene1);
                            adminStage.show();
                            break;

                        case "Transporteur":
                            Transporteur loginTransporteur = su.getTransporteurByUsername(USERNAME);
                            id_Connector = loginTransporteur.getId_user();
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ProfilTransporteurs.fxml"));
                            Parent root2 = loader2.load();
                            ProfilTransporteursController pr = loader2.getController();
                            pr.setId_connecté(id_Connector);
                            Stage loginStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            loginStage2.close();
                            Scene scene2 = new Scene(root2);
                            Stage appStage2 = new Stage();

                            appStage2.setScene(scene2);
                            appStage2.show();
                            break;
                        case "Client":
                            try {
                                Client loginClient = su.getClientByUsername(USERNAME);
                                LocalDateTime now = LocalDateTime.now();
                                id_Connector = loginClient.getId_user();
                                setDate_login(now);
                                // Chargement du fichier FXML du l'aceuille
                                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Aceuille.fxml"));
                                Parent root3 = loader3.load();

                                AceuilleController ac = loader3.getController();
                                ac.setDate_login(date_login);
                                ac.setId_Connector(id_Connector);                
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                loginStage.close();
                                //  alert.setContentText("votre date de connexion." + date_login);
                                // alert.show();
                                // Affichage de la page du profil client
                                Scene scene3 = new Scene(root3);
                                Stage appStage3 = new Stage();
                                appStage3.setScene(scene3);
                                appStage3.show();
                                break;
                            } catch (IOException | IllegalStateException e) {
                                e.printStackTrace();
                            }
                    }

                }
                if (found == false) {
                    message.setText("username or password is wrong");
                    username.setText("");
                    username.setStyle("-fx-border-color: red;");
                    password.setText("");
                    password.setStyle("-fx-border-color: red;");
                    su.UpdateUserErreur(username.getText());
                }

                   } else {
                        Alert alert3 = new Alert(Alert.AlertType.WARNING);
                        alert3.setContentText("votre compte est bloqué");
                        alert3.showAndWait();
                        alert3.close();
                        break;
                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forget(ActionEvent event) throws IOException {

        try {
            fxml = FXMLLoader.load(getClass().getResource("EmailCheck.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
