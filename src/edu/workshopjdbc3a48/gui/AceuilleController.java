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
import javafx.stage.Stage;

public class AceuilleController implements Initializable {

    private int Id_Connector ;

    public int getId_Connector() {
        return Id_Connector;
    }

    public void setId_Connector(int Id_Connector) {
        this.Id_Connector = Id_Connector;
    }

    
    
   
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
    }
   

    @FXML
    private void goToProfil(ActionEvent event) throws SQLException {
        try {  // Recuperation de l'objet Client a traver son id 
            // Récupération de l'instance de LoginController
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
            Parent root = loader.load();
           
            ProfilCController pc = loader.getController();
            pc.setId_connecté(Id_Connector);
            pc.affiche(Id_Connector);
            
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

}
