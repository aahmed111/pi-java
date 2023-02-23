/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.tests;

import edu.projetpi.entities.Echange;
import edu.projetpi.services.ServiceEchange;
import java.sql.SQLException;
import java.util.List;

public class TestEchange {

    public static void main(String[] args) throws SQLException {

     
            ServiceEchange se = new ServiceEchange();
             List<Echange> le = se.getAll();
             
             System.out.println(le.toString());

           /* Echange e1 = new Echange("en attente",31,32,42,46);
            //Echange e2 = new Echange("terminée");
            System.out.println("\n date : "+e1.toString());
            //se.ajouter(e1);
            List<Echange> list=se.getAll();*/
            
         
           // se.modifier(e);
           
            
          

        
           
        }
    
    }


