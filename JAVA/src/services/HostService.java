/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Guest;
import entities.Host;
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
public class HostService implements IService<Host>{
     
    DataSource ds = DataSource.getInstance();
            PreparedStatement stmt; 
    
    @Override
    public void add(Host t) {
            try {
            
            String requete = "INSERT into host(id_Client)"

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
    public void update(int id, Host t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Host> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public Host getIdHost(int i){
        try {
            

            String requete = "SELECT * FROM host where id_Client = ?  " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, i);

            
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Host x = new Host(rs.getInt("id"), rs.getInt("id_Client"));
                   
            return x;
            }
            
            return null;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
        
    }

    
}
