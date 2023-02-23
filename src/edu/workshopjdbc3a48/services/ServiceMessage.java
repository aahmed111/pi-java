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
    public void ajouter(Message m) throws SQLException {
        try {
            String req;
            if (m.isReclamation()) {
                req = "INSERT INTO `Reclamation`(`Date`, `text`, `id_chat`) VALUES (NOW(),?,?)";
            } else {
                req = "INSERT INTO `Message`(`Date`, `text`, `id_chat`) VALUES (NOW(),?,?)";
            }
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, m.getText());
            ps.setInt(2, m.getChat().getId_chat()); 
            ps.executeUpdate();     
            System.out.println("Message envoyé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }

    @Override
    public void supprimer(int id) throws SQLException {
        try {
            String req = "DELETE FROM `Message` WHERE `id_Message`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Message supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Message t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> getAll() throws SQLException {
         List<Message> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `message` UNION SELECT * FROM `reclamation`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int id = rs.getInt("id_message");
                if (rs.wasNull()) {
                    id = rs.getInt("id_reclamation");
                }
                Date date = rs.getDate("date");
                String text = rs.getString("text");
                int id_chat = rs.getInt("id_chat");
                boolean isReclamation = !rs.wasNull();
                ServiceChat sc = new ServiceChat();
                Chat chat = sc.getOneById(id_chat);
                Message m = new Message(id, date, text, chat, isReclamation);
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Message getOneById(int id) throws SQLException {
 
    Message message = null;
    try {
        String req = "SELECT * FROM `Message` WHERE `id_message` = ? UNION SELECT * FROM `Reclamation` WHERE `id_reclamation` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int messageId = rs.getInt("id_message");
            Date date = rs.getDate("date");
            String text = rs.getString("text");
            int chatId = rs.getInt("id_chat");
            boolean isReclamation = !rs.wasNull();
            ServiceChat serviceChat = new ServiceChat();
            Chat chat = serviceChat.getOneById(chatId);
            message = new Message(messageId, date, text, chat, isReclamation);
        } else {
            System.out.println("Aucun message trouvé avec l'identifiant : " + id);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return message;
}
   
}
    
    
     
    
    
   
     

        
    

 
    

