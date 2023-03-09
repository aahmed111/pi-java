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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author zayne
 */
public class FXML_EchangeController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ScrollPane root;
    @FXML
    private AnchorPane conteneur;
    @FXML
    private Label titre;
    @FXML
    private Label cc;
    @FXML
    private ListView<?> lc;
    @FXML
    private Label ca;
    @FXML
    private ListView<?> la;
    @FXML
    private Label ma;
    @FXML
    private ListView<?> lma;
    @FXML
    private Button propose;

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


}
