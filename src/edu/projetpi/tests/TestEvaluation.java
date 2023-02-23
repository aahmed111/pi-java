/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.tests;

import edu.projetpi.entities.Echange;
import edu.projetpi.entities.Evaluation;
import edu.projetpi.services.ServiceEvaluation;
import java.util.List;

/**
 *
 * @author zayne
 */
public class TestEvaluation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceEvaluation se = new ServiceEvaluation();
        Evaluation e = new Evaluation(3,4,2);
           List<Evaluation> le = se.getAll();
             
             System.out.println(le.toString());
       //e.setId_echange(5);
       // se.ajouter(e);
        
        
        
        
    }
    
}
