/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hmila
 */
public class Room {
    private int id ;
    private int id_host;
    private String name; 
    private int capacite;
    private int nbr_lit;
    private int prix;
    private String description;
    private String type;
    private String img_name;
    private String updatetime;
    private String adresse;

    public Room() {
    }

    public Room(int id, int id_host, String name, int capacite, int nbr_lit, int prix, String description, String type, String img_name, String updatetime, String adresse) {
        this.id = id;
        this.id_host = id_host;
        this.name = name;
        this.capacite = capacite;
        this.nbr_lit = nbr_lit;
        this.prix = prix;
        this.description = description;
        this.type = type;
        this.img_name = img_name;
        this.updatetime = updatetime;
        this.adresse = adresse;
    }

    public Room(int id_host, String name, int capacite, int nbr_lit, int prix, String description, String type, String img_name, String updatetime, String adresse) {
        this.id_host = id_host;
        this.name = name;
        this.capacite = capacite;
        this.nbr_lit = nbr_lit;
        this.prix = prix;
        this.description = description;
        this.type = type;
        this.img_name = img_name;
        this.updatetime = updatetime;
        this.adresse = adresse;
    }

    public Room(int aInt, int aInt0, String string, Object object, int aInt1, int aInt2, int aInt3, String string0, String string1, String string2, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_host() {
        return id_host;
    }

    public void setId_host(int id_host) {
        this.id_host = id_host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNbr_lit() {
        return nbr_lit;
    }

    public void setNbr_lit(int nbr_lit) {
        this.nbr_lit = nbr_lit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", id_host=" + id_host + ", name=" + name + ", capacite=" + capacite + ", nbr_lit=" + nbr_lit + ", prix=" + prix + ", description=" + description + ", type=" + type + ", img_name=" + img_name + ", updatetime=" + updatetime + ", adresse=" + adresse + '}';
    }
    
    
    
    
    
}
