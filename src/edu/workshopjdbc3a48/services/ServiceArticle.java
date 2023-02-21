/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Produit;
import edu.workshopjdbc3a48.entities.Services;
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
public class ServiceArticle implements IService<Article> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Article a) {
        String type_article = "inconnu";
        String type_produit = null;
        String type_service = null;

        if (a instanceof Produit) {
            type_article = "produit";
            type_produit = ((Produit) a).getType_produit();
            type_service = "N/A"; // Valeur par défaut pour les produits
        } else if (a instanceof Services) {
            type_article = "service";
            type_produit = "N/A"; // Valeur par défaut pour les services
            type_service = ((Services) a).getType_Service();
        } else if (a instanceof Produit) {
            a.setType_article("produit");
            type_produit = ((Produit) a).getType_produit();

        } else {
            a.setType_article("service");

            type_service = ((Services) a).getType_Service();

        }

        try {
            String req = "INSERT INTO article`( date_ajout`, description, image, proprietaire, estimation, type_article,`type_produit`,`type_service`) "
                      + "VALUES (NOW(),?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setString(1, a.getDescription());
            ps.setString(2, a.getImage());
            ps.setString(3, a.getProprietaire());
            ps.setInt(4, a.getEstimation());
            ps.setString(5, type_article);
            ps.setString(6, type_produit);
            ps.setString(7, type_service);

            ps.executeUpdate();
            System.out.println("article ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*  String notificationTitle = "Nouvel article ajouté";
    String notificationMessage = "L'article a ete ajoute ";
    SystemTray tray = SystemTray.getSystemTray();
    Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
    TrayIcon trayIcon = new TrayIcon(image, "Tray Icon");
    trayIcon.setImageAutoSize(true);
    try {
        tray.add(trayIcon);
    } catch (AWTException e) {
        System.out.println("Erreur lors de l'ajout de l'icône de la barre d'état système");
    }
    trayIcon.displayMessage(notificationTitle, notificationMessage, MessageType.INFO);
}*/
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM article  WHERE id_categorie = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("article supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Article a) {
        try {
            String req = "UPDATE article SET  description`=?, image`=?,  `estimation`=? WHERE id_article=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setString(1,a.getDescription());
            ps.setString(2,a.getImage());
            ps.setInt(3, a.getEstimation());
            ps.setInt(4, a.getId_article());

            ps.executeUpdate();
            System.out.println("Article modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Article> getAll() {
        List<Article> list = new ArrayList<>();
        try {
            String req = "SELECT article.*, c.type FROM article INNER JOIN categorie ON categorie.id_categorie = article.id_categorie;";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("type_article").equals("produit")) {
                    Article a = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                    list.add(a);
                } else if (rs.getString("type_article").equals("service")) {
                    Article a = new Services(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
                    list.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    @Override
    public Article getOneById(int id) {
        Article a = null;

        try {
            String req = "SELECT * FROM article WHERE id_article = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id); // Set the ID parameter
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Use if instead of while since we're only expecting one result
                if (rs.getString("type_article").equals("produit")) {
                    a = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));

                } else if (rs.getString("type_article").equals("service")) {
                    a = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

}
