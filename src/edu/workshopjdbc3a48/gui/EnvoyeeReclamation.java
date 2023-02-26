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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class EnvoyeeReclamation extends Application {

     
    @Override
    public void start(Stage primaryStage) {

        // Création des éléments de l'interface graphique
        Label lblTitre = new Label("Envoyer_Reclamation");
        lblTitre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        
        
        Label lblNom = new Label("Nom :");
        TextField txtNom = new TextField();
        txtNom.setText("veuillez entrer votre nom complet");    
        txtNom.setStyle("-fx-background-color: lightgrey;");
        txtNom.setOnMouseClicked(e -> {
        txtNom.clear();
        txtNom.setStyle("-fx-background-color: white;");});
       
      
  
        
        
        
        
        Label lblEmail = new Label("Email :");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("votre_Email ");
        txtEmail.setStyle("-fx-background-color: lightgrey;");
        txtEmail.setOnMouseClicked(e -> {
        txtEmail.clear();
        txtEmail.setStyle("-fx-background-color: white;");});


     

        Label lblDescription = new Label("Description :");
        TextArea txtDescription = new TextArea();
        txtDescription.setText("votre_Description ");
        
        txtDescription.setStyle("-fx-background-color: lightgrey;");

        txtDescription.setOnMouseClicked(e -> {
        txtDescription.clear();
        txtDescription.setStyle("-fx-background-color: white;");});
        
        
        
        
        
        
        
        // lorsque je click ici je veut que la reclamation sera afficher dans une fenetre dans le dashboard admin .
        Button btnSoumettre = new Button("Soumettre");
        btnSoumettre.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
         btnSoumettre.setOnAction(e -> {
            try {
                String nom = txtNom.getText();
                String Email = txtEmail.getText();
                String Description = txtDescription.getText();
                
                Reclamation t = new Reclamation (Description,Email);
                ServiceReclamation Rs = new ServiceReclamation ();
                
                Rs.ajouter(t);
            } catch (SQLException ex) {
                
            }
            
            
            
            
            // Créer une alerte avec le message "Réclamation envoyée"
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Réclamation envoyée");
              alert.setHeaderText(null);
              alert.setContentText("Votre réclamation a été envoyée avec succès!");

             // Afficher l'alerte dans une fenêtre modale
              alert.showAndWait();});


         
         
             
      
        
        
        
        
        
        
        
        
        
      // ajouter le button annuler On click fermer la fenetre         
        
        
        
        
        
        
        
        
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
