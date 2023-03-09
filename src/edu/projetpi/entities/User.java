/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.entities;

/**
 *
 * @author pc
 */
public class User {

    private int id_user;
    private String nom, prenom, gmail, adresse, sexe, emploie, recto_cin, verso_cin;

    public User(int id_user, String nom, String prenom, String gmail, String adresse, String sexe, String emploie, String recto_cin, String verso_cin) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.adresse = adresse;
        this.sexe = sexe;
        this.emploie = emploie;
        this.recto_cin = recto_cin;
        this.verso_cin = verso_cin;
    }

    public User(String nom, String prenom, String gmail, String adresse, String sexe, String emploie, String recto_cin, String verso_cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.adresse = adresse;
        this.sexe = sexe;
        this.emploie = emploie;
        this.recto_cin = recto_cin;
        this.verso_cin = verso_cin;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGmail() {
        return gmail;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public String getEmploie() {
        return emploie;
    }

    public String getRecto_cin() {
        return recto_cin;
    }

    public String getVerso_cin() {
        return verso_cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setEmploie(String emploie) {
        this.emploie = emploie;
    }

    public void setRecto_cin(String recto_cin) {
        this.recto_cin = recto_cin;
    }

    public void setVerso_cin(String verso_cin) {
        this.verso_cin = verso_cin;
    }

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", gmail=" + gmail + ", adresse=" + adresse + ", sexe=" + sexe + ", emploie=" + emploie + ", recto_cin=" + recto_cin + ", verso_cin=" + verso_cin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

}
