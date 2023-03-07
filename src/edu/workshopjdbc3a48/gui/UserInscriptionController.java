/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class UserInscriptionController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField pasworrd;
    @FXML
    private TextField email;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private TextField phoneNumber;

    @FXML
    private ImageView ImageV;
    private byte[] bytes;
    private String[] types = {"Transporteur", "Client", "Admin"};
    private String sexe;
    @FXML
    private ToggleGroup genre;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll(types);
    }

    @FXML
    private void browser(ActionEvent event) {
        //afficher une boîte de dialogue de sélection de fichiers
        FileChooser fileChooser = new FileChooser();
        //retourne le fichier selectionnee
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            try {
                // crée un nouvel objet "Image" à partir de l'URI (Uniform Resource Identifier) 
                Image photo = new Image(file.toURI().toString());
                ImageV.setImage(photo);
                //lire selon un tableau de bits ,Files qui a generer ce traitement 
                bytes = Files.readAllBytes(file.toPath());
                setBytes(bytes);
            } catch (IOException ex) {
                Logger.getLogger(UserInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isEmpty() {
        boolean result = true;
        StringBuilder errorMessage = new StringBuilder("Veuillez remplir les champs suivants:\n");

        if (username.getText().length() == 0) {
            result = false;
            username.setStyle("-fx-border-color: red;");
            errorMessage.append("- Nom d'utilisateur\n");
        } else {
            username.setStyle(null);
        }

        if (pasworrd.getText().length() == 0) {
            result = false;
            pasworrd.setStyle("-fx-border-color: red;");
            errorMessage.append("- Mot de passe\n");
        } else {
            pasworrd.setStyle(null);
        }

        if (email.getText().length() == 0) {
            result = false;
            email.setStyle("-fx-border-color: red;");
            errorMessage.append("- Adresse e-mail\n");
        } else {
            email.setStyle(null);
        }

        if (phoneNumber.getText().length() == 0) {
            result = false;
            phoneNumber.setStyle("-fx-border-color: red;");
            errorMessage.append("- Numéro de téléphone\n");
        } else {
            phoneNumber.setStyle(null);
        }

        if (!result) {
            Alert alert = new Alert(Alert.AlertType.WARNING, errorMessage.toString());
            alert.showAndWait();
        }
        //condition sur le type
        //conditionsur le sexe 

        return result;
    }
// Méthode appelée en cas d'erreur de validation

    private void afficherErreurGenre() {
        Alert alertSexe = new Alert(Alert.AlertType.ERROR, "Veuillez choisir un sexe");
        alertSexe.show();

        for (Toggle bouton : genre.getToggles()) {
            RadioButton radio = (RadioButton) bouton;
            radio.getStyleClass().add("erreur");
        }
    }

    public static boolean verifierNumero(int numero) {
        try {
            String numeroStr = String.valueOf(numero);
            if (numeroStr.length() != 8) {
                return false;
            }
            int[] prefixes = {5, 2, 9, 7};
            boolean prefixeValide = false;
            for (int i = 0; i < prefixes.length; i++) {
                int prefixe = prefixes[i];
                if (numero / 10000000 == prefixe) {
                    prefixeValide = true;
                    break;
                }
            }
            if (!prefixeValide) {
                return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static String encrypt(String plaintext, String keyHex) throws Exception {
        byte[] key = Hex.decode(keyHex);
        byte[] input = plaintext.getBytes("UTF-8");

        BlockCipher engine = new AESFastEngine();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(true, new KeyParameter(key));

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, output, 0);
        len += cipher.doFinal(output, len);

        return Hex.toHexString(output);
    }

    @FXML
    private void ajouterUser(ActionEvent event) throws SQLException, Exception {

        if (isEmpty() == true) {
            if (verifierPassword(pasworrd.getText()) == true) {
                pasworrd.setStyle(null);
                String password = pasworrd.getText();
                String keyHex = "00112233445566778899AABBCCDDEEFF"; // la clé AES en hexadécimal
                String encryptedPassword = encrypt(password, keyHex);
                if (verifierUsername(username.getText())) {
                    username.setStyle(null);
                    ServiceUser su = new ServiceUser();
                    String typeUser = type.getValue();
                    String mail = email.getText();

                    if (phoneNumber.getText().matches("-?\\d+")) {

                        int phoneNumb = (int) Long.parseLong(phoneNumber.getText());
                        if (verifierNumero(phoneNumb) == true) {
                            phoneNumber.setStyle(null);
                            if (su.isValidEmailAddress(mail) == true) {
                                email.setStyle(null);
                                if (su.verifierUsername(username.getText()) == false) {

                                    if (male.isSelected() || female.isSelected()) {
                                        if (male.isSelected()) {
                                            sexe = "Male";
                                        } else if (female.isSelected()) {
                                            sexe = "Female";
                                        }
                                        if (verifierType(typeUser) == true) {
                                            if (verifierImage(bytes) == true) {
                                                if (typeUser.equals("Client")) {

                                                    User u = new Client(username.getText(), encryptedPassword, email.getText(), bytes, typeUser, phoneNumb, sexe, 0);

                                                    su.ajouter(u);
                                                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                                    alert1.setContentText("client ajouté avec succés! ");
                                                    alert1.show();

                                                } else if (typeUser.equals("Admin")) {
                                                    User u2 = new Admin(username.getText(), encryptedPassword, email.getText(), bytes, typeUser, phoneNumb, sexe);
                                                    su.ajouter(u2);
                                                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                                                    alert3.setContentText("Admin ajouté avec succés!");
                                                    alert3.show();
                                                } else {
                                                    User u2 = new Transporteur(username.getText(), encryptedPassword, email.getText(), bytes, typeUser, phoneNumb, sexe);
                                                    su.ajouter(u2);
                                                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                                                    alert3.setContentText("Transporteur ajouté avec succés!");
                                                    alert3.show();

                                                }

                                                try {
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSinup.fxml"));
                                                    Parent rootU;

                                                    rootU = loader.load();

                                                    Scene sceneU = new Scene(rootU);
                                                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                    sceneU.setFill(Color.TRANSPARENT);
                                                    appStage.setScene(sceneU);
                                                    appStage.show();
                                                } catch (IOException ex) {
                                                    System.out.println(ex.getMessage());
                                                }
                                            } else {
                                                Alert alertType = new Alert(Alert.AlertType.ERROR, "Veuillez choisir une photo !");
                                                alertType.show();
                                            }
                                        } else {
                                            Alert alertType = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un type d'utilisateur !");
                                            alertType.show();
                                        }
                                    } else {
                                        afficherErreurGenre();
                                    }
                                } else {

                                    Alert alertName = new Alert(Alert.AlertType.ERROR, "compte exist");
                                    Optional<ButtonType> resultName = alertName.showAndWait();
                                    if (resultName.isPresent() && resultName.get() == ButtonType.OK) {
                                        username.setText("");
                                        pasworrd.setText("");

                                    }

                                }
                            } else {
                                Alert alerttMail = new Alert(Alert.AlertType.ERROR, "Le champ inscription email doit être une adresse courriel valide !");
                                alerttMail.show();
                                email.setText("");
                                alerttMail.show();
                                email.setText("");
                                email.getStyleClass().add("error-field");

                            }
                        } else {
                            Alert alerttPhone = new Alert(Alert.AlertType.ERROR, "PhoneNumber  invalide !");
                            alerttPhone.show();
                            phoneNumber.setText("");
                            phoneNumber.getStyleClass().add("error-field");

                        }
                    } else {
                        Alert alerttPhone = new Alert(Alert.AlertType.ERROR, "PhoneNumber  invalide !");
                        alerttPhone.show();
                        phoneNumber.setText("");
                        phoneNumber.getStyleClass().add("error-field");

                    }
                } else {
                    Alert alertPassword = new Alert(Alert.AlertType.ERROR, "username invalide !");
                    alertPassword.show();
                    username.setText("");
                    username.getStyleClass().add("error-field");

                }
            } else {
                Alert alertPassword = new Alert(Alert.AlertType.ERROR, "Password invalide !");
                alertPassword.show();
                pasworrd.setText("");
                pasworrd.getStyleClass().add("error-field");

            }

        }

    }

    public boolean verifierPassword(String password) {

        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        return true;
    }

    public boolean verifierUsername(String username) {

        if (username.length() < 5) {
            return false;
        }
        if (username.length() > 12) {
            return false;
        }
        if (!username.matches("[a-zA-Z0-9]*")) {
            return false;
        }
        return true;
    }

    private boolean verifierType(String type) {
        if (type != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifierImage(byte[] image) {
        if (image != null) {
            return true;
        } else {
            return false;
        }
    }

    private void afficherErreurType() {

        Alert alertSexe = new Alert(Alert.AlertType.ERROR, "Veuillez choisir un type");
        alertSexe.show();
    }

}
