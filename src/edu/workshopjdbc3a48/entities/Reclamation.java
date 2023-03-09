package edu.workshopjdbc3a48.entities;





import edu.workshopjdbc3a48.entities.Echange;
import edu.workshopjdbc3a48.entities.User;
import java.sql.Date;

public class Reclamation {

    
    private int id;
    private User id_user1;
     private String objet;
    private Date date_envoie;
    private String description ; 
    private String username ;
     private String Email;
    private Echange echange ;

    public User getId_user1() {
        return id_user1;
    }

    public void setId_user1(User id_user1) {
        this.id_user1 = id_user1;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }


 
    
    
    public Reclamation(int id, User id_user1, String objet,  String description,  Echange echange,Date date_envoie) {
        this.id = id;
        this.id_user1 = id_user1;
        this.objet = objet;
        this.date_envoie = date_envoie;
        this.description = description;

        this.echange = echange;
    }

        public Reclamation( User id_user1, String objet,  String description,  Echange echange) {
        
        this.id_user1 = id_user1;
        this.objet = objet;
      
        this.description = description;
        
        this.echange = echange;
    }


  

    public int getId() {
        return id;
    }


    public User getUser1() {
        return id_user1;
    }

   

 

    public Date getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(Date date_envoie) {
        this.date_envoie = date_envoie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Echange getEchange() {
        return echange;
    }

    public void setEchange(Echange echange) {
        this.echange = echange;
    }

    public String getobjet() {
        return objet;
    }

    public void setobjet(String objet) {
        this.objet = objet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        return true;
    }
    


  

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user1=" + id_user1 + ", objet=" + objet + ", date_envoie=" + date_envoie + ", description=" + description + ", Email=" + Email + ", echange=" + echange + '}';
    }
   
 
    
    
    
   

   


   
   

    
   }