/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Avis;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Audience;
import objects.Reactions;

/**
 *
 * @author pc
 */
public class ServiceAvis implements IService<Avis>{
 Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Avis a) {
     try {
            String req = "INSERT INTO `avis`(`reaction`, `commentaire`, `date_creation`, `id_user`, `id_annonce`) VALUES (?,?,CURRENT_TIMESTAMP(),?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, a.getReaction().toString());
            ps.setString(2, a.getCommentaire());
            ps.setInt(3,a.getUser().getId_user() );
            ps.setInt(4,a.getAnnonce().getId_annonce() );
             ps.executeUpdate();
            System.out.println("avis ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void supprimer(int id) {
      try {
            String req = "DELETE FROM `avis` WHERE `id_avis`= ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("avis supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    @Override
    public void modifier(Avis a) {
     try {
            String req = "UPDATE `avis` SET `reaction`=?,`commentaire`=? WHERE `id_avis`=" + a.getId_avis();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, a.getReaction().toString());
            ps.setString(2, a.getCommentaire());
            ps.executeUpdate();
            System.out.println("avis modifié !");
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  }

    @Override
    public List<Avis> getAll() {
      List<Avis> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `avis`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceUser su = new ServiceUser();
                ServiceAnnonce sa = new ServiceAnnonce();
                 User user = su.getOneById(rs.getInt(5));
                 Annonce annonce = sa.getOneById(rs.getInt(6));
               Avis a = new Avis(rs.getInt(1), Reactions.valueOf(rs.getString(2)),rs.getString(3),rs.getDate(4),user,annonce);
                list.add(a);
             }
            }  
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         for(int i=0;i<list.size();i++)
       
       {
           System.out.println(list.get(i));
       }
        return list;}

    @Override
    public Avis getOneById(int id) {
     Avis a =new Avis();
        try {
            String req = "SELECT * FROM `avis` WHERE `id_avis` = " + id ;
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
            ServiceUser su = new ServiceUser();
                ServiceAnnonce sa = new ServiceAnnonce();
                 User user = su.getOneById(rs.getInt(5));
                 Annonce annonce = sa.getOneById(rs.getInt(6));
                a = new Avis(rs.getInt(1), Reactions.valueOf(rs.getString(2)),rs.getString(3),rs.getDate(4),user,annonce);
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return a; 
    } }

  
  
  

