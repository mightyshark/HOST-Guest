/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Guest;
import entities.Guide;
import interfaces.IService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Test;
import utiles.DataSource;

/**
 *
 * @author gaddour
 */
public class GuestService implements IService<Guest>{

    
        
            DataSource ds = DataSource.getInstance();
            PreparedStatement stmt; 
    
    @Override
    public void add(Guest t) {
            try {
            
            String requete = "INSERT into guest(id_Client)"

                    + " values(?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, t.getIdClient());

            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }           }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(int id, Guest t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Guest> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public Guest getIdGuest(int i){
        try {
            

            String requete = "SELECT * FROM guest where id_Client = ?  " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, i);

            
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Guest x = new Guest(rs.getInt("id"), rs.getInt("id_Client"));
                   
            return x;
            }
            
            return null;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
        
    }
}
