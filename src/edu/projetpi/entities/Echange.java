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
    private int id_user1;
    private int id_user2;
    private int id_article1;
    private int id_article2;

    public int getId_article1() {
        return id_article1;
    }

    public void setId_article1(int id_article1) {
        this.id_article1 = id_article1;
    }

    public int getId_article2() {
        return id_article2;
    }

    public void setId_article2(int id_article2) {
        this.id_article2 = id_article2;
    }
    
    public Echange(){
    this.date_echange=new Date();
    }

    public int getId_client1() {
        return id_user1;
    }

    public void setId_client1(int id_client1) {
        this.id_user1 = id_client1;
    }

    public int getId_client2() {
        return id_user2;
    }

    public void setId_client2(int id_client2) {
        this.id_user2 = id_user2;
    }
    
    

    public Echange(int id_echange, String statut,Date date_echange,int id_article1,
            int id_article2,int id_client1,int id_client2
            ) {
        this.id_echange = id_echange;
        this.date_echange = date_echange;
        this.statut = statut;
        this.id_article1=id_article1;
        this.id_article2=id_article2;
        this.id_user1=id_client1;
        this.id_user2=id_client2;
    }
    
     public Echange( String statut,int id_article1,
            int id_article2,int id_client1,int id_client2
            ) {
        //this.id_echange = id_echange;
        this.date_echange = new Date();
        this.statut = statut;
        this.id_article1=id_article1;
        this.id_article2=id_article2;
        this.id_user1=id_client1;
        this.id_user2=id_client2;
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
