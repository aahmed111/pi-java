/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilAdminController implements Initializable {

    private int Id_User;
 
    private int id_admin;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }
    @FXML
    private Pane pane;
    private Parent fxml;

    ServiceUser su = new ServiceUser();

    /* public List<String> afficherClient() {
        List<String> listName = new ArrayList<>();
        List<Client> listClient = new ArrayList<>();
        try {

            listClient = su.getAllClient();
            for (Client client : listClient) {
                listName.add(client.getUsername());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return listName;
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       try {
            fxml = FXMLLoader.load(getClass().getResource("DashbordAdmin.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateClient.fxml"));
            fxml = loader.load();
            UpdateClientController md = loader.getController();
            md.setId_user(getId_admin());
            md.initialiseText(id_admin);
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dashbord(ActionEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("DashbordAdmin.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recherche(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RechercheUsers.fxml"));
            fxml = loader.load();

            RechercheUsersController r = loader.getController();
            r.setId_connector(id_admin);
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment quitter ?");
        //alert.setContentText("La durée sera enregistrée dans la base de données." + duree + "minutes");
        //applique le css sur l'alerte
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("Alert.css").toExternalForm());
        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginSinup.fxml"));

                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage PROFILStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                PROFILStage1.close();
                scene.setFill(Color.TRANSPARENT);
                Stage loginStage = new Stage(StageStyle.TRANSPARENT);
                //FERMER LA STAGE ACTUELLE

                loginStage.setScene(scene);
                loginStage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListReclamation.fxml"));
            fxml = loader.load();

            ListReclamationController l = loader.getController();
            l.setId_User(id_admin);
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
