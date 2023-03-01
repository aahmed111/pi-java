/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

 
import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.Reclamation;
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
 public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx = DataSource.getInstance().getCnx();

  /*  
    @Override
    public void ajouter(Reclamation t) throws SQLException {
        try {
            String req = "INSERT INTO `reclamation`( `id_user1`, `Nom_user`, `description`,`Email`, `id_echange`, `date_envoie`) VALUES (null,null,?,?,null,NOW()";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getid_user1().getId_user()); 
            ps.setString(1, t.getNom_user());  
            ps.setString(3, t.getDescription());  
            ps.setString(4, t.getEmail());  
            ps.setInt(5, t.getEchange().getId_echange());    
            ps.executeUpdate();
            System.out.println("Reclamation ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'envoi de la reclamation : " + e.getMessage());
        }
    }
 
 */
    
    
    @Override
public void ajouter(Reclamation t) throws SQLException {
    try {
        String req = "INSERT INTO reclamation (id_user1, Nom_user, description, Email ,id_echange, date_envoie) VALUES (4,?,?,?,4,NOW())";
        PreparedStatement ps = cnx.prepareStatement(req);
         ps.setString(1, t.getNom_user()); 
         ps.setString(2, t.getDescription()); 
         ps.setString(3, t.getEmail()); 
         
        ps.executeUpdate();
        System.out.println("Reclamation ajoutée");
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'envoi de la reclamation : " + e.getMessage());
    }
}
   
    
    
    
    
    @Override
    public void supprimer(int id) throws SQLException {
        try {
            String req = "DELETE FROM reclamation WHERE id_reclamation = ?"+id;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reclamation supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation t) throws SQLException {
        try {
            String req = "UPDATE `reclamation` SET `description`=? WHERE `id_reclamation`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getDescription());  
         
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("Reclamation modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    /*
  @Override
public List<Reclamation> getAll() throws SQLException {
    List<Reclamation> reclamations = new ArrayList<>();
    try {
        String req = "SELECT * FROM reclamation";
        Statement ps = cnx.createStatement();
        ResultSet rs = ps.executeQuery(req);
        while (rs.next()) {
          ServiceUser su = new ServiceUser() ;
          User user1  = su.getOneById(rs.getInt("id_user1"));
     //     User user  = su.getOneById(rs.getInt("Nom_user"));     //a verifier 
          ServiceEchange se = new ServiceEchange();
          Echange e = se.getOneById(rs.getInt("id_echange"));
        //  Reclamation rec = new Reclamation( rs.getInt(1), user1, user2,rs.getString("description") 
         // Reclamation rec = new Reclamation( rs.getInt(1),   user1,    user2     ,rs.getString("description")  ,      rs.getDate("date_envoie")        ,rs.getString("Email") , e) ;
      Reclamation rec = new Reclamation( rs.getInt(1),   user1,     rs.getString("Nom_user")   ,rs.getString("description")   ,rs.getString("Email") , e ,  rs.getDate("date_envoie") ) ;
            reclamations.add(rec);
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
    }
    return reclamations;
}
    */
    
    @Override
   public List<Reclamation> getAll() throws SQLException {
    List<Reclamation> reclamations = new ArrayList<>();
    
        String req = "SELECT * FROM `reclamation`";
        Statement ps = cnx.createStatement();
        ResultSet rs = ps.executeQuery(req);
        while (rs.next()) {
            ServiceUser su = new ServiceUser();
         
              User  user1 = su.getOneById(4);

            ServiceEchange se = new ServiceEchange();
             Echange   e = se.getOneById(4);
            Reclamation rec = new Reclamation(
                    rs.getInt(1),
                    user1,
                    rs.getString("Nom_user"),
                    rs.getString("description"),
                    rs.getString("Email"),
                    e,
                    rs.getDate("date_envoie")
            );
            reclamations.add(rec);
        }
   
    return reclamations;
}





    
    
  /*  public List<Reclamation> getAll() throws SQLException {
    List<Reclamation> reclamations = new ArrayList<>();
    try {
        String req = "SELECT * FROM reclamation";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ServiceUser su = new ServiceUser();
            User user1 = su.getOneById(rs.getInt("id_user1"));
            User user2 = su.getOneById(rs.getInt("id_user2"));
            ServiceEchange se = new ServiceEchange();
            Echange e = se.getOneById(rs.getInt("id_echange"));
            Reclamation rec = new Reclamation(rs.getInt(1), user1, user2, rs.getString("description"),
                    rs.getDate("date_envoie"), rs.getString("Email"), e);
            reclamations.add(rec);
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des réclamations : " + e.getMessage());
    }
    return reclamations;
}
    
   */ 
    @Override
    public Reclamation getOneById(int id) throws SQLException {
      Reclamation recl = null;
    try {
        String req = "SELECT * FROM reclamation WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {       
          ServiceUser su = new ServiceUser() ;
          User user1  = su.getOneById(rs.getInt("id_user1"));
          User user  = su.getOneById(rs.getInt("Nom_user"));
          ServiceEchange se = new ServiceEchange();
          Echange e = se.getOneById(rs.getInt("id_echange"));
             Reclamation rec = new Reclamation( rs.getInt(1),   user1,     rs.getString("Nom_user")   ,rs.getString("description")   ,rs.getString("Email") , e ,  rs.getDate("date_envoie") ) ;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return recl;
    }
 }