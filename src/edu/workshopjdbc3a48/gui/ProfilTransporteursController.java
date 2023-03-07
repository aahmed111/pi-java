/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilTransporteursController implements Initializable {
    private int id_admin ;
 private int Id_connecté;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_connecté() {
        return Id_connecté;
    }

    public void setId_connecté(int Id_connecté) {
        this.Id_connecté = Id_connecté;
    }

    @FXML
    private ImageView ImageV;
    @FXML
    private Label nomUser;
    @FXML
    private Pane pane;
   
     private Parent fxml;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
            fxml = loader.load();
            ChatController mc = loader.getController();
            pane.getChildren().removeAll();
            mc.setId_user(getId_connecté());
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void affiche(int id) {
        ServiceUser su = new ServiceUser();
        try {
            Transporteur t =  (Transporteur) su.getOneById(id);
            String nomUtilisateur = t.getUsername();
            nomUser.setText(nomUtilisateur);
            byte[] imageData = t.getPhoto();
            if (imageData != null) {
                try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
                    Image image = new Image(inputStream);
                    ImageV.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void agence(ActionEvent event) {
    }

     @FXML
    private void setting(ActionEvent event) throws IOException, SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateClient.fxml"));
            fxml = loader.load();
            UpdateClientController mc = loader.getController();
            
            mc.setId_user(getId_connecté());
            mc.initialiseText(Id_connecté);
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chat(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
        if (id_admin!=0){
             try {  // Recuperation de l'objet Client a traver son id 
            // Récupération de l'instance de LoginController

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilAdmin.fxml"));
            Parent root = loader.load();
            ProfilAdminController ac = loader.getController();
            ac.setId_admin(id_admin);
          
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
       
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        }
        
        }
    
}
