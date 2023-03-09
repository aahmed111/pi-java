/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.Date;
import objects.Reactions;

/**
 *
 * @author pc
 */
public class Avis {
   private int id_avis;
   private Date date_creation;
   private String commentaire;
   private Reactions reaction;

   private Annonce annonce;
   private User user;

    public Avis(int id_avis,  Reactions reaction,String commentaire,Date date_creation, User user, Annonce annonce) {
        this.id_avis = id_avis;
        this.reaction = reaction;
        this.commentaire = commentaire;
        this.date_creation = date_creation;
        this.user = user;
        this.annonce = annonce;
        
    }

    public Avis( Reactions reaction,String commentaire, User user,  Annonce annonce) {
       this.reaction = reaction; 
       this.commentaire = commentaire;
        
        this.user = user;
        this.annonce = annonce;
        
    }
   
    

   

    public Avis() {
    }

    public int getId_avis() {
        return id_avis;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Reactions getReaction() {
        return reaction;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public User getUser() {
        return user;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setReaction(Reactions reaction) {
        this.reaction = reaction;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Avis{" + "id_avis=" + id_avis + ", date_creation=" + date_creation + ", commentaire=" + commentaire + ", reaction=" + reaction + ", annonce=" + annonce + ", user=" + user + '}';
    }
    
    
    
   
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avis other = (Avis) obj;
        if (this.id_avis != other.id_avis) {
            return false;
        }
        return true;
    }
   
   
   
   
}
