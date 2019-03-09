/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import utiles.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IService;
import entities.Personne;
import test.Test;

/**
 *
 * @author gaddour
 */
public class PersonneService implements IService<Personne>{
    
            DataSource ds = DataSource.getInstance();
            PreparedStatement stmt;
            
    @Override
    public void add(Personne p) {
        try {
            
            String requete = "INSERT into personne(nom,age) values(?,?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setString(1, p.getNom());
            stmt.setInt(2, p.getAge());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void remove(int id) {
        try {
            
            String requete = "delete from personne where id = ?" ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void update(int id, Personne p) {
        try {
            
            String requete = "UPDATE  personne  set nom = ? , age = ?   where id = ? " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(3, id);
            stmt.setString(1, p.getNom());
            stmt.setInt(2, p.getAge());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<Personne> getAll() {
       try {
            
            ArrayList<Personne> personnes = new ArrayList<Personne>();

            String requete = "SELECT  * FROM personne " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                personnes.add(new Personne(rs.getInt("id"), rs.getString("nom"), rs.getInt("age")));
            }
            return personnes;
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        }  
    }
    

}
