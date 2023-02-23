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
public class Chat {

    private int id_chat;
    private String id_emetteur, id_destinatire, objet, corps, date_envoie;

    public Chat(int id_chat, String id_emetteur, String id_destinatire, String objet, String corps, String date_envoie) {
        this.id_chat = id_chat;
        this.id_emetteur = id_emetteur;
        this.id_destinatire = id_destinatire;
        this.objet = objet;
        this.corps = corps;
        this.date_envoie = date_envoie;
    }

    public Chat(String id_emetteur, String id_destinatire, String objet, String corps, String date_envoie) {
        this.id_emetteur = id_emetteur;
        this.id_destinatire = id_destinatire;
        this.corps = corps;
        this.date_envoie = date_envoie;
    }

    public int getId_chat() {
        return id_chat;
    }

    public String getId_emetteur() {
        return id_emetteur;
    }

    public String getId_destinatire() {
        return id_destinatire;
    }

    public String getCorps() {
        return corps;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public void setId_emetteur(String id_emetteur) {
        this.id_emetteur = id_emetteur;
    }

    public void setId_destinatire(String id_destinatire) {
        this.id_destinatire = id_destinatire;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    @Override
    public String toString() {
        return "chat{" + "id_chat=" + id_chat + ", id_emetteur=" + id_emetteur + ", id_destinatire=" + id_destinatire + ", corps=" + corps + ", date_envoie=" + date_envoie + '}';
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
        final Chat other = (Chat) obj;
        return this.id_chat == other.id_chat;
    }

}
