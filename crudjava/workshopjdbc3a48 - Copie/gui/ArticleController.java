package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Services;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Services;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class ArticleController implements Initializable {

    @FXML
    private AnchorPane layout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnajouter1;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfproprietaire;
    @FXML
    private TextField tfestimation;
    private TextField tftype_produit;
    private TextField tftype_article;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfimage;
    private TextField tftype_service;
    @FXML
    private TextArea tabox;
    @FXML
    private Button btnbyid;
    @FXML
    private TextField tftype_produit1;
    @FXML
    private TextField tfimage1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    Article a;

    @FXML
    private void modifier(javafx.event.ActionEvent event) {

    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) {
        String str_id_article = tfid.getText();

        int id_article = Integer.parseInt(str_id_article);

        ServiceArticle sc = new ServiceArticle();

        sc.supprimer(id_article);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("Article   supprimer avec succés!");
        alert2.show();

    }

    @FXML
    private void ajouter(javafx.event.ActionEvent event) throws SQLException {

        String str_estimation = tfestimation.getText();
        int estimation = Integer.parseInt(str_estimation);
         String description=tfdescription.getText();
        String image=tfimage.getText();
         String proprietaire=tfproprietaire.getText();
       
      String type_article=tftype_article.getText();
   String type_produit=tftype_produit.getText();
 
   String  type_service=tftype_service.getText();
      

     
            
                    ServiceArticle su = new ServiceArticle();
                  
                        Article p = new Produit(tfdescription.getText(), tfimage.getText(), tfproprietaire.getText(), estimation, tftype_produit.getText());
                  Article s = new Services(tfdescription.getText(), tfimage.getText(), tfproprietaire.getText(), estimation, tftype_service.getText());

                  
                    if (p instanceof Produit){
                    su.ajouter(p);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("produit ajouté avec succés! ");
                        alert1.show();
                    }
                    else if (p instanceof Services){
                           su.ajouter(s);
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("service aouté avec succés!");
                        alert2.show();
                    }
                    
                    
                
              
            }
 
        
      
    
    
    


@FXML
        private void getAll(javafx.event.ActionEvent event) {
           List<Article> list2 = new ArrayList<>();
           ServiceArticle sc = new ServiceArticle();
       list2= sc.getAll();
     tabox.setText(list2.toString());
     
    }

    @FXML
        private void getOnebyid(javafx.event.ActionEvent event) {
       Article list1;
        String str_id_article= tfid.getText();
        int id_article = Integer.parseInt(str_id_article);
        
        ServiceArticle sc = new ServiceArticle();
    list1= sc.getOneById(id_article);
        tabox.setText(list1.toString());
        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setContentText("Article afficher par id avec succés!");
                        alert3.show();
             
         }
}
