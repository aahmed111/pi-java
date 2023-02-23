/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Optional;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class UserInscriptionController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pasworrd;
    @FXML
    private TextField email;
    @FXML
    private Button ajouter;

    @FXML
    private ChoiceBox<String> type;
   
    @FXML
    private TextField phoneNumber;

    private String[] types = {"Client", "Transporteur"};
    @FXML
    private Button exit;
    @FXML
    private TextField adresse;
    @FXML
    private ImageView ImageV;
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll(types);
    }

    @FXML
    private void browser(ActionEvent event) {
        //afficher une boîte de dialogue de sélection de fichiers
        FileChooser fileChooser = new FileChooser();
        //retourne le fichier selectionnee
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            try {
                // crée un nouvel objet "Image" à partir de l'URI (Uniform Resource Identifier) 
                Image photo = new Image(file.toURI().toString());
                ImageV.setImage(photo);
                //lire selon un tableau de bits ,Files qui a generer ce traitement 
                bytes = Files.readAllBytes(file.toPath());
                setBytes(bytes);
            } catch (IOException ex) {
                Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isEmpty(){
        boolean result = true;
             if  (username.getText().length()==0)  {
               result =false;        
             }  
              if  (pasworrd.getText().length()==0)  {
               result =false; 
              } if  (email.getText().length()==0)  {
               result =false; 
              }
               if  (adresse.getText().length()==0)  {
               result =false; 
               }
                if  (phoneNumber.getText().length()==0)  {
               result =false; 
                }
                 if  (username.getText().length()==0)  {
                     
               result =false; 
           }
        return result ;
    }
    
    @FXML
    private void ajouterUser(ActionEvent event) {
    
       if (isEmpty()==true){
        /* if(type.getValue().equals("")){
            Alert alertName = new Alert(Alert.AlertType.ERROR, "veuiller selectionner le type");
            alertName.show();
          }else{*/
            try{
                ServiceUser su = new ServiceUser();
                 String typeUser = type.getValue();
                 String mail = email.getText();
                 int phoneNumb = Integer.parseInt(phoneNumber.getText());
                 if (su.isValidEmailAddress(mail)==true) {
                    
                    if (su.verifier(username.getText(), pasworrd.getText()) == false) {
                   
                    
                       if (typeUser.equals("Client")) {
                        
                        User u = new Client(username.getText(), pasworrd.getText(), email.getText(), bytes, typeUser, phoneNumb, 0, adresse.getText());

                        su.ajouter(u);

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("client ajouté avec succés! ");
                        alert1.show();
                        
                        } else {
                        User u2 = new Transporteur(username.getText(), pasworrd.getText(), email.getText(), bytes, typeUser, phoneNumb, 0,adresse.getText(), null, 0);
                        su.ajouter(u2);
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setContentText("Transporteur ajouté avec succés!");
                        alert3.show();
                        }
                    
                        try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                        Parent rootU;

                        rootU = loader.load();

                        Scene sceneU = new Scene(rootU);
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appStage.setScene(sceneU);
                        appStage.show();
                        } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        }
                   
                    }else {
                    
                    Alert alertName = new Alert(Alert.AlertType.ERROR, "compte exist !");
                    Optional<ButtonType> resultName = alertName.showAndWait();
                    if (resultName.isPresent() && resultName.get() == ButtonType.OK) {
                        username.setText("");
                        pasworrd.setText(""); 
                           }
                     }
                      }else{
                    Alert alerttMail = new Alert(Alert.AlertType.ERROR, "email invalide !");
                    alerttMail.show();
                 }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
         // }
       }
    else{
        Alert alertd = new Alert(Alert.AlertType.ERROR, "veuiller remplir votres donnée pour ajouter un compte");
        alertd.show();
        }
       
    }
    

        @FXML
        private void exit (ActionEvent event ) {
        try {
                // crée un objet FXMLLoader qui va permettre de charger les informations de la vue "Login.fxml"
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                //charger l'interface graphique (Parent rootU)
                Parent rootU = loader.load();
                //crée une nouvelle scène (Scene sceneU)
                Scene sceneU = new Scene(rootU);
                //récupère l'objet Stage correspondant à la fenêtre principale
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //remplacer la scene 
                appStage.setScene(sceneU);
                //voir la nouvelle scene
                appStage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
