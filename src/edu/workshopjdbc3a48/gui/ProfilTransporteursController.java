/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class ProfilTransporteursController implements Initializable {

    private int id_Transporteur ;
    @FXML
    private ImageView ImageV;
    @FXML
    private Label nomUser;
    public int getId_Transporteur() {
        return id_Transporteur;
    }

    public void setId_Transporteur(int id_Transporteur) {
        this.id_Transporteur = id_Transporteur;
    }
    
    private String[] etats = {"Diponible", "InDisponible"};
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
