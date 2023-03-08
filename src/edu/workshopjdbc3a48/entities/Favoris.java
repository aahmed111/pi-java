/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Favoris {
  	private int id_favoris;
        private Date date_ajout;
        
        private User user;
        private ArrayList<Annonce> annonces;
        private Annonce annonce;

    public Favoris(int id_favoris, Date date_ajout, User user, Annonce annonce) {
        this.id_favoris = id_favoris;
        this.date_ajout = date_ajout;
        this.user = user;
        this.annonce = annonce;
    }

    public Favoris(Date date_ajout, User user, Annonce annonce) {
        this.date_ajout = date_ajout;
        this.user = user;
        this.annonce = annonce;
    }

    public Favoris(User user, Annonce annonce) {
        this.user = user;
        this.annonce = annonce;
    }
    
   

    public Favoris() {
    }

    public int getId_favoris() {
        return id_favoris;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public User getUser() {
        return user;
    }
public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(ArrayList<Annonce> annonces) {
        this.annonces = annonces;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
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
        final Favoris other = (Favoris) obj;
        if (this.id_favoris != other.id_favoris) {
            return false;
        }
        return true;
    }
        

   

        
}
