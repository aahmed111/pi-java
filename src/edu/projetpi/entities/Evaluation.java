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
    private int id_echange;
    private int id_client1;
    private int id_client2;

    public Evaluation(int id_evaluation, int valeur1, int valeur2, int id_echange, int id_client1, int id_client2) {
        this.id_evaluation = id_evaluation;
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.id_echange = id_echange;
        this.id_client1 = id_client1;
        this.id_client2 = id_client2;
    }
    
    
            

    public Evaluation(int valeur1,int valeur2,
    int id_echange
    ) {
       // this.id_evaluation = id_evaluation;
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.id_echange=id_echange;
        
    }

    public int getId_client1() {
        return id_client1;
    }

    public void setId_client1(int id_client1) {
        this.id_client1 = id_client1;
    }

    public int getId_client2() {
        return id_client2;
    }

    public void setId_client2(int id_client2) {
        this.id_client2 = id_client2;
    }

    public Evaluation(int valeur1, int valeur2) {
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.id_echange=12;
    }

    public int getId_echange() {
        return id_echange;
    }

    public void setId_echange(int id_echange) {
        this.id_echange = id_echange;
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
        return "Evaluation{" + "id_evaluation=" + id_evaluation + ", valeur1=" + valeur1 + ", valeur2=" + valeur2 + ", id_echange=" + id_echange + ", id_client1=" + id_client1 + ", id_client2=" + id_client2 + '}';
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
    
    
