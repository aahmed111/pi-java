/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.gui;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class Receipt extends Application {

    private TextField nameField;
    private TextField amountField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Receipt Generator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);

        nameField = new TextField();
        GridPane.setConstraints(nameField, 1, 0);

        Label amountLabel = new Label("Amount:");
        GridPane.setConstraints(amountLabel, 0, 1);

        amountField = new TextField();
        GridPane.setConstraints(amountField, 1, 1);

        Button generateButton = new Button("Generate Receipt");
        GridPane.setConstraints(generateButton, 1, 2);

        generateButton.setOnAction(e -> {
            try {
                createPDF();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        grid.getChildren().addAll(nameLabel, nameField, amountLabel, amountField, generateButton);

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createPDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        PDType0Font font = PDType0Font.load(document, new File("C:\\Users\\zayne\\Downloads\\static\\OpenSans"));
        
        float fontSize = 12;

        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);

        String name = nameField.getText();
        String amount = amountField.getText();

        contentStream.showText("Receipt for " + name);
        contentStream.newLine();
        contentStream.setFont(font, fontSize);
        contentStream.showText("Amount: $" + amount);

        contentStream.endText();

        contentStream.close();

        document.save("receipt.pdf");
        document.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
