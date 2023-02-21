/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ChoiceBox;
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
    private TextField photo;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField phoneNumber;

    private String[] types={"Client","Transporteur"};
    @FXML
    private Button exit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       type.getItems().addAll(types);
    }    

    @FXML
    private void ajouterUser(ActionEvent event) {
         
            try {
                
                try {
                    ServiceUser su = new ServiceUser();
                    int phoneNumb = Integer.parseInt(phoneNumber.getText());
                    User u = new Admin(username.getText(), pasworrd.getText(), email.getText(), photo.getText(), type.getValue(), phoneNumb);
                    su.ajouter(u);
                  
                    if (u instanceof Admin){
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("admin ajouté avec succés! ");
                        alert1.show();
                    }
                    else if (u instanceof Client){
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("Client aouté avec succés!");
                        alert2.show();
                    }
                    else {
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setContentText("Transporteur aouté avec succés!");
                        alert3.show();
                    }
                    
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ex.getMessage());
                    alert.show();
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent rootU = loader.load();
                Scene sceneU = new Scene(rootU);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(sceneU);
                appStage.show();
                
            } catch (IOException ex) {
                Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
      }

    @FXML
    private void exit(ActionEvent event) {
  
        try{
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
  
    

