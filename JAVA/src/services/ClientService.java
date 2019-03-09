/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import entities.Personne;
import interfaces.IService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;
import utiles.DataSource;

/**
 *
 * @author gaddour
 */
public class ClientService implements IService<Client>{

    
            DataSource ds = DataSource.getInstance();
            PreparedStatement stmt;  
    
    @Override
    public void add(Client c) {
        
         try {
            
            String requete = "INSERT into client(username,"
                    + "email,"
                    + "password,"
                    + "nom,"
                    + "prenom"
                    + ",sexe,"
                    + "datenaissance,"
                    + "cin,"
                    + "description,"
                    + "rating,"
                    + "adress,"
                    + "type)"
                    //+ "id_Photo)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getPassword());
            stmt.setString(4, c.getNom());
            stmt.setString(5, c.getPrenom());
            stmt.setString(6, c.getSexe());
            stmt.setDate(7, c.getDatenaissance());
            stmt.setInt(8, c.getCin());
            stmt.setString(9, c.getDescription());
            stmt.setInt(10, c.getRating());
            stmt.setString(11, c.getAdress());
            stmt.setString(12, c.getType());
            //stmt.setInt(13, c.getId_Photo());


            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    

    }

    @Override
    public void remove(int id) {
        try {
            
            String requete = "delete from client where id = ?" ; 
            //
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void update(int id, Client c) {
        try {
            
            String requete = "UPDATE  client  set "
                    + "username = ? , "
                    + "email = ? , "
                    + "password =? ,"
                    + "nom = ? ,"
                    + "prenom = ? ,"
                    + "sexe = ? ,"
                    + "datenaissance = ? ,"
                    + "cin = ? ,"
                    + "description = ? ,"
                    + "rating = ? ,"
                    + "adress= ? ,"
                    + "type = ?  "
                    + "where id =     ?" ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getPassword());
            stmt.setString(4, c.getNom());
            stmt.setString(5, c.getPrenom());
            stmt.setString(6, c.getSexe());
            stmt.setDate(7, c.getDatenaissance());
            stmt.setInt(8, c.getCin());
            stmt.setString(9, c.getDescription());
            stmt.setInt(10, c.getRating());
            stmt.setString(11, c.getAdress());
            stmt.setString(12, c.getType());
            stmt.setInt(13, id);
           
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public List<Client> getAll() {
               try {
            
            ArrayList<Client> clients = new ArrayList<Client>();

            String requete = "SELECT  * FROM client " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                clients.add(new Client(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        null, rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getDate("datenaissance"), rs.getInt("cin"),
                        rs.getString("description"), rs.getInt("rating"), rs.getString("adress"), rs.getString("type"), rs.getInt("id_photo")) );
            }
            return clients;
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
    }
    
    public Client getByUsername(String username , String password ){
        
                

        try {
            

            String requete = "SELECT * FROM client where username = ? and password = ? " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setString(1,username);
            stmt.setString(2,password);

            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Client x = new Client(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        null, rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getDate("datenaissance"), rs.getInt("cin"),
                        rs.getString("description"), rs.getInt("rating"), rs.getString("adress"), rs.getString("type"), rs.getInt("id_photo"));
                        
            return x;
            }
            
            return null;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
        
        
        
    }
    
    
    public Client getByOnlyUsername(String username  ){
        
                

        try {
            

            String requete = "SELECT * FROM client where username = ?  " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setString(1,username);

            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Client x = new Client(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        null, rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"), rs.getDate("datenaissance"), rs.getInt("cin"),
                        rs.getString("description"), rs.getInt("rating"), rs.getString("adress"), rs.getString("type"), rs.getInt("id_photo"));
                        
            return x;
            }
            
            return null;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
    }
    
}
