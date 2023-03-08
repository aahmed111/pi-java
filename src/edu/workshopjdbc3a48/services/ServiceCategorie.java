/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Categorie;
import static edu.workshopjdbc3a48.entities.Categorie.verifChampLettres;
import edu.workshopjdbc3a48.utils.DataSource;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
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
public class ServiceCategorie implements IService<Categorie> {

    Connection cnx = DataSource.getInstance().getCnx();



    @Override
   public void ajouter(Categorie c) {
    try {
        if (verifChampLettres(c.getNom()) && verifChampLettres(c.getDescription())) {
            String req = "INSERT INTO `categorie`( `nom`, `description`, `image`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getDescription());
              ps.setBytes(3, c.getImage());
            ps.executeUpdate();
            System.out.println("Catégorie ajoutée !");
            
            // Envoyer une notification système
            String notificationTitle = "Nouvelle catégorie ajoutée";
            String notificationMessage = "La catégorie " + c.getNom() + " a été ajoutée avec succès.";
            afficherNotificationSysteme(notificationTitle, notificationMessage);
        } else {
            System.out.println("Erreur : nom ou description invalide !");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
}

public void afficherNotificationSysteme(String titre, String message) {
    SystemTray tray = SystemTray.getSystemTray();
    Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
    TrayIcon trayIcon = new TrayIcon(image, "Tray Icon");
    trayIcon.setImageAutoSize(true);
    try {
        tray.add(trayIcon);
    } catch (AWTException e) {
        System.out.println("Erreur lors de l'ajout de l'icône de la barre d'état système");
    }
    trayIcon.displayMessage(titre, message, MessageType.INFO);
}


    @Override
    public void supprimer(int id_categorie) {
        try {
            String req = "DELETE FROM categorie WHERE id_categorie = ?" ;
          PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_categorie);
           ps.executeUpdate();
            System.out.println("catégorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
        

    @Override
  public void modifier(Categorie c) {
    try {
        String req = "UPDATE categorie SET description`=?, nom`=? WHERE `id_categorie`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getDescription());
        ps.setString(2, c.getNom());
        ps.setInt(3, c.getId_categorie());
       
        ps.executeUpdate();
        System.out.println("categorie modifié !");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public List<Categorie> getAll() {
        List<Categorie> list = new ArrayList<>();
        try {
           String req = "SELECT * FROM categorie";
            Statement s = cnx.createStatement();
            ResultSet rs = s.executeQuery(req);
            while (rs.next()) {
                int id_categorie = rs.getInt(1);
                 String nom = rs.getString(2);
                String description = rs.getString(3);
               
                byte[] image = rs.getBytes(4);
               Categorie c = new Categorie(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getBytes(4));
                list.add(c);
             }
            }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         for(int i=0;i<list.size();i++) {
        System.out.println(list.get(i));
    }
   
        return list;
    }
    
    
    @Override
    public Categorie getOneById(int id) {
    Categorie c = null;
    
    
    try {
        String req = "SELECT * FROM categorie WHERE id_categorie = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id); 
        ResultSet rs = ps.executeQuery();
        if (rs.next()) { 
           
                     c = new Categorie (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getBytes(4));
                
            
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return c;
}
    
    
    }