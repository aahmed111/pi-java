/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import edu.projetpi.entities.Echange;
import edu.projetpi.entities.Evaluation;
import edu.projetpi.utils.DataSource;
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
public class ServiceEvaluation implements IService<Evaluation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evaluation e) {
        try {
            //get echange by id echange
            //set client 1 and client 2 from echange
            ServiceEchange s=new ServiceEchange();
            Echange ech=s.getOneById(e.getId_echange());
            System.out.println(" echange returned from evaluation : "+ech);
            String req = "INSERT INTO `evaluation`"
                    + "(`valeur1`,`valeur2` ,`id_echange`,`id_user1`,`id_user2` )"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getValeur1());
            ps.setInt(2, e.getValeur2());
            ps.setInt(3, e.getId_echange());
                        ps.setInt(4, ech.getId_client1());
                        ps.setInt(5, ech.getId_client2());

            ps.executeUpdate();
            System.out.println("evaluation ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evaluation` WHERE `id_evaluation`= ?";
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
            String req;
            req = "UPDATE `evaluation` SET `valeur1`=?,`valeur2`=? WHERE `id_evaluation`= " + e.getId_evaluation();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getValeur1());
            ps.setInt(2, e.getValeur2());
            ps.executeUpdate();
            System.out.println("Evaluation modifié !");
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
                //Evaluation e = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                Evaluation e1=new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6));
                list.add(e1);
                System.out.println("\n eval :"+e1.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Evaluation getOneById(int id) {
        Evaluation e = null;
        try {
            String req = "SELECT * FROM `evaluation` WHERE `id_evaluation`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                e = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }
}
