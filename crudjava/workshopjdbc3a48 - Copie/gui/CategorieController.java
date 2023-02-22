/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceCategorie;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField tfid_categorie;
    @FXML
    private TextField tfdescriptrion;
    @FXML
    private TextField tftype;
    @FXML
    private TextArea tabox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         if (tfdescriptrion.getText().isEmpty() || tftype.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nom ou prenom invalide(s)", ButtonType.OK);
            a.showAndWait();
            } else {
              String description=tfdescriptrion.getText();
        String type=tftype.getText();
        Categorie c=new Categorie(tfdescriptrion.getText(),tftype.getText());
          ServiceCategorie sc = new ServiceCategorie();
          sc.ajouter(c);
         }
         Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Categorie  ajouté avec succés!");
                        alert1.show();
             
         }
       
    

    @FXML
    private void supprimer(ActionEvent event) {
      
String str_id_categorie = tfid_categorie.getText();


int id_categorie = Integer.parseInt(str_id_categorie);

        ServiceCategorie sc = new ServiceCategorie();
     
        sc.supprimer(id_categorie);
         Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("Categorie  supprimer avec succés!");
                        alert2.show();
             
         }
        
       
    

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void getAll(ActionEvent event) {
           List<Categorie> list1 = new ArrayList<>();
           ServiceCategorie sc = new ServiceCategorie();
       list1= sc.getAll();
     tabox.setText(list1.toString());
     Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("Categorie  afficher !");
                        alert2.show();
             
         }
     
    

    @FXML
    private void getOnebyId(ActionEvent event) {
        
        Categorie list2;
        String str_id_categorie = tfid_categorie.getText();
        int id_categorie = Integer.parseInt(str_id_categorie);
        
        ServiceCategorie sc = new ServiceCategorie();
    list2= sc.getOneById(id_categorie);
        tabox.setText(list2.toString());
        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setContentText("Categorie afficher par id avec succés!");
                        alert3.show();
             
         }
        
    }
    

