/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.Echange;
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
public class ServiceEchange implements IService<Echange>{
    
     Connection cnx= DataSource.getInstance().getCnx();

    
        
    @Override
    public void ajouter(Echange e) {
         try {
            String req = "INSERT INTO `echange`(`date_echange`, `statut`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, e.getDate_echange());
            ps.setString(2, e.getStatut());
            ps.executeUpdate();
            System.out.println("echange ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
             try {
            String req = "DELETE FROM `echange` WHERE `id_echange` = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("echange supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Echange e) {
         try {
            String req = "UPDATE `echange` SET `date_echange`=?,`statut`=? WHERE `id_echange`= " + e.getId_echange();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, e.getDate_echange());
            ps.setString(2, e.getStatut());
            ps.executeUpdate();
            System.out.println("echange modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Echange> getAll() {
          List<Echange> list = new ArrayList<>();
        try {
            String req = "Select * from `echange`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Echange e = new Echange(rs.getInt(1), rs.getString(2),rs.getString(3));
                list.add(e);
             } }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Echange getOneById(int id) {
      Echange e=null;
        try {
            String req = "SELECT * FROM `echange` WHERE `id_echange`?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 e = new Echange(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return e ;
    }
}
