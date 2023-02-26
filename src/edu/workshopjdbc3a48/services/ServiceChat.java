/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.User;
 
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author pc
 */
public class ServiceChat implements IService<Chat> {
 
Connection cnx= DataSource.getInstance().getCnx();

    @Override
   public void ajouter(Chat c) {
    try {
        
        // Ajouter le nouveau chat
        String req = "INSERT INTO chat(id_user1, id_user2, nom) VALUES (?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, c.getUser1().getId_user());
        ps.setInt(2, c.getUser2().getId_user());
        ps.setString(3, c.getNom());
        ps.executeUpdate();
        System.out.println("Chat ajouté.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'ajout du chat : " + e.getMessage());
    }
}


    @Override
    public void supprimer(int id)   {
        try {
            String req = "DELETE FROM `chat` WHERE `id_chat`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("chat supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
@Override
public void modifier(Chat c) {
    try {
        String req = "UPDATE `chat` SET `nom`=? WHERE `id_chat`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getNom());
        ps.setInt(2, c.getId_chat());
        ps.executeUpdate();
        System.out.println("chat modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
}

    @Override
  public List<Chat> getAll() {
    List<Chat> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `chat`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id_chat = rs.getInt("id_chat");
            
            String nom = rs.getString("nom");
            int id_user1 = rs.getInt("id_user1");
            int id_user2 = rs.getInt("id_user2");
            ServiceUser su = new ServiceUser();
            User user1 = su.getOneById(id_user1);
            User user2 = su.getOneById(id_user2);
            Chat c = new Chat(id_chat, user1, user2, nom);
            list.add(c);
        }
      
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

    @Override
    public Chat getOneById(int id)   {
   Chat c = null;
    try {
        String req = "SELECT * FROM `chat` WHERE `id_chat`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id_chat = rs.getInt("id_chat");
            
            String nom = rs.getString("nom");
            int id_user1 = rs.getInt("id_user1");
            int id_user2 = rs.getInt("id_user2");
            ServiceUser su = new ServiceUser();
            User user1 = su.getOneById(id_user1);
            User user2 = su.getOneById(id_user2);
            c = new Chat(id_chat, user1, user2, nom);
        }
     
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
    }
    
    
public String getChatNameById(int id) {
    String chatName = null;
    try {
        String req = "SELECT nom FROM chat WHERE id_chat = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            chatName = rs.getString("nom");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération du nom du chat : " + ex.getMessage());
    }
    return chatName;
}    
  /*  public boolean isUnique(Chat chat){
        
        
    }*/
}

 