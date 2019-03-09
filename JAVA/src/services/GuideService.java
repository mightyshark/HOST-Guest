/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class GuideService implements IService<Guide>{

    
            DataSource ds = DataSource.getInstance();
            PreparedStatement stmt; 
    
    @Override
    public void add(Guide t) {
        try {
            
            String requete = "INSERT into guide(id_Client)"

                    + " values(?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, t.getId_Client());

            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(int id, Guide t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Guide> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Guide getIdGuide(int i){
        try {
            

            String requete = "SELECT * FROM guide where id_Client = ?  " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, i);

            
            ResultSet rs=stmt.executeQuery();
            if (rs.next()){
                Guide x = new Guide(rs.getInt("id"), rs.getInt("id_Client"), rs.getString("name"));
                   
            return x;
            }
            
            return null;
            

            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
        
    }
    
}
