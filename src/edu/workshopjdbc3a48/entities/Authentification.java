/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author abdel
 */
public class Authentification {
    private int numbErreur;
     private LocalDateTime lastErreur ;
     private String useername;

    public Authentification(int numbErreur, LocalDateTime lastErreur, String useername) {
        this.numbErreur = numbErreur;
        this.lastErreur = lastErreur;
        this.useername = useername;
    }

    public int getNumbErreur() {
        return numbErreur;
    }

    public void setNumbErreur(int numbErreur) {
        this.numbErreur = numbErreur;
    }

    public LocalDateTime getLastErreur() {
        return lastErreur;
    }

    public void setLastErreur(LocalDateTime lastErreur) {
        this.lastErreur = lastErreur;
    }

    public String getUseername() {
        return useername;
    }

    public void setUseername(String useername) {
        this.useername = useername;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.useername);
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
        final Authentification other = (Authentification) obj;
        if (!Objects.equals(this.useername, other.useername)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Authentification{" + "numbErreur=" + numbErreur + ", lastErreur=" + lastErreur + ", useername=" + useername + '}';
    }
     
     
     
     
}
