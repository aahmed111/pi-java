/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

 



public class ProfilTransporteurController implements Initializable {
            
    @FXML
    private Button exit;
        
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
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
    
}
