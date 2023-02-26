
package edu.workshopjdbc3a48.entities;


public abstract class User {
    private int id_user,  phoneNumber ;
    private String username , password ,email  ,type ;
    private byte[] photo;

    public User(String username, String password, String email, byte[] photo, String type,int phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.type = type;
        this.phoneNumber=phoneNumber;
    }

    public User(int id_user, String username, String password, String email, byte[] photo, String type,int phoneNumber) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.type = type;
        this.phoneNumber=phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId_user() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id_user;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "id_user=" + id_user + ", username=" + username + ", password=" + password + ", email=" + email + ", photo=" + photo + ", type=" + type +",phoneNumber="+phoneNumber +",";
    }
    
    
  
}