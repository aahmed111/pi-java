/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abdel
 */
public class RechercheUsersController implements Initializable {
     ServiceUser su = new ServiceUser();
    private int id_user;
    private int id_connector;

    public int getId_connector() {
        return id_connector;
    }

    public void setId_connector(int id_connector) {
        this.id_connector = id_connector;
    }

    @FXML
    private TextField recherche;
    @FXML
    private ComboBox<String> selectType;
    @FXML
    private ComboBox<String> selectGenre;
    @FXML
    private ImageView image;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> genre;
    ObservableList<String> types = FXCollections.observableArrayList("aucun", "Client", "Transporteur");
    ObservableList<String> genres = FXCollections.observableArrayList("aucun", "Female", "Male");

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {

            table.setRowFactory(tv -> {
                TableRow<User> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && !row.isEmpty()) {
                      
                        User user = row.getItem();
                        String nom = user.getUsername();             
                        try {
                            id_user = su.getIdByName(nom);
                            affiche();
                        } catch (SQLException ex) {
                            Logger.getLogger(RechercheUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
            selectType.setItems(types);
            selectGenre.setItems(genres);

            // Un écouteur sur l'événement de modification du texte dans le champ de recherche
            recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                updateTable();
            });
            // écouteur d'événement pour la sélection du type
            selectType.valueProperty().addListener((observable, oldValue, newValue) -> {
                updateTable();
            });

            //  écouteur d'événement pour la sélection du genre
            selectGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
                updateTable();
            });

            //  tous les utilisateurs au démarrage de l'application
            ObservableList<User> users = FXCollections.observableArrayList(recherche());
            table.setItems(users);
            username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
            type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
            genre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSexe()));
        } catch (SQLException ex) {
            Logger.getLogger(RechercheUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void affiche() throws SQLException {
  

        User u=  su.getOneById(id_user);
        String nomUtilisateur = u.getUsername();

        byte[] imageData = u.getPhoto();
        if (imageData != null) {
            try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
                Image imageUser = new Image(inputStream);
                image.setImage(imageUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateTable() {
        try {
            List<User> filteredUsers = recherche();

            ObservableList<User> users = FXCollections.observableArrayList(filteredUsers);
            table.setItems(users);
            username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
            email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
            type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
            genre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSexe()));

        } catch (SQLException ex) {
            Logger.getLogger(RechercheUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> recherche() throws SQLException {
        List<User> list = new ArrayList<User>();
       
        list = su.getAll();
        List<User> filteredUsers = list.stream()
                  .filter(user -> user.getUsername().contains(recherche.getText()))
                  .filter(user -> selectType.getValue() == null || selectType.getValue().equals("aucun") || user.getType().equals(selectType.getValue()))
                  .filter(user -> selectGenre.getValue() == null || selectGenre.getValue().equals("aucun") || user.getSexe().equals(selectGenre.getValue()))
                  .collect(Collectors.toList());
        return filteredUsers;
    }

    @FXML
    private void profil(ActionEvent event) throws SQLException, IOException {
      
                User u = su.getOneById(id_user);
                String typeUser =u.getType();
                if (typeUser.equals("Client")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilC.fxml"));
            Parent root;
            root = loader.load();

            ProfilCController pc = loader.getController();
            pc.setId_connecté(id_user);
            pc.affiche(id_user);
            pc.setId_admin(id_connector);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
       
         }else {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilTransporteurs.fxml"));
            Parent root;
            root = loader.load();

            ProfilTransporteursController pc = loader.getController();
            pc.setId_connecté(id_user);
            pc.affiche(id_user);
            pc.setId_admin(id_connector);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
         
                }
    }

    @FXML
    private void bloque(ActionEvent event) {
       
       
         try {
             User u = su.getOneById(id_user);
             TextInputDialog dialog = new TextInputDialog();            
             // Définir le titre et le contenu du dialogue
             dialog.setTitle("Blocage d'utilisateur");
             dialog.setHeaderText("Entrez la durée de blocage en minutes :");
             
             // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
             Optional<String> result = dialog.showAndWait();
             
             // Si l'admin a saisi une valeur, afficher un message avec cette valeur
             result.ifPresent(value -> {
                 int duree = Integer.parseInt(value);
                 try {     
                     su.blockUser(id_user, duree);
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             });
         } catch (SQLException ex) {
             Logger.getLogger(RechercheUsersController.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    

}
