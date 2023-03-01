/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;

/**
 *
 * @author abdel
 */
public class Reservation {
    private int id_reservation ,confirmation ;
    private User client;
    private Article article ;
    private Echange echange ;
    private Date date ;
    private Agence agence;

    public Reservation(int id_reservation,  User client,int confirmation , Article article, Echange echange,Date date,Agence agence ) {
        this.id_reservation = id_reservation;
        this.client = client;
        this.article = article;
        this.echange = echange;
        this.confirmation = confirmation;
        this.date =date ;
        this.agence=agence;
    }
        public Reservation(  User client,int confirmation , Article article, Echange echange,Date date,Agence agence ) {
       
        this.client = client;
        this.article = article;
        this.echange = echange;
        this.confirmation = confirmation;
        this.date =date ;
        this.agence=agence;
    }
              public Reservation(  User client,int confirmation , Article article, Echange echange,Agence agence ) {
       
        this.client = client;
        this.article = article;
        this.echange = echange;
        this.confirmation = confirmation;
       
        this.agence=agence;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Echange getEchange() {
        return echange;
    }

    public void setEchange(Echange echange) {
        this.echange = echange;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id_reservation;
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
        final Reservation other = (Reservation) obj;
        if (this.id_reservation != other.id_reservation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", confirmation=" + confirmation + ", client=" + client + ", article=" + article + ", echange=" + echange + ", date=" + date + ", agence=" + agence + '}';
    }

   
   
    
}
