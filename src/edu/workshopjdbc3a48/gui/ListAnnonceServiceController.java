/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListAnnonceServiceController implements Initializable {


    private int id_User;
    @FXML
    private HBox listService;

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int Id_User) {
        this.id_User = Id_User;
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
    }    

    @FXML
    private void ajoutService(ActionEvent event) {
    }
    
}
