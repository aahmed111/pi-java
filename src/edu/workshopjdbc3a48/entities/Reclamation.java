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
     private Admin admin;
    private Client client;
   
      private Date dateReclamation;
    private List<Message> messages; 
    
   
  

    public Reclamation() {
        this.id = 0;
        this.client = null;
        this.admin = null;
        
        
     
        this.dateReclamation = null;
        this.messages = new ArrayList<Message>();
    }

    public Reclamation(int id, Client client, Admin admin, Date dateReclamation, List<Message> messages) {
        this.id = id;
        this.client = client;
        this.admin = admin;
        this.dateReclamation = dateReclamation;
        this.messages = messages;
    }

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
        return "Reclamation{" + "id=" + id + ", client=" + client + ", admin=" + admin + ", dateReclamation=" + dateReclamation + ", messages=" + messages + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        hash = 73 * hash + Objects.hashCode(this.client);
        hash = 73 * hash + Objects.hashCode(this.admin);
        hash = 73 * hash + Objects.hashCode(this.dateReclamation);
        hash = 73 * hash + Objects.hashCode(this.messages);
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
     