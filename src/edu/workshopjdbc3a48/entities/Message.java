
package edu.workshopjdbc3a48.entities;

import java.sql.Date;
import java.util.Objects;


public class Message {
    private int id_message;
    private Date date ;
    private String text ;
    private Chat chat;
    private Reclamation reclamation;

    public Message(int id_message, Date date, String text, Chat chat, Reclamation reclamation) {
        this.id_message = id_message;
        this.date = date;
        this.text = text;
        this.chat = chat;
        this.reclamation = reclamation;
    }

    public Message(String text, Chat chat, Reclamation reclamation) {
        this.text = text;
        this.chat = chat;
        this.reclamation = reclamation;
    }

    public Message(String text) {
        this.text = text;
    }
     

    // Constructeur pour les messages de chat
    public Message(Date date, String text, Chat chat) {
        this.date = date;
        this.text = text;
        this.chat = chat;
    }

    // Constructeur pour les messages de réclamation
    public Message(Date date, String text, Reclamation reclamation) {
        this.date = date;
        this.text = text;
        this.reclamation = reclamation;
    }

    public Message(int id, Date date, String text, Chat chat, boolean reclamation) {
        
    }
 

    // Méthode permettant de savoir si le message appartient à une réclamation ou un chat
    public boolean isReclamation() {
        return reclamation != null;
    }

    public boolean isChat() {
        return chat != null;
    }

    public Message(Date date) {
        this.date = date;
    }

    public int getId_message() {
        return id_message;
    }

     
    public Date getDate() {
        return date;
    }
 

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id_message;
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.text);
        hash = 47 * hash + Objects.hashCode(this.chat);
        hash = 47 * hash + Objects.hashCode(this.reclamation);
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
        final Message other = (Message) obj;
        if (this.id_message != other.id_message) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.chat, other.chat)) {
            return false;
        }
        if (!Objects.equals(this.reclamation, other.reclamation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "id_message=" + id_message + ", date=" + date + ", text=" + text + ", chat=" + chat + ", reclamation=" + reclamation + '}';
    }

    
}