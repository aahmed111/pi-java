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
    private User user1,user2;
    private Date date_envoie;
    private String description ; 
    private Echange echange ;

    public Reclamation(User user1, User user2, String description, Echange echange) {
        this.user1 = user1;
        this.user2 = user2;
        this.description = description;
        this.echange = echange;
    }

    public Reclamation(int id, User user1, User user2, String description, Echange echange, Date date_envoie) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.date_envoie = date_envoie;
        this.description = description;
        this.echange = echange;
    }
    public Reclamation( User user1, User user2, String description, Echange echange, Date date_envoie) {
       
        this.user1 = user1;
        this.user2 = user2;
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

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
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
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user1=" + user1 + ", user2=" + user2 + ", date_envoie=" + date_envoie + ", description=" + description + ", echange=" + echange + '}';
    }
    
     


   

    
   }