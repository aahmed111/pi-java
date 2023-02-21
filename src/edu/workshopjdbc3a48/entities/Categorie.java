/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author pc
 */
public class Categorie {
    private int id_categorie;
    private String nom,description;

    public Categorie(int id_categorie, String nom, String description) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
    }

    
    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_categorie=" + id_categorie + ", nom=" + nom + ", description=" + description + '}';
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
        final Categorie other = (Categorie) obj;
        if (this.id_categorie != other.id_categorie) {
            return false;
        }
        return true;
    }



}
