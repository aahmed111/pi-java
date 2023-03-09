package edu.projetpi.gui;



import edu.projetpi.entities.Article;
import edu.projetpi.entities.Echange;
import edu.projetpi.entities.User;
import edu.projetpi.services.ServiceArticle;
import edu.projetpi.services.ServiceEchange;
import edu.projetpi.services.ServiceUser;
import java.util.List;
import java.util.stream.Collectors;

import edu.projetpi.utils.Mailer;
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
        Scene scene = new Scene(root, 800, 700);
        Label titleLabel = new Label("Effectuer un échange ");
        Label selectUserLabel = new Label("Choisir un client :");
        Label selectItemLabel = new Label("choisir un article :");
        Label mySelectItemLabel = new Label("mes articles :");

        Label yourItemLabel = new Label("Votre article :");
        Label otherItemLabel = new Label("Ajouter un commentaire :");
        ListView<String> userList = new ListView<>();
        ListView<String> itemList = new ListView<>();
        ListView<String> myList = new ListView<>();

        TextArea yourItemArea = new TextArea();
        TextArea otherItemArea = new TextArea();
        Button proposeButton = new Button("Proposer l'échange");

        // Configuration des éléments graphiques
        root.setPadding(new Insets(10));
        titleLabel.setFont(new Font("Arial", 20));
        selectUserLabel.setFont(new Font("Arial", 16));
        selectItemLabel.setFont(new Font("Arial", 16));
        mySelectItemLabel.setFont(new Font("Arial", 16));

        yourItemLabel.setFont(new Font("Arial", 16));
        otherItemLabel.setFont(new Font("Arial", 16));
        yourItemArea.setPromptText("Entrez une description de votre article");
        otherItemArea.setPromptText("Spécifiez vos préférences");
        yourItemArea.setWrapText(true);
        otherItemArea.setWrapText(true);
        userList.setPrefHeight(200);
        itemList.setPrefHeight(200);
        myList.setPrefHeight(200);

        myList.setPrefHeight(200);

        yourItemArea.setPrefHeight(100);
        otherItemArea.setPrefHeight(100);
        proposeButton.setDefaultButton(true);

        // Organisation des éléments graphiques dans des conteneurs
        VBox titleBox = new VBox(titleLabel);
        HBox selectUserBox = new HBox(10, selectUserLabel, userList);
        HBox selectItemBox = new HBox(10, selectItemLabel, itemList);
        HBox mySelectItemBox = new HBox(10, mySelectItemLabel, myList);

        VBox yourItemBox = new VBox(10, yourItemLabel, yourItemArea);
        VBox otherItemBox = new VBox(10, otherItemLabel, otherItemArea);
        VBox inputBox = new VBox(10, otherItemBox, proposeButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);
        mySelectItemBox.setPadding(new Insets(30));
        VBox contentBox = new VBox(20, selectUserBox, selectItemBox, mySelectItemBox, inputBox);
        contentBox.setPadding(new Insets(10));
        root.setTop(titleBox);
        root.setCenter(contentBox);
        ServiceUser su = new ServiceUser();
        List<User> lu = su.getAll();
        List<String> names = lu.stream().map(User::getNom).collect(Collectors.toList());
        ServiceArticle sa = new ServiceArticle();
        List<Article> la = sa.getAll();
        List<String> articles = la.stream().map(Article::getDesignation).collect(Collectors.toList());

        //get articles by connected user using id 50 for now
        List<Article> userArticles = sa.getAllByIdUser(50);
        System.out.println(userArticles.toString());
        List<String> myArticles = userArticles.stream().map(Article::getDesignation).collect(Collectors.toList());

        // Simulation des données des utilisateurs et des articles
        userList.getItems().addAll(names);
        itemList.getItems().addAll(articles);
        myList.getItems().addAll(myArticles);

       /* Echange e1 = new Echange();
        e1.setId_client1(50);
        e1.setStatut("en attente");*/
        // Gestion de l'événement de sélection d'un utilisateur
        userList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println("Utilisateur sélectionné : " + newValue);
            lu.forEach((u) -> {
                if (u.getNom().equalsIgnoreCase(newValue)) {
                    System.out.println("\n ++++++++++ \n found article" + u.toString());
                    e1.setId_client2(u.getId_user());

                }
            });
        });

        // Gestion de l'événement de sélection d'un article
        itemList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            la.forEach((a) -> {
                if (a.getDesignation().equalsIgnoreCase(newValue)) {
                    System.out.println("\n ++++++++++ \n found article"+a.getDesignation());
                    e1.setId_article2(a.getId_article());
                }
            });
            // System.out.println("Article sélectionné : " + newValue);
        });

        myList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            la.forEach((a) -> {
                if (a.getDesignation().equalsIgnoreCase(newValue)) {
                    System.out.println("\n ++++++++++ \n found article" + a.toString());
                    e1.setId_article1(a.getId_article());
                }
            });
            // System.out.println("Article sélectionné : " + newValue);
        });

        // Gestion de l'événement de clic sur le bouton "Proposer l'échange"
        proposeButton.setOnAction(event -> {
            String selectedUser = userList.getSelectionModel().getSelectedItem();
            String selectedItem = itemList.getSelectionModel().getSelectedItem();
            String yourItem = myList.getSelectionModel().getSelectedItem();
            // String otherItem = otherItemArea.getText();
           // int client1Id = getOneById(userList.getAccessibleText());
           // int client2Id = getClientIdByName(u.getId_user());

            // Vérification de la saisie de l'utilisateur et de l'article sélectionné
            if (selectedUser == null || selectedItem == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Sélection manquante");
                alert.setContentText("Veuillez sélectionner un utilisateur et un article.");
                alert.showAndWait();
                return;
            } else {
            }
            ServiceEchange se = new ServiceEchange();
            System.out.println(e1.toString());
            se.ajouter(e1);
            // Confirmation de la proposition d'échange
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous proposer l'échange suivant ?");
            alert.setContentText("- Vous : " + yourItem + "\n- " + selectedUser + " : " + selectedItem);
            ButtonType yesButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == yesButton) {
                    try {
                        User me = lu.stream().filter(u -> u.getId_user() == 50).findFirst().get();
                        User other = lu.stream().filter(u -> u.getNom().equalsIgnoreCase(selectedUser)).findFirst().get();
                        Article myArticle = la.stream().filter(a -> a.getDesignation().equalsIgnoreCase(yourItem)).findFirst().get();
                        Article otherArticle = la.stream().filter(a -> a.getDesignation().equalsIgnoreCase(selectedItem)).findFirst().get();

                        System.out.println("sending confiramtion mail");
                        Mailer.sendSwapConfirmations(me, other, myArticle, otherArticle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
