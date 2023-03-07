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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ArticleController implements Initializable {
    private int id_user;
    @FXML
    private Pane panel;
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
     
      private Parent p ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutproduit(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("captcha.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait(); // Attendre que l'utilisateur ait validé le captcha avant de poursuivre

        // vérifier si le captcha est valide
        boolean captchaValid = true; // TODO: remplacer par la validation réelle du captcha

        if (captchaValid) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
            p = loader.load();
            ProduitController rc = loader.getController();
            panel.getChildren().removeAll();
            rc.setId_user(getId_user());
            panel.getChildren().setAll(p);
             } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        } }else {
            // afficher un message d'erreur
            System.out.println("Captcha invalide");

            // fermer la fenêtre du captcha
            stage.close();

            // afficher un message d'erreur
            System.out.println("Captcha invalide");

            // charger la scène "produitservice.fxml" dans une nouvelle fenêtre
            root = FXMLLoader.load(getClass().getResource("Article.fxml"));
            scene = new Scene(root);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.show();
        }
        
    }

    @FXML
    private void ajoutservice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("captcha.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait(); // Attendre que l'utilisateur ait validé le captcha avant de poursuivre

        // vérifier si le captcha est valide
        boolean captchaValid = true; // TODO: remplacer par la validation réelle du captcha

        if (captchaValid) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Service.fxml"));
            p = loader.load();
            ServiceController rc = loader.getController();
            panel.getChildren().removeAll();
            rc.setId_user(id_user);
            panel.getChildren().setAll(p);
             } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }else {
            // afficher un message d'erreur
            System.out.println("Captcha invalide");

            // fermer la fenêtre du captcha
            stage.close();

            // afficher un message d'erreur
            System.out.println("Captcha invalide");

            // charger la scène "produitservice.fxml" dans une nouvelle fenêtre
            root = FXMLLoader.load(getClass().getResource("Article.fxml"));
            scene = new Scene(root);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.show();
        }
    }
   
    
}
