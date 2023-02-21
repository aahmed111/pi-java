/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Admin;
import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User u) throws SQLException {

        if (u instanceof Client) {

                String type = "Client";
                Client client = (Client) u;
                String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber`, `noteEvaluation`, `adresse` ,`etat`,`cout`)  VALUES (?,?,?,?,?,?,?,?,DEFAULT,DEFAULT)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, client.getUsername());
                ps.setString(2, client.getPassword());
                ps.setString(3, client.getEmail());
                ps.setString(4, client.getPhoto());
                ps.setString(5, type);
                ps.setInt(6, client.getPhoneNumber());
                ps.setInt(7, client.getNoteEvaluation());
                ps.setString(8, client.getAdresse());
                ps.executeUpdate();
                System.out.println("client ajouté !");
           
        } else if (u instanceof Admin) {
           
                String type = "Admin";
                Admin admin = (Admin) u;
                String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber`,`noteEvaluation` ,`adresse`  ,`etat`,`cout`)  VALUES (?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, admin.getUsername());
                ps.setString(2, admin.getPassword());
                ps.setString(3, admin.getEmail());
                ps.setString(4, admin.getPhoto());
                ps.setString(5, type);
                ps.setInt(6, admin.getPhoneNumber());

                ps.executeUpdate();
                System.out.println("admin ajouté !");
           
        } else {

            {
                Transporteur t = (Transporteur) u;
                
                    String type = "Transporteur";

                    String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber`,`noteEvaluation`,`adresse`,`etat`,`cout`)  VALUES (?,?,?,?,?,?,?,DEFAULT,?,?)";
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setString(1, t.getUsername());
                    ps.setString(2, t.getPassword());
                    ps.setString(3, t.getEmail());
                    ps.setString(4, t.getPhoto());
                    ps.setString(5, type);
                    ps.setInt(6, t.getPhoneNumber());
                    ps.setInt(7, t.getPhoneNumber());
                    ps.setString(9, t.getEtat());
                    ps.setInt(10, t.getCout());

                    ps.executeUpdate();
                    System.out.println("Transporteur ajouté !");
                
            }
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String type = null;
     
            String requette = "SELECT `type` FROM `user` WHERE `id_user`= " + id;
            Statement pr = cnx.createStatement();
            ResultSet rs = pr.executeQuery(requette);
            if (rs.next()) {
                type = rs.getString("type");
            }

        if (type.equals("Admin")) {
            System.out.println("tu ne peut pas supprimé un administrateur ");
        } else {
           
                String req = "DELETE FROM `user` WHERE `id_user`= " + id;
                Statement ps = cnx.createStatement();
                ps.executeUpdate(req);
                System.out.println("User supprimé !");
           
        }

    }

    @Override
    public void modifier(User u) throws SQLException{
        if (u instanceof Transporteur) {

        }

       
            String req = "UPDATE `user` SET `username`=?,`password`=?,`email`=?,`photo`=?,`phoneNumber`=? WHERE id_user=?";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhoto());
            ps.setInt(5, u.getPhoneNumber());
            ps.setInt(6, u.getId_user());

            ps.executeUpdate();
            System.out.println("User modifié !");

        

    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> list = new ArrayList<>();
       
            String req = "SELECT * FROM `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                if (rs.getString("type").equals("Admin")) {
                    User admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                    list.add(admin);

                } else if (rs.getString("type").equals("Client")) {
                    User client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                    list.add(client);
                } else {
                    User transporteur = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString("etat"), rs.getInt("cout"));
                    list.add(transporteur);
                }

            }
        

        return list;
    }

    @Override
    public User getOneById(int id) throws SQLException {
        User u = null;   
            String req = "SELECT * FROM `user` WHERE `id_user`=" + id;
            Statement ps = cnx.createStatement();
            ResultSet rs = ps.executeQuery(req);
            if (rs.next()) {
                if (rs.getString("type").equals("Admin")) {
                    u = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                } else if (rs.getString("type").equals("Client")) {
                    u = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
                } else {
                    u = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString("etat"), rs.getInt("cout"));
                }
            }
       
        return u;
    }
    public Client getClientByUsernamePassword(String username,String password) throws SQLException{
        Client c =null;
        String req = "SELECT * FROM user WHERE username=? AND password=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery(); 
        if (rs.next()){
             c = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9));  
            }
          
        return  c;
    }
  public Admin getAdminByUsernamePassword(String username,String password) throws SQLException{
        Admin a =null;
        String req = "SELECT * FROM user WHERE username=? AND password=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery(); 
        if (rs.next()){
             a = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
          
        return  a;
  }
   public List<Client> getAllClient() throws SQLException {
        List<Client> listClient = new ArrayList<>();
       
        List<User> users = getAll();
        
        for(User user : users ){
            if (user instanceof Client){
            Client client = (Client) user;
            listClient.add(client);
           }
        }
       return listClient;
    }
}
