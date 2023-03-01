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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
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

    private int Id_User;
    @FXML
    private ListView<String> ListView;

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }
    ServiceUser su = new ServiceUser();
    @FXML
    private Button users;
    @FXML
    private Button exit;
    @FXML
    private PieChart pieChart;
    @FXML
    private LineChart<?, ?> LineChart;
   
    public List<String> afficherClient() {
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> list = new ArrayList<>();
        list = afficherClient();
        

        ListView.getItems().addAll(list);
        ListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selection = ListView.getSelectionModel().getSelectedItem();
                String nom = selection;
                try {
                    Id_User = su.getIdByName(nom);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    
    @FXML
    private void goToProfil(ActionEvent event)  {
        try {
            User u =  su.getOneById(Id_User);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
            Parent root;
             root = loader.load();
         
            
            ProfilCController pc = loader.getController();
            pc.setId_connecté(Id_User);
            pc.affiche(Id_User);
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

    @FXML
    private void users(ActionEvent event) {
        
    }

}
