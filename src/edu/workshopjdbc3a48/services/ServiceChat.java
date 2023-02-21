/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ServiceChat  {
Connection cnx= DataSource.getInstance().getCnx();


 private int id_chat;
   private String id_emetteur,id_destinatire,objet,corps,date_envoie;
   
  
    public void ajouter(Chat c) throws SQLException {
            String req = "INSERT INTO `chat`( `date_envoie`, `id_user1`, `id_user2`,`nom`) VALUES (NOW(),?,?,?)";
              PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1,c.getUser1().getId_user());
                ps.setInt(2, c.getUser2().getId_user());
                ps.setString(3,c.getNom());
                ps.executeUpdate();
              System.out.println("chat ajouté");  
    }

    public void supprimer(int id) {
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

   
    public void modifier(Chat c) {
       try {
            String req = "UPDATE `chat` SET `nom`=? WHERE `id_chat`= " + c.getId_chat();
            Statement ps = cnx.createStatement();
            
            ps.executeUpdate(req);
            System.out.println("chat modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
/*
    @Override
    public List<Chat> getAll() {
         List<Chat> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `chat`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               Chat c = new Chat(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(c);
             }
            }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Chat getOneById(int id) {
         Chat c =null;
        try {
            String req = "SELECT * FROM `chat` WHERE `id_chat`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 c = new Chat(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return c; 
    }*/
    }
    

