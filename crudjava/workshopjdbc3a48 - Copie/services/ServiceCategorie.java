/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Categorie;
import edu.workshopjdbc3a48.utils.DataSource;
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
public class ServiceCategorie implements IService<Categorie>{
 Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Categorie c) {
     try {
            String req = "INSERT INTO `categorie`(`description`, `type`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
          
            ps.setString(1, c.getDescription());
             ps.setString(2, c.getType());
             ps.executeUpdate();
            System.out.println("catégorie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE `id_categorie` = ?" ;
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
            String req = "UPDATE `categorie` SET `description`=?,`type`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getDescription());
            ps.setString(2, c.getType());
           
            ps.executeUpdate();
            System.out.println("categorie modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `categorie`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               Categorie c = new Categorie(rs.getInt(1), rs.getString(2),rs.getString(3));
                list.add(c);
             }
            }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         for(int i=0;i<list.size();i++) {
        System.out.println(list.get(i));
    }
   
        return list;
    }
    
    
    @Override
    public Categorie getOneById(int id) {
    Categorie c = null;
    
    
    try {
        String req = "SELECT * FROM `categorie` WHERE `id_categorie` = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id); // Set the ID parameter
        ResultSet rs = ps.executeQuery();
        if (rs.next()) { // Use if instead of while since we're only expecting one result
           
                     c = new Categorie (rs.getInt(1), rs.getString(2), rs.getString(3));
                
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}
    
    }
    

