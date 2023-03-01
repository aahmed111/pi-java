/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Reclamation;
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
import java.sql.SQLException;
 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;




public class EnvoyeeReclamation extends Application {

     
    @Override
    public void start(Stage primaryStage) {

        // Création des éléments de l'interface graphique
        Label lblTitre = new Label("Envoyer_Reclamation");
        lblTitre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            
 // Nom
     Label lblNom = new Label("Nom :");
    TextField txtNom = new TextField();
    txtNom.setPromptText("Veuillez entrer votre nom complet");
    txtNom.setStyle("-fx-background-color: white ;");
    txtNom.focusedProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue) { // Le champ a perdu le focus
        String nom = txtNom.getText();
        if (nom.isEmpty()) {
            txtNom.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer votre nom complet");
            alert.showAndWait();
        } else if (!nom.matches("^[a-zA-Z]{3,}$")) {
            txtNom.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nom doit contenir uniquement des lettres et avoir au moins 3 caractères");
            alert.showAndWait();
        } else {
            txtNom.setStyle("-fx-background-color: white; -fx-border-color: green;");
        }
    }
});

// Email
  Label lblEmail = new Label("Email :");
  TextField txtEmail = new TextField();
   txtEmail.setPromptText("Veuillez entrer votre adresse email");
   txtEmail.setStyle("-fx-background-color: white ;");
   txtEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue) { // Le champ a perdu le focus
        String email = txtEmail.getText();
        if (email.isEmpty()) {
            txtEmail.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer votre adresse email");
            alert.showAndWait();
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            txtEmail.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse email valide");
            alert.showAndWait();
        } else {
            txtEmail.setStyle("-fx-background-color: white; -fx-border-color: green;");
        }
    }
});

// Description
    Label lblDescription = new Label("Description :");
    TextArea txtDescription = new TextArea();
    txtDescription.setPromptText("Veuillez entrer une description");
    txtDescription.setStyle("-fx-background-color: white ;");
    txtDescription.focusedProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue) { // Le champ a perdu le focus
        String description = txtDescription.getText();
        if (description.isEmpty()) {
            txtDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une description");
            alert.showAndWait();
        } else if (description.split("\\s+").length < 9) {
            txtDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 9 mots");
            } else {
             txtDescription.setStyle("-fx-background-color: white; -fx-border-color: green;");}

             }
             });
            







    
        



Button btnSoumettre = new Button("Soumettre");
btnSoumettre.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
btnSoumettre.setOnAction(e -> {
try{
String nom = txtNom.getText();
String Email = txtEmail.getText();
String Description = txtDescription.getText();
boolean isValid = true;
        
          // Vérifier le champ "Nom"
    if (nom.isEmpty() || !nom.matches("^[a-zA-Z]{3,}$")) {
        txtNom.setStyle("-fx-background-color: white; -fx-border-color: red;");
        isValid = false;
    } else {
        txtNom.setStyle("-fx-background-color: white; -fx-border-color: green;");
    }

    // Vérifier le champ "Email"
    if (Email.isEmpty() || !Email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        txtEmail.setStyle("-fx-background-color: white; -fx-border-color: red;");
        isValid = false;
    } else {
        txtEmail.setStyle("-fx-background-color: white; -fx-border-color: green;");
    }

    // Vérifier le champ "Description"
    if (Description.isEmpty() || Description.split("\\s+").length < 9) {
        txtDescription.setStyle("-fx-background-color: white; -fx-border-color: red;");
        isValid = false;
    } else {
        txtDescription.setStyle("-fx-background-color: white; -fx-border-color: green;");
    }

    if (isValid) {
        try {
            Reclamation t = new Reclamation(nom, Description, Email);
            ServiceReclamation Rs = new ServiceReclamation();

            Rs.ajouter(t);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Réclamation envoyée");
            alert.setHeaderText(null);
            alert.setContentText("Votre réclamation a été envoyée avec succès!");

            alert.showAndWait();

            Stage stage = (Stage) btnSoumettre.getScene().getWindow();
            stage.close();
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
} catch (Exception ex) {
    ex.printStackTrace();
}
});


         
        
        
      // ajouter le button annuler On click fermer la fenetre 
      Button btnAnnuler = new Button("Annuler");
      btnAnnuler.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
      btnAnnuler.setOnAction(e -> {
      primaryStage.close();});
 
             
      
                
        HBox hboxBtns = new HBox(10);
        hboxBtns.setAlignment(Pos.CENTER);
        hboxBtns.getChildren().addAll(btnSoumettre, btnAnnuler);

        VBox vboxForm = new VBox(10);
        vboxForm.getChildren().addAll(lblNom, txtNom, lblEmail, txtEmail, lblDescription, txtDescription, hboxBtns);
        vboxForm.setPadding(new Insets(20));
        vboxForm.setStyle("-fx-background-color: #f2f2f2; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vboxForm);
        borderPane.setTop(lblTitre);
        BorderPane.setAlignment(lblTitre, Pos.CENTER);
        borderPane.setPadding(new Insets(20));

        Scene scene = new Scene(borderPane, 500, 400);
        scene.setFill(Color.WHITE);

        // Affichage de l'interface graphique
        primaryStage.setTitle("Gestion des réclamations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

        
        
        
        
        
        
        
        
           
        
        
        
        
        
        
        

      

       
        
        
        
        
        
        
        
        
        
        
