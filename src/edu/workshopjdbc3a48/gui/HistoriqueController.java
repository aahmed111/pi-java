/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceEchange;
import edu.workshopjdbc3a48.services.ServiceReclamation;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class HistoriqueController implements Initializable {

    @FXML
    private VBox v;
    @FXML
    private Label labelEchange;
    @FXML
    private Button annuler;
    @FXML
    private Button supprimer;
    @FXML
    private Button reclamation;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
    
    }    
   
     public void ajouter(Echange e ,int id_user){
         ServiceEchange se = new ServiceEchange();
         String label = e.toString();
         labelEchange.setText(label);
         
         annuler.setOnAction(event -> {
                if (e.getStatut().equals("en attente")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation d'annulation");
                    alert.setHeaderText("Voulez-vous vraiment annuler cet échange ?");
                    alert.setContentText("Cet échange sera supprimé de la liste.");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            se.supprimer(1);
                         //   hbox.setVisible(false);
                        }
                    });
                }
            });
          supprimer.setOnAction(event -> {
                if (e.getStatut().equals("terminé")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation de suppression");
                    alert.setHeaderText("Voulez-vous vraiment supprimer cet échange ?");
                    alert.setContentText("Cet échange sera définitivement supprimé de la liste.");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            se.supprimer(1);
                           // hbox.setVisible(false);
                        }
                    });
                }
            }); 
           reclamation.setOnAction(event -> {
               
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation de L'envoie");
                    alert.setHeaderText("Voulez-vous vraiment envoyer une reclamation?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                                                                           
                               try {
                                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                                    Parent  root = fxml.load();
                                    ReclamationController rc =fxml.getController();
                                        rc.setId_user(id_user);
                                        rc.setId_echange(e.getId_echange());
                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setScene(scene);
                                        stage.show();
                                       
                                   } catch (IOException ex) {
                                    Logger.getLogger(HistoriqueController.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                        }
                    });
                
            }); 
     }
     
    
}
