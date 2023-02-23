package edu.projetpi.gui;

import edu.projetpi.entities.Echange;
import edu.projetpi.services.ServiceEchange;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListeEchange extends Application {

    private ServiceEchange se = new ServiceEchange();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        // Titre de la fenêtre
        Label titre = new Label("Liste des échanges");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        root.getChildren().add(titre);
        VBox vBox = new VBox();
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vBox);
        Scene scene = new Scene(scroll, 600, 600);
      

        // Liste des échanges
        for (Echange echange : se.getAll()) {

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(20);
            hbox.setPadding(new Insets(10));
            hbox.setMinHeight(50);
            Label labelEchange = new Label(echange.toString());
            hbox.getChildren().add(labelEchange);

            // Bouton d'annulation
            Button annulerBtn = new Button("Annuler");
            annulerBtn.setOnAction(event -> {
                if (echange.getStatut().equals("en attente")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation d'annulation");
                    alert.setHeaderText("Voulez-vous vraiment annuler cet échange ?");
                    alert.setContentText("Cet échange sera supprimé de la liste.");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            se.supprimer(1);
                            hbox.setVisible(false);
                        }
                    });
                }
            });
            hbox.getChildren().add(annulerBtn);

            // Bouton de suppression
            Button supprimerBtn = new Button("Supprimer");
            supprimerBtn.setOnAction(event -> {
                if (echange.getStatut().equals("terminé")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation de suppression");
                    alert.setHeaderText("Voulez-vous vraiment supprimer cet échange ?");
                    alert.setContentText("Cet échange sera définitivement supprimé de la liste.");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            se.supprimer(1);
                            hbox.setVisible(false);
                        }
                    });
                }
            });
            hbox.getChildren().add(supprimerBtn);

            root.getChildren().add(hbox);
        }
        vBox.getChildren().add(root);

        //Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
