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
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilAdminController implements Initializable {

    private int Id_Client;
    @FXML
    private Label msgAdmin;
    @FXML
    private ListView<Integer> ListView;
    @FXML
    private Label Clients;
    ServiceUser su = new ServiceUser();

    public List<Integer> afficherClient() {
        List<Integer> listId_user = new ArrayList<>();
        List<Client> listClient = new ArrayList<>();
        try {

            listClient = su.getAllClient();
            for (Client client : listClient) {
                listId_user.add(client.getId_user());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return listId_user;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Integer> listId = new ArrayList<>();
        listId = afficherClient();

        ListView.getItems().addAll(listId);
        ListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer selection = ListView.getSelectionModel().getSelectedItem();
                Id_Client = selection;

            }
        });
    }

    
    @FXML
    private void goToProfil(ActionEvent event)  {
        try {
            Client c = (Client) su.getOneById(Id_Client);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilClient.fxml"));
            Parent root;
          
                root = loader.load();
         
            
            ProfilClientController pc = loader.getController();
            pc.setId_connecté(Id_Client);
            pc.afficher(Id_Client);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
               } catch (IOException ex) {
                Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
       catch (SQLException ex) {
            Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
