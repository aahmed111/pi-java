/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.entities.User;

import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Audience;

/**
 *
 * @author pc
 */
public class ServiceAnnonce implements IService<Annonce> {

 Connection cnx= DataSource.getInstance().getCnx();

    @Override
   public void ajouter(Annonce a) {
       
    try {
        String req = " INSERT INTO `annonce`( `description`, `condition-echange`, `titre`, `audience`, `totalreactions`, `nbcommentaires`, `date_publication`, `id_user`, `id_article`, `id_categorie`) ) VALUES(?, ?, ?,?,DEFAULT,DEFAULT,CURRENT_TIMESTAMP(),?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, a.getDescription()); 
        ps.setString(2, a.getCondition_echange());
        ps.setString(3, a.getTitre());
        ps.setString(4, a.getAudience().toString());
        ps.setInt(5,a.getActicle().getId_article() );
         ps.setInt(6,a.getUser().getId_user() );
         ps.setInt(7,a.getCategorie().getId_categorie() );
        ps.executeUpdate();
        System.out.println("annonce ajoutée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
   
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `annonce` WHERE `id_annonce` = ?" ;
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
            String req = "UPDATE `annonce` SET `description`=?,`condition-echange`=?,`titre`=?,`audience`=? WHERE `id_annonce`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
         ps.setString(1, a.getDescription()); 
        ps.setString(2, a.getCondition_echange());
        ps.setString(3, a.getTitre());
        ps.setString(4, a.getAudience().toString());
        ps.setInt(5, a.getId_annonce());
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
            String req = "SELECT * FROM `annonce`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceArticle sa = new ServiceArticle();
                ServiceUser su = new ServiceUser();
                ServiceCategorie sc = new ServiceCategorie();
                Article article =sa.getOneById(rs.getInt(10));
                User user = su.getOneById(rs.getInt(9));
                Categorie categorie =sc.getOneById(11);
                
               
               Annonce a = new Annonce(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),Audience.valueOf(rs.getString(5)),rs.getInt(6),rs.getInt(7),rs.getDate(8),user,article,categorie);
                list.add(a);
             }
        }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       for(int i=0;i<list.size();i++)
       
       {
           System.out.println(list.get(i));
       }

        return list;
    }

    @Override
    public Annonce getOneById(int id) {
         Annonce a =null;
        try {
          String req = "SELECT * FROM `annonce` WHERE `id_annonce`=" + id;
            PreparedStatement ps = cnx.prepareStatement(req);
           // ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                ServiceArticle sa = new ServiceArticle();
                ServiceUser su = new ServiceUser();
                Article article =sa.getOneById(rs.getInt(10));
                User user = su.getOneById(rs.getInt(9));
                ServiceCategorie sc = new ServiceCategorie();
                Categorie categorie =sc.getOneById(11);
               
              a = new Annonce(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),Audience.valueOf(rs.getString(5)),rs.getInt(6),rs.getInt(7),rs.getDate(8),user,article,categorie);
               
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return a; 
    }
    }
 

    
    
    

