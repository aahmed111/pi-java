/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author abdel
 */
public class Agence {
    private int id_agence ;
    private String nom;

    public Agence(int id_agence, String nom) {
        this.id_agence = id_agence;
        this.nom = nom;
    }

    public Agence(String nom) {
        this.nom = nom;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
