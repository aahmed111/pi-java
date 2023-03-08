/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;
import java.util.Arrays;

import java.util.Date;

/**
 *
 * @author pc
 */
public   abstract  class Article  {

  protected  int id_article;
   protected String description,type_article;
  
  protected int estimation;
 
protected byte[] image;
protected Produit p;
protected Service s;

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

 
  protected Categorie c;
  private  User user ;
  private Date date_ajout ;
      private String nom;
      

    public Article(int id_article, String description, String type_article, int estimation, byte[] image, Categorie c, User user, Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        this.c = c;
        this.user = user;
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
    public Article(int id_article, String description,  int estimation, byte[] image, Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
  
        this.estimation = estimation;
        this.image = image;
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
     public Article(int id_article, String description,  int estimation, byte[] image, Categorie c,Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
  
        this.estimation = estimation;
        this.image = image;
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
     public Article(String description,  int estimation, byte[] image, String nom) {
       
        this.description = description;
  
        this.estimation = estimation;
        this.image = image;
        
        this.nom = nom;
    }
    public Article(int id_article, String description, String type_article, int estimation, byte[] image, Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
     public Article(int id_article, String description, String type_article, int estimation, byte[] image,  String nom) {
        this.id_article = id_article;
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
    public Article(int id_article, String description, String type_article, int estimation, byte[] image,Categorie c, Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
    

    public Article(String description, String type_article, int estimation, byte[] image, Date date_ajout, Categorie c,  String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        this.c = c;
   
        this.date_ajout = date_ajout;
        this.nom = nom;
          
    }
    public Article(String description, String type_article, int estimation, byte[] image, Date date_ajout,  String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
      
   
        this.date_ajout = date_ajout;
        this.nom = nom;
          this.id_article = id_article;
    }
   
    public Article(String description, String type_article, int estimation, byte[] image, Categorie c, User user, String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
         this.image=image;
        this.c = c;
        this.user = user;
        this.nom = nom;
    }
 public Article(String description, String type_article, int estimation, byte[] image, String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
     
        this.nom = nom;
    }

    /*public Article(String description, String type_article, int estimation, byte[] image, Date date_ajout, String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
        this.image = image;
        this.date_ajout = date_ajout;
        this.nom = nom;
    }*/
     public Article(String description, String type_article, int estimation,  Date date_ajout, String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
      
        this.date_ajout = date_ajout;
        this.nom = nom;
    }
     public Article(String description, String type_article, int estimation,   String nom) {
        this.description = description;
        this.type_article = type_article;
        this.estimation = estimation;
      
        this.nom = nom;
    }
 public Article( String type_article,byte[] image) {
      this.type_article=type_article;
        this.image = image;
     
        
    }
  

    public int getId_article() {
        return id_article;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_article() {
        return type_article;
    }

    public void setType_article(String type_article) {
        this.type_article = type_article;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    

    public Categorie getC() {
        return c;
    }

    public void setC(Categorie c) {
        this.c = c;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_article;
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


 
   
   
   
   
   
   
   
   
  
/*public List<Article> getArticleByCategorie(getAll() , Categorie categorie) {
    List<Article> list = new ArrayList<>();
    return list.stream()
            .filter(article -> {
                if (article instanceof Produit) {
                    return ((Produit) article).getC().equals(c);
                } else if (article instanceof Service) {
                    return ((Service) article).getC().equals(c);
                } else {
                    return article.getC().equals(c);
                }
            })
            .collect(Collectors.toList());
}


public List<Article>getallbyclient(username)
{
    
}
*/
    
 
   @Override
public String toString() {
    String dureeStr = (this instanceof Service && ((Service) this).getDuree() != null) ? ((Service) this).getDuree().toString() : "N/A";
    return "Article{" +
            "id_article=" + id_article +
            ", date_ajout=" + date_ajout +
            ", description='" + description + '\'' +
            ", type_article='" + type_article + '\'' +
            ", estimation=" + estimation +
             ", image=" + image +
             ", nom=" + nom +
            ", etat=" + (this instanceof Produit ? ((Produit) this).getEtat() : "N/A") +
            ", duree_de_vie=" + (this instanceof Produit ? ((Produit) this).getDuree_de_vie() : "N/A") +
            
      ", duree=" + (this instanceof Service && ((Service) this).getDuree() != null ? ((Service) this).getDuree() : "N/A") +
          ", niveau=" + (this instanceof Service && ((Service) this).getDuree() != null ? ((Service) this).getDuree() : "N/A")


+
            '}';
}

}