/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ServiceAdmin implements IService<Admin>{
    
     Connection cnx= DataSource.getInstance().getCnx();

     

     @Override
    public void ajouter (Admin a){
         try {
            String req = "INSERT INTO `admin`( `nom`, `prenom`, `gmail`, `sexe`, `adresse`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, a.getNom());
            ps.setString(2, a.getPrenom());
            ps.setString(3, a.getGmail());
             ps.setString(4, a.getSexe()); 
             ps.setString(5, a.getAdresse()); 
            ps.executeUpdate();
            System.out.println("admin ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
       public void supprimer(int id){
         try {
            String req = "DELETE FROM `admin` WHERE `id_admin` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("admin supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

} 
    
 
       
    
   
     @Override
    public void modifier(Admin p) {
            try {
            String req = "UPDATE `admin` SET `nom`=?,`prenom`=?,`gmail`=?,`sexe`=?,`adresse`=? WHERE `id_admin`= " + p.getId_admin();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getGmail());
             ps.setString(4, p.getSexe()); 
             ps.setString(5, p.getAdresse()); 
            ps.executeUpdate();
            System.out.println("admin modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Admin> getAll() {
       List<Admin> list = new ArrayList<>();
        try {
            String req = "Select * from `admin`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Admin a = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(a);
             } }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Admin getOneById(int id) {
        Admin a =null;
        try {
            String req = "SELECT * FROM `admin` WHERE `id_admin`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 a = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return a; 
    }
       
    
    }

  
  


  
