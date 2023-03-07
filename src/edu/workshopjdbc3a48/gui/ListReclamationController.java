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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ListReclamationController implements Initializable {
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
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> username;
    @FXML
    private TableColumn<?, ?> objet;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> date_Reclamation;
    @FXML
    private Pane PaneEchange;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void voirEchange(ActionEvent event) {
    }
    
}
