/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaddour
 */
public class DataSource {
    

    String url = "jdbc:mySQL://localhost:3306/pidevhg";
    String username="root";
    String password ="";
    private Connection cnx ;
    private static DataSource instance  ; //sele instance
    
    private  DataSource() {
        
        try {
            
            cnx = DriverManager.getConnection(url,username,password);
        
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public Connection getConnection()
    {
        return cnx;
    }
    
    
    public static DataSource getInstance(){
        
        if ( instance == null )
           instance =  new DataSource();
        
        
           return instance;
    }
    
}
