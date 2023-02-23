/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zayne
 */
public class FXML_EchangeController implements Initializable {

    @FXML
    private TextField fxNom;
    @FXML
    private TextField fxprenom;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterP(ActionEvent event) {
        // ServicePersonne sp = new ServicePersonne();
        //ServicePersonne = new Personne(FxNom.getText(), type.getText());

    }

    @FXML
    private void AfficherP(ActionEvent event) {
    }

}
