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
public class Profil {

    private int id_profil;
    private String pseudo, bio, image;

    public Profil(int id_profil, String pseudo, String bio, String image) {
        this.id_profil = id_profil;
        this.pseudo = pseudo;
        this.bio = bio;
        this.image = image;
    }

    public Profil(String pseudo, String bio) {
        this.pseudo = pseudo;
        this.bio = bio;
    }

    public int getId_profil() {
        return id_profil;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "profil{" + "id_profil=" + id_profil + ", pseudo=" + pseudo + ", bio=" + bio + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Profil other = (Profil) obj;
        if (this.id_profil != other.id_profil) {
            return false;
        }
        return true;
    }

}
