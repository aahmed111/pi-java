/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.projetpi.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author abdelazizmezri
 * @param <T>
 */
public interface IService<T> {

    public void ajouter(T t) throws SQLException;

    public void supprimer(int id);

    public void modifier(T t);

    public List<T> getAll();

    public T getOneById(int id);
    
   // public T getOneByStatut(String statut);
}
