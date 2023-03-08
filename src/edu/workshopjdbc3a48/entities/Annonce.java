/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.Date;
import objects.Audience;

public class Annonce {
    private int id_annonce;
    private Audience audience;
    private String description,titre,condition_echange;
    private Date date_publication;
    
    private int totalReactions=0;
    private int nbcommentaires=0;
    
    private Article acticle;
    private User user;
    private Categorie categorie;
    
    
    
    
    public Annonce(String description, String condition_echange,String titre, Audience audience, User user, Article article,Categorie categorie) {
       
        this.description = description;
         this.condition_echange = condition_echange;
         this.titre= titre;
        this.audience = audience;
        this.user = user;
        this.acticle = article;
        this.categorie = categorie;       
        
    }
    
    
    public Annonce(int id_annonce,String description, String condition_echange,String titre, Audience audience,int totalReactions,int nbcommentaires , Date date_publication, User user, Article article,Categorie categorie ) {
        this.id_annonce = id_annonce;
        this.description = description;
         this.condition_echange = condition_echange;
         this.titre = titre;
        this.audience = audience;
        this.totalReactions = totalReactions;
        this.nbcommentaires = nbcommentaires;
        this.date_publication = date_publication;
        this.user = user;
        this.acticle = article;
        this.categorie = categorie;       
    }
    
    public Annonce(String description, String condition_echange,String titre, Audience audience,int totalReactions,int nbcommentaires , Date date_publication ) {
       
        this.description = description;
         this.condition_echange = condition_echange;
         this.titre = titre;
        this.audience = audience;
        this.totalReactions = totalReactions;
        this.nbcommentaires = nbcommentaires;
        this.date_publication = date_publication;
               
    }

    public Annonce(String description, String condition_echange,String titre, Audience audience) {
       
        this.description = description;
         this.condition_echange = condition_echange;
         this.titre = titre;
        this.audience = audience;
        
               
    }

    public Annonce(String description, String condition_echange,String titre) {
       
        this.description = description;
         this.condition_echange = condition_echange;
         this.titre = titre;
        
        
               
    }
    
 

    public Annonce() { }

    public int getId_annonce() {
        return id_annonce;
    }

    

    public String getDescription() {
        return description;
    }

    

    public Date getDate_publication() {
        return date_publication;
    }

   

    public void setDescription(String description) {
        this.description = description;
    }

   

   

    public String getCondition_echange() {
        return condition_echange;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    

    public void setCondition_echange(String condition_echange) {
        this.condition_echange = condition_echange;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

    public int getNbcommentaires() {
        return nbcommentaires;
    }

    public Audience getAudience() {
        return audience;
    }
    

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public void setNbcommentaires(int nbcommentaires) {
        this.nbcommentaires = nbcommentaires;
    }

    public Article getActicle() {
        return acticle;
    }

    public User getUser() {
        return user;
    }

    public void setActicle(Article acticle) {
        this.acticle = acticle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", audience=" + audience + ", description=" + description + ", titre=" + titre + ", condition_echange=" + condition_echange + ", date_publication=" + date_publication + ", totalReactions=" + totalReactions + ", nbcommentaires=" + nbcommentaires + ", acticle=" + acticle + ", user=" + user + ", categorie=" + categorie + '}';
    }

    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Annonce other = (Annonce) obj;
        if (this.id_annonce != other.id_annonce) {
            return false;
        }
        return true;
    }

   

    

    
    
}
