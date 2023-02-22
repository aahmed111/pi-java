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
public class Evaluation {
    private int id_evaluation;
    private String valeur;

    
    public Evaluation(int id_evaluation, String valeur) {
        this.id_evaluation = id_evaluation;
        this.valeur = valeur;
    }

    
    public Evaluation(String valeur) {
        this.valeur = valeur;
    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "evaluation{" + "id_evaluation=" + id_evaluation + ", valeur=" + valeur + '}';
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
        final Evaluation other = (Evaluation) obj;
        if (this.id_evaluation != other.id_evaluation) {
            return false;
        }
        return true;
    }
    
    
    
}
