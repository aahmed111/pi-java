/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Avis;
import edu.workshopjdbc3a48.entities.Favoris;
import edu.workshopjdbc3a48.entities.User;


import edu.workshopjdbc3a48.services.ServiceAnnonce;
import edu.workshopjdbc3a48.services.ServiceAvis;
import edu.workshopjdbc3a48.services.ServiceFavoris;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.xml.bind.DatatypeConverter;
import objects.Audience;
import objects.Reactions;



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
    private Label caption1;
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
     private HBox comhbox;
    @FXML
    private HBox commenter;
    @FXML
    private HBox reactionscontainer1;
    @FXML
    private ImageView imgclient;
    private TextField cmt;
    @FXML
    private ImageView sendcmt;
    @FXML
    private TextArea ccmmtt;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label condition;
      private int Id_Connector;
    @FXML
    private HBox commenter1;

    public int getId_Connector() {
        return Id_Connector;
    }

    public void setId_Connector(int Id_Connector) {
        this.Id_Connector = Id_Connector;
    }
    
    
@FXML
private void onLikeContainerReleased(javafx.scene.input.MouseEvent event) {
    if(System.currentTimeMillis() - startTime > 500){
            reactionscontainer.setVisible(true);
        }
    else { if(reactionscontainer.isVisible()){
                reactionscontainer.setVisible(false);
            }

            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
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
        
    setData(getAnnonce());
    }
    public Annonce getAnnonce(){
    ServiceAnnonce sa = new ServiceAnnonce();
    Annonce annonce1 =sa.getOneById(18);
        return annonce1;
    } 
    // service artice et user ayant les 2 id pour pouvoir accéder aux photos .
    
    public void setData(Annonce annonce1){
        
        
        this.annonce = annonce1;
       byte[] imageData = annonce.getUser().getPhoto();
            if (imageData != null) {
                try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
                    Image image = new Image(inputStream);
                    
                     imgprofil.setImage(image);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    
        username.setText(annonce.getUser().getUsername());
        Image img;
        date_ajout.setText(annonce.getDate_publication().toString());
        
         if(annonce.getAudience() == Audience.PUBLIQUE){
            img = new Image(getClass().getResourceAsStream(Audience.PUBLIQUE.getImgSrc()));
        }else{
            img = new Image(getClass().getResourceAsStream(Audience.MOI_UNIQUEMENT.getImgSrc()));
        }
        imgstatut.setImage(img);
        
        description.setText(annonce.getDescription());
        titre.setText(annonce.getTitre());
        
         if(annonce.getCondition_echange().isEmpty()){
             condition.setManaged(false);
            
        }else{
         condition.setText(annonce.getCondition_echange());   
        }
  /*     Article article = new Article();
byte[] imageBytes = article.getImage();

// Vérifier que l'image n'est pas null et qu'elle contient des données
if (imageBytes != null && imageBytes.length > 0) {
    // Créer une instance de la classe Image en utilisant un objet PixelReader*/
    img = new Image(getClass().getResourceAsStream("/img/img2.jpg"));
        
            imgpost.setImage(img);
      /*  }else{ 
            imgpost.setVisible(false);
           imgpost.setManaged(false);*/
      //  }  
         

           Nb_Reactions.setText(String.valueOf(annonce.getTotalReactions())); //(count * from avis)
        nb_comments.setText(annonce.getNbcommentaires() + " comments");
        
        currentReaction = Reactions.NON;
     }
   
    // service artice et user ayant les 2 id pour pouvoir accéder aux photos .

  @FXML
    private void onReactionImgPressed(javafx.scene.input.MouseEvent event) {
         switch (((ImageView) event.getSource()).getId()){
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

    public void setReaction(Reactions reaction){
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgreaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));
        
         if(currentReaction == Reactions.NON){
            annonce.setTotalReactions(annonce.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
            annonce.setTotalReactions(annonce.getTotalReactions() - 1);
        }

        Nb_Reactions.setText(String.valueOf(annonce.getTotalReactions()));
    }

   

    @FXML
    private void ajouterAuFavoris(javafx.scene.input.MouseEvent event) {
        Image image = new Image(getClass().getResourceAsStream("/img/saved.PNG"));
        imgsaved.setImage(image);
        nomsaved.setText("ajoutée au favoris");
        nomsaved.setTextFill(Color.web("#E1306C"));
        
       /* ServiceFavoris sf = new ServiceFavoris();
        ServiceAnnonce sa = new ServiceAnnonce();
        ServiceUser su = new ServiceUser();
        Favoris f = new Favoris(su.getOneById(Id_Connector),sa.getOneById(0)); //commment avoir id de l'annonce que je clique sur elle et comment avoir l'id du user connecté.
        sf.ajouter(f);*/
    }


    @FXML
    private void commentaireReleased(javafx.scene.input.MouseEvent event) {
         if(System.currentTimeMillis() - startTime > 500){
            reactionscontainer1.setVisible(true);
        }
         
    }

    @FXML
    private void commentairePressed(javafx.scene.input.MouseEvent event) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    private void validateComment(javafx.scene.input.MouseEvent event) {
   
 // liste de mots vulgaires prédéfinie
    
    
     String comm = ccmmtt.getText().toLowerCase();
    List<String> vulgarWords = Arrays.asList("à chier","à couilles rabattues","à deux trois poils de cul",
            "à la con","à la mords-moi-le-nœud","à la roule-moi les couilles dans la laitue","à un poil de cul",
            "alibofialler aux putes","aller chier dans sa caisse","aller libérer Mandela","aller niquer sa mère",
            "aller se faire empapaouter","aller se faire enculer","aller se faire foutre","aller se faire mettre"
            ,"aller voir la veuve poignet","aller voir madame cinq doigts","allez vous faire foutre","archicon"
            ,"archifoutre","arriver comme le marquis de CouilleVerte","asphalteuse","astiquer","avoir de la chatte"
            ,"avoir de la merde dans les yeux","avoir de la moule");
    for(String word : vulgarWords) {
        if(comm.contains(word)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Commentaire non valide");
            alert.setHeaderText("Le commentaire contient des mots vulgaires");
            alert.setContentText("Veuillez modifier votre commentaire.");
            alert.showAndWait();
           
        }
        else {
        reactionscontainer1.setVisible(false);
    /*   ServiceUser su = new ServiceUser();
        User user=su.getOneById(92);
        ServiceAvis sa = new ServiceAvis();
        ServiceAnnonce s =new ServiceAnnonce();
        Annonce ann=s.getOneById(19);
        Avis avis = new Avis(Reactions.NON,comm,user,ann);
        sa.ajouter(avis); */
        }
    }   
   
}

    @FXML
    private void echanger(ActionEvent event) {
    }
}







    
    
     
    

    

   
   
   
            








        
        
        
        
        
       
    

