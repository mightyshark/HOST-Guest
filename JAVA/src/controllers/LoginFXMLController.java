package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import entities.Client;
import entities.Guest;
import entities.Guide;
import entities.Host;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.ClientService;
import services.GuestService;
import services.GuideService;
import services.HostService;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class LoginFXMLController implements Initializable {

    

    public static String lemoi;
    public static int idsession;
    private ToggleGroup group;
    
    @FXML
    private JFXRadioButton rdhomme;
    @FXML
    private JFXRadioButton rdfemme;
    @FXML
    private ImageView image;
    @FXML
    private TextField tfusername;
    @FXML
    private JFXPasswordField tfpwd;
    @FXML
    private Button btnconnecter;
    @FXML
    private ImageView logimg;
    @FXML
    private JFXButton btnFB;
    @FXML
    private JFXComboBox<String> cb;
    @FXML
    private JFXTextField tfusernamenew;
    @FXML
    private JFXPasswordField tfpasswordnew2;
    @FXML
    private JFXPasswordField tfpasswordnew1;
    @FXML
    private JFXTextField tfemailnew;
    @FXML
    private JFXButton signupbtn;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb.getItems().add("Host");
        cb.getItems().add("Guest");
        cb.getItems().add("Guide");
        group = new ToggleGroup();
        rdhomme.setToggleGroup(group);
        rdfemme.setToggleGroup(group);
        rdhomme.setSelected(true);


        Image ima = new Image("file:C:/wamp64/www/GitLab/Pidev/web/images/Evenement/images.jpg");
        image.setImage(ima);
    }    
    
    @FXML
    public Boolean connecter(ActionEvent event){
        
        
        ClientService cs = new ClientService();

        Client x = cs.getByUsername(tfusername.getText(),tfpwd.getText());
        
                        this.setLemoi( x.getUsername()); 
                        this.setIdsession(x.getId());
        
        if (x != null)      {
            System.out.println(x);

            try {

            Parent page1;
                
            if (x.getType().equals("Host")){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeHost.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                HomeHostController cont = fxmlLoader.<HomeHostController>getController();
                cont.setMyUserName(x.getUsername());

                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                
                //System.out.println("connecté en tant que host");
            }else if (x.getType().equals("Guest")){
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeGuest.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                HomeGuestController cont = fxmlLoader.<HomeGuestController>getController();
                cont.setMyUserName(x.getUsername());

                
                
                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                //System.out.println("connecté en tant que Guest");
            }else {
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeGuide.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                //System.out.println(lemoi);
                HomeFXMLController cont = fxmlLoader.<HomeFXMLController>getController();
                cont.setMyUserName(x.getUsername());

                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                System.out.println("connecté en tant que Guide");
            }
            
            

            

            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            
        }  
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error ! ");
            alert.setHeaderText("Password Incorrect or Username Invalid ! ");
            //alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            //System.out.println(" repeter ");

        }
        
        
        return null ;
    }

    
     @FXML
    private void authUser(ActionEvent event) {
       
        String domain = "http://google.com";
        String appId = "406764043035728";
       
        
        

//         String authUrl ="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
//                 + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,user_events,user_friends,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,manage_notifications,manage_pages,publish_actions,read_friendlists,read_insights,read_mailbox,read_page_mailboxes,rsvp_event﻿" ;
//        
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event";
       
        

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
       
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        
        while(true){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            //accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
           
            
            accessToken = "EAACEdEose0cBANA58V4ZC35wrovb7OK1ZBcLIZAnGiyr3p4y96UsEPcxRPmtkVrQoxSCPZCBPmezs2zAhPDG43DGyGjTVjY6JA1NzPaw5VSWUqnk6j9QRGhtilzsmTCfCamEXnnSIuSfft81qcdqQSAbZCux8TeZBI6kZAadW5NRqnRV8SB8rP8ILRKTxJ3Y1rarqCDk9XDYQZDZD";
            
            driver.quit();

                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me",User.class);
                System.out.println(user);
                tfusernamenew.setText(user.getFirstName());
                tfemailnew.setText(user.getEmail());
            }
       
        }
        
    }
    
    
    
    
    
    @FXML
    private void signUp(ActionEvent event) {
        
        ClientService cs = new ClientService();
        
        String sexe ;
        if (rdhomme.isSelected()){
            sexe = "Homme";
        }else sexe= "Femme";
        
        if (tfusernamenew.getText().isEmpty() || tfpasswordnew1.getText().isEmpty() || !(tfpasswordnew1.getText().equals(tfpasswordnew2.getText())) || tfemailnew.getText().isEmpty() || (cb.getSelectionModel().isEmpty()) ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error ! ");
            alert.setHeaderText("Remplir tout les champs !  ");
            alert.setContentText("Ooops, there was an error!");
            //System.out.println(cb.getSelectionModel().isEmpty());
            alert.showAndWait();
            
        } else{
            
        
        
        Client x = new Client(tfusernamenew.getText(), tfemailnew.getText(), tfpasswordnew1.getText(), sexe, cb.getValue());
                                this.setLemoi( x.getUsername()); 
                        this.setIdsession(x.getId());
        cs.add(x);
        
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Compte accepté ! ");
            alert.setHeaderText("You are welcome  ! ");
            //alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            
        try{
            if (x.getType().equals("Host")){
                
                                
                HostService gs = new HostService();
                ClientService ccc = new ClientService();
                Client cl = ccc.getByOnlyUsername(x.getUsername());
                Host g = new Host(cl.getId());
                gs.add(g);
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeHost.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                HomeHostController cont = fxmlLoader.<HomeHostController>getController();
                cont.setMyUserName(x.getUsername());

                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                
                System.out.println("connecté en tant que host");
            }else if (x.getType().equals("Guest")){
                
                
                GuestService gs = new GuestService();
                ClientService ccc = new ClientService();
                Client cl = ccc.getByOnlyUsername(x.getUsername());
                Guest g = new Guest( cl.getId());
                gs.add(g);
                
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeGuest.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                HomeGuestController cont = fxmlLoader.<HomeGuestController>getController();
                cont.setMyUserName(x.getUsername());

                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                System.out.println("connecté en tant que Guest");
            }else {
                
                GuideService gs = new GuideService();
                ClientService ccc = new ClientService();
                Client cl = ccc.getByOnlyUsername(x.getUsername());
                //gs.getIdGuide(cl.getId());
                
                //System.out.println(cl);
                
                
                Guide g = new Guide( cl.getId());
                gs.add(g);
                
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeGuide.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                HomeFXMLController cont = fxmlLoader.<HomeFXMLController>getController();
                cont.setMyUserName(x.getUsername());

                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
                System.out.println("connecté en tant que Guide");
            }
            
            

            

            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

            
            }
    }

    public static String getLemoi() {
        return lemoi;
    }

    public static void setLemoi(String lemoi) {
        LoginFXMLController.lemoi = lemoi;
    }

    public static int getIdsession() {
        return idsession;
    }

    public static void setIdsession(int idsession) {
        LoginFXMLController.idsession = idsession;
    }
    
    
    
    
    
}
