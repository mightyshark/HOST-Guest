/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gaddour
 */
public class Test extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        
       
//        String accessToken = "EAACEdEose0cBALo26Sv1WEJcul5l9A7gjSJSHOx9hZC2yAmgRZBVnu0So6B94loSOJLgyfk3TtzN7kBVKFpKoQvHpJV9VP8xuSENTkv5sTt56IzI26ZC2Gew6ZC7pWSU9vnZC5ADAiuaKyPTcDjWBEPBLa9pPl0QxpOWc7M7is4ROXaLyk2WwT9abI95iBpOJCWXhrcyUWQZDZD";
//        FacebookClient fbclient = new DefaultFacebookClient(accessToken);
//        User me = fbclient.fetchObject("me",User.class);
//        System.out.println(" hey " +me.getName());
//        System.out.println(" hey " +me.getBirthday());
//        System.out.println(" hey " +me.getEmail());
    
        
        /*Client c = new Client("userrrr", "emaa@sss.ss", "okok", "nom", "prenom", "homme", null, 77766655, "ojokj", 0, "tunis", "host", 1);
        ClientService cs = new ClientService();
        
        
        
        //cs.add(c);
        //cs.remove(12);
        //cs.update(14, c);

        List<Client> pers = new ArrayList<Client>();
        pers = cs.getAll();
        
        
       
        
        for( int  i = 0 ; i<pers.size(); i++){
                System.out.println(pers.get(i).toString());
        }
        */
        
        //Evenement e = new Evenement("new new ", "here", new Date(2017, 3, 1) , new Date(2017, 3, 10), "nice", 1, "img.png");
        //EvenementService ce = new EvenementService();
        //List<Evenement> events = new ArrayList<Evenement>();
        //events = ce.getAll();
        //System.out.println(events);
        
        //Parent root = FXMLLoader.load(getClass().getResource("/views/HomeFXML.fxml"));
        
        //Scene scene = new Scene(root);
        
        //stage.setScene(scene);
        //stage.show();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/LoginFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        //HomeFXMLController cont = fxmlLoader.<HomeFXMLController>getController();
        //cont.setMyUserName(" non ");
        
        Scene scene = new Scene(root1);
        
        stage.setScene(scene);
        stage.show();

        
        
    }
    
    
   
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

                launch(args);

        
    }

    
}
