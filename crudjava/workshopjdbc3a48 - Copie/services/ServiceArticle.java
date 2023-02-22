/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;







import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Services;
import edu.workshopjdbc3a48.utils.DataSource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.Blob;
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
    String type_article = "inconnu";
    String type_produit = "N/A";
   String type_service = "N/A";
    
    if (a instanceof Produit) {
        type_article = "produit";
        type_produit = ((Produit) a).getType_produit();
    } else if (a instanceof Services) {
        type_article = "service";
        type_service = ((Services) a).getType_service();
    }
 
    try {
        String req = "INSERT INTO `article`( `date_ajout`, `description`, `image`, `proprietaire`, `estimation`, `type_article`,`type_produit`,`type_service`) " +
                     "VALUES (NOW(),?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1, a.getDescription());
      ps.setString(2, a.getImage());
        ps.setString(3, a.getProprietaire());
        ps.setInt(4, a.getEstimation());
        ps.setString(5, type_article);
        ps.setString(6, type_produit);
        ps.setString(7, type_service);
          
      
        ps.executeUpdate();
        System.out.println("article ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



   

 public void supprimer(int id) {
        try {
            String req = "DELETE FROM `article` WHERE `id_article` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("article supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Article a) {
        if(a instanceof Produit)
        {
            try {
        String req = "String req = \"UPDATE `article` SET `description`=?, `estimation`=?, `image`=?, `type_produit`=? WHERE id_article=?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1, a.getDescription());
      
        ps.setInt(2, a.getEstimation());
        ps.setString(3, a.getImage());
         ps.setString(4,((Produit) a).getType_produit());
         
     
       
        ps.executeUpdate();
        System.out.println("Article modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        }
        else 
        {
            try {
        String req = "UPDATE `article` SET `description`=?, `estimation`=?, `image`=?, `type_service`=? WHERE id_article=?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1, a.getDescription());
      
        ps.setInt(2, a.getEstimation());
        ps.setString(3, a.getImage());
            ps.setString(4,((Services) a).getType_service());
        
           ps.setInt(5, a.getId_article());
       
        ps.executeUpdate();
        System.out.println("Article modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        }
    
}

    


    @Override
    public List<Article> getAll() {
        List<Article> list = new ArrayList<>();
        try {
            //String req = "SELECT description, categorie.type FROM article a join categorie c on (a.id_categorie=c.id_categorie) ";
             String req=  "SELECT * FROM article ";


   

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("type_article").equals("produit")) {
                    Article a = new Produit(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getString(8));
                    list.add(a);
         
                } else if (rs.getString("type_article").equals("service")) {
                    Article a = new Services(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getString(8));
                    list.add(a);
                }
                
   
        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         for(int i=0;i<list.size();i++) {
        System.out.println(list.get(i));
    }
    return list;
       
    }

    @Override
     public Article getOneById(int id) {
    Article a = null;
    
    
    try {
        String req = "SELECT * FROM `article` WHERE `id_article` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id); // Set the ID parameter
        ResultSet rs = ps.executeQuery();
        if (rs.next()) { // Use if instead of while since we're only expecting one result
            if (rs.getString("type_article").equals("produit")) {
                     a = new Produit(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getString(8));
                
            } else if (rs.getString("type_article").equals("service")) {
                     a = new Produit(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7),rs.getString(8));
                      
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return a;
}

    }


