package edu.projetpi.gui;

import edu.projetpi.entities.Echange;
import edu.projetpi.services.ServiceEchange;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Evaluation extends Application {

    private ServiceEchange se = new ServiceEchange();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        // Titre de la fenêtre
        Label titre = new Label("Evaluation d'un échange");
        titre.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        root.getChildren().add(titre);

        // Liste des échanges terminés
        VBox vboxEchanges = new VBox();
        vboxEchanges.setAlignment(Pos.CENTER);
        vboxEchanges.setSpacing(10);

        Label labelEchanges = new Label("Echanges terminés :");
        vboxEchanges.getChildren().add(labelEchanges);

        ComboBox<Echange> comboBoxEchanges = new ComboBox<>();
        List<Echange> e = se.getAll().stream()
                .filter(p -> p.getStatut().equalsIgnoreCase("terminé")).collect(Collectors.toList());
        comboBoxEchanges.getItems().addAll(e);
        comboBoxEchanges.setPromptText("Choisir un échange");
        vboxEchanges.getChildren().add(comboBoxEchanges);

        root.getChildren().add(vboxEchanges);

        // Liste des notes possibles
        VBox vboxNotes = new VBox();
        vboxNotes.setAlignment(Pos.CENTER);
        vboxNotes.setSpacing(10);

        Label labelNotes = new Label("Attribuer une note :");
        vboxNotes.getChildren().add(labelNotes);

        ComboBox<Integer> comboBoxNotes = new ComboBox<>();
        comboBoxNotes.getItems().addAll(0, 1, 2, 3, 4, 5);
        comboBoxNotes.setPromptText("Choisir une note");
        vboxNotes.getChildren().add(comboBoxNotes);

        root.getChildren().add(vboxNotes);

        // Bouton de validation
        Button validerBtn = new Button("Valider");
        validerBtn.setOnAction(event -> {
            Echange echange = comboBoxEchanges.getValue();
            Integer note = comboBoxNotes.getValue();

            if (echange == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un échange terminé.");
                alert.showAndWait();
            } else if (note == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir une note.");
                alert.showAndWait();
            } else {
                se.evaluer(echange.getStatut(), note);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Evaluation enregistrée");
                alert.setHeaderText(null);
                alert.setContentText("L'échange a été évalué avec succès.");
                alert.showAndWait();
            }
        });
        root.getChildren().add(validerBtn);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
