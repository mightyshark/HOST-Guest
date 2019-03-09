/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class AjoutEventController implements Initializable {

    @FXML
    private JFXDatePicker dp_date_debut;
    @FXML
    private JFXDatePicker dp_date_fin;
    @FXML
    private JFXTextField tf_nom;
    @FXML
    private JFXTextField tf_place;
    @FXML
    private JFXTextField tf_description;
    @FXML
    private JFXButton btn_aout_event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void ajout(){
        EvenementService es = new EvenementService();
        Date date_debut = Date.valueOf(dp_date_debut.getValue());
        Date date_fin  = Date.valueOf(dp_date_fin.getValue());
        String nom_event = tf_nom.getText();
        String place_event = tf_place.getText();
        String description_event = tf_description.getText();
        
        
        if (nom_event.isEmpty() || place_event.isEmpty() || date_debut.equals(null) || date_fin.equals(null) || description_event.isEmpty() )
        {
            Notifications notififcationBuilder = Notifications.create()
                    .title("Erreur !")
                    .text("Choix incorrect !")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            System.out.println("Hello");
                        }
                    });
            notififcationBuilder.showError();
        }else{
            Evenement e = new Evenement(nom_event, place_event ,date_debut , date_fin , description_event, 1, " ");
            es.add(e);
            
            
            

        }
        
       
    }
    
}
