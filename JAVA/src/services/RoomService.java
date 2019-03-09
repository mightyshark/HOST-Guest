/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services ;

import entities.Client;
import entities.Host;
import utiles.DataSource;
import entities.Room;
import test.Test;
import interfaces.IService;
import interfaces.Rservice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author hmila
 */
public class RoomService implements IService<Room>,Rservice{

    DataSource ds = DataSource.getInstance();
    PreparedStatement stmt;
            
    @Override
    public void add(Room t) {
        try {
            
            String requete = "INSERT into room(id_host,"
                    + "name,"
                    + "capacite,"
                    + "nbr_lit,"
                    + "prix,"
                    + "description,"
                    + "type,"
                    + "image_name,"
                    + "updated_at,"
                    + "adresse)"
                    + " values(?,?,?,?,?,?,?,?,?,?) " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, t.getId_host());
            stmt.setString(2, t.getName());
            stmt.setInt(3, t.getCapacite());
            stmt.setInt(4, t.getNbr_lit());
            stmt.setInt(5, t.getPrix());
            stmt.setString(6, t.getDescription());
            stmt.setString(7, t.getType());
            stmt.setString(8, t.getImg_name());
            stmt.setString(9, t.getUpdatetime());
            stmt.setString(10, t.getAdresse());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void remove(int id) {
    try {
            
            String requete = "delete from room where id = ?" ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    
    

    
    @Override
    public void update(int id, Room r) {
        
        try {
            
            String requete = "UPDATE  `room`  set `id_host` = ? , `name` = ? , `capacite` = ? , `nbr_lit` = ? , `prix` = ? , `description` = ? , `type` = ? , `image_name` = ? , `updated_at` = ? , `adresse` = ?   where `id` = ? " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            stmt.setInt(1, r.getId_host());
            stmt.setString(2, r.getName());
            stmt.setInt(3, r.getCapacite());
            stmt.setInt(4, r.getNbr_lit());
            stmt.setInt(5, r.getPrix());
            stmt.setString(6, r.getDescription());
            stmt.setString(7, r.getType());
            stmt.setString(8, r.getImg_name());
            stmt.setString(9, r.getUpdatetime());
            stmt.setString(10, r.getAdresse());
            stmt.setInt(11, id);
            stmt.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
      
    public Room getRoomById(int id)
    {
                try{
                    
            String req="Select * from room where id=?";        
            stmt = ds.getConnection().prepareStatement(req) ;
            stmt.setInt(1, id);
            ResultSet rs= stmt.executeQuery();
            
       
        if(rs.first())
        {
                Room r=new Room();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setId_host(rs.getInt("id_host"));
                r.setDescription(rs.getString("description"));
                r.setCapacite(rs.getInt("capacite"));
                r.setNbr_lit(rs.getInt("nbr_lit"));
                r.setPrix(rs.getInt("prix"));
                r.setType(rs.getString("type"));
                r.setImg_name(rs.getString("image_name"));
                r.setUpdatetime(rs.getString("updated_at"));
                r.setAdresse(rs.getString("adresse"));
                
                
                return r;
        }
       }
       catch (SQLException e){
             e.printStackTrace();
            
        }
                return null;
    }
    
    
    
    
    public void afficher(ObservableList<Room> data)
    {
        try {
            String req="SELECT  * FROM room ";
             stmt = ds.getConnection().prepareStatement(req);
            ResultSet rs= stmt.executeQuery();
            
 
            while(rs.next()){
                 data.add(new Room(rs.getInt("id"), rs.getInt("id_host"), rs.getString("name"),
                         rs.getInt("capacite"), rs.getInt("nbr_lit"), rs.getInt("prix"), rs.getString("description"), rs.getString("type"),
                        rs.getString("image_name"), rs.getString("updated_at"), rs.getString("adresse")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Room> getAll() {
            try {
            
            List<Room> rooms = new ArrayList<>();

            String requete = "SELECT  * FROM room " ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
            
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                rooms.add(new Room(rs.getInt("id"), rs.getInt("id_host"), rs.getString("name"),
                         rs.getInt("capacite"), rs.getInt("nbr_lit"), rs.getInt("prix"), rs.getString("description"), rs.getString("type"),
                        rs.getString("image_name"), rs.getString("updated_at"), rs.getString("adresse")) );
            }
            return rooms;
            
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                        return null;

        } 
    }
    
    
    
   
    public ResultSet recherche(String v) {

        ResultSet result = null;
        String sql = "SELECT * FROM room WHERE type LIKE '%" + v + "%' OR name LIKE '%" + v + "%'";
        try {
            
        stmt  = ds.getConnection().prepareStatement(sql) ;                
            result= stmt.executeQuery();
            

            
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;

    }
    
       
    


    
    
    
    
     public void getAllMyRooms(ObservableList<Room> data,Host h)
    {
        try {
            String requete = "SELECT  * FROM room where id_host = ?" ; 
            stmt  = ds.getConnection().prepareStatement(requete) ;
                        stmt.setInt(1,h.getId());

            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                data.add(new Room(rs.getInt("id"), rs.getInt("id_host"), rs.getString("name"),
                         rs.getInt("capacite"), rs.getInt("nbr_lit"), rs.getInt("prix"), rs.getString("description"), rs.getString("type"),
                        rs.getString("image_name"), rs.getString("updated_at"), rs.getString("adresse")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
//    public List<Room> getAllMyRooms(Host c) {
//            try {
//            
//            List<Room> rooms = new ArrayList<>();
//
//            String requete = "SELECT  * FROM room where id_host = ?" ; 
//            stmt  = ds.getConnection().prepareStatement(requete) ;
//                        stmt.setInt(1,c.getId());
//
//            ResultSet rs=stmt.executeQuery();
//            while(rs.next()){
//                rooms.add(new Room(rs.getInt("id"), rs.getInt("id_host"), rs.getString("name"),
//                         rs.getInt("capacite"), rs.getInt("nbr_lit"), rs.getInt("prix"), rs.getString("description"), rs.getString("type"),
//                        rs.getString("img_name"), rs.getString("updatetime"), rs.getString("adresse")) );
//            }
//            return rooms;
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//                        return null;
//
//        } 
//    }
    
    
    
    public int Calculertotal() {
        String req = "SELECT COUNT(*) FROM room ";
        Room type = null;
        int nombreLignes = 0;
        try {
            PreparedStatement ps=ds.getConnection().prepareStatement(req);
//            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                nombreLignes = resultSet.getInt(1);
            }

//           resultSet.last();
//            //on récupère le numéro de la ligne 
//           nombreLignes = resultSet.getRow();
//            //on replace le curseur avant la première ligne 
//            resultSet.beforeFirst();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }
     public int Calculer(String type) {
        String req = "SELECT COUNT(*) AS count FROM room where type =?";
       Room u = null;
        int nombreLignes = 0;
        try {
             PreparedStatement ps=ds.getConnection().prepareStatement(req);
            ps.setString(1, type);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                nombreLignes = resultSet.getInt(1);
            }
     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }

    
    
}
