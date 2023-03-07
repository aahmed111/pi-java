/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LoginSinupController implements Initializable {

    @FXML
    private VBox vbox;

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }
    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX(vbox.getLayoutX() *7);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      /*  try {
            fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    @FXML
    private void open_SinIn(ActionEvent event) {
      TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX(vbox.getLayoutX() * 7);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       /* try {
            fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void open_SinUp(ActionEvent event) {
       TranslateTransition t = new TranslateTransition(Duration.seconds(0.5), vbox);
        t.setToX( 15);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("UserInscription.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         /*
       try {
            fxml = FXMLLoader.load(getClass().getResource(".fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(LoginSinupController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}
