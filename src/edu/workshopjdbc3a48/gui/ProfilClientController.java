/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilClientController implements Initializable {
    
     @FXML
     private TextField msg;
     
 private int Id_connecté ;

    public int getId_connecté() {
        return Id_connecté;
    }

    public void setId_connecté(int Id_connecté) {
        this.Id_connecté = Id_connecté;
    }

 
  public void afficher(int id ) {
         try {
             ServiceUser su = new ServiceUser();
             Client c =  (Client) su.getOneById(Id_connecté);
             msg.setText(c.getUsername());
         } catch (SQLException ex) {
             Logger.getLogger(ProfilClientController.class.getName()).log(Level.SEVERE, null, ex);
         }
  
  }
   
     
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
             
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

    @FXML
    private void ajouterArticle(ActionEvent event) {
        
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
