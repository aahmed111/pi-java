/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Agence;
import edu.workshopjdbc3a48.entities.Chat;
import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Reservation;
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
 * @author abdel
 */
public class ServicReservation implements IService<Reservation>{
    Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation t) throws SQLException {
         String req = "INSERT INTO `reservation`( `id_client`, `confirmation`, `id_article`, `id_echange`, `date`, `id_agence`) VALUES (?,?,?,?,NOW(),?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getClient().getId_user());
        ps.setInt(2, t.getConfirmation());
         ps.setInt(3, t.getArticle().getId_article());
           ps.setInt(4,t.getEchange().getId_echange());
               ps.setInt(5, t.getAgence().getId_agence());
        ps.executeUpdate();
        System.out.println("reservation ajouté.");
    }
    @Override
    public void supprimer(int id) throws SQLException {
      String req = "DELETE FROM `reservation` WHERE reservation_id =" + id;
      PreparedStatement ps = cnx.prepareStatement(req);
      ps.executeUpdate();
      System.out.println("Reservation supprimée.");
    }

    @Override
    public void modifier(Reservation t) throws SQLException {
         String req = "UPDATE `reservation` SET `confirmation`=? WHERE `id_reservation`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getConfirmation());
        ps.setInt(2, t.getId_reservation());
        ps.executeUpdate();
        System.out.println("reservation modifié !");
    }

    @Override
    public List<Reservation> getAll() throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String req = "SELECT * FROM `reservation` ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            ServiceAgence sa = new ServiceAgence();    
            ServiceUser su = new ServiceUser();
            ServiceArticle sart = new ServiceArticle();
            ServiceEchange se =new ServiceEchange();     
            User user = su.getOneById(rs.getInt("id_user"));
            Produit article = (Produit) sart.getOneById(rs.getInt("id_article"));
            Agence agence = sa.getOneById(rs.getInt("id_agence"));
            Echange echange = se.getOneById(rs.getInt("id_echange"));
            Reservation reservation = new Reservation(rs.getInt("id_reservation"),user ,rs.getInt("confirmation"),article,echange,rs.getDate("date"),agence);
        
            list.add(reservation);
        }
        return list;
    }

    @Override
    public Reservation getOneById(int id) throws SQLException {
      Reservation reservation = null;
        String req = "SELECT * FROM `reservation`WHERE `id_reservation`=?";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            ServiceAgence sa = new ServiceAgence();    
            ServiceUser su = new ServiceUser();
            ServiceArticle sart = new ServiceArticle();
            ServiceEchange se =new ServiceEchange();     
            User user = su.getOneById(rs.getInt("id_user"));
            Produit article = (Produit) sart.getOneById(rs.getInt("id_article"));
            Agence agence = sa.getOneById(rs.getInt("id_agence"));
            Echange echange = se.getOneById(rs.getInt("id_echange"));
             reservation = new Reservation(rs.getInt("id_reservation"),user ,rs.getInt("confirmation"),article,echange,rs.getDate("date"),agence);
            
        }
        return reservation;
    }
}
