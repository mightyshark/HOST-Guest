package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Room;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.RoomService;
import utiles.DataSource;

/**
 * FXML Controller class
 *
 * @author gaddour
 */
public class HomeGuestController implements Initializable {
    
     DataSource connection=DataSource.getInstance();
 ObservableList<Room> data =FXCollections.observableArrayList();
  ObservableList<Room> data1 =FXCollections.observableArrayList();

  PreparedStatement preparedstatement=null;
   ResultSet rs=null;
    
     RoomService rm = new RoomService();

    @FXML
    private Label MyUserName;
    @FXML
    private TableView<Room> tableView;
    private TableColumn<?, ?> c_nom;
    @FXML
    private ImageView imgVw;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_capacite;
    @FXML
    private JFXTextField txt_nbr_Lit;
    @FXML
    private JFXTextField txt_Prix;
    @FXML
    private JFXComboBox<String> txt_Type;
    @FXML
    private JFXTextField txt_adresse;
    @FXML
    private JFXTextArea txt_description;
    @FXML
    private TableColumn<Room, String> Nom;
    @FXML
    private Button btnrechevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txt_Type.getItems().add("Chambre");
        txt_Type.getItems().add("Maison");

        rm.afficher(data);
                tableView.setItems(data);

        

        TableColumn Name = new TableColumn("Name");
        Nom.setCellValueFactory(new PropertyValueFactory<>("name"));
       

    }   
    
    public void setMyUserName(String UserN) {
        this.MyUserName.setText(UserN);
    }
    Room room = new Room();

    @FXML
    private void ShowOnClick(MouseEvent event) {
        
        
         try {

            Room rmm = (Room) tableView.getSelectionModel().getSelectedItem();
            rmm = (Room) tableView.getSelectionModel().getSelectedItem();
            
            txt_nom.setText(rmm.getName());
            txt_capacite.setText(String.valueOf(rmm.getCapacite()));
            txt_nbr_Lit.setText(String.valueOf(rmm.getNbr_lit()));
            txt_Prix.setText(String.valueOf(rmm.getPrix()));
            txt_description.setText(rmm.getDescription());
            txt_Type.setValue(rmm.getType());
            txt_adresse.setText(rmm.getAdresse());
            
            String url = rmm.getImg_name();
            File file = new File(url);
            Image image = new Image(file.toURI().toString());
            imgVw.setImage(image);


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
    
    
    
     @FXML
    private void afficherPageRechercheEvent(ActionEvent event) {
        
        try {
            
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/RechercherParEvent.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                
                Scene scene = new Scene(root1);
                Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;

                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeGuestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }

