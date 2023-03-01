/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Agence;
import edu.workshopjdbc3a48.entities.Chat;
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
public class ServiceAgence  implements IService<Agence>{
Connection cnx= DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Agence t) throws SQLException {
       String req = "INSERT INTO `agence`( `nom`) VALUES (? )";
       PreparedStatement pr = cnx.prepareStatement(req);
       pr.setString(1,"nom");
       pr.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
       String req ="DELETE FROM `agence` WHERE id ="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("agence supprimé !");
    }

    @Override
    public void modifier(Agence t) throws SQLException {
       String req = "UPDATE `chat` SET `nom`=? WHERE `id_chat`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setInt(2, t.getId_agence());
        ps.executeUpdate();
        System.out.println("chat modifié !");
    
    }

    @Override
    public List<Agence> getAll() throws SQLException {
        List<Agence> list = new ArrayList<>();
        String req = "SELECT * FROM `agence`";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
         
         Agence a = new Agence(rs.getInt(1),rs.getString(2));
         
            list.add(a);
        }
        return list;
    }

    @Override
    public Agence getOneById(int id) throws SQLException {
       Agence agence = null ;
        String req = "SELECT * FROM `agence` WHERE `id_agence`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
           
            agence = new Agence(rs.getInt(1), rs.getString(2));
      
    }
       return agence;
    }
}
