/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;


import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Favoris;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceAnnonce;

import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceFavoris;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import objects.Audience;
import objects.Reactions;
//import objects.Audience;
/**
 * FXML Controller class
 *
 * @author abdel
 */
public class AnnonceController implements Initializable {

  @FXML
    private ImageView imgprofil;
    @FXML
    private Label username;
    @FXML
    private ImageView imgverified;
    @FXML
    private Label date_ajout;
    @FXML
    private ImageView imgstatut;
    private Label caption;
    @FXML
    private ImageView imgpost;
    @FXML
    private HBox reactionscontainer;
    @FXML
    private ImageView imglike;
    @FXML
    private ImageView imglove;
    @FXML
    private ImageView imgcare;
    @FXML
    private ImageView imghaha;
    @FXML
    private ImageView imgwow;
    @FXML
    private ImageView imgsad;
    @FXML
    private ImageView imgangry;
    @FXML
    private Label Nb_Reactions;
    @FXML
    private Label nb_comments;
    @FXML
    private HBox likecontainer;
    @FXML
    private Label caption1;
    @FXML
    private Label caption2;
    private Annonce annonce;

    private long startTime = 0;
    @FXML
    private ImageView imgreaction;
    @FXML
    private Label reactionName;
    private Reactions currentReaction;
    @FXML
    private Label comment;
    @FXML
    private ImageView imgsaved;
    @FXML
    private Label nomsaved;
    @FXML
    private HBox hboxCommentaire;
    @FXML
    private ImageView imgprofil1;
    @FXML
    private HBox commenter;

   @FXML
    private void onLikeContainerReleased(javafx.scene.input.MouseEvent event) {
        if (System.currentTimeMillis() - startTime > 500) {
            reactionscontainer.setVisible(true);
        } else {
            if (reactionscontainer.isVisible()) {
                reactionscontainer.setVisible(false);
            }

            if (currentReaction == Reactions.NON) {
                setReaction(Reactions.LIKE);
            } else {
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    private void onLikeContainerPressed(javafx.scene.input.MouseEvent event) {
        startTime = System.currentTimeMillis();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commenter.setVisible(true);

        hboxCommentaire.setVisible(false);

        hboxCommentaire.setManaged(false);
        setData(getAnnonce());
    }

    public Annonce getAnnonce() {
        ServiceAnnonce sa = new ServiceAnnonce();
        Annonce annonce = sa.getOneById(15);
        return annonce;
    }
    // service artice et user ayant les 2 id pour pouvoir accéder aux photos .

    public void setData(Annonce annonce) {

        this.annonce = annonce;
        Image img;
        // img = new Image(getClass().getResourceAsStream(annonce.getUser().getImg()));
        // imgprofil.setImage(img);
        // username.setText(annonce.getUser().getNom());

        date_ajout.setText(annonce.getDate_publication().toString());

        if (annonce.getAudience() == Audience.PUBLIQUE) {
            img = new Image(getClass().getResourceAsStream(Audience.PUBLIQUE.getImgSrc()));
        } else {
            img = new Image(getClass().getResourceAsStream(Audience.MOI_UNIQUEMENT.getImgSrc()));
        }
        imgstatut.setImage(img);

        caption1.setText(annonce.getDescription());

        if (annonce.getCondition_echange().isEmpty()) {
            caption2.setManaged(false);

        } else {
            caption2.setText(annonce.getCondition_echange());
        }
        ServiceArticle sa = new ServiceArticle();
        Article article = sa.getOneById(12);

        byte[] image = article.getImage();
        if (image != null) {
            InputStream inputStream = new ByteArrayInputStream(image);
            Image imageArticle = new Image(inputStream);
            imgpost.setImage(imageArticle);
        } else {
            imgpost.setVisible(false);
            imgpost.setManaged(false);
        }

        Nb_Reactions.setText(String.valueOf(annonce.getTotalReactions())); //(count * from avis)
        nb_comments.setText(annonce.getNbcommentaires() + " comments");

        currentReaction = Reactions.NON;
    }

    // service artice et user ayant les 2 id pour pouvoir accéder aux photos .
    @FXML
    private void onReactionImgPressed(javafx.scene.input.MouseEvent event) {
        switch (((ImageView) event.getSource()).getId()) {
            case "imglove":
                setReaction(Reactions.LOVE);
                break;
            case "imgcare":
                setReaction(Reactions.CARE);
                break;
            case "imghaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgwow":
                setReaction(Reactions.WOW);
                break;
            case "imgsad":
                setReaction(Reactions.SAD);
                break;
            case "imgangry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionscontainer.setVisible(false);

    }

    public void setReaction(Reactions reaction) {
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgreaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if (currentReaction == Reactions.NON) {
            annonce.setTotalReactions(annonce.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if (currentReaction == Reactions.NON) {
            annonce.setTotalReactions(annonce.getTotalReactions() - 1);
        }

        Nb_Reactions.setText(String.valueOf(annonce.getTotalReactions()));
    }

    @FXML
    private void ajouterAuFavoris(javafx.scene.input.MouseEvent event) throws SQLException {
        Image image = new Image(getClass().getResourceAsStream("/img/saved.PNG"));
        imgsaved.setImage(image);
        nomsaved.setText("ajoutée au favoris");
        nomsaved.setTextFill(Color.web("#E1306C"));

        ServiceFavoris sf = new ServiceFavoris();
        ServiceAnnonce sa = new ServiceAnnonce();
        ServiceUser su = new ServiceUser();
        User u = su.getOneById(5);
        Annonce a = sa.getOneById(5);
        Favoris f = new Favoris(u,a); //commment avoir id de l'annonce que je clique sur elle et comment avoir l'id du user connecté.
        sf.ajouter(f);
    }

    private boolean isCommentaireVisible = false;

    @FXML
    private void commenterReleased(javafx.scene.input.MouseEvent event) {
        isCommentaireVisible = !isCommentaireVisible;
        hboxCommentaire.setVisible(isCommentaireVisible);
        hboxCommentaire.setManaged(isCommentaireVisible);
        System.out.print("gtbtyyt");
    }

    @FXML
    private void commenterPressed(javafx.scene.input.MouseEvent event) {

    }
    
}
