/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author gaddour
 */
public class Client {
    private int id ;
    private String username;
    private String email ;
    private String password;
    private String nom;
    private String prenom;
    private String sexe;
    private Date datenaissance;
    private int cin;
    private String description;
    private int rating;
    private String adress;
    private String type;
    private int id_Photo;

    public Client() {
    }

    public Client(String username, String email, String password, String nom, String prenom, String sexe, Date datenaissance, int cin, String description, int rating, String adress, String type, int id_Photo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.description = description;
        this.rating = rating;
        this.adress = adress;
        this.type = type;
        this.id_Photo = id_Photo;
    }

    public Client(int id, String username, String email, String password, String nom, String prenom, String sexe, Date datenaissance, int cin, String description, int rating, String address, String type, int id_Photo) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.description = description;
        this.rating = rating;
        this.adress = address;
        this.type = type;
        this.id_Photo = id_Photo;
    }

    public Client(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Client(String username, String email, String password, String sexe, String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.type = type;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String address) {
        this.adress = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_Photo() {
        return id_Photo;
    }

    public void setId_Photo(int id_Photo) {
        this.id_Photo = id_Photo;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", datenaissance=" + datenaissance + ", cin=" + cin + ", description=" + description + ", rating=" + rating + ", adress=" + adress + ", type=" + type + ", id_Photo=" + id_Photo + '}';
    }
    
    
}
