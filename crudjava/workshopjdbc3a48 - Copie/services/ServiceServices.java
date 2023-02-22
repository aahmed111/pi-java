package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Services;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*public class ServiceServices implements IService<Services>{
 Connection cnx= DataSource.getInstance().getCnx();
 

   
    @Override
    public void ajouter(Services s) {
          try {
            String req = "INSERT INTO `service`( `date_ajout`, `proprietaire`, `estimation`, `description`, `image`, `type_services`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, s.getDate_ajout());
            ps.setString(2, s.getProprietaire());
            ps.setInt(3, s.getEstimation());
             ps.setString(4,s.getDescription()); 
             ps.setString(5, s.getImage()); 
            ps.setString(6, s.getType_article()) ;
            ps.executeUpdate();
            System.out.println("service ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    

    @Override
    public void supprimer(int id_service) {
        try {
           // String req = "DELETE FROM `produit` WHERE `id_produit` = ?" ;
            String req = "DELETE FROM `service` WHERE `id_service` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_service);
           ps.executeUpdate();
            System.out.println("service supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Services s) {
          try {
            String req = "UPDATE `service` SET `date_ajout`=?,`proprietaire`=?,`estimation`=?,`description`=?,`image`=? WHERE `Id_service`= " + s.getId_service();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, s.getDate_ajout());
            ps.setString(2, s.getProprietaire());
            ps.setInt(3, s.getEstimation());
             ps.setString(4, s.getDescription()); 
             ps.setString(5, s.getImage()); 
            ps.executeUpdate();
            System.out.println("service modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Services> getAll() {
      List<Services> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `service`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
               Services s = new Services(rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7));
                list.add(s);
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
    public Services getOneById(int id_service) {
        Services s =null;
        try {String req = "SELECT * FROM `service` WHERE ` id_service` = ? " ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id_service);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 s = new Services(rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return s; 
    }
    }*/