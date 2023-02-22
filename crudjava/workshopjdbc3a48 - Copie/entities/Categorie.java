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
    private String description,type;

    public Categorie(int id_categorie, String description, String type) {
        this.id_categorie = id_categorie;
        this.description = description;
        this.type = type;
    }

    public Categorie(String description, String type) {
        this.description = description;
        this.type = type;
    }


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    @Override
    public String toString() {
        return "categorie{" + "id_categorie=" + id_categorie + ", type=" + type + ", description=" + description + '}';
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
