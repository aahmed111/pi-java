/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;


import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceReclamation;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Detailreclamation extends Application {

    // Liste de réclamations
    

    @Override
  public void start(Stage primaryStage) {
        
    ServiceReclamation sr = new ServiceReclamation();// Ajoutez une instance de la classe de service de réclamation

    // Créer une table pour afficher les réclamations
    TableView<Reclamation> table = new TableView<>();
    try {
        ObservableList<Reclamation> reclamations = FXCollections.observableArrayList(sr.getAll()); //recupere d'appres la db
        System.out.println(sr.getAll());
        table.setItems(reclamations);
    } 
    catch (SQLException ex) {
       System.out.println("Erreur lors de la récupération des réclamations : " + ex.getMessage()); 
    }

        // Ajouter les colonnes à la table
        TableColumn<Reclamation, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Reclamation, String> id_userCol = new TableColumn<>("id_user");
        id_userCol.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        
        

        TableColumn<Reclamation, String> Nom_user = new TableColumn<>("Nom_user");
        Nom_user.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Reclamation, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Reclamation, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Reclamation, String> echangeCol = new TableColumn<>("ID échange");
        echangeCol.setCellValueFactory(new PropertyValueFactory<>("echange"));

         TableColumn<Reclamation, String> dateCol = new TableColumn<>("Date d'envoi");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        
        
        
       TableColumn<Reclamation, Void> deleteCol = new TableColumn<>("Supprimer");
       deleteCol.setCellFactory(new Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>>() { 
    @Override
    public TableCell<Reclamation, Void> call(final TableColumn<Reclamation, Void> param) {
        final TableCell<Reclamation, Void> cell = new TableCell<Reclamation, Void>() {
            private final Button btn = new Button("Supprimer");

            {
                btn.setOnAction((ActionEvent event) -> {   
                    try {
                        Reclamation r = getTableView().getItems().get(getIndex());
                        ServiceReclamation sr = new ServiceReclamation();
                        sr.supprimer(r.getId());
                        // Refresh the TableView to remove the deleted row
                        getTableView().getItems().remove(r);
                    } catch (SQLException ex) {
                        // Handle the exception
                    }
                });
            }

            // Ajouter le bouton à chaque cellule de la colonne
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        return cell;
    }
});

 

  table.getColumns().addAll(idCol, id_userCol, Nom_user, descriptionCol, emailCol, echangeCol, dateCol, deleteCol);      
        
        
        
        
        
        // Créer un bouton pour consulter les réclamations 
        Button consulterBtn = new Button("Consulter réclamations");
        consulterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Afficher la table des réclamations
                VBox vbox = new VBox();
                vbox.getChildren().addAll(new Label("Réclamations"), table);
                Scene scene = new Scene(vbox);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        // Créer la scène principale
        Scene scene = new Scene(consulterBtn, 200, 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}