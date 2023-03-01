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
public class Transporteur extends User {

    private String adresse;
    private int noteEvaluation;
    private int cout;
    private String etat;
    private Agence agence;
    
    
    public Transporteur(String username, String password, String email, byte[] photo, int phoneNumber) {
        super(username, password, email, photo, phoneNumber);
    }

    public Transporteur(String username, String password, String email, byte[] photo, String type, int phoneNumber,String sexe, int noteEvaluation,  String etat, int cout) {
        super(username, password, email, photo, type, phoneNumber,sexe);
        this.noteEvaluation = noteEvaluation;
     
        this.etat = etat;
        this.cout = cout;
    }

    public Transporteur(int id_user, String username, String password, String email, byte[] photo, String type, int phoneNumber, int noteEvaluation, String sexe, String etat, int cout) {
        super(id_user, username, password, email, photo, type, phoneNumber,sexe);

        this.noteEvaluation = noteEvaluation;
     
        this.etat = etat;
        this.cout = cout;
    }

 

    public int getNoteEvaluation() {
        return noteEvaluation;
    }

    public void setNoteEvaluation(int noteEvaluation) {
        this.noteEvaluation = noteEvaluation;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Transporteur{" + "adresse=" + adresse + ", noteEvaluation=" + noteEvaluation + ", cout=" + cout + ", etat=" + etat + '}';
    }

}
