/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

/**
 *
 * @author pc
 */
public class Admin extends User {

    public Admin(String username, String password, String email, String photo, String type, int phoneNumber) {
        super(username, password, email, photo, type, phoneNumber);
    }

    public Admin(int id_user, String username, String password, String email, String photo, String type, int phoneNumber) {
        super(id_user, username, password, email, photo, type, phoneNumber);
    }

  

    @Override
    public String toString() {
        return "Admin{" + super.toString()+ '}';
    }

   

    
}
