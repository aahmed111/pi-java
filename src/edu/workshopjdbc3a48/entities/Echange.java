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
public class Echange {
    	private int id_echange;
        private String date_echange,statut;

    public Echange(int id_echange, String date_echange, String statut) {
        this.id_echange = id_echange;
        this.date_echange = date_echange;
        this.statut = statut;
    }

        
        
    public Echange(String date_echange, String statut) {
        this.date_echange = date_echange;
        this.statut = statut;
    }

    public int getId_echange() {
        return id_echange;
    }

    public String getDate_echange() {
        return date_echange;
    }

    public String getStatut() {
        return statut;
    }

    public void setDate_echange(String date_echange) {
        this.date_echange = date_echange;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "echange{" + "id_echange=" + id_echange + ", date_echange=" + date_echange + ", statut=" + statut + '}';
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
        final Echange other = (Echange) obj;
        if (this.id_echange != other.id_echange) {
            return false;
        }
        return true;
    }
        
        
        
        
}
