/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Louay
 */
/*public  class ServiceProduit implements IService<Produit> {
    Connection cnx= DataSource.getInstance().getCnx();
   
    @Override
     public void ajouter(Produit p) {
    try {
          Article a = p.getA();
          //  super.ajouter(p.getA());
        String req = "INSERT INTO `produit`( `date_ajout`, `proprietaire`, `estimation`, `description`, `image`,`type_produit`) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1, p.getDate_ajout()); // convertit la date en java.util.Date en java.sql.Date pour la base de données
        ps.setString(2, p.getProprietaire()); 
        ps.setInt(3, p.getEstimation()); 
        ps.setString(4, p.getDescription()); 
        ps.setString(5, p.getImage());  
        ps.setString(6, p.gettype_produit()); 

        ps.executeUpdate();
        System.out.println("Produit ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }  
}
   
    @Override
    public void supprimer(int id_produit) {
        try {
            String req = "DELETE FROM `produit` WHERE `id_produit` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_produit);
           ps.executeUpdate();
            System.out.println("produit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Produit p) {
          try {
         String req = "UPDATE `produit` SET `type_produit`=? WHERE `id_produit`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
  ps.setString(1, p.gettype_produit());
        ps.setInt(2, p.getId_produit());
            
            ps.executeUpdate();
            System.out.println("produit modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Produit> getAll() {
      List<Produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `produit`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               Produit p = new Produit(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(4),rs.getString(5));
                list.add(p);
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
    public Produit getOneById(int id_produit) {
          Produit p =null;
        try {
            String req = "SELECT * FROM `produit` WHERE `id_produit`= ? " ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id_produit);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 p = new Produit(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return p; 
    }

    
      
    }*/

