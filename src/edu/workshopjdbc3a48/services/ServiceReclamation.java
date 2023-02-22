/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Reclamation;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
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

    

    
 

    public static List<Reclamation> getReclamationsByClient(Client client) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Reclamation> reclamations = new ArrayList<Reclamation>();
        return null;
  
    
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
     

        try {
             
            String req = "INSERT INTO reclamation (id_client, sujet, contenu, date_reclamation, est_traitee) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getClient().getId());
            ps.setString(2, t.getSujet());
            ps.setString(3, t.getContenu());
            ps.setDate(4, new java.sql.Date(t.getDateReclamation().getTime()));
            ps.setBoolean(5, t.isEstTraitee());
 System.out.println("Reclamation ajouté");  
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'envoie de la reclamation : " + e.getMessage());
    }    
    }
         
    

    @Override
    public void supprimer(int id) throws SQLException {
        try {
             
            String req = "DELETE FROM reclamation WHERE id = ?";
             PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("reclamation  supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation t) throws SQLException {
 
    }

    @Override
    public List<Reclamation> getAll() throws SQLException {
        return null;
         
    }

    @Override
    public Reclamation getOneById(int id) throws SQLException {
        return null;
         
    }
}
           
    

