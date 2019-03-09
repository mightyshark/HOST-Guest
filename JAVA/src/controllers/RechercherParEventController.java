/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import entities.Room;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class RechercherParEventController implements Initializable {

    
    ObservableList<Evenement> data =FXCollections.observableArrayList();
    ObservableList<Room> datarooms =FXCollections.observableArrayList();

    EvenementService es = new EvenementService();
    
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> c_nom;
    @FXML
    private TableColumn<Evenement, String> c_place;
    @FXML
    private TableView<Room> tvrooms;
    @FXML
    private TableColumn<Room, String> c_r_nom;
    @FXML
    private TableColumn<Room, String> c_r_adresse;
    @FXML
    private JFXTextField tfrechercher;
    @FXML
    private TableColumn<Evenement, String> c_description;
    @FXML
    private TableColumn<Evenement, ?> c_date_debut;
    @FXML
    private TableColumn<Evenement, ?> c_date_fin;
    @FXML
    private JFXButton btnreserver;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnreserver.setVisible(false);
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
        
        
    }    

    @FXML
    public void getEventByPlace(){
        
        //btnsupprimer.setVisible(true);
        btnreserver.setVisible(false);
        try {

            Evenement ev =  table.getSelectionModel().getSelectedItem();
             
            EvenementService es = new EvenementService();
            
            
            datarooms.clear();
            es.getRoomsByEvenementPlace(datarooms, ev.getPlace());
                      
            //System.out.println(datarooms);
            
            tvrooms.setItems(datarooms);
            
            TableColumn nom = new TableColumn("name");
            c_r_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn adresse = new TableColumn("adresse");
            c_r_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            
            

        } catch (Exception e) {
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
        }
    }

    public void rechercher(ActionEvent event){
        
        
        FilteredList<Evenement> listeFiltre = new FilteredList<>(data, e -> true);
            tfrechercher.textProperty().addListener((observableValue,oldValue,newValue)-> {
                listeFiltre.setPredicate((Predicate<? super Evenement>) evenement -> {
                    
                    if (newValue == null || newValue.isEmpty())
                    { 
                         return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (evenement.getId() == Integer.parseInt(newValue))
                    {
                        return true;
                    }
                    return false;
                     });
                    SortedList<Evenement> ListeTries = new SortedList<>(listeFiltre);
                    ListeTries.comparatorProperty().bind(table.comparatorProperty());
                    table.setItems(ListeTries);  
                 });
         
    }

    private void hidebtn(MouseEvent event) {
        btnreserver.setVisible(false);
    }

    @FXML
    private void showbtn(MouseEvent event) {
        btnreserver.setVisible(true);
    }

    @FXML
    private void retour(ActionEvent event) {
           try {
            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/HomeGuest.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                
                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RechercherParEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
