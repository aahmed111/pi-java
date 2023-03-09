/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Evaluation;
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
public class ServiceEvaluation implements IService<Evaluation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Evaluation e) {
        try {

            ServiceEchange s = new ServiceEchange();
            Echange ech = s.getOneById(e.getEchange().getId_echange());
            String req = "INSERT INTO `evaluation`"
                    + "(`valeur1`,`valeur2` ,`id_echange`,`id_user1`,`id_user2` )"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getValeur1());
            ps.setInt(2, e.getValeur2());
            ps.setInt(3, e.getEchange().getId_echange());
            ps.setInt(4, ech.getUser1().getId_user());
            ps.setInt(5, ech.getUser2().getId_user());

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
                ServiceUser su = new ServiceUser();
                ServiceEchange se = new ServiceEchange();
                User user1 = su.getOneById(rs.getInt(1));
                User user2 = su.getOneById(rs.getInt(2));
                Echange echange = new Echange();
                Evaluation e1 = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(3), echange ,
                        user1, user2);
                list.add(e1);
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
                 ServiceUser su = new ServiceUser();
                  ServiceEchange se = new ServiceEchange();
                User user1 = su.getOneById(rs.getInt(1));
                User user2 = su.getOneById(rs.getInt(2));
                 Echange echange = new Echange();
                e = new Evaluation(rs.getInt(1), rs.getInt(2), rs.getInt(2),echange , user1, user2);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }
}
