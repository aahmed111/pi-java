/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceEchange;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListAnnonceArticleController implements Initializable {

     private int id_User;
    @FXML
    private HBox listArticle;

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int Id_User) {
        this.id_User = Id_User;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ServiceArticle se = new ServiceArticle();
        List<Article> listA = se.getAll();

        for (Article a :listA) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListArticle.fxml"));
                HBox h = loader.load();
                ListArticleController hc = loader.getController();
                hc.ajouter(a);
                listArticle.getChildren().add(h);
            } catch (IOException ex) {
                Logger.getLogger(HistoriqueEchangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }    

    @FXML
    private void ajoutService(ActionEvent event) {
    }
    
}
