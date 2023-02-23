/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Chat;
import edu.projetpi.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ServiceChat implements IService<Chat> {

    Connection cnx = DataSource.getInstance().getCnx();

    private int id_chat;
    private String id_emetteur, id_destinatire, objet, corps, date_envoie;

    @Override
    public void ajouter(Chat c) {
        try {
            String req = "INSERT INTO `chat`(`id_emetteur`, `id_destinatire`, `corps`, `date_envoie`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getId_emetteur());
            ps.setString(2, c.getId_destinatire());
            ps.setString(4, c.getCorps());
            ps.setString(5, c.getDate_envoie());
            ps.executeUpdate();
            System.out.println("message ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `chat` WHERE `id_chat`= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("message supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Chat c) {
        try {
            String req = "UPDATE `chat` SET `id_emetteur`=?,`id_destinatire`=?,corps`=?,`date_envoie`=? WHERE `id_chat`= " + c.getId_chat();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getId_emetteur());
            ps.setString(2, c.getId_destinatire());
            ps.setString(4, c.getCorps());
            ps.setString(5, c.getDate_envoie());
            ps.executeUpdate();
            System.out.println("message modifié !");
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
                Chat c = new Chat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Chat getOneById(int id) {
        Chat c = null;
        try {
            String req = "SELECT * FROM `chat` WHERE `id_chat`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                c = new Chat(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
