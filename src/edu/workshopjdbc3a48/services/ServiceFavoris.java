/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Annonce;
import edu.workshopjdbc3a48.entities.Favoris;
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

/**
 *
 * @author pc
 */
public class ServiceFavoris implements IService<Favoris>{
 Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Favoris f) {
        try {
            String req = "INSERT INTO `favoris`(`date_ajout`, `id_user`, `id_annonce`) VALUES (CURRENT_TIMESTAMP(),?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1,f.getUser().getId_user());
             ps.setInt(2,f.getAnnonce().getId_annonce());
            ps.executeUpdate();
            System.out.println("favoris ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `favoris` WHERE `id_favoris`= ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           ps.executeUpdate();
            System.out.println("favoris supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    @Override
    public List<Favoris> getAll() {
       List<Favoris> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `favoris`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceUser su = new ServiceUser();
                ServiceAnnonce sa = new ServiceAnnonce();
                 User user = su.getOneById(rs.getInt(3));
                 Annonce annonce = sa.getOneById(rs.getInt(4));
                Favoris f = new Favoris(rs.getInt(1), rs.getDate(2),user,annonce);
                list.add(f);
             } }
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
    public Favoris getOneById(int id) {
            Favoris f =new Favoris();
         try {
            String req = "SELECT * FROM `favoris` WHERE `id_favoris`=" + id ;
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
              ServiceUser su = new ServiceUser();
                ServiceAnnonce sa = new ServiceAnnonce();
                 User user = su.getOneById(rs.getInt(3));
                 Annonce annonce = sa.getOneById(rs.getInt(4));
                f = new Favoris(rs.getInt(1), rs.getDate(2),user,annonce);
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return f; 
    }

    @Override
    public void modifier(Favoris t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    

