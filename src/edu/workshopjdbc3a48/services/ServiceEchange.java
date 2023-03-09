/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;


import edu.workshopjdbc3a48.entities.Article;
import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.text.Segment;

/**
 *
 * @author pc
 */
public class ServiceEchange implements IService<Echange> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Echange e) {
        try {
            String req = "INSERT INTO `echange`( `statut`, `date_Echange`,"
                    + "`id_user1`, `id_user2`, `id_article1` ,`id_article2`,`confirmation` )"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getStatut());

            ps.setDate(2, new Date(e.getDate_echange().getYear(),
                    e.getDate_echange().getMonth(),
                    e.getDate_echange().getDate()));

            ps.setInt(3, e.getUser1().getId_user());
            ps.setInt(4, e.getUser2().getId_user());
            ps.setInt(5, e.getArticle1().getId_article());
            ps.setInt(6, e.getArticle2().getId_article());
            ps.setInt(7, e.getConfirmation());

            ps.executeUpdate();
            System.out.println("echange ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `echange` WHERE `id_echange` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("echange supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Echange e) {
        try {
            String req = "UPDATE `echange` SET `statut`=? WHERE `id_echange`= " + e.getId_echange();
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, e.getStatut());
            ps.setInt(2, e.getConfirmation());
            ps.executeUpdate();
            System.out.println("echange modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Echange> getAll() {
        List<Echange> list = new ArrayList<>();
        try {
            String req = "Select * from `echange`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceUser su = new ServiceUser();
                User user1 = su.getOneById(rs.getInt("id_user1"));
                User user2 = su.getOneById(rs.getInt("id_user2"));
                ServiceArticle sa = new ServiceArticle();
                Article article1 = sa.getOneById(rs.getInt("id_article1"));
                Article article2 = sa.getOneById(rs.getInt("id_article2"));
                Echange e = new Echange(rs.getInt(1), rs.getDate(2), rs.getString(3),
                        user1, user2, article1, article2, rs.getInt("confirmation"));

                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Echange getOneById(int id) {
        Echange e = new Echange();
        try {
            String req = "SELECT * FROM echange WHERE id_echange = " + id;
            PreparedStatement ps = cnx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);

            if (rs.next()) {
                ServiceUser su = new ServiceUser();
                User user1 = su.getOneById(rs.getInt("id_user1"));
                User user2 = su.getOneById(rs.getInt("id_user2"));
                ServiceArticle sa = new ServiceArticle();
                Article article1 = sa.getOneById(rs.getInt("id_article1"));
                Article article2 = sa.getOneById(rs.getInt("id_article2"));
                e = new Echange(rs.getInt(1), rs.getDate(2), rs.getString(3), user1, user2,
                        article1, article2, rs.getInt("confirmation"));

            }
        } catch (SQLException ex) {
            System.out.println("echange ISERVICE " + ex.getMessage());
        }
        return e;
    }

   

    public void evaluer(String statut, Integer note) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* public void generatePdf(ObservableList<Echange> stockList) {
    // Créer un document PDF vide
    Document document = new Document() {
        @Override
        public int getLength() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void addDocumentListener(DocumentListener listener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeDocumentListener(DocumentListener listener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void addUndoableEditListener(UndoableEditListener listener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void removeUndoableEditListener(UndoableEditListener listener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getProperty(Object key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void putProperty(Object key, Object value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getText(int offset, int length) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void getText(int offset, int length, Segment txt) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Position getStartPosition() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Position getEndPosition() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Position createPosition(int offs) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public javax.swing.text.Element[] getRootElements() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public javax.swing.text.Element getDefaultRootElement() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void render(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    try {
        // Créer une instance de PdfWriter pour écrire le document
        PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream("Echange.pdf"));

        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph title = new Paragraph("Liste des produits en stock");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter une table avec les données de stock
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        // Ajouter les en-têtes de colonnes
        Stream.of("Code barre", "Prix", "Marque", "Date d'ajout", "Date d'expiration")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        // Ajouter les données de stock
        Echange.forEach(stock -> {
            table.addCell(Echange.IdEchange());
            table.addCell(Double.toString(stock.getPrix()));
            table.addCell(stock.getMarque());
            table.addCell(stock.getDateAjout().toString());
            table.addCell(stock.getDateExpiration().toString());
        });

        // Ajouter la table au document
        document.add(table);

        // Fermer le document
        document.close();

        // Afficher une notification de succès
        Notifications.create()
                .title("PDF généré")
                .text("Le PDF a été généré avec succès.")
                .showInformation();
    } catch (FileNotFoundException | DocumentException e) {
        // Afficher une notification d'erreur
        Notifications.create()
                .title("Erreur de génération de PDF")
                .text("Une erreur s'est produite lors de la génération du PDF.")
                .showError();
    }
}*/
}
