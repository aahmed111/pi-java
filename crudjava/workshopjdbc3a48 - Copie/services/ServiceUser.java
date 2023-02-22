/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.User;
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
public class ServiceUser implements IService<User>{
  Connection cnx= DataSource.getInstance().getCnx();
  
    
    @Override
    public void ajouter(User u) {
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `gmail`, `adresse`, `sexe`, `emploie`, `recto_cin`, `verso_cin`) VALUES VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getGmail());
             ps.setString(4, u.getAdresse()); 
             ps.setString(5, u.getSexe()); 
             ps.setString(5, u.getEmploie()); 
             ps.setString(5, u.getRecto_cin()); 
             ps.setString(5, u.getVerso_cin()); 
            ps.executeUpdate();
            System.out.println("user ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String req = "DELETE FROM `user` WHERE `id_user` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("user supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User u) {
     try {
            String req = "UPDATE `user` SET `nom`=?,`prenom`=?,`gmail`=?,`adresse`=?,`sexe`=?,`emploie`=?,`recto_cin`=?,`verso_cin`=? WHERE `id_user`= " + u.getId_user();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getGmail());
             ps.setString(4, u.getAdresse()); 
             ps.setString(5, u.getSexe()); 
             ps.setString(5, u.getEmploie()); 
             ps.setString(5, u.getRecto_cin()); 
             ps.setString(5, u.getVerso_cin()); 
            ps.executeUpdate();
            System.out.println("user modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }

    @Override
    public List<User> getAll() {
          List<User> list = new ArrayList<>();
        try {
            String req = "Select * from `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public User getOneById(int id) {
          User u =null;
        try {
            String req = "SELECT * FROM `user` WHERE `id_user`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return u; 
    }   
}
    

