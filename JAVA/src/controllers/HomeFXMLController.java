/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Client;
import entities.Evenement;
import entities.Guide;
import entities.Room;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ClientService;
import services.EvenementService;
import services.GuideService;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class HomeFXMLController implements Initializable {

    
    
    ObservableList<Evenement> data =FXCollections.observableArrayList();

    EvenementService es = new EvenementService();
    
    
    @FXML
    private Label MyUserName ;
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> c_nom;
    @FXML
    private TableColumn<Evenement, String> c_place;

    @FXML
    private AnchorPane panenew;
    @FXML
    private JFXButton btnrefresh;
    @FXML
    private JFXButton btnsupprimer;
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
    @FXML
    private TableColumn<Evenement, ?> c_date_debut;
    @FXML
    private TableColumn<Evenement, ?> c_date_fin;
    @FXML
    private TableColumn<Evenement, String> c_description;
    @FXML
    private JFXTextField tfrechercher;
    @FXML
    private JFXButton btnafficherajout;
    @FXML
    private JFXButton btn_modifier;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnsupprimer.setVisible(false);
        btnafficherajout.setVisible(false);
        btn_modifier.setVisible(false);
        
        ClientService cs = new ClientService();
        Client me  = cs.getByOnlyUsername(MyUserName.getText());
        
        System.out.println(LoginFXMLController.getLemoi());
        
        //es.getMyEvents(data, me.getId());
        es.afficher(data);
        table.setItems(data);
        TableColumn nom = new TableColumn("nom");
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn place = new TableColumn("place");
        c_place.setCellValueFactory(new PropertyValueFactory<>("place"));
        TableColumn description = new TableColumn("description");
        c_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn date_debut = new TableColumn("date_debut");
        c_date_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));   
        TableColumn date_fin = new TableColumn("date_fin");
        c_date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));


        
        FilteredList<Evenement> listeFiltre = new FilteredList<>(data, e -> true);
            tfrechercher.textProperty().addListener((observableValue,oldValue,newValue)-> {
                listeFiltre.setPredicate((Predicate<? super Evenement>) evenement -> {
                    
                    if (newValue == null || newValue.isEmpty())
                    { 
                         return true;
                    }
                    //String lowerCaseFilter = newValue.toLowerCase();
                    if ( evenement.toString().toLowerCase().contains(newValue.toLowerCase()))
                    {
                        return true;
                    }
                    return false;
                     });
                    SortedList<Evenement> ListeTries = new SortedList<>(listeFiltre);
                    ListeTries.comparatorProperty().bind(table.comparatorProperty());
                    table.setItems(ListeTries);  
                 });
            
                //System.out.println(MyUserName.getText());

        
    
    }    


    public HomeFXMLController() {
    }

    public void setMyUserName(String UserN) {
        this.MyUserName.setText(UserN);
    }
    

    @FXML
    public  void refresh(){
        
        data.clear();
        es.afficher(data);
        table.setItems(data);
        TableColumn nom = new TableColumn("nom");
        c_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn place = new TableColumn("place");
        c_place.setCellValueFactory(new PropertyValueFactory<>("place"));
        
    }

    @FXML
    private void ajout(ActionEvent event) {
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
            GuideService gs = new GuideService();
            Guide g = gs.getIdGuide(LoginFXMLController.getIdsession());
            
            Evenement e = new Evenement(nom_event, place_event ,date_debut , date_fin , description_event, g.getId(), " ");
            System.out.println(e);
            es.add(e);
            
        }
        
        this.refresh();

    }

    @FXML
    private void affichermodifier(MouseEvent event) {
        Evenement ev = table.getSelectionModel().getSelectedItem();
        tf_nom.setText(ev.getNom());
        tf_place.setText(ev.getPlace());
        tf_description.setText(ev.getDescription());
        LocalDate datedebut = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(ev.getDate_debut()) );
        dp_date_debut.setValue(datedebut);
        
        LocalDate datefin = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(ev.getDate_fin()) );
        dp_date_fin.setValue(datefin);
        btn_aout_event.setVisible(false);
                
        btnsupprimer.setVisible(true);
        btn_modifier.setVisible(true);
        btnafficherajout.setVisible(true);
        
        

        //System.out.println(ev.toString());
    }

    @FXML
    private void afficherAjout(ActionEvent event) {
        btn_aout_event.setVisible(true);
        btn_modifier.setVisible(false);
        tf_nom.setText("");
        tf_description.setText("");
        tf_place.setText("");
        dp_date_debut.setValue(null);
        dp_date_fin.setValue(null);
        btnsupprimer.setVisible(false);
        btnafficherajout.setVisible(false);
    }

    @FXML
    private void suppr(ActionEvent event) {
        Evenement ev = table.getSelectionModel().getSelectedItem();
        es.remove(ev.getId());
        this.refresh();
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        Evenement ev = table.getSelectionModel().getSelectedItem();

        EvenementService es = new EvenementService();
        Date date_debut = Date.valueOf(dp_date_debut.getValue());
        Date date_fin  = Date.valueOf(dp_date_fin.getValue());
        String nom_event = tf_nom.getText();
        String place_event = tf_place.getText();
        String description_event = tf_description.getText();
        
        Evenement e = new Evenement(nom_event, place_event ,date_debut , date_fin , description_event, 1, " ");
        //System.out.println(e);
        es.update(ev.getId(), e);
        
        
        this.refresh();
    }






}
