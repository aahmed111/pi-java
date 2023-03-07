/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.time.LocalDateTime;

/**
 *
 * @author abdel
 */
public class Transporteur extends User {

  
    private int noteEvaluation;
    private int cout;
    private String etat;
    private Agence agence;
    private LocalDateTime date_deblock ;
    private int is_block;

    public Transporteur( int id_user, String username, String password, String email, byte[] photo, String type, int phoneNumber, String sexe,int noteEvaluation,  String etat,int cout, LocalDateTime date_deblock, int is_block) {
        super(id_user, username, password, email, photo, type, phoneNumber, sexe);
        this.noteEvaluation = noteEvaluation;
        this.cout = cout;
        this.etat = etat;
        this.date_deblock = date_deblock;
        this.is_block = is_block;
    }

    public Transporteur( String username, String password, String email, byte[] photo,String type, int phoneNumber,String sexe ) {
        super(username, password, email, photo, type,phoneNumber,sexe );
       
    }

   
   
    public Transporteur(String username, String password, String email, byte[] photo, int phoneNumber) {
        super(username, password, email, photo, phoneNumber);
    }

    public Transporteur(String username, String password, String email, byte[] photo, String type, int phoneNumber,String sexe, int noteEvaluation,  String etat, int cout) {
        super(username, password, email, photo, type, phoneNumber,sexe);
        this.noteEvaluation = noteEvaluation;
     
        this.etat = etat;
        this.cout = cout;
    }

    public Transporteur(int id_user, String username, String password, String email, byte[] photo, String type, int phoneNumber,  String sexe,int noteEvaluation, String etat, int cout) {
        super(id_user, username, password, email, photo, type, phoneNumber,sexe);

        this.noteEvaluation = noteEvaluation;
     
        this.etat = etat;
        this.cout = cout;
    }

    public LocalDateTime getDate_deblock() {
        return date_deblock;
    }

    public void setDate_deblock(LocalDateTime date_deblock) {
        this.date_deblock = date_deblock;
    }

    public int getIs_block() {
        return is_block;
    }

    public void setIs_block(int is_block) {
        this.is_block = is_block;
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
        return "Transporteur{" + ", noteEvaluation=" + noteEvaluation + ", cout=" + cout + ", etat=" + etat + '}';
    }

}
