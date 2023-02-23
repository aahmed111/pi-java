/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.entities;

/**
 *
 * @author pc
 */
public class Article {

    private int id_article;
    private String date_ajout, proprietaire, estimation, description, image;

    public Article(int id_article, String date_ajout, String proprietaire, String estimation, String description, String image) {
        this.id_article = id_article;
        this.date_ajout = date_ajout;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
        this.description = description;
        this.image = image;
    }

    public Article(String date_ajout, String proprietaire, String estimation, String description) {
        this.date_ajout = date_ajout;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
        this.description = description;
    }

    public int getId_article() {
        return id_article;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public String getEstimation() {
        return estimation;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "article{" + "id_article=" + id_article + ", date_ajout=" + date_ajout + ", proprietaire=" + proprietaire + ", estimation=" + estimation + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Article other = (Article) obj;
        if (this.id_article != other.id_article) {
            return false;
        }
        return true;
    }

}
