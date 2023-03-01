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
    private User id_user1;
     private String Nom_user;
    private Date date_envoie;
    private String description ; 
    
     private String Email;
    private Echange echange ;
 
    
    
    public Reclamation(int id, User id_user1, String Nom_user,  String description, String Email, Echange echange,Date date_envoie) {
        this.id = id;
        this.id_user1 = id_user1;
        this.Nom_user = Nom_user;
        this.date_envoie = date_envoie;
        this.description = description;
        this.Email = Email;
        this.echange = echange;
    }

    public Reclamation(String Nom_user, String description, String Email) {
        this.Nom_user = Nom_user;
        this.description = description;
        this.Email = Email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser1() {
        return id_user1;
    }

   

    public String getNom_user() {
        return Nom_user;
    }

    public void setNom_user(String Nom_user) {
        this.Nom_user = Nom_user;
    }

    public Date getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(Date date_envoie) {
        this.date_envoie = date_envoie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.id_user1);
        hash = 73 * hash + Objects.hashCode(this.Nom_user);
        hash = 73 * hash + Objects.hashCode(this.date_envoie);
        hash = 73 * hash + Objects.hashCode(this.description);
        hash = 73 * hash + Objects.hashCode(this.Email);
        hash = 73 * hash + Objects.hashCode(this.echange);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.Nom_user, other.Nom_user)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        if (!Objects.equals(this.id_user1, other.id_user1)) {
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

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user1=" + id_user1 + ", Nom_user=" + Nom_user + ", date_envoie=" + date_envoie + ", description=" + description + ", Email=" + Email + ", echange=" + echange + '}';
    }
   
 
    
    
    
   

   


   
   

    
   }