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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(User u) throws SQLException {

        if (u instanceof Client) {

            Client client = (Client) u;
            String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber`, `noteEvaluation`, `sexe` ,`etat`,`cout`,`duree`)  VALUES (?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, client.getUsername());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getEmail());
            ps.setBytes(4, client.getPhoto());
            ps.setString(5, client.getType());
            ps.setInt(6, client.getPhoneNumber());
            ps.setInt(7, client.getNoteEvaluation());
            ps.setString(8, client.getSexe());
            ps.executeUpdate();
            System.out.println("client ajouté !");

        } else if (u instanceof Admin) {

            String type = "Admin";
            Admin admin = (Admin) u;
            String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber` ,`sexe`)  VALUES (?,?,?,?,?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            ps.setBytes(4, admin.getPhoto());
            ps.setString(5, "Admin");
            ps.setInt(6, admin.getPhoneNumber());
            ps.setString(7, admin.getSexe());
            ps.executeUpdate();
            System.out.println("admin ajouté !");
        } else {

            {
                Transporteur t = (Transporteur) u;

                String req = "INSERT INTO `user`(`username`, `password`, `email`, `photo`, `type`, `phoneNumber`,`noteEvaluation`,`sexe`)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, t.getUsername());
                ps.setString(2, t.getPassword());
                ps.setString(3, t.getEmail());
                ps.setBytes(4, t.getPhoto());
                ps.setString(5, t.getType());
                ps.setInt(6, t.getPhoneNumber());
                ps.setInt(7, t.getNoteEvaluation());
                ps.setString(8, t.getSexe());
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
    public void modifier(User u) throws SQLException {

        String req = "UPDATE `user` SET `username`=?,`password`=?,`email`=?,`photo`=?,`phoneNumber`=?  WHERE id_user=?";

        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setBytes(4, u.getPhoto());
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
                User admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                list.add(admin);

            } else if (rs.getString("type").equals("Client")) {
                Timestamp T = rs.getTimestamp("date_deblockage");
                LocalDateTime date_deblockage = null;
                if (T != null) {
                    date_deblockage = T.toLocalDateTime();
                }

                User client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), date_deblockage, rs.getInt(11), rs.getInt(12));
                list.add(client);
            } else {
                User transporteur = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString("etat"), rs.getInt("cout"));
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
                u = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8));
            } else if (rs.getString("type").equals("Client")) {

                Timestamp T = rs.getTimestamp("date_deblockage");
                LocalDateTime date_deblockage = null;
                if (T != null) {
                    date_deblockage = T.toLocalDateTime();
                }

                u = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), date_deblockage, rs.getInt("is_block"), rs.getInt("duree"));
            } else {
                Timestamp T = rs.getTimestamp("date_deblockage");
                LocalDateTime date_deblockage = null;
                if (T != null) {
                    date_deblockage = T.toLocalDateTime();
                }

                u = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString("etat"), rs.getInt("cout"), date_deblockage, rs.getInt("is_block"));
                // int id_user, String username, String password, String email, byte[] photo, String type, int phoneNumber, String sexe,int noteEvaluation,  String etat,int cout, LocalDateTime date_deblock, int is_block)
            }
        }

        return u;
    }

    public boolean verifierUsername(String username) throws SQLException {
        boolean check = false;
        String req = "SELECT `username`  FROM USER ";

        Statement ps = cnx.createStatement();
        ResultSet rs = ps.executeQuery(req);
        while (rs.next()) {

            if (rs.getString("username").equals(username)) {
                check = true;
                break;
            }

        }
        return check;
    }

    public Client getClientByUsername(String username) throws SQLException {
        Client c = null;
        String req = "SELECT * FROM user WHERE username=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            c = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9));

        }
        return c;
    }

    public Transporteur getTransporteurByUsername(String username) throws SQLException {
        Transporteur t = null;
        String req = "SELECT * FROM user WHERE username=?  ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            t = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString("etat"), rs.getInt("cout"));
        }
        return t;
    }

    public Admin getAdminByUsername(String username) throws SQLException {
        Admin a = null;
        String req = "SELECT * FROM user WHERE username=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            a = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8));
        }

        return a;
    }

    public List<Client> getAllClient() throws SQLException {
        List<Client> listClient = new ArrayList<>();

        List<User> users = getAll();

        for (User user : users) {
            if (user instanceof Client) {
                Client client = (Client) user;
                listClient.add(client);
            }
        }
        return listClient;
    }

    public List<Transporteur> getAllTransporteur() throws SQLException {
        List<Transporteur> listTransporteur = new ArrayList<>();

        List<User> users = getAll();

        for (User user : users) {
            if (user instanceof Transporteur) {
                Transporteur tr = (Transporteur) user;
                listTransporteur.add(tr);
            }
        }
        return listTransporteur;
    }

    public boolean isValidEmailAddress(String email) throws TextParseException {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            String domain = emailAddress.getAddress().split("@")[1];
            Record[] mxRecords = new Lookup(domain, Type.MX).run();
            if (mxRecords == null || mxRecords.length == 0) {
                result = false;
            }

            if (email.matches(regex)) {
                result = true;
            }
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public List<User> getUserByType(String type) throws SQLException {
        List<User> list = new ArrayList<>();
        String req = "SELECT * FROM user WHERE type=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("type").equals("Admin")) {
                User admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8));
                list.add(admin);

            } else if (rs.getString("type").equals("Client")) {
                User client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
                list.add(client);
            } else {
                User transporteur = new Transporteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBytes(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getString("etat"), rs.getInt("cout"));
            }

        }
        return list;

    }

    public boolean mailExist(String email) throws SQLException {
        boolean check = false;

        String req = "SELECT `email`  FROM USER ";
        Statement ps = cnx.createStatement();
        ResultSet rs = ps.executeQuery(req);
        while (rs.next()) {

            if (rs.getString("email").equals(email)) {
                check = true;
                break;
            }
        }

        return check;
    }

    public String getPasswordByEmail(String email) throws SQLException {
        String password = null;
        String req = "SELECT `password` FROM `user` WHERE `email`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            password = rs.getString("password");
        }
        return password;
    }

    public int getIdByName(String name) throws SQLException {

        int Id = 0;

        String req = "SELECT `id_user` FROM USER where `username`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Id = rs.getInt("id_user");
        }
        return Id;
    }

    public void blockUser(int id_user, int duration) throws SQLException {
        // Obtenez la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        // Calculez la date et l'heure de déblocage en ajoutant la durée spécifiée
        LocalDateTime unblockDateTime = now.plusMinutes(duration);
        String query = "UPDATE `user` SET `date_deblockage`=? ,`is_block`=? WHERE `id_user`=?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setTimestamp(1, Timestamp.valueOf(unblockDateTime));
        statement.setInt(2, 1);
        statement.setInt(3, id_user);
        statement.executeUpdate();

    }

    public void unblockUser(String username) throws SQLException {
        // Mettez à jour la base de données pour définir la colonne "est_bloque" sur false et supprimer la colonne "date_deblocage"
        String query = "UPDATE user SET is_block = 0 , date_deblockage = NULL WHERE username = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
    }
    public void UpdateUserErreur(String username) throws SQLException {
        int id_user = getIdByName(username);
        String req = "SELECT numbErreur, lastErreur FROM autentification WHERE username = ?";
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();

        int numbErreur = 0;
        Timestamp lastErreur = null;
        if (rs.next()) {
            numbErreur = rs.getInt("numbErreur");
            lastErreur = rs.getTimestamp("lastErreur");
            // Vérifie si l'utilisateur a échoué à la connexion dans les 5 dernières minutes
            /*  boolean hasFailedRecently = lastErreur != null
                      && System.currentTimeMillis() - lastErreur.getTime() < 5 * 60 * 1000;
            if (hasFailedRecently) {
                numbErreur++;
            } else {
                numbErreur = 1;
            }
             */
            String req2 = "UPDATE autentification SET numbErreur = ?, lastErreur = ? WHERE username = ?";
            PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, numbErreur);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, username);
            ps.executeUpdate();
            if (numbErreur >= 5) {
                blockUser(id_user, 10);
            }
        } else {
            String query = "INSERT INTO  autentification (username,numbErreur, lastErreur) VALUES (?, 1, ?)";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, username);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        }
    }

    public boolean checkBlocked(String username) throws SQLException {
        boolean isblocked = false;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateDeblock = null;
        Timestamp date = null;
        //Timestamp.valueOf(LocalDateTime.now()
        // Calculez la date et l'heure de déblocage en ajoutant la durée spécifiée
        String req = "SELECT `date_deblockage` FROM USER where `username`= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            date = rs.getTimestamp("date_deblockage");
            if (date != null) {
                dateDeblock = date.toLocalDateTime();
                if (dateDeblock.isBefore(now)) {
                    unblockUser(username);
                }
            }

        }
        String req2 = "SELECT `is_block` FROM USER where `username`= ?";
        PreparedStatement ps2 = cnx.prepareStatement(req2);
        ps2.setString(1, username);
        ResultSet rs2 = ps2.executeQuery();
        if (rs2.next()) {
            int etat = rs2.getInt(1);
            if (etat == 1) {
                isblocked = true;
            }
        }
        return isblocked;
    }

    public void updateDuree(int userId, int duree) throws SQLException {
        String reqSelect = "SELECT  `duree` FROM `user` WHERE `id_user`=?";
        PreparedStatement prSelect = cnx.prepareStatement(reqSelect);
        prSelect.setInt(1, userId);
        ResultSet rs = prSelect.executeQuery();
        int currentDuree = 0;
        if (rs.next()) {
            currentDuree = rs.getInt("duree");
        }
        String req = "UPDATE `user` SET `duree`=? WHERE `id_user`=?";
        PreparedStatement pr = cnx.prepareStatement(req);
        pr.setInt(1, currentDuree + duree);
        pr.setInt(2, userId);
        pr.executeUpdate();
    }

    public Map<String, Integer> getUserByDuree() throws SQLException {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        String req = "SELECT `username` , `duree` FROM user ORDER BY duree DESC LIMIT 10";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            map.put(rs.getString(1), rs.getInt(2));
        }
        return map;
    }

    public Map<String, Integer> getListMaleFemale() throws SQLException {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String req = "SELECT `sexe`, sum(duree) FROM user GROUP BY sexe ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            map.put(rs.getString(1), rs.getInt(2));
        }
        return map;
    }
}
