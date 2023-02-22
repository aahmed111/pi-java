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
    private int id_produit;
 private String type_produit;
 
   
    
   

    public Produit(int id_article, Date date_ajout, String description, String  image, String proprietaire, int estimation, String type_article,String type_produit) {
        super(id_article, date_ajout,description,image, proprietaire, estimation, type_article);
        this.type_produit=type_produit;
    }
  public Produit(int id_article, String description, String  image, String proprietaire, int estimation, String type_article,String type_produit) {
        super(id_article,description,image, proprietaire, estimation, type_article);
        this.type_produit=type_produit;
    }
    
    public Produit( String description, String  image ,String proprietaire, int estimation,String type_article,String type_produit) {
        super( description, image, proprietaire, estimation,type_article);
        this.type_produit=type_produit;
   
    }
    
     public Produit( String description, String  image ,String proprietaire, int estimation,String type_produit) {
        super( description, image, proprietaire, estimation);
        this.type_produit=type_produit;
   
    }
    
    
    
    
    
    
    
    
    
    
    
    public Produit(int id_article ,String description, String  image ,String proprietaire, int estimation,String type_article,String type_produit,String type,int id_categorie) {
        super(id_article, description, image, proprietaire, estimation,type_article,type,id_categorie );
        this.type_produit=type_produit;
   
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

   
    

   

    

  
    
   
   
    

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }


   

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    

    public int getId_produit() {
        return id_produit;
    }
  
  /*public String toString() {
        return "produit{" + "id_article=" + id_article + ", date_ajout=" + date_ajout + ", description=" + description + ", image=" + image + ", proprietaire=" + proprietaire + ", estimation=" + estimation + '}';
    }*/

    /*public Produit( String date_ajout, String proprietaire, int estimation, String description, String image) {
        super(date_ajout, proprietaire, estimation, description, image, type_article);
        
    }*/

   

  


   
  

    
      
    
    
    
}
