package edu.workshopjdbc3a48.gui;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CaptchaController {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;

    @FXML
    private VBox captchaBox;

    @FXML
    private TextField captchaInput;

    @FXML
    private Button submitButton;

    @FXML
    private Label captchaCode;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField tfcodeal;

    private String generatedCode;

   @FXML
private void initialize() {
    refreshCaptcha();
}

private static String userInput;

    public static String getUserInput() {
        return userInput;
    }
  private void refreshCaptcha() {
    // generate a new captcha code
    String captchaCode = generateCaptchaCode();
    // set the new captcha code in the Label component
    this.captchaCode.setText(captchaCode);
    // store the new captcha code in the generatedCode field
    this.generatedCode = captchaCode;
    // set the new captcha code in the code aléatoire field
    this.tfcodeal.setText(captchaCode);
}

@FXML
private void submitCaptcha() {
    // get the captcha code and user input
    String captchaCode = getCaptchaCode();
    String userInput = this.captchaInput.getText().trim();
    // check if the input is empty
    if (userInput.isEmpty()) {
        this.messageLabel.setText("Veuillez saisir le captcha");
    } else if (userInput.equalsIgnoreCase(captchaCode)) {
        // the input is correct
        ((Stage) captchaInput.getScene().getWindow()).close(); // fermer la fenêtre du captcha
        this.messageLabel.setText("Captcha validé !");
        // redirect to produitservice.fxml
       
    } else {
        // the input is incorrect
        this.messageLabel.setText("Captcha invalide !");
        // clear the input field
        this.captchaInput.setText("");
        // optionally, disable the submit button to prevent further attempts
        this.submitButton.setDisable(true);
    }
}

private String generateCaptchaCode() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < CODE_LENGTH; i++) {
        char c = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        sb.append(c);
    }
    return sb.toString();
}

private String getCaptchaCode() {
    return this.generatedCode;
}
}