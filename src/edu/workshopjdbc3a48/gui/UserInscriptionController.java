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
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class UserInscriptionController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pasworrd;
    @FXML
    private TextField email;
    @FXML
    private Button ajouter;

    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField phoneNumber;

    private String[] types = {"Client", "Transporteur"};
    @FXML
    private Button exit;
    @FXML
    private TextField adresse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll(types);
    }

    @FXML
    private void ajouterUser(ActionEvent event) {

   

            try {

                ServiceUser su = new ServiceUser();
                int phoneNumb = Integer.parseInt(phoneNumber.getText());
                String typeUser = type.getValue();
                if (su.verifier(username.getText(), pasworrd.getText()) == false) {

                    if (typeUser.equals("Client")) {

                        User u = new Client(username.getText(), pasworrd.getText(), email.getText(), null, typeUser, phoneNumb, 0, adresse.getText());

                        su.ajouter(u);

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("client ajouté avec succés! ");
                        alert1.show();

                     } else {
                        User u2 = new Transporteur(username.getText(), pasworrd.getText(), email.getText(), null, typeUser, phoneNumb, 0, null, 0);
                        su.ajouter(u2);
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setContentText("Transporteur aouté avec succés!");
                        alert3.show();
                     }
                       //retour a la menu principal login
                       try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                        Parent rootU;
                   
                        rootU = loader.load();
                   
                        Scene sceneU = new Scene(rootU);
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appStage.setScene(sceneU);
                        appStage.show(); 
                      } catch (IOException ex) {
                        Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                } else {

                      Alert alert = new Alert(Alert.AlertType.ERROR, "compte exist !");
                      Optional<ButtonType> result = alert.showAndWait();
                      if (result.isPresent() && result.get() == ButtonType.OK) {
                        username.setText("");
                        pasworrd.setText("");
                        
                      }
                }
            }catch (SQLException ex) {
                  Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
             

            
        }

        @FXML
        private void exit
        (ActionEvent event
        
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
                //remplacer la scene 
                appStage.setScene(sceneU);
                //voir la nouvelle scene
                appStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfilClientController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
