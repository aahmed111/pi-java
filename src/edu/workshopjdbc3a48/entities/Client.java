/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.time.LocalDateTime;

public class Client extends User {
    private  int  noteEvaluation ;
    private LocalDateTime date_deblock ;
    private int is_block;
    private int duree;

    public Client( int id_user, String username, String password, String email, byte[] photo, String type, int phoneNumber, String sexe,int noteEvaluation, LocalDateTime date_deblock, int is_block,int duree) {
        super(id_user, username, password, email, photo, type, phoneNumber, sexe);
        this.noteEvaluation = noteEvaluation;
        this.date_deblock = date_deblock;
        this.is_block = is_block;
        this.duree=duree;
    }
  
    
    public Client(int id_user, String username, String password, String email, byte[]photo, String type, int phoneNumber ,String sexe,int  noteEvaluation) {
        super(id_user, username, password, email, photo, type, phoneNumber,sexe);
        this.noteEvaluation=noteEvaluation;
       
    }

    public Client(String username, String password, String email, byte[] photo, String type, int phoneNumber,String sexe,int noteEvaluation ) {
        super(username, password, email, photo, type, phoneNumber,sexe);
        this.noteEvaluation=noteEvaluation;
     
    }

    public Client(String username, String password, String email, byte[] photo, int phoneNumber) {
        super(username, password, email, photo, phoneNumber);
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

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNoteEvaluation() {
        return noteEvaluation;
    }

    public void setNoteEvaluation(int noteEvaluation) {
        this.noteEvaluation = noteEvaluation;
    }

    @Override
    public String toString() {
        return  "Client{"  +super.toString() +  ", noteEvaluation=" + noteEvaluation + '}';
    }
   
    
}