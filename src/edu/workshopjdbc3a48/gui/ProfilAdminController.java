/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilAdminController implements Initializable {

    @FXML
    private Label msgAdmin;
    @FXML
    private ListView<String> ListView;
    @FXML
    private Label Clients;

    public List<String> afficherClient() {
        List<String> listNom = new ArrayList<>();
        List<Client> listClient = new ArrayList<>();
        try {

            ServiceUser su = new ServiceUser();
            listClient = su.getAllClient();
            for (Client client : listClient) {
                listNom.add(client.getUsername());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNom;
    }

    public void afficheHello(String username) {
        msgAdmin.setText("HELLO" + username);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> listNom = new ArrayList<>();
        listNom = afficherClient();

        ListView.getItems().addAll(listNom);
    }

    @FXML
    private void exit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent rootU = loader.load();
            Scene sceneU = new Scene(rootU);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(sceneU);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
