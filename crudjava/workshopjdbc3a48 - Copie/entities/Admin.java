/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author pc
 */
public class Admin {
   private int id_admin;
   private String nom,prenom,gmail,sexe,adresse;

    public Admin(int id_admin, String nom, String prenom, String gmail, String sexe, String adresse) {
        this.id_admin = id_admin;
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.sexe = sexe;
        this.adresse = adresse;
    }

   
    public Admin(String nom, String prenom, String gmail, String sexe, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.gmail = gmail;
        this.sexe = sexe;
        this.adresse = adresse;
    }

    public int getId_admin() {
        return id_admin;
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

    public String getSexe() {
        return sexe;
    }

    public String getAdresse() {
        return adresse;
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

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "admin{" + "id_admin=" + id_admin + ", nom=" + nom + ", prenom=" + prenom + ", gmail=" + gmail + ", sexe=" + sexe + ", adresse=" + adresse + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Admin other = (Admin) obj;
        if (this.id_admin != other.id_admin) {
            return false;
        }
        return true;
    }
    
    
    
}
