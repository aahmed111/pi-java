/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

 
import edu.workshopjdbc3a48.entities.Message;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ahmed
 */
    private int id_message;
    private Date date ;
    private String text ;
    private Chat chat;


public class ServiceMessage implements IService<Message> {

    @Override
    public void ajouter(Message t) {
        try {
            String req = "INSERT INTO `Message`(`id_message`, `Date`, `text`, `id_chat`) VALUES (?,NOW(),?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.id_message());
            ps.setString(2, t.getTitre());
            ps.setString(3, t.getDescription());
             ps.setString(4, t.getDate_publication()); 
             ps.setString(5, t.getStatut()); 
            ps.executeUpdate();
            System.out.println("message envoyer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

        
    }

    @Override
    public void supprimer(int id)   {
         
    }

    @Override
    public void modifier(Message t)   {
        
    }

    @Override
    public List<Message> getAll()   {
        return null;
         
    }

    @Override
    public Message getOneById(int id)   {
        return null;
         
    }
    
}
