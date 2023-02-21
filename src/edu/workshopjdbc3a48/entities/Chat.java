/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Chat {
    
   private int id_chat;
   private Date date_envoie;
   private User user1,user2;
 private String nom ;

    public Chat(int id_chat, Date date_envoie, User user1, User user2, String nom) {
        this.id_chat = id_chat;
        this.date_envoie = date_envoie;
        this.user1 = user1;
        this.user2 = user2;
        this.nom = nom;
    }

    public Chat(User user1, User user2, String nom) {
        this.user1 = user1;
        this.user2 = user2;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
  

  


    public Chat(Date date_envoie) {
        this.date_envoie = date_envoie;
    }

    public int getId_chat() {
        return id_chat;
    }

    

    public Date getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(Date date_envoie) {
        this.date_envoie = date_envoie;
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


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_chat;
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
        final Chat other = (Chat) obj;
        if (this.id_chat != other.id_chat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Chat{" + "id_chat=" + id_chat + ", date_envoie=" + date_envoie + ", user1=" + user1 + ", user2=" + user2 + ", nom=" + nom + '}';
    }

   
   
}