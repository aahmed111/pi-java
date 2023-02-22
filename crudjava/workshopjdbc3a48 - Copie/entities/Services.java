/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.io.File;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Louay
 */
public class Services  extends Article {
    private int id_service;
       private String type_service;
      private Article a;

    public Services(int id_article, Date date_ajout, String description,String  image, String proprietaire, int estimation, String type_article,String type_service) {
        super(id_article, date_ajout,description,image, proprietaire, estimation, type_article);
        this.type_service=type_service;
    }

    public Services(int id_article,  String description,String  image, String proprietaire, int estimation, String type_article,String type_service) {
        super(id_article, description,image, proprietaire, estimation, type_article);
        this.type_service=type_service;
    }
    
    public Services(String description,String  image, String proprietaire, int estimation,String type_article,String type_service) {
        super( description, image, proprietaire, estimation,type_article);
        this.type_service=type_service;
    }
    
    
    
    
    
     public Services(String description,String  image, String proprietaire, int estimation,String type_service) {
        super( description, image, proprietaire, estimation);
        this.type_service=type_service;
    }
    public String getType_service() {
        return type_service;
    }
 public Services(int id_article ,String description, String  image ,String proprietaire, int estimation,String type_article,String type_produit,String type,int id_categorie) {
        super(id_article, description, image, proprietaire, estimation,type_article,type,id_categorie );
        this.type_service=type_service;
   
    }
    
    
   

   

      

    /*@Override
   public String toString() {
        return "service{" + "id_article=" + id_article + ", date_ajout=" + date_ajout + ", description=" + description + ", image=" + image + ", proprietaire=" + proprietaire + ", estimation=" + estimation + '}';
    }*/

     


    

    
   

    
    
    
    
}
