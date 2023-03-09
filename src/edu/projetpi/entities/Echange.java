/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.entities;

import java.util.Date;

/**
 *
 * @author pc
 */
public class Echange {

    private int id_echange;
    private Date date_echange;
    private String statut;
    private User user1;
    private User user2;
    private Article article1;
    private Article article2;
    private int confirmation;

    public Article getArticle1() {
        return article1;
    }

    public void setArticle1(Article article1) {
        this.article1 = article1;
    }

    public Article getArticle2() {
        return article2;
    }

    public void setArticle2(Article article2) {
        this.article2 = article2;
    }
    

   
    
    public Echange(){
    this.date_echange=new Date();
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

   

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public Echange(int id_echange, Date date_echange, String statut, User user1, User user2, Article article1, Article article2, int confirmation) {
        this.id_echange = id_echange;
        this.date_echange = date_echange;
        this.statut = statut;
        this.user1 = user1;
        this.user2 = user2;
        this.article1 = article1;
        this.article2 = article2;
        this.confirmation = confirmation;
    }
    
    
    

    public Echange(int id_echange, String statut,Date date_echange,Article article1,
            Article article2,User user1, User usert2
            ) {
        this.id_echange = id_echange;
        this.date_echange = date_echange;
        this.statut = statut;
        this.article1=article1;
        this.article2=article2;
        this.user1=user1;
        this.user2=user2;
    }
    
     public Echange( String statut,Article article1,
            Article article2,User user1,User user2
            ) {
        //this.id_echange = id_echange;
        this.date_echange = new Date();
        this.statut = statut;
        this.article1=article1;
        this.article2=article2;
        this.user1=user1;
        this.user2=user2;
    }

    public Echange(String statut) {
        this.statut = statut;
    }
    
    public Echange(Date date_echange, String statut) {
        date_echange = new Date();
        this.statut = statut;
    }

    public Echange(int id_echange, String statut) {
        this.id_echange = id_echange;
        this.statut = statut;
    }
    

    public int getId_echange() {
        return id_echange;
    }

    public Date getDate_echange() {
        return date_echange;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Ref : "+id_echange+"\t"+"statut : "+statut+"\t"+"\n"+"le "+date_echange;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Echange other = (Echange) obj;
        if (this.id_echange != other.id_echange) {
            return false;
        }
        return true;
    }

}
