/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;


import static com.mysql.cj.MysqlType.BLOB;
import edu.workshopjdbc3a48.entities.Categorie;
import java.io.File;
import static java.sql.JDBCType.BLOB;
import static java.sql.Types.BLOB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.Blob;

/**
 *
 * @author pc
 */
public   abstract  class Article  {
  protected  int id_article;
   protected String description,type_article;
   protected String proprietaire;
  protected int estimation;
 protected String titre;
  protected Categorie c;
  protected Produit p;
protected Services s;
protected String image;
  Date date_ajout;
  List<Produit> list = new ArrayList<>();

    public Article(int id_article,Date date_ajout, String description,String  image, String proprietaire,int estimation ,String type_article) {
        this.id_article = id_article;
       
      this.image=image;
        this.date_ajout = date_ajout;
        this.description = description;
      
        this.type_article = type_article;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
        
      
    }
       public Article(int id_article, String description,String  image , String proprietaire,int estimation ,String type_article) {
        this.id_article = id_article;
       
       this.image=image;
        
        this.description = description;
        
        this.type_article = type_article;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
        
     
    }
       public Article(int id_article,  String description, String image, String proprietaire, int estimation, String type_article,String type,int id_categorie) {
this.id_article = id_article;
this.type_article=type;
this.description = description;
this.image = image;
this.proprietaire = proprietaire;
this.estimation = estimation;
this.type_article = type_article;
}



    public Article( String description,String image, String proprietaire,int estimation ,String type_article) {
        
        this.description = description;
      this.image=image;
        this.type_article = type_article;
        this.proprietaire = proprietaire;
        this.estimation = estimation;
    }
     public Article( String description,String image, String proprietaire,int estimation ) {
        
        this.description = description;
      this.image=image;
   
        this.proprietaire = proprietaire;
        this.estimation = estimation;
    }


    public String getTitre() {
        return titre;
    }

    public Categorie getC() {
        return c;
    }

    public void setC(Categorie c) {
        this.c = c;
    }

  
   
    
   

    


    

   

    public String getType_article() {
        return type_article;
    }

    public void setType_article(String type_article) {
        this.type_article = type_article;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    return "Article{" + 
           "id_article=" + id_article + 
           ", date_ajout=" + date_ajout + 
           ", description=" + description + 
           
           ", type_article=" + type_article + 
           ", proprietaire=" + proprietaire + 
           ", estimation=" + estimation + 
           ", type_produit=" + (this instanceof Produit ? ((Produit) this).getType_produit() : "N/A") +
           ", type_service=" + (this instanceof Services ? ((Services) this).getType_service() : "N/A") +
           "}";
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
