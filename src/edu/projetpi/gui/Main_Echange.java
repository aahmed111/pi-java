package edu.projetpi.gui;

import edu.projetpi.entities.Article;
import edu.projetpi.entities.User;
import edu.projetpi.services.ServiceArticle;
import edu.projetpi.services.ServiceUser;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main_Echange extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialisation des éléments graphiques
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        Label titleLabel = new Label("Effectuer un échange ");
        Label selectUserLabel = new Label("Choisir un client :");
        Label selectItemLabel = new Label("choisir un article :");
        Label yourItemLabel = new Label("Votre article :");
        Label otherItemLabel = new Label("Ajouter un commentaire :");
        ListView<String> userList = new ListView<>();
        ListView<String> itemList = new ListView<>();
        TextArea yourItemArea = new TextArea();
        TextArea otherItemArea = new TextArea();
        Button proposeButton = new Button("Proposer l'échange");

        // Configuration des éléments graphiques
        root.setPadding(new Insets(10));
        titleLabel.setFont(new Font("Arial", 20));
        selectUserLabel.setFont(new Font("Arial", 16));
        selectItemLabel.setFont(new Font("Arial", 16));
        yourItemLabel.setFont(new Font("Arial", 16));
        otherItemLabel.setFont(new Font("Arial", 16));
        yourItemArea.setPromptText("Entrez une description de votre article");
        otherItemArea.setPromptText("Spécifiez vos préférences");
        yourItemArea.setWrapText(true);
        otherItemArea.setWrapText(true);
        userList.setPrefHeight(200);
        itemList.setPrefHeight(200);
        yourItemArea.setPrefHeight(100);
        otherItemArea.setPrefHeight(100);
        proposeButton.setDefaultButton(true);

        // Organisation des éléments graphiques dans des conteneurs
        VBox titleBox = new VBox(titleLabel);
        HBox selectUserBox = new HBox(10, selectUserLabel, userList);
        HBox selectItemBox = new HBox(10, selectItemLabel, itemList);
        VBox yourItemBox = new VBox(10, yourItemLabel, yourItemArea);
        VBox otherItemBox = new VBox(10, otherItemLabel, otherItemArea);
        VBox inputBox = new VBox(10, yourItemBox, otherItemBox, proposeButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);
        VBox contentBox = new VBox(20, selectUserBox, selectItemBox, inputBox);
        contentBox.setPadding(new Insets(10));
        root.setTop(titleBox);
        root.setCenter(contentBox);
        ServiceUser su = new ServiceUser();
        List<User> lu = su.getAll();
        List<String> names = lu.stream().map(User::getNom).collect(Collectors.toList());
        ServiceArticle sa = new ServiceArticle();
        List<Article> la = sa.getAll();
        List<String> articles = la.stream().map(Article::getDescription).collect(Collectors.toList());
        // Simulation des données des utilisateurs et des articles
        userList.getItems().addAll(names);
        itemList.getItems().addAll(articles);

                // Gestion de l'événement de sélection d'un utilisateur
        userList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println("Utilisateur sélectionné : " + newValue);
        });

        // Gestion de l'événement de sélection d'un article
        itemList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println("Article sélectionné : " + newValue);
        });

        // Gestion de l'événement de clic sur le bouton "Proposer l'échange"
        proposeButton.setOnAction(event -> {
            String selectedUser = userList.getSelectionModel().getSelectedItem();
            String selectedItem = itemList.getSelectionModel().getSelectedItem();
            String yourItem = yourItemArea.getText();
            String otherItem = otherItemArea.getText();

            // Vérification de la saisie de l'utilisateur et de l'article sélectionné
            if (selectedUser == null || selectedItem == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Sélection manquante");
                alert.setContentText("Veuillez sélectionner un utilisateur et un article.");
                alert.showAndWait();
                return;
            }

            // Vérification de la saisie des descriptions des articles
            if (yourItem.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Description manquante");
                alert.setContentText("Veuillez entrer une description pour votre article.");
                alert.showAndWait();
                return;
            } else {
            }

            // Confirmation de la proposition d'échange
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous proposer l'échange suivant ?");
            alert.setContentText("- Vous : " + yourItem + "\n- " + selectedUser + " : " + otherItem);
            ButtonType yesButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == yesButton) {
                    System.out.println("Proposition d'échange envoyée.");
                } else if (buttonType == noButton) {
                    System.out.println("Proposition d'échange annulée.");
                }
            });
        });

        // Affichage de la fenêtre principale
        primaryStage.setTitle("Swapify");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
