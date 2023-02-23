/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

  



public class ProfilTransporteurController implements Initializable {
            
    @FXML
    private Button exit;
        
    private int id_Transporteur ;
    @FXML
    private TextField msg;
    @FXML
    private ChoiceBox<String> etat;
    

    public int getId_Transporteur() {
        return id_Transporteur;
    }

    public void setId_Transporteur(int id_Transporteur) {
        this.id_Transporteur = id_Transporteur;
    }
    
     private String[] etats = {"Diponible", "InDisponible", "Arrivé ","En route"};
     
    
    public void afficher(int id ) {
         try {
             ServiceUser su = new ServiceUser();
             Transporteur t =  (Transporteur) su.getOneById(id_Transporteur);
             msg.setText(t.getUsername());
         } catch (SQLException ex) {
             Logger.getLogger(ProfilClientController.class.getName()).log(Level.SEVERE, null, ex);
         }
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        etat.getItems().addAll(etats);
          
    }    

    @FXML
    private void exit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent rootU = loader.load();
            Scene sceneU = new Scene(rootU);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(sceneU);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilTransporteurController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }

    @FXML
    private void supprimerCompte(ActionEvent event) {
    }

    @FXML
    private void modifierCompte(ActionEvent event) {
    }
    
}
