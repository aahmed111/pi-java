/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.services.ServiceAnnonce;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AceuilleController implements Initializable {

    private LocalDateTime date_login;
    private LocalDateTime date_logout;
    @FXML
    private VBox postcontainer;
    @FXML
    private TextField ajoutAnnonce;
    @FXML
    private ListView<String> mylistview;

    public LocalDateTime getDate_logout() {
        return date_logout;
    }

    public void setDate_logout(LocalDateTime date_logout) {
        this.date_logout = date_logout;
    }

    public LocalDateTime getDate_login() {
        return date_login;
    }

    public void setDate_login(LocalDateTime date_login) {
        this.date_login = date_login;
    }

    private int Id_Connector;

    public int getId_Connector() {
        return Id_Connector;
    }

    public void setId_Connector(int Id_Connector) {
        this.Id_Connector = Id_Connector;
    }
    @FXML

    List<Annonce> annonces;

    ObservableList<String> items = FXCollections.observableArrayList("publiée récemment", "populaire", "service", "produit");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mylistview.setItems(items);
        annonces = new ArrayList<>(showAnnonce());
        try {
            for (Annonce annonce : annonces) {

                FXMLLoader l = new FXMLLoader();
                l.setLocation(getClass().getResource("Annonce.fxml"));
                VBox vbox = l.load();
                AnnonceController a = l.getController();
                a.setData(annonce);
                postcontainer.getChildren().add(vbox);
            }
        } catch (IOException ex) {
            Logger.getLogger(AceuilleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Annonce> showAnnonce() {
        List<Annonce> la = new ArrayList<>();
        Annonce annonce;
        for (int i = 0; i < 20; i++) {
            ServiceAnnonce sa = new ServiceAnnonce();
            annonce = sa.getOneById(15);
            la.add(annonce);
        }
        return la;

    }

    @FXML
    private void goToProfil(ActionEvent event) throws SQLException {
        try {  // Recuperation de l'objet Client a traver son id 
            // Récupération de l'instance de LoginController
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
            Parent root = loader.load();
            ProfilCController pc = loader.getController();
            pc.setId_connecté(Id_Connector);
            pc.affiche(Id_Connector);
            pc.setDate_login(date_login);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("votre date de connexion." + date_login);
            alert.show();

            appStage.setScene(scene);
            appStage.show();

        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterAnnonce(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjoutAnnonce.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

      
    }

}
