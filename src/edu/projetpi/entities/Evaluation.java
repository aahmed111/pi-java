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
public class Evaluation {

    private int id_evaluation;
    private int valeur1;
    private int valeur2;
    private Echange echange;
   private User user1;
    private User user2;

    public Evaluation(int id_evaluation, int valeur1, int valeur2, Echange echange, User user1, User user2) {
        this.id_evaluation = id_evaluation;
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.echange = echange;
        this.user1 = user1;
        this.user2= user2;
    }
    
    
            

    public Evaluation(int valeur1,int valeur2,
    int id_echange
    ) {
       
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.echange=echange;
        
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

  

    public Evaluation(int valeur1, int valeur2) {
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        
    }

    public Echange getEchange() {
        return echange;
    }

    public void setEchange(Echange echange) {
        this.echange = echange;
    }

   
    

    public int getId_evaluation() {
        return id_evaluation;
    }

    public int getValeur1() {
        return valeur1;
    }

    public void setValeur1(int valeur1) {
        this.valeur1 = valeur1;
    }

    public int getValeur2() {
        return valeur2;
    }

    public void setValeur2( int valeur2) {
        this.valeur2 = valeur2;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id_evaluation=" + id_evaluation + ", valeur1=" + valeur1 + ", valeur2=" + valeur2 + ", echange=" + echange + ", user1=" + user1 + ", user2=" + user2 + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Evaluation other = (Evaluation) obj;
        if (this.id_evaluation != other.id_evaluation) {
            return false;
        }
        return true;
    }
    
    }
    
    
