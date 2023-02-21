/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author abdel
 */
public class Article {

    protected int id_article, id_categorie;
    protected String  description, image, type_article;
    protected String proprietaire;
    protected int estimation;
    protected String titre;
    private Categorie c;
    private Date  date_ajout;

    public Article(int id_article, String description, String image, String proprietaire, int estimation, String type_article) {
        this.id_article = id_article;

       
        this.description = description;
        this.image = image;
        this.type_article = type_article;
        this.proprietaire = proprietaire;
        this.estimation = estimation;

    }

    public Article( String description, String image, String proprietaire, int estimation, String type_article) {
        
        this.description = description;
        this.image = image;
        this.type_article = type_article;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
    }

    public String getTitre() {
        return titre;
    }

    public String getType_article() {
        return type_article;
    }

    public void setType_article(String type_article) {
        this.type_article = type_article;
    }

    public int getId_article() {
        return id_article;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public Categorie getCategorie() {
        return c;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return " " + "id_article=" + id_article + ", date_ajout=" + date_ajout + ", description=" + description + ", image=" + image + ", type_article=" + type_article + ", proprietaire=" + proprietaire + ", estimation=" + estimation + ",type_article=" + type_article + " ";
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
        final Article other = (Article) obj;
        if (this.id_article != other.id_article) {
            return false;
        }
        return true;
    }

    

}
