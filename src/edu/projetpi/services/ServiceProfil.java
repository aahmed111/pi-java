/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Profil;
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
public class ServiceProfil implements IService<Profil> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Profil p) {
        try {
            String req = "INSERT INTO `profil`(`pseudo`, `bio`, `image`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getPseudo());
            ps.setString(2, p.getBio());
            ps.setString(3, p.getImage());

            ps.executeUpdate();
            System.out.println(" profil ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `profil` WHERE `id_profil` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("profil supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Profil p) {
        try {
            String req = "UPDATE `profil` SET `pseudo`=?,`bio`=,`image`=? WHERE `id_profil`= " + p.getId_profil();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getPseudo());
            ps.setString(2, p.getBio());
            ps.setString(3, p.getImage());
            ps.executeUpdate();
            System.out.println(" profil modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Profil> getAll() {
        List<Profil> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `profil`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Profil p = new Profil(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Profil getOneById(int id) {
        Profil p = null;
        try {
            String req = "SELECT * FROM `profil` WHERE `id_profil`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                p = new Profil(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
}
