/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pc
 */
 
   public class Reclamation {

    
    private int id;
    private User user1,Nom_user;
   
    private Date date_envoie;
    private String description ; 
    
     private String Email;
    private Echange echange ;
   

    public Reclamation(int id, User user1, User Nom_user, String description , String Email, Echange echange, Date date_envoie) {
        this.id = id;
        this.user1 = user1;
        this.Nom_user = Nom_user;
        
        this.description = description;
        this.Email = Email;
        this.echange = echange;
        this.date_envoie = date_envoie;
    }

    public Reclamation(int id) {
        this.id = id;
    }
    
    
    
    
    
    
    
    public Reclamation(User user1, User Nom_user, String description, Echange echange) {
        this.user1 = user1;
        this.Nom_user = Nom_user;
        this.description = description;
        this.echange = echange;
    }

    public Reclamation(String description, String Email) {
        this.description = description;
        this.Email = Email;
    }
    


    public Reclamation(int id, User user1, User user2, String description, Echange echange, Date date_envoie) {
        this.id = id;
        this.user1 = user1;
        this.Nom_user = Nom_user;
        this.date_envoie = date_envoie;
        this.description = description;
        this.echange = echange;
    }
    public Reclamation( User user1, User user2, String description, Echange echange, Date date_envoie) {
       
        this.user1 = user1;
        this.Nom_user = Nom_user;
        this.date_envoie = date_envoie;
        this.description = description;
        this.echange = echange;
    }

    public int getId() {
        return id;
    }

  

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User Nom_user() {
        return Nom_user;
    }

    public void Nom_user(User user2) {
        this.Nom_user = user2;
    }

    public Date getDate_envoie() {
        return date_envoie;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Echange getEchange() {
        return echange;
    }

    public void setEchange(Echange echange) {
        this.echange = echange;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user1=" + user1 + ", Nom_user=" + Nom_user + ", date_envoie=" + date_envoie + ", description=" + description + ", echange=" + echange + ", Email=" + Email + '}';
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.user1, other.user1)) {
            return false;
        }
        if (!Objects.equals(this.Nom_user, other.Nom_user)) {
            return false;
        }
        if (!Objects.equals(this.date_envoie, other.date_envoie)) {
            return false;
        }
        if (!Objects.equals(this.echange, other.echange)) {
            return false;
        }
        return true;
    }

  
     


   
   

    
   }