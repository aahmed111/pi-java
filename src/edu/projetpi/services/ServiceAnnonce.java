/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Annonce;
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
public class ServiceAnnonce implements IService<Annonce> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Annonce a) {
        try {
            String req = "INSERT INTO `annonce`(`id_annonce`, `titre`, `description`, `date_publication`, `statut`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, a.getId_annonce());
            ps.setString(2, a.getTitre());
            ps.setString(3, a.getDescription());
            ps.setString(4, a.getDate_publication());
            ps.setString(5, a.getStatut());
            ps.executeUpdate();
            System.out.println("annonce ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `annonce` WHERE `id_annonce` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("annonce supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Annonce a) {
        try {
            String req = "UPDATE `annonce` SET `titre`=?`description`=?,`date_publication`=?,`statut`=? WHERE `id_annonce`= " + a.getId_annonce();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getTitre());
            ps.setString(2, a.getDescription());
            ps.setString(3, a.getDate_publication());
            ps.setString(4, a.getStatut());
            ps.executeUpdate();
            System.out.println("annonce modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Annonce> getAll() {
        List<Annonce> list = new ArrayList<>();
        try {
            String req = "Select * from `annonce`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Annonce a = new Annonce(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Annonce getOneById(int id) {
        Annonce a = null;
        try {
            String req = "SELECT * FROM `annonce` WHERE `id_annonce`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                a = new Annonce(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}
