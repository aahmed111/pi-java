/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author abdel
 */
public class Services extends Article{
    private String type_Service ;

    public Services( int id_article, String date_ajout, String description, String image, String proprietaire, int estimation, String type_article,String type_Service) {
        super(id_article, date_ajout, description, image, proprietaire, estimation, type_article);
        this.type_Service = type_Service;
    }

    public Services( String date_ajout, String description, String image, String proprietaire, int estimation, String type_article,String type_Service) {
        super(date_ajout, description, image, proprietaire, estimation, type_article);
        this.type_Service = type_Service;
        
    }

    public String getType_Service() {
        return type_Service;
    }

    public void setType_Service(String type_Service) {
        this.type_Service = type_Service;
    }

    @Override
    public String toString() {
        return "Services{" + super.toString()+"type_Service=" + type_Service + '}';
    }
    
}
