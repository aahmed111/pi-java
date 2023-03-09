/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Article;
import edu.projetpi.entities.Echange;
import edu.projetpi.entities.User;
import edu.projetpi.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceEchange implements IService<Echange> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Echange e) {
        try {
            String req = "INSERT INTO `echange`( `statut`, `date_Echange`,"
                    + "`id_user1`, `id_user2`, `id_article1` ,`id_article2`,`confirmation` )"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getStatut());

            ps.setDate(2, new Date(e.getDate_echange().getYear(),
                    e.getDate_echange().getMonth(),
                    e.getDate_echange().getDate()));

            ps.setInt(3, e.getUser1().getId_user());
            ps.setInt(4, e.getUser2().getId_user());
            ps.setInt(5, e.getArticle1().getId_article());
            ps.setInt(6, e.getArticle2().getId_article());
            ps.setInt(7, e.getConfirmation());

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
            ps.setInt(2, e.getConfirmation());
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
                ServiceUser su = new ServiceUser();
                User user1 = su.getOneById(rs.getInt(1));
                User user2 = su.getOneById(rs.getInt(2));
                ServiceArticle sa = new ServiceArticle();
                Article article1 = sa.getOneById(rs.getInt(1));
                Article article2 = sa.getOneById(rs.getInt(2));
                Echange e = new Echange(rs.getInt(1), rs.getDate(2), rs.getString(3),
                        user1, user2, article1, article2, rs.getInt("confirmation"));

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

            ResultSet rs = ps.executeQuery(req);

            if (rs.next()) {
                ServiceUser su = new ServiceUser();
                User user1 = su.getOneById(rs.getInt(1));
                User user2 = su.getOneById(rs.getInt(2));
                ServiceArticle sa = new ServiceArticle();
                Article article1 = sa.getOneById(rs.getInt(1));
                Article article2 = sa.getOneById(rs.getInt(2));
                e = new Echange(rs.getInt(1), rs.getDate(2), rs.getString(3), user1, user2,
                        article1, article2, rs.getInt("confirmation"));

            }
        } catch (SQLException ex) {
            System.out.println("echange ISERVICE " + ex.getMessage());
        }
        return e;
    }

    public void evaluer(String statut, Integer note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public List<Echange> getAllForUser(int id_user) {
    List<Echange> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM `echange` WHERE id_user1 = ? OR id_user2 = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id_user);
        st.setInt(2, id_user);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            ServiceUser su = new ServiceUser();
            User user1 = su.getOneById(rs.getInt("id_user"));
            User user2 = su.getOneById(rs.getInt("id_user"));
            ServiceArticle sa = new ServiceArticle();
            Article article1 = sa.getOneById(rs.getInt("id_article"));
            Article article2 = sa.getOneById(rs.getInt("id_article"));
            Echange e = new Echange(rs.getInt(1), rs.getDate(2), rs.getString(3),
                    user1, user2, article1, article2, rs.getInt("confirmation"));
            list.add(e);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}

}
