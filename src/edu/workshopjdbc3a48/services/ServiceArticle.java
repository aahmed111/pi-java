/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Article;

import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Service;
import edu.workshopjdbc3a48.entities.User;

import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.workshopjdbc3a48.services.IService;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class ServiceArticle implements IService<Article> {

    Connection cnx = DataSource.getInstance().getCnx();
@Override
    public void ajouter(Article a) throws SQLException {

        if (a instanceof Produit) {

            Produit p = (Produit) a;
   //public Produit(  String description, String type_article, int estimation, byte[] image, Date date_ajout, Categorie c, User u,String nom,int poids,String etat,  String duree_de_vie) {

            String req = "INSERT INTO article`( description`, type_article, estimation, image,`id_user`, date_ajout, nom, poid, etat, duree_de_vie, duree, niveau) "
                    + "VALUES (?, ?, ?, ?, NOW(),?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, a.getDescription());
            ps.setString(2, a.getType_article());
            ps.setInt(3, a.getEstimation());
            ps.setBytes(4, p.getImage());
            ps.setInt(5,p.getUser().getId_user());
            ps.setString(5, a.getNom());
            ps.setInt(6, p.getPoids());
            ps.setString(7, p.getEtat());
            ps.setString(8, p.getDuree_de_vie());

            ps.executeUpdate();

            System.out.println("article ajouté !");
        } else {

            Service s = (Service) a;

            String req = "INSERT INTO article (description, type_article, estimation, image, id_user,`date_ajout`,, nom, poid, etat, duree_de_vie, duree, niveau) "
                    + "VALUES (?, ?, ?, ?, NOW(), ?, DEFAULT, DEFAULT, DEFAULT, ?, ?)";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, a.getDescription());
            ps.setString(2, a.getType_article());
            ps.setInt(3, a.getEstimation());
            ps.setBytes(4, a.getImage());
             ps.setInt(5,a.getUser().getId_user());
            ps.setString(6, a.getNom());
            ps.setString(7, s.getDuree());
            ps.setString(8, s.getNiveau());

            ps.executeUpdate();

            System.out.println("article ajouté !");

        }

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM article WHERE id_article = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("article supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Article a) {
        try {
            String req;
            if (a instanceof Produit) {

                // Update fields for a product
                req = "UPDATE article SET etat`=?, duree_de_vie`=?, `poid`=? WHERE id_article=?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, ((Produit) a).getEtat());
                ps.setString(2, ((Produit) a).getDuree_de_vie());
                ps.setInt(3, ((Produit) a).getPoids());
                ps.setInt(4, a.getId_article());
            } else if (a instanceof Service) {
                // Update fields for a service
                req = "UPDATE article SET duree`=?, niveau`=? WHERE id_article=?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, ((Service) a).getDuree());
                ps.setString(2, ((Service) a).getNiveau());
                ps.setInt(3, a.getId_article());
                ps.executeUpdate();
            }

            System.out.println("Article modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

   @Override
    public List<Article> getAll() {
        List<Article> result = new ArrayList<>();
      
        try {

            String req = "SELECT * FROM article";
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                int id_article = rs.getInt(1);
                String description = rs.getString(2);
                String type_article = rs.getString(3);
                int estimation = rs.getInt(4);
                byte[] image = rs.getBytes(5);
                Date date_ajout = rs.getDate(6);
                String nom = rs.getString(7);
                int poids = rs.getInt(8);
                String etat = rs.getString(9);
                String duree_de_vie = rs.getString(10);
                String duree = rs.getString(11);
                String niveau = rs.getString(12);
                 ServiceUser su = new ServiceUser();
                ServiceArticle sa = new ServiceArticle();
                Article article =sa.getOneById(rs.getInt(10));
                User user = su.getOneById(rs.getInt(9));

                if (rs.getString("type_article").equals("produit")) {


 // User u =new User();
  //User user=new User(String username, String password, String email, byte[] photo, String type,int phoneNumber,String sexe);
                   Article a1 = new Produit(id_article, description, type_article, estimation, image,user,date_ajout, nom, poids, etat, duree_de_vie);
                    result.add(a1);
                } else if (rs.getString("type_article").equals("service")) {
                    Article s1 = new Service(id_article, description, type_article, estimation, image, user,date_ajout, nom, duree, niveau);
                    result.add(s1);
                }

            /*    for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i));
                }*/

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public Article getOneById(int id) {
        Article a = null;
        try {
            String req = "SELECT * FROM article WHERE id_article = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ServiceUser su = new ServiceUser();
                User user = su.getOneById(rs.getInt(9));
                if (rs.getString("type_article").equals("produit")) {

                    a = new Produit(
                              rs.getInt("id_article"),
                              rs.getString("description"),
                              rs.getString("type_article"),
                              rs.getInt("estimation"),
                              rs.getBytes("image"),
                              user,
                              rs.getDate("date_ajout"),
                              rs.getString("nom"),
                              rs.getInt("etat"),
                              rs.getString("poids"),
                              rs.getString("duree_vie")
                    );
                } else if (rs.getString("type_article").equals("service")) {
                    a = new Service(
                              rs.getInt("id_article"),
                              rs.getString("description"),
                              rs.getString("type_article"),
                              rs.getInt("estimation"),
                              rs.getBytes("image"),
                              user,
                              rs.getDate("date_ajout"),
                              rs.getString("nom"),
                              rs.getString("niveau"),
                              rs.getString("duree")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

}
