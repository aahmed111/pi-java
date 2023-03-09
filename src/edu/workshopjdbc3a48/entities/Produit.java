/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Louay
 */
public class Produit  extends Article{
    
 private String etat;
 private int poids;
 private String duree_de_vie;
 

   

   public Produit(  String description, String type_article, int estimation, byte[] image, Date date_ajout, Categorie c, String nom,int poids,String etat,  String duree_de_vie) {
        super( description, type_article, estimation, image,  nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }
   public Produit( String description, String type_article, int estimation, byte[] image,  String nom,int poids,String etat,  String duree_de_vie) {
        super(description, type_article, estimation, image,  nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }
    public Produit( String description, String type_article, int estimation,   String nom,int poids,String etat,  String duree_de_vie) {
        super(description, type_article, estimation,   nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }
    
    public Produit( String description, String type_article, int estimation, byte[] image, Date date_ajout, String nom,int poids,String etat,  String duree_de_vie) {
        super(description, type_article, estimation, image, date_ajout,  nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }

 /*public Produit(  String description, String type_article, int estimation, byte[] image,  Date date_ajout, String nom,int poids,String etat,  String duree_de_vie) {
        super( description, type_article, estimation, image,  date_ajout, nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }*/
public Produit( int id_article, String description, String type_article, int estimation, byte[] image, User u,Date date_ajout, String nom,int poids,String etat,  String duree_de_vie) {
        super(id_article, description, type_article, estimation, image,  nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }
   public Produit( int id_article, String description, String type_article, int estimation, byte[] image,  User user, String nom, int poids,String etat, String duree_de_vie) {
        super(id_article,description, type_article, estimation, image,user, nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
       
    }
      public Produit( int id_article, String description, String type_article, int estimation, byte[] image,  Date date_ajout, String nom, int poids,String etat, String duree_de_vie) {
        super(id_article, description, type_article, estimation, image,  date_ajout, nom);
        this.etat = etat;
        this.poids = poids;
        this.duree_de_vie = duree_de_vie;
    }
       public Produit(  String type_article,byte[] image) {
        super(  type_article, image );
        
       this.type_article=type_article;
        this.image=image;
         
    
      
    }
 
 
  

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getDuree_de_vie() {
        return duree_de_vie;
    }

    public void setDuree_de_vie(String duree_de_vie) {
        this.duree_de_vie = duree_de_vie;
    }

   
    

 
    



  

   
    

   
  

    
      
    
    
    
}