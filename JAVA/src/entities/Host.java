/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author gaddour
 */
public class Host {
    private int id ;
    private int idClient;
    private String address;

    public Host() {
    }

    public Host(int idClient) {
        this.idClient = idClient;
    }

    public Host(int id, int idClient) {
        this.id = id;
        this.idClient = idClient;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
