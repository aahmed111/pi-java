/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;
import edu.workshopjdbc3a48.entities.User;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private  int  noteEvaluation ;
    private  String adresse;
    public Client(int id_user, String username, String password, String email, byte[]photo, String type, int phoneNumber,int noteEvaluation,String adresse) {
        super(id_user, username, password, email, photo, type, phoneNumber);
        this.noteEvaluation=noteEvaluation;
        this.adresse=adresse;
    }

    public Client(String username, String password, String email, byte[] photo, String type, int phoneNumber,int noteEvaluation,String adresse ) {
        super(username, password, email, photo, type, phoneNumber);
        this.noteEvaluation=noteEvaluation;
        this.adresse=adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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