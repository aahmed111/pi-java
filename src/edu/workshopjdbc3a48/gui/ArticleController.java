/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Service;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceEchange;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    @FXML
    private HBox listArticle;
    @FXML
    private HBox listService;
    @FXML
    private VBox vBox;
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
     
      private Parent p ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            ServiceArticle se = new ServiceArticle();
            Article a = se.getOneById(110);
            
            
         
                if (a instanceof Produit){
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListArticle.fxml"));
                        VBox hb = loader.load();
                        ListArticleController listArt = loader.getController();
                        // = new Label(a.toString());
                        listArt.ajouter(a);
                        listArticle.getChildren().add(hb);
                    } catch (IOException ex) {
                        Logger.getLogger(HistoriqueEchangeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*  else{
                Service s = (Service) a;
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListService.fxml"));
                HBox hb = loader.load();
                ListArticleController listArt = loader.getController();
                listArt.ajouter(s);
                listArticle.getChildren().add(hb);
                } catch (IOException ex) {
                Logger.getLogger(HistoriqueEchangeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }*/
            
     
    }    

    @FXML
    private void ajoutproduit(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("captchaa.fxml"));
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

    private void ajoutservice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("captchaa.fxml"));
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
