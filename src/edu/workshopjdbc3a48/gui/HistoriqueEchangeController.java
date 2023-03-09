/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.services.ServiceEchange;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class HistoriqueEchangeController implements Initializable {
    private int id_User;

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int Id_User) {
        this.id_User = Id_User;
    }
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vBox;
    Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceEchange se = new ServiceEchange();
        List<Echange> listEchange = se.getAll();

        for (Echange echange : listEchange) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Historique.fxml"));
                VBox v = loader.load();
                HistoriqueController hc = loader.getController();
                hc.ajouter(echange,getId_User());
                vBox.getChildren().add(v);
            } catch (IOException ex) {
                Logger.getLogger(HistoriqueEchangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
