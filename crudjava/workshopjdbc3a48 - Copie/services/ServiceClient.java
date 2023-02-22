/*package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.entities.Client;
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
 
public class ServiceClient extends ServiceUser {
  Connection cnx= DataSource.getInstance().getCnx();
  
    

    public void ajouter(Client c) {
        try {
            User u = c.getU();
            super.ajouter(c.getU());
            String req = "INSERT INTO client`( nom`, prenom, gmail, adresse, sexe, emploie, recto_cin, verso_cin,`id_user`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setString(1, c.getNom());
             ps.setString(2, c.getPrenom());
             ps.setString(3, c.getGmail());
             ps.setString(4, c.getAdresse()); 
             ps.setString(5, c.getSexe()); 
             ps.setString(6, c.getEmploie()); 
             ps.setString(7, c.getRecto_cin()); 
             ps.setString(8, c.getVerso_cin());
             ps.setInt(9, u.getId_user()); 
             ps.executeUpdate();
            System.out.println("client ajouté !");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public void supprimer(Client c) {
         try {
            
            String req = "DELETE FROM client WHERE id_client = ?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId_client());
           ps.executeUpdate();
            System.out.println("client supprimé !");
        
         super.supprimer(c.getU().getId_user());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void modifier(Client c) {
     try {
            String req = "UPDATE client SET nom`=?,prenom`=?,`gmail`=?,`adresse`=?,`sexe`=?,`emploie`=?,`recto_cin`=?,`verso_cin`=? WHERE `id_client`= " + c.getId_client();
            PreparedStatement ps = cnx.prepareStatement(req);
           ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getGmail());
             ps.setString(4, c.getAdresse()); 
             ps.setString(5, c.getSexe()); 
             ps.setString(5, c.getEmploie()); 
             ps.setString(5, c.getRecto_cin()); 
             ps.setString(5, c.getVerso_cin()); 
            ps.executeUpdate();
            System.out.println("client modifié !");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
    }

   

  @Override
    public List<Client> getAll() {
        List<Client> list = new ArrayList<>();
        try {
            String req = "Select * from client c INNER JOIN USER u ON c.id_user =u.id_client";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Client c = new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

  

  @Override
    public Client getOneById(int id_client) {
          Client c =null;
        try {
            String req = "SELECT * FROM client WHERE `id_client`=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id_client);
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                c = new Client(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return c; 
    }   
}*/