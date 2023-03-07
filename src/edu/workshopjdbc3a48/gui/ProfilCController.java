/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.services.ServiceUser;
import edu.workshopjdbc3a48.tests.FXmain;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProfilCController implements Initializable {
    private int id_admin;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    private LocalDateTime date_login;
    private LocalDateTime date_logout;
    private int Id_connecté;

    public int getId_connecté() {
        return Id_connecté;
    }

    public void setId_connecté(int Id_connecté) {
        this.Id_connecté = Id_connecté;
    }

    public LocalDateTime getDate_login() {
        return date_login;
    }

    public void setDate_login(LocalDateTime date_login) {
        this.date_login = date_login;
    }

    public LocalDateTime getDate_logout() {
        return date_logout;
    }

    public void setDate_logout(LocalDateTime date_logout) {
        this.date_logout = date_logout;
    }
    @FXML
    private Pane pane;
    @FXML
    private ImageView ImageV;
    @FXML
    private Label nomUser;

    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public void affiche(int id) {
        ServiceUser su = new ServiceUser();
        try {
            Client c = (Client) su.getOneById(id);
            String nomUtilisateur = c.getUsername();
            nomUser.setText(nomUtilisateur);
            byte[] imageData = c.getPhoto();
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
    private void home(ActionEvent event) {
        try {  // Recuperation de l'objet Client a traver son id 
            // Récupération de l'instance de LoginController

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Aceuille.fxml"));
            Parent root = loader.load();

            AceuilleController ac = loader.getController();
            ac.setId_Connector(getId_connecté());
            ac.setDate_login(date_login);
            
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) throws SQLException {
        LocalDateTime logout = LocalDateTime.now();
        setDate_logout(logout);
        Duration duration = Duration.between(date_login, date_logout); // calculer la durée entre les deux temps
        long minutes = duration.toMinutes(); // obtenir la durée en minutes
        int duree = (int) minutes; // convertir la durée en un entier

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment quitter ?");
        //alert.setContentText("La durée sera enregistrée dans la base de données." + duree + "minutes");

        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            // L'utilisateur a confirmé, mettre à jour la durée dans la base de données
            ServiceUser su = new ServiceUser();

            su.updateDuree(Id_connecté, duree);

      
                // crée un objet FXMLLoader qui va permettre de charger les informations de la vue "Login.fxml"
                 try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginSinup.fxml"));

                Parent root = loader.load();
                Scene scene = new Scene(root);
               
                Stage PROFILStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                PROFILStage1.close();
                scene.setFill(Color.TRANSPARENT);
                Stage loginStage = new Stage(StageStyle.TRANSPARENT);
                //FERMER LA STAGE ACTUELLE
              
                loginStage.setScene(scene);
                loginStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXmain.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }
    }

    @FXML
    private void chat(ActionEvent event) throws IOException {
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
        else {
             try {  // Recuperation de l'objet Client a traver son id 
            // Récupération de l'instance de LoginController

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Aceuille.fxml"));
            Parent root = loader.load();

            AceuilleController ac = loader.getController();
            ac.setId_Connector(getId_connecté());
            ac.setDate_login(date_login);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        }
    }

    @FXML
    private void Echange(ActionEvent event) {
        
    }

    private void reclamation(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
            fxml = loader.load();
            ReclamationController rc = loader.getController();
            pane.getChildren().removeAll();
            rc.setId_user(getId_connecté());
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void article(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Article.fxml"));
            fxml = loader.load();
            ArticleController rc = loader.getController();
            pane.getChildren().removeAll();
            rc.setId_user(getId_connecté());
            pane.getChildren().setAll(fxml);
             } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
