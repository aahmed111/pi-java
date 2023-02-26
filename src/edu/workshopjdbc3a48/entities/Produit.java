/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author abdel
 */
public class Produit extends Article {
   private  String type_produit;

    public Produit(String type_produit, int id_article, String description, String image, String proprietaire, int estimation, String type_article) {
        super(id_article, description, image, proprietaire, estimation, type_article);
        this.type_produit = type_produit;
    }

    public Produit(String type_produit, String description, String image, String proprietaire, int estimation, String type_article) {
        super(description, image, proprietaire, estimation, type_article);
        this.type_produit = type_produit;
    }

  

    
    @Override
    public String toString() {
        return "Produit{" +super.toString()+ "type_produit=" + type_produit +"}" ;
    }
   
}
