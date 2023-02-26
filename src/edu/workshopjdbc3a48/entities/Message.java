
package edu.workshopjdbc3a48.entities;

import java.sql.Date;
import java.util.Objects;


public class Message {
    private int id_message;
    private Date date ;
    private String text ;
    private Chat chat;

    public Message(String text, Chat chat) {
        this.text = text;
        this.chat = chat;
    }

    public Message(int id_message, Date date, String text, Chat chat) {
        this.id_message = id_message;
        this.date = date;
        this.text = text;
        this.chat = chat;
    }

    public Message(Date date, String text, Chat chat) {
        this.date = date;
        this.text = text;
        this.chat = chat;
    }
    

    public int getId_message() {
        return id_message;
    }

   

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        int hash = 7;
        hash = 79 * hash + this.id_message;
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.text);
        hash = 79 * hash + Objects.hashCode(this.chat);
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
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "id_message=" + id_message + ", date=" + date + ", text=" + text + ", chat=" + chat + '}';
    }
 
    

     

    
}