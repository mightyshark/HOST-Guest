package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class HomeHostController implements Initializable {

    @FXML
    private Label MyUserName;
    @FXML
    private Button btnafficher;
    @FXML
    private ImageView img;
    @FXML
    private VBox logo_id;
    @FXML
    private JFXButton btn_stat;
    @FXML
    private JFXButton home_btn;
    @FXML
    private Label welcom;
    @FXML
    private Label welcom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setMyUserName(String UserN) {
        this.MyUserName.setText(UserN);
    }
    
    @FXML
    private void afficherAction(ActionEvent event) {
        try {
            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/AffichFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                
                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeHostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    @FXML
    private void Statistique(ActionEvent event) {
    
        try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/StatFXML.fxml"));
            
             Scene scene1 = new Scene(page1);

            Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;
            stage.setScene(scene1);
            stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
    }

    @FXML
    private void GoHome(ActionEvent event) {
        
        
         try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/LoginFXML.fxml"));
            
             Scene scene1 = new Scene(page1);

            Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;
            stage.setScene(scene1);
            stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
