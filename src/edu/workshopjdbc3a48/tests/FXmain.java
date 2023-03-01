
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.gui.ProfilCController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FXmain extends Application { 
    @Override
    public void start(Stage primaryStage) {
           try {
        
            FXMLLoader loader =new FXMLLoader(getClass().getResource("../GUI/Login.fxml"));
     
            Parent root = loader.load();
         
            Scene scene = new Scene(root); 
      
            primaryStage.setScene(scene);
           
            primaryStage.show();  
            /*  Button btn = new Button();
            btn.setText("Say 'Hello World'");
            btn.setOnAction(new EventHandler<ActionEvent>() {      
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Hello World!");
            }
            });
            
            StackPane root = new StackPane();
            root.getChildren().add(btn);
            
            Scene scene = new Scene(root, 300, 250);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();*/
        } catch (IOException ex) {
            Logger.getLogger(FXmain.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
