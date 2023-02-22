/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.services.ServiceArticle;
import edu.workshopjdbc3a48.services.ServiceCategorie;


/**
 *
 * @author Louay
 */
public class TestCategorie {
     public static void main(String[] args)  {
   
 
         ServiceCategorie s = new ServiceCategorie();
         Categorie c1=new Categorie("dd","football");
          Categorie c2=new Categorie("ssikopmkh","xx");
            Categorie c3=new Categorie("5eme","art ");
              Categorie c4=new Categorie("linux","kn");
       /// s.ajouter(c4);
         //s.supprimer(33);
        // s.modifier(c3);
//s.getAll();
//System.out.println(s.getOneById(31));
     
        
       
}
}
