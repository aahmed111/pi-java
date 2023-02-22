/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Services;
import edu.workshopjdbc3a48.services.ServiceArticle;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;



/**
 *
 * @author pc
 */
public class TestArticle {
      public static void main(String[] args)   {
    //(String date_ajout, String description, String image, String proprietaire, int estimation,String type_article,String type_produit)
   
  Article p1 = new Produit("dd", "oo", "gui", 5, "ff", "dd");

   Article p2=new Services("htx","htp","s",108,"ppppppppp","xx");
      // public Services(String date_ajout, String description, String image, String proprietaire, int estimation,String type_article,String type_service) {

       
    /* public Produit(String date_ajout, String description, String image, String type_article, String proprietaire, int estimation) {
        super(date_ajout, description, image, type_article, proprietaire, estimation);
    }*/
                       


            ServiceArticle s = new ServiceArticle();
s.ajouter(p2);
    // s.ajouter(p1);
    // s.supprimer(9);
//s.modifier(p2);
//  System.out.println(s.getOneById(29));
 //s.getAll();



       
        
       
}

   
    }
      
    
      

       
          
       

