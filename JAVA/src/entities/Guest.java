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
public class Guest {
    
    
    private int id ;
    private int idClient;
    private int  age;

    public Guest() {
    }

    public Guest(int idClient) {
        this.idClient = idClient;
    }

    public Guest(int id, int idClient) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
