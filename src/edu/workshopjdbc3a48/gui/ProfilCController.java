/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilCController implements Initializable {

    private int Id_connecté;
    @FXML
    private ImageView ImageV;
    @FXML
    private Label nomUser;
    @FXML
    private Button setting;
    @FXML
    private Button article;
    @FXML
    private Button home;
    @FXML
    private Button annonce;
    @FXML
    private Button reclamation;
    @FXML
    private Button message;
    @FXML
    private Button exit;
    @FXML
    private Label LabeStatus;

    public int getId_connecté() {
        return Id_connecté;
    }

    public void setId_connecté(int Id_connecté) {
        this.Id_connecté = Id_connecté;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setting.setOnAction(event -> {
            // Afficher le formulaire de modification de profil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
            Parent root;
            try {
                root = loader.load();
                ModifierUserController controller = loader.getController();
                controller.setId_user(Id_connecté);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfilCController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void affiche(int id) {
        ServiceUser su = new ServiceUser();
        try {
            Client c = (Client) su.getOneById(Id_connecté);
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
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == setting) {
            LabeStatus.setText("Settings");

        }
        if (event.getSource() == message) {
            LabeStatus.setText("messages");
        }
        if (event.getSource() == reclamation) {
            LabeStatus.setText("reclamation");
        }
        if (event.getSource() == annonce) {
            LabeStatus.setText("annonce");
        }
        if (event.getSource() == article) {
            LabeStatus.setText("articles");
        }
    }

    /* public void afficher(int id) throws SQLException {
      
          ServiceUser su = new ServiceUser();
Client c = (Client) su.getOneById(Id_connecté);
byte[] imageData = c.getPhoto();

Image image = null;
if (imageData != null) {
    try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
        image = new Image(inputStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Set the Image as the image for your imageUser component
imageUser.setImage(image);
       }*/

 /*    exit.setOnMouseClicked(event -> {
        try {
            // crée un objet FXMLLoader qui va permettre de charger les informations de la vue "Login.fxml" 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            //charger l'interface graphique (Parent rootU)
            Parent rootU = loader.load();
            //crée une nouvelle scène (Scene sceneU)
            Scene sceneU = new Scene(rootU);
            //récupère l'objet Stage correspondant à la fenêtre principale
            Stage appStage = (Stage) exit.getScene().getWindow();
            //remplacer la scene 
            appStage.setScene(sceneU);
            //voir la nouvelle scene
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    private void supprimer(ActionEvent event) {
        ServiceUser su = new ServiceUser();
        Alert alertName = new Alert(Alert.AlertType.ERROR, "Voulez vous supprimer cette compte !");
                    Optional<ButtonType> resultSupression = alertName.showAndWait();
                    if (resultSupression.isPresent() && resultSupression.get() == ButtonType.OK) {
                      try {
                           su.supprimer(Id_connecté);
                           Alert succes = new Alert(Alert.AlertType.INFORMATION, "compte supprimé !");
                           succes.show();
                          } catch (SQLException ex) {
                          System.out.println(ex.getMessage());
                          }
                       
                    }
    }*/
}
