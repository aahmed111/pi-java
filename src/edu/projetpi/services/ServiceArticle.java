/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Article;
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
public class ServiceArticle implements IService<Article> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Article a) {
        try {
            String req = "INSERT INTO `article`( `date_ajout`, `proprietaire`, `estimation`, `description`, `image`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getDate_ajout());
            ps.setString(2, a.getProprietaire());
            ps.setString(3, a.getEstimation());
            ps.setString(4, a.getDescription());
            ps.setString(5, a.getImage());
            ps.executeUpdate();
            System.out.println("article ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `article` WHERE `id_article` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("article supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Article a) {
        try {
            String req = "UPDATE `article` SET `date_ajout`=?,`proprietaire`=?,`estimation`=?,`description`=?,`image`=? WHERE `id_article`= " + a.getId_article();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getDate_ajout());
            ps.setString(2, a.getProprietaire());
            ps.setString(3, a.getEstimation());
            ps.setString(4, a.getDescription());
            ps.setString(5, a.getImage());
            ps.executeUpdate();
            System.out.println("article modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Article> getAll() {
        List<Article> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `article`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Article getOneById(int id) {
        Article a = null;
        try {
            String req = "SELECT * FROM `article` WHERE `id_article`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}
