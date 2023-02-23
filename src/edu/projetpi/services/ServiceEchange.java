/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Echange;
import edu.projetpi.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ServiceEchange implements IService<Echange> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Echange e) {
        try {
            String req = "INSERT INTO `echange`( `statut`, `date_Echange`,"
                    + "`id_client1`, `id_client2`, `id_article1` ,`id_article2`)"
                    + " VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getStatut());

            ps.setDate(2, new Date(e.getDate_echange().getYear(),
                    e.getDate_echange().getMonth(),
                    e.getDate_echange().getDate()));

            ps.setInt(3, e.getId_client1());
            ps.setInt(4, e.getId_client2());
            ps.setInt(5, e.getId_article1());
            ps.setInt(6, e.getId_article2());

            ps.executeUpdate();
            System.out.println("echange ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `echange` WHERE `id_echange` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("echange supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Echange e) {
        try {
            String req = "UPDATE `echange` SET `statut`=? WHERE `id_echange`= " + e.getId_echange();
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, e.getStatut());
            ps.executeUpdate();
            System.out.println("echange modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Echange> getAll() {
        List<Echange> list = new ArrayList<>();
        try {
            String req = "Select * from `echange`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Echange e = new Echange(rs.getInt(1), rs.getString(3), rs.getDate(2),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));

                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Echange getOneById(int id) {
        Echange e = new Echange();
        try {
            String req = "SELECT * FROM echange WHERE id_echange = " + id;
            PreparedStatement ps = cnx.prepareStatement(req);
            // ps.setInt(1, id);
            System.out.println("\n getoneById Echange" + ps);

            ResultSet rs = ps.executeQuery(req);

            if (rs.next()) {
                e = new Echange(rs.getInt(1), rs.getString(3), rs.getDate(2),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
                System.out.println("\n getoneById Echange" + e);
            }
        } catch (SQLException ex) {
            System.out.println("echange ISERVICE " + ex.getMessage());
        }
        return e;
    }

    public Echange getOneByStatut(String statut) {
        Echange e = new Echange();
        try {
            String req = "SELECT * FROM echange WHERE statut = " + statut;
            PreparedStatement ps = cnx.prepareStatement(req);

            System.out.println("\n getoneByStatut Echange" + ps);

            ResultSet rs = ps.executeQuery(req);

            if (rs.next()) {
                e = new Echange(rs.getInt(1), rs.getString(3), rs.getDate(2),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
                System.out.println("\n getoneById Echange" + e);
            }
        } catch (SQLException ex) {
            System.out.println("echange ISERVICE " + ex.getMessage());
        }
        return e;
    }

    public void evaluer(String statut, Integer note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
