/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.util.Date;

/**
 *
 * @author Louay
 */
public class Service extends Article {
    private String niveau;
    private String duree;

    public Service( int id_article, String description, String type_article, int estimation, byte[] image, Categorie c, User user, Date date_ajout, String nom,String niveau, String duree) {
        super(id_article, description, type_article, estimation, image, c, user, date_ajout, nom);
        this.niveau = niveau;
        this.duree = duree;
    }
 public Service( int id_article, String description, String type_article, int estimation, byte[] image,Date date_ajout, String nom,String niveau, String duree) {
        super(id_article, description, type_article, estimation, image,  date_ajout, nom);
        this.niveau = niveau;
        this.duree = duree;
    }
  public Service(  String description, String type_article, int estimation,  String nom,String niveau, String duree) {
        super( description, type_article, estimation,   nom);
        this.niveau = niveau;
        this.duree = duree;
    }
 public Service(  String description, String type_article, int estimation, byte[] image, String nom,String niveau, String duree) {
        super( description, type_article, estimation, image,   nom);
        this.niveau = niveau;
        this.duree = duree;
    }
   
   /*  public Article(int id_article, String description,  int estimation, byte[] image, Categorie c,Date date_ajout, String nom) {
        this.id_article = id_article;
        this.description = description;
  
        this.estimation = estimation;
        this.image = image;
        
        this.date_ajout = date_ajout;
        this.nom = nom;
    }*/

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

   

   

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

   

   

    
    
}
