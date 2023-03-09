/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceEchange;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import edu.workshopjdbc3a48.services.ServiceReclamation;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.description;

public class ReclamationController implements Initializable {
  private int id_user;
     public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    private int id_echange ;

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
    }

    

    @FXML
    private TextField textObject;
    @FXML
    private TextField Email;
    @FXML
    private TextArea textDescription;
    // lblTitre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");       // lblTitre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    //  textObject.setStyle("-fx-background-color: white ;");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Email.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Le champ a perdu le focus
                String email = Email.getText();
                if (email.isEmpty()) {
                    Email.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer votre adresse email");
                    alert.showAndWait();
                } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    Email.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer une adresse email valide");
                    alert.showAndWait();
                } else {
                    Email.setStyle("-fx-background-color: white; -fx-border-color: green;");
                }
            }
        });
        textDescription.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // Le champ a perdu le focus
                String description = textDescription.getText();
                if (description.isEmpty()) {
                    textDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer une description");
                    alert.showAndWait();
                } else if (description.split("\\s+").length < 9) {
                    textDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("La description doit contenir au moins 9 mots");
                } else {
                    textDescription.setStyle("-fx-background-color: white; -fx-border-color: green;");
                }

            }

        });
          textObject.focusedProperty().addListener((observable, oldValue, newValue) -> {
           if (!newValue) { // Le champ a perdu le focus
                String objet = textObject.getText();
                if (objet.isEmpty()) {
                    textObject.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer votre objet");
                    alert.showAndWait();
                } else if (!objet.matches("^[a-zA-Z\\s]{3,}$")) {
                    textObject.setStyle("-fx-background-color: white; -fx-border-color: red;");
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Le objet doit contenir uniquement des lettres et avoir au moins 3 caractères");
                    alert.showAndWait();
                } else {
                    textObject.setStyle("-fx-background-color: white; -fx-border-color: green;");
                }
           }
         });
    }                  


    
    //     txtEmail.setPromptText("Veuillez entrer votre adresse email");
    //     txtEmail.setStyle("-fx-background-color: white ;");

  
    //  txtDescription.setPromptText("Veuillez entrer une description");
    //    txtDescription.setStyle("-fx-background-color: white ;");
    //  btnSoumettre.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    // ajouter le button annuler On click fermer la fenetre 
    //    btnAnnuler.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
    @FXML
    private void soumettre(ActionEvent event) {
        String objet = textObject.getText();
        String email = Email.getText();
        String Description = textDescription.getText();
        boolean isValid = true;

        // Vérifier le champ "objet"
        if (objet.isEmpty() || !objet.matches("^[a-zA-Z\\s]{3,}$")) {
            textObject.setStyle("-fx-background-color: white; -fx-border-color: red;");
            isValid = false;
        } else {
            textObject.setStyle("-fx-background-color: white; -fx-border-color: green;");
        }

        // Vérifier le champ "Email"
        if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Email.setStyle("-fx-background-color: white; -fx-border-color: red;");
            isValid = false;
        } else {
            Email.setStyle("-fx-background-color: white; -fx-border-color: green;");
        }

        // Vérifier le champ "Description"
        if (Description.isEmpty() || Description.split("\\s+").length < 9) {
            textDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
            isValid = false;
        } else {
            textDescription.setStyle("-fx-background-color: white; -fx-border-color: green;");
        }

        if (isValid) {
            try {
                ServiceUser su = new ServiceUser();
                User u = su.getOneById( getId_user());
                ServiceEchange se = new ServiceEchange();
                Reclamation t = new Reclamation(u ,objet,Description,se.getOneById(id_echange));
                ServiceReclamation Rs = new ServiceReclamation();

                Rs.ajouter(t);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Réclamation envoyée");
                alert.setHeaderText(null);
                alert.setContentText("Votre réclamation a été envoyée avec succès!");

                alert.showAndWait();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement.");
            alert.showAndWait();
        }

    }

    @FXML
    private void annuler(ActionEvent event) {

    }

}
