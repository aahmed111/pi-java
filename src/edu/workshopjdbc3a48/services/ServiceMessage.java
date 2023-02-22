/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

 
import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Message;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author ahmed
 */
 

public class ServiceMessage implements IService<Message> {

    Connection cnx= DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Message m) {
        try {
            String req = "INSERT INTO `Message`(`Date`, `text`, `id_chat`) VALUES (NOW(),?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setString(2, m.getText());
             ps.setInt(3, m.getChat().getId_chat()); 
             
             ps.executeUpdate();
                   
            System.out.println("message envoyer !");
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }  
            
            
           
    

    @Override
    public void supprimer(int id)   {
             try {
            String req = "DELETE FROM `Message` WHERE `id_Message`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("message supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    }
    
    @Override
    public void modifier(Message t) {
    }  
    
    
    @Override
   public List<Message> getAll() {
    List<Message> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `message`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id_message = rs.getInt("id_message");
            Date date = rs.getDate("date");
            String text = rs.getString("text");
            int id_chat = rs.getInt("id_chat");
            ServiceChat sc = new ServiceChat();
            Chat chat = sc.getOneById(id_chat);
            Message m = new Message(id_message, date, text, chat);
            list.add(m);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}


    @Override
  public Message getOneById(int id) {
    Message m = null;
    try {
        String req = "SELECT * FROM `Message` WHERE `id_Message`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id_message = rs.getInt("id_Message");
            Date date = rs.getDate("date");
            String text = rs.getString("text");
            int chat_id = rs.getInt("chat_id");
            ServiceChat sc = new ServiceChat();
            Chat chat = sc.getOneById(chat_id);
            m = new Message(id_message, date, text, chat);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return m;
}

    }

     
    

        
    

 
    

