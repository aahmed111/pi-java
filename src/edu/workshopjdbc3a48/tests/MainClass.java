/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;



import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.*;
import edu.workshopjdbc3a48.utils.DataSource;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Message;

 
public class MainClass {


    
    public static void main(String[] args) throws SQLException {
        
        
        
     
    
// Créer un objet ServiceUser pour interagir avec la base de données des utilisateurs
ServiceUser su = new ServiceUser();

// Créer un objet ServiceChat pour interagir avec la base de données des chats
ServiceChat sc = new ServiceChat();
 
ServiceChat chatService = new ServiceChat();

// Récupérer les utilisateurs pour le chat
Client user1 = su.getClientByUsernamePassword("ahmed", "zamer");
Client user2 = su.getClientByUsernamePassword("abdou", "abdouch");

// Créer un nouvel objet Chat
Chat c = new Chat(user1, user2, "echangi");

// Ajouter le chat à la base de données
//sc.ajouter(c);
//sc.supprimer(1);
 
// Modifier le nom du chat
c.setNom("Nouveau__nom");

// Appeler la méthode modifier de l'objet ServiceChat pour enregistrer les modifications dans la base de données
sc.modifier(c);

 
List<Chat> chats = sc.getAll();
for (Chat chat : chats) {
    System.out.println(chat);
}
Chat chat = chatService.getOneById(7);
System.out.println(chat.getNom());

ServiceMessage rs = new ServiceMessage();
 
Message m1 = new Message ("jbj");
rs.ajouter(m1);

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
     
       
  
 //Reclamation rec = new Reclamation( "abdouchnjnjjjjjj hdujjjjjjdcjd ");
 //ServiceReclamation sr = new ServiceReclamation ();
 //sr.ajouter(rec);
    
}
     
}
    
