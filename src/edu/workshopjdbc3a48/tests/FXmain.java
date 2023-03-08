package edu.workshopjdbc3a48.tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXmain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginSinup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage loginStage = new Stage(StageStyle.TRANSPARENT);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXmain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }

}
