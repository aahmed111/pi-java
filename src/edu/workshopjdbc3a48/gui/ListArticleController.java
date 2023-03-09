/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceEchange;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListArticleController implements Initializable {

    @FXML
    private Label nomArticle;
    @FXML
    private Label note;
    @FXML
    private HBox produit;
    @FXML
    private ImageView imageProduit;
    @FXML
    private Button editer;
    @FXML
    private Button supprimer;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void ajouter(Article a) {
       
        
            ServiceArticle sa = new ServiceArticle();
            String articleName = a.getNom();
            String desc = a.getDescription();
            int Note = a.getEstimation();
            byte[] bytes = a.getImage();
            nomArticle.setText(articleName);
            String not = String.valueOf(Note);
            note.setText(not);
            if (bytes != null) {
                try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
                    Image image = new Image(inputStream);
                    imageProduit.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*   editer.setOnAction (event -> {
              
            });   */
            supprimer.setOnAction(event -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Voulez-vous vraiment supprimer cet Article ?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        sa.supprimer(a.getId_article());
                        // hbox.setVisible(false);
                    }
                });

            });

        }
    
}
