/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class Categorie {
    private int id_categorie;
    private String nom;
    private String description;
    private List<Article> articles;
     private  byte[] image;

    public Categorie(int id_categorie, String nom, String description,byte[] image) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
        this.image=image;
    }
  public Categorie(int id_categorie, String nom, String description) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
       
    }
   public Categorie(String nom, String description,byte[] image) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description = description;
        this.image=image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    

    public int getId_categorie() {
        return id_categorie;
    }

   

    public String getDescription() {
        return description;
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
public static boolean verifChampLettres(String str) {
    if (str == null || str.trim().isEmpty()) {
        // Si la chaîne est nulle, vide ou ne contient que des espaces, elle est invalide
        return false;
    }
    // On utilise une expression régulière pour vérifier si la chaîne contient uniquement des lettres alphabétiques
    return str.matches("[a-zA-Z]+");
}

    public byte[] getImageBytes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setImageView(ImageView imageView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




}