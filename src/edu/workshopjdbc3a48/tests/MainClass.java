/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;



import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.*;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;


 
public class MainClass {
    
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
         ServiceUser sc = new ServiceUser();
        ServiceChat sc1 = new ServiceChat(); 
 //  TEST SUR L'AJOUT
        // Création de l'utilisateur
    // User u = new Client();
    Client user1 = sc.getClientByUsernamePassword("ahmed", "zamer");
    Client user2 = sc.getClientByUsernamePassword("abdou", "abdouch");

 
         // us.ajouter(u);
      
        System.out.println( sc.verifier("ahmed","zamer"));
  //      us.ajouter(a);
 //TEST SUR MODIFICATION
         //us.modifier(a);
 //TEST SUR SUPRESSION
         // us.supprimer(40);
  //TEST SUR GetALL
       /* List<User> ls = us.getAll();
    
          for (User user : ls){
          System.out.println(user); 
          }*/
  //TEST SUR GetOneByid
        //System.out.print(us.getOneById(46)); 
    }  
}        
     

        
    

    
