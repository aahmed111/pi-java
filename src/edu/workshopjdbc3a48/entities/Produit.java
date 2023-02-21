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

    public Produit( int id_article, String date_ajout, String description, String image, String proprietaire, int estimation, String type_article,String type_produit) {
        super(id_article, date_ajout, description, image, proprietaire, estimation, type_article);
        this.type_produit = type_produit;
    }

    public Produit( String date_ajout, String description, String image, String proprietaire, int estimation, String type_article,String type_produit) {
        super(date_ajout, description, image, proprietaire, estimation, type_article);
        this.type_produit = type_produit;
    }

    public String getType_produit() {
        return type_produit;
    }

    public void setType_produit(String type_produit) {
        this.type_produit = type_produit;
    }

    @Override
    public String toString() {
        return "Produit{" +super.toString()+ "type_produit=" + type_produit +"}" ;
    }
   
}
