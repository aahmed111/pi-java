/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

 
 
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Message;
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
 * @author ahmed
 */
 
 
public class ServiceMessage implements IService<Message> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Message t) {
        try {
            // Ajouter le nouveau message
            String req = "INSERT INTO `message`(`date`, `text`, `id_chat`) VALUES (Now(),?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getText());
            ps.setInt(2,t.getChat().getId_chat());
            ps.executeUpdate();
            System.out.println("Message ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du message : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `message` WHERE `id_message`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Message supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Message t) {
        try {
            String req = "UPDATE `message` SET `text`=? WHERE `id_message`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getText());
            ps.setInt(2, t.getId_message());
            ps.executeUpdate();
            System.out.println("Message modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
            String req = "SELECT * FROM `message` WHERE `id_message`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id_message = rs.getInt("id_message");
                Date date = rs.getDate("date");
                String text = rs.getString("text");
                int id_chat = rs.getInt("id_chat");
                ServiceChat sc = new ServiceChat();
                Chat chat = sc.getOneById(id_chat);
                m = new Message(id_message, date, text, chat);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return m;
    }
}

