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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ModifierUserController implements Initializable {
   @FXML
    private AnchorPane ty;
    @FXML
    private TextField username;
    @FXML
    private TextField pasworrd;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private ImageView ImageV;

    private int id_user;
     private byte[] bytes;
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    private String typeUser;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exit(ActionEvent event) {
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

    @FXML
    private void modifierProfil(ActionEvent event) throws SQLException, IOException {

        ServiceUser su = new ServiceUser();
        User user = su.getOneById(id_user);
        typeUser = user.getType();
        String mail = email.getText();
        int phoneNumb = (int) Long.parseLong(phoneNumber.getText());
        if (typeUser.equals("Client")) {
         
            User u = new Client(username.getText(), pasworrd.getText(), email.getText(), bytes,  phoneNumb );
            su.modifier(u);
        } else if(typeUser.equals("Admin")){
            User u = new Admin(username.getText(), pasworrd.getText(), email.getText(), bytes,  phoneNumb );
            su.modifier(u);
        }
        else {
            User u2 = new Transporteur(username.getText(), pasworrd.getText(), email.getText(), bytes,  phoneNumb );
            su.ajouter(u2);
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("Transporteur ajouté avec succés!");
            alert3.show();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
            Parent rootU;

            rootU = loader.load();

            Scene sceneU = new Scene(rootU);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(sceneU);
            appStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }     
 }
    

