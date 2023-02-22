/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author pc
 */
 
   public class Reclamation {
    private int id;
    private Client client;
    private Admin admin;
    private String sujet;
    private String contenu;
    private boolean estTraitee;
    private Date dateReclamation;
    private List<Message> messages;

    public Reclamation() {
        this.id = 0;
        this.client = null;
        this.admin = null;
        this.sujet = "";
        this.contenu = "";
        this.estTraitee = false;
        this.dateReclamation = null;
        this.messages = new ArrayList<Message>();
    }

    public Reclamation(Client client, String sujet, String contenu, Date dateReclamation) {
        this.id = 0;
        this.client = client;
        this.admin = null;
        this.sujet = sujet;
        this.contenu = contenu;
        this.estTraitee = false;
        this.dateReclamation = dateReclamation;
        this.messages = new ArrayList<Message>();
    }

    // getters, setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public boolean isEstTraitee() {
        return estTraitee;
    }

    public void setEstTraitee(boolean estTraitee) {
        this.estTraitee = estTraitee;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", client=" + client + ", admin=" + admin + ", sujet=" + sujet + ", contenu=" + contenu + ", estTraitee=" + estTraitee + ", dateReclamation=" + dateReclamation + ", messages=" + messages + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.client);
        hash = 97 * hash + Objects.hashCode(this.admin);
        hash = 97 * hash + Objects.hashCode(this.sujet);
        hash = 97 * hash + Objects.hashCode(this.contenu);
        hash = 97 * hash + (this.estTraitee ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.dateReclamation);
        hash = 97 * hash + Objects.hashCode(this.messages);
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
        if (this.id != other.id) {
            return false;
        }
        if (this.estTraitee != other.estTraitee) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        if (!Objects.equals(this.dateReclamation, other.dateReclamation)) {
            return false;
        }
        if (!Objects.equals(this.messages, other.messages)) {
            return false;
        }
        return true;
    }

   
}
