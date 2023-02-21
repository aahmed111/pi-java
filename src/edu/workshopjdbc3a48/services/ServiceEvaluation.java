/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Evaluation;
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
public class ServiceEvaluation implements IService<Evaluation>{
    
     Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evaluation e) {
         try {
            String req = "INSERT INTO `evaluation`(`valeur`) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, e.getValeur());
            ps.executeUpdate();
            System.out.println("evaluation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
         try {
            String req = "DELETE FROM `evaluation` WHERE `id_evaluation`= ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("evaluation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evaluation e) {
        try {
            String req = "UPDATE `evaluation` SET ,`valeur`=? WHERE `id_evaluation`= " + e.getId_evaluation();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, e.getValeur());
            ps.executeUpdate();
            System.out.println("echange modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evaluation> getAll() {
           List<Evaluation> list = new ArrayList<>();
        try {
            String req = "Select * from `evaluation`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evaluation e = new Evaluation(rs.getInt(1), rs.getString(2));
                list.add(e);
             } }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Evaluation getOneById(int id) {
          Evaluation e=null;
        try {
            String req = "SELECT * FROM `evaluation` WHERE `id_evaluation`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                 e = new Evaluation(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return e ;
    }
    }
   
