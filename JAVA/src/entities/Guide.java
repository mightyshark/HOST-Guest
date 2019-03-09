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
public class Guide {
    private int id ;
    private int id_Client;
    private String name;

    public Guide(int id, int id_Client, String name) {
        this.id = id;
        this.id_Client = id_Client;
        this.name = name;
    }

    public Guide(int id_Client) {
        this.id_Client = id_Client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Client() {
        return id_Client;
    }

    public void setId_Client(int id_Client) {
        this.id_Client = id_Client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
