/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Categorie;
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
public class ServiceCategorie implements IService<Categorie> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Categorie c) {
        try {
            String req = "INSERT INTO `catégorie`(`nom`, `description`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
            ps.executeUpdate();
            System.out.println("catégorie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `catégorie` WHERE `id_categorie` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("catégorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorie c) {
        try {
            String req = "UPDATE `catégorie` SET `nom`=?,`description`=? WHERE `id_categorie`=" + c.getId_categorie();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
            ps.executeUpdate();
            System.out.println("categorie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `catégorie`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Categorie getOneById(int id) {
        Categorie c = null;
        try {
            String req = "SELECT * FROM `catégorie` WHERE `id_categorie`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                c = new Categorie(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
