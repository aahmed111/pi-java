/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.services.ServiceUser;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class LoginController implements Initializable {
    private  int id_Connector ;
    @FXML
    private Button login;
    @FXML
    private Button sinUp;
    @FXML
    private TextField username;
 
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox show;
    private String[] types = {"Client", "Transporteur", "Admin"};
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    type.getItems().addAll(types);

    }
    
    
   
    
    public int getId_Connector() {
        return id_Connector;
    }

    public void setId_Connector(int id_Connector) {
        this.id_Connector = id_Connector;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        String USERNAME = username.getText();
        String PASSWORD = password.getText();
        String TYPE = type.getValue();
        boolean found = false;

        try {

            Connection cnx = DataSource.getInstance().getCnx();
            ServiceUser su = new ServiceUser();
            String req = "SELECT `username`,`password`,`type` FROM `user` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String typeUser = rs.getString("type");
                if ((rs.getString(1).equals(USERNAME)) && (rs.getString(2).equals(PASSWORD)) && (typeUser.equals(TYPE))) {
                    found = true;
                    switch (typeUser) {

                        case "Admin":

                            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ProfilAdmin.fxml"));
                            Parent root1 = loader1.load();

                            ProfilAdminController pa = loader1.getController();
                            Admin adminLogin = su.getAdminByUsernamePassword(USERNAME, PASSWORD);
                           
                            Scene scene1 = new Scene(root1);
                            Stage appStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            appStage1.setScene(scene1);
                            appStage1.show();
                            break;
                        case "Transporteur":
                            Transporteur loginTransporteur =su.getTransporteurByUsernamePassword(USERNAME, PASSWORD);
                            id_Connector = loginTransporteur.getId_user();
                            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ProfilTransporteur.fxml"));
                            Parent root2 = loader2.load();
                            ProfilTransporteurController pr = loader2.getController();
                            pr.setId_Transporteur(id_Connector);
                            pr.afficher(id_Connector);
                            Scene scene2 = new Scene(root2);
                            Stage appStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            appStage2.setScene(scene2);
                            appStage2.show();
                            break;
                        case "Client":
                            try {  // Recuperation de l'objet Client et son Id correspondant au client connecté 
                                Client loginClient = su.getClientByUsernamePassword(USERNAME, PASSWORD);
                                id_Connector = loginClient.getId_user();
                                // Chargement du fichier FXML du l'aceuille
                               
                                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Aceuille.fxml"));
                                Parent root3 = loader3.load();
                        
                                AceuilleController ac = loader3.getController();
                            
                                ac.setId_Connector(id_Connector);
                                ac.afficher(id_Connector);
                               
                                // Affichage de la page du profil client
                                Scene scene3 = new Scene(root3);
                                Stage appStage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                appStage3.setScene(scene3);
                                appStage3.show();
                                break;
                            } catch (IOException | IllegalStateException e) {
                                e.printStackTrace();
                            }
                    }

                }

            }
            if (found == false) {
                Alert alert = new Alert(AlertType.ERROR, "Username ou mot de passe incorrect !");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    username.setText("");
                    password.setText("");

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



  
    @FXML
    private void sinUp(ActionEvent event) {
        try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInscription.fxml"));
    Parent rootU = loader.load();
    Scene sceneU = new Scene(rootU);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(sceneU);
    appStage.show();
} catch (IOException ex) {
    System.out.println("Erreur lors du chargement du fichier FXML : " + ex.getMessage());
    ex.printStackTrace();
}


    }


    

}
