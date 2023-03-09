/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.services.ServiceAnnonce;
import edu.workshopjdbc3a48.services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjoutAnnonceController implements Initializable {

    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField titre;
    @FXML
    private TextArea condition;
    @FXML
    private TextArea description;
    @FXML
    private Button btnsuivant;
    @FXML
    private RadioButton rbpub;
    @FXML
    private ToggleGroup audience;
    @FXML
    private RadioButton rbmoi;
    @FXML
    private ComboBox<String> TypeArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   ServiceCategorie sc = new ServiceCategorie();
        // List<Categorie> listCategorie = sc.getAll();
        ObservableList<String> typeList = FXCollections.observableArrayList("sport");
        // for (Categorie c : listCategorie) {
        //      typeList.add(c.getNom());
        //  }
        categorie.setItems(typeList);
        ObservableList<String> typeArticle = FXCollections.observableArrayList("Service", "Produit");
        TypeArticle.setItems(typeArticle);

    }

    @FXML
    private void suivant(ActionEvent event) {

        try {

            String type = TypeArticle.getValue();
            String selectedOption = categorie.getValue();

            String descriptionn = description.getText();
            String titree = titre.getText();
            String conditionn = condition.getText();
            String audience = "";
            if (isEmpty() == true) {
                if (selectedOption != null) {
                    if (rbpub.isSelected() || rbmoi.isSelected()) {
                        if (rbpub.isSelected()) {
                            audience = "publique";
                        } else if (rbmoi.isSelected()) {
                            audience = "moi-uniquement";
                        }

                        // Vérifiez que les champs de texte contiennent des valeurs valides
                        if (!titree.matches("[a-zA-Z\\s]+")) {
                            throw new Exception("La description doit contenir des lettres et des espaces uniquement");
                        } else {
                            if (!descriptionn.matches("[a-zA-Z\\s]+")) {
                                throw new Exception("La description doit contenir des lettres et des espaces uniquement");
                            } else {

                                if (!conditionn.matches("[a-zA-Z\\s]+")) {
                                    throw new Exception("La condition doit contenir des lettres et des espaces uniquement");
                                } else {
                                    if (type.equals("Article")) {
                                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        appStage.close();

                                        Parent root = FXMLLoader.load(getClass().getResource("ListAnnonceArticle.fxml"));
                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setScene(scene);
                                        stage.showAndWait();

                                    } else {
                                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        appStage.close();
                                        Parent root = FXMLLoader.load(getClass().getResource("ListAnnonceService.fxml"));
                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setScene(scene);
                                        stage.showAndWait();

                                    }
                                }
                            }
                        }

                    } else {
                        afficherErreurAudience();

                    }

                } else {
                    throw new Exception("Veuiller choisir une categorie");
                }
            }

        } catch (Exception ex) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(ex.getMessage());
            alert2.show();
        }


    }

    public boolean isEmpty() {
        boolean result = true;
        StringBuilder errorMessage = new StringBuilder("Veuillez remplir les champs suivants:\n");

        if (titre.getText().length() == 0) {
            result = false;
            titre.setStyle("-fx-border-color: red;");
            errorMessage.append("- titre \n");
        } else {
            titre.setStyle(null);
        }

        if (description.getText().length() == 0) {
            result = false;
            description.setStyle("-fx-border-color: red;");
            errorMessage.append("- description\n");
        } else {
            description.setStyle(null);
        }

        if (condition.getText().length() == 0) {
            result = false;
            condition.setStyle("-fx-border-color: red;");
            errorMessage.append("- Condition\n");
        } else {
            condition.setStyle(null);
        }

  
        if (!result) {
            Alert alert = new Alert(Alert.AlertType.WARNING, errorMessage.toString());
            alert.showAndWait();
        }
       
        return result;
    }

    private void afficherErreurAudience() {
        Alert alertSexe = new Alert(Alert.AlertType.ERROR, "Veuillez choisir une audience");
        alertSexe.show();

        for (Toggle bouton : audience.getToggles()) {
            RadioButton radio = (RadioButton) bouton;
            radio.getStyleClass().add("erreur");
        }
    }
}
