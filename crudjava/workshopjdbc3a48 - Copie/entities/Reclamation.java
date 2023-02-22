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
public class Reclamation {
   private int id_reclamation;
   private String date_envoie,etat;

    public Reclamation(int id_reclamation, String date_envoie, String etat) {
        this.id_reclamation = id_reclamation;
        this.date_envoie = date_envoie;
        this.etat = etat;
    }

   
    public Reclamation(String date_envoie, String etat) {
        this.date_envoie = date_envoie;
        this.etat = etat;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public String getEtat() {
        return etat;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_reclamation=" + id_reclamation + ", date_envoie=" + date_envoie + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id_reclamation != other.id_reclamation) {
            return false;
        }
        return true;
    }
           
           
}
