/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controllers.LoginFXMLController;
import entities.Client;
import entities.Evenement;
import entities.Guide;
import entities.Room;
import interfaces.IService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import test.Test;
import utiles.DataSource;

/**
 *
 * @author gaddour
 */
public class EvenementService implements IService<Evenement>{

    
        DataSource ds = DataSource.getInstance();
        PreparedStatement stmt;  
    
    @Override
    public void add(Evenement t) {
        try {
            
            String requete = "INSERT into evenement(nom,"
                    + "place,"
                    + "date_debut,"
                    + "date_fin,"
                    + "description"
                    + ",id_Guide,"
                    + "image_name)"

                    + " values(?,?,?,?,?,?,?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            
            stmt.setString(1, t.getNom());
            stmt.setString(2, t.getPlace());
            stmt.setDate(3, t.getDate_debut());
            stmt.setDate(4, t.getDate_fin());
            stmt.setString(5, t.getDescription());
            
            GuideService gs = new GuideService();
            Guide x = gs.getIdGuide(LoginFXMLController.getIdsession());
            
            System.out.println(x);
            
            stmt.setInt(6, x.getId());
            stmt.setString(7, t.getImage_name());

            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    

    }

    @Override
    public void remove(int id) {
        try {
            
            String requete = "delete from evenement where id = ?" ; 
            //
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }


    @Override
    public void update(int id, Evenement t) {
    try {
            
            String requete = "UPDATE evenement set "
                    + "nom = ? ,"
                    + "place = ?,"
                    + "date_debut = ? ,"
                    + "date_fin = ? ,"
                    + "description = ?,"
                    + "id_Guide = ? ,"
                    + "image_name = ?  " 
                    + "where id =     ?" ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;

 
            stmt.setString(1, t.getNom());
            stmt.setString(2, t.getPlace());
            stmt.setDate(3, t.getDate_debut());
            stmt.setDate(4, t.getDate_fin());
            stmt.setString(5, t.getDescription());
            stmt.setInt(6, t.getId_Guide());
            stmt.setString(7, t.getImage_name());
            stmt.setInt(8, id);

            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    @Override
    public List<Evenement> getAll() {
        try {
            
            ArrayList<Evenement> events = new ArrayList<Evenement>();

            String requete = "SELECT  * FROM evenement " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                events.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("place"), 
                        rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("description"), rs.getInt("id_Guide"), rs.getString("image_name")) );
            }
            return events;
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        }     }
    
    
    public void afficher(ObservableList<Evenement> data)
    {
        try {
            String req = "SELECT  * FROM evenement " ; 
             stmt = ds.getConnection().prepareStatement(req);
            ResultSet rs= stmt.executeQuery();
            
 
            while(rs.next()){
                 data.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("place"), 
                        rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("description"), rs.getInt("id_Guide"), rs.getString("image_name")) );
               }
            //System.out.println(data.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getRoomsByEvenementPlace(ObservableList<Room> data,String place){
        try {
            String req = "SELECT  * FROM room where adresse = ?" ; 
            stmt = ds.getConnection().prepareStatement(req);
            stmt.setString(1, place);
            
            ResultSet rs= stmt.executeQuery();
            
 
            while(rs.next()){
                 data.add(new Room(rs.getInt("id"), rs.getInt("id_host"), rs.getString("name"),
                         rs.getInt("capacite"), rs.getInt("nbr_lit"), rs.getInt("prix"), rs.getString("description"), rs.getString("type"),
                        rs.getString("image_name"), rs.getString("updated_at"), rs.getString("adresse")) );
               }
            //System.out.println(data.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       
    public void getMyEvents(ObservableList<Evenement> data , int id )
    {
        try {
            String req = "SELECT  * FROM evenement where id_guide = ?" ; 
             stmt = ds.getConnection().prepareStatement(req);
             stmt.setInt(1, id);
            ResultSet rs= stmt.executeQuery();
            
 
            while(rs.next()){
                 data.add(new Evenement(rs.getInt("id"), rs.getString("nom"), rs.getString("place"), 
                        rs.getDate("date_debut"), rs.getDate("date_fin"), rs.getString("description"), rs.getInt("id_Guide"), rs.getString("image_name")) );
               }
            //System.out.println(data.toString());
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
