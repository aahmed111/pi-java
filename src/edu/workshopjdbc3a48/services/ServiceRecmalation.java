/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Reclamation;
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
public class ServiceRecmalation implements IService<Reclamation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation r) {
       try {
            String req = "INSERT INTO `reclamation`(`date_envoie`, `etat`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, r.getDate_envoie());
           ps.setString(2, r.getEtat());
            ps.executeUpdate();
            System.out.println("reclamation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
    try {
            String req = "DELETE FROM `reclamation` WHERE `id_reclamation` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation r) {
     try {
            String req = "UPDATE `reclamation` SET `date_envoie`=?,`etat`=? WHERE `id_reclamation`= " + r.getId_reclamation();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, r.getDate_envoie());
            ps.setString(2, r.getEtat());
            ps.executeUpdate();
            System.out.println("reclamation modifiée !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public List<Reclamation> getAll() {
     List<Reclamation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `reclamation`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reclamation r = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(r);
             } }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public Reclamation getOneById(int id) {
      Reclamation r =null;
        try {
            String req = "SELECT * FROM `reclamation` WHERE `id_reclamation`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 r = new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return r; 
    } 
    }
    

