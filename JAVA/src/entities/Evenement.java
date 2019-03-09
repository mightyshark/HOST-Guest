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
public class Evenement {
    
    private int id ;
    private String nom;
    private String place;
    private Date date_debut;
    private Date date_fin;
    private String description;
    private int id_Guide;
    private String image_name;

    public Evenement(int id, String nom, String place, Date date_debut, Date date_fin, String description, int id_Guide, String image_name) {
        this.id = id;
        this.nom = nom;
        this.place = place;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.id_Guide = id_Guide;
        this.image_name = image_name;
    }

    public Evenement(String nom, String place, Date date_debut, Date date_fin, String description, int id_Guide, String image_name) {
        this.nom = nom;
        this.place = place;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
        this.id_Guide = id_Guide;
        this.image_name = image_name;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_Guide() {
        return id_Guide;
    }

    public void setId_Guide(int id_Guide) {
        this.id_Guide = id_Guide;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", place=" + place + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + ", id_Guide=" + id_Guide + ", image_name=" + image_name + '}';
    }
    
    
    
    
}
