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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class UpdateClientController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initialiseText(getId_user());
        } catch (SQLException ex) {
            Logger.getLogger(UpdateClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        String mail = email.getText();
        int phoneNumb = (int) Long.parseLong(phoneNumber.getText());
        if (user instanceof Client) {

            User u = new Client(username.getText(), pasworrd.getText(), email.getText(), bytes, phoneNumb);
            su.modifier(u);
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("votre est modifié avec succés");
            alert3.show();
        } else if (user instanceof Transporteur) {
            User u2 = new Transporteur(username.getText(), pasworrd.getText(), email.getText(), bytes, phoneNumb);
            su.modifier(u2);
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("votre est modifié avec succés");
            alert3.show();
        }
    }

    public void initialiseText(int id) throws SQLException {

        ServiceUser su = new ServiceUser();
        User user = su.getOneById(id);
        if (user instanceof Client) {
            Client c = (Client) user;
            username.setText(c.getUsername());
            pasworrd.setText(c.getPassword());
            email.setText(c.getEmail());
            String phoneNumb = String.valueOf(c.getPhoneNumber());
            phoneNumber.setText(phoneNumb);
            byte[] imageData = c.getPhoto();
            if (imageData != null) {
                InputStream inputStream = new ByteArrayInputStream(imageData);
                Image image = new Image(inputStream);
                ImageV.setImage(image);

            }

        } else if (user instanceof Transporteur) {
            Transporteur t = (Transporteur) user;
            username.setText(t.getUsername());
            pasworrd.setText(t.getPassword());
            email.setText(t.getEmail());
            String phoneNumb = String.valueOf(t.getPhoneNumber());
            phoneNumber.setText(phoneNumb);
            byte[] imageData = t.getPhoto();
            if (imageData != null) {
                InputStream inputStream = new ByteArrayInputStream(imageData);
                Image image = new Image(inputStream);
                ImageV.setImage(image);

            }
        }

    }

}
