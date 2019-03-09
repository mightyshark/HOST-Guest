/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import utiles.DataSource;
import services.RoomService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import controllers.AjoutFXMLController;
import controllers.LoginFXMLController;
import entities.Host;

import entities.Room;
import interfaces.Rservice;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;
import services.HostService;

/**
 * FXML Controller class
 *
 * @author hmila
 */
public class AffichFXMLController implements Initializable,MapComponentInitializedListener{
   DataSource connection=DataSource.getInstance();
 ObservableList<Room> data =FXCollections.observableArrayList();
  ObservableList<Room> data1 =FXCollections.observableArrayList();

  PreparedStatement preparedstatement=null;
   ResultSet rs=null;
    
   

    @FXML
    private TableView<Room> tableView;
    @FXML
    private TableColumn<Room, String> c_nom;
    @FXML
    private TableColumn<Room, String> c_cap;
    @FXML
    private TableColumn<Room, String>c_nbrlit;
    @FXML
    private TableColumn<Room, String> c_prix;
    @FXML
    private TableColumn <Room, String> c_desc;
    @FXML
    private TableColumn<?,?> c_type;
    @FXML
    private TableColumn<Room, String> c_uptime;
    @FXML
    private TableColumn<Room, String> c_adr;
    @FXML
    private TableColumn<Room, Integer> c_id;
    @FXML
    private TableColumn<Room, Integer> c_idhost;
    @FXML
    private TableColumn <Room, String> c_img;
    @FXML
    private JFXButton btn_delete;
    
    @FXML
    private GoogleMapView mapVie;
    private GoogleMap map;
    private Marker marker;
   

    /**
     * Initializes the controller class.
     * @param url
     */
    
     RoomService rm = new RoomService();
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
    private JFXTextField txt_updateTime;
    @FXML
    private JFXTextField txt_img_name;
    @FXML
    private JFXTextArea txt_description;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXButton btn_ajout;
    @FXML
    private JFXTextField txt_search2;
    @FXML
    private JFXButton btn_home;
    @FXML
    private ImageView imgVw;
    @FXML
    private JFXTextArea texthost;
    @FXML
    private JFXButton browse_btn;
        static int id;
        
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_Type.getItems().add("Chambre");
        txt_Type.getItems().add("Maison");
        mapVie.addMapInializedListener(this);

        //rm.afficher(data);
        HostService hs = new HostService();
        Host h = hs.getIdHost(LoginFXMLController.getIdsession());
          
        
        rm.getAllMyRooms(data, h);
        tableView.setItems(data);
        
        

        TableColumn Name = new TableColumn("Name");
        c_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn Capacite = new TableColumn("Capacite");
        c_cap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        TableColumn Nbr_Lit = new TableColumn("Nbr_Lit");
        c_nbrlit.setCellValueFactory(new PropertyValueFactory<>("nbr_lit"));
        TableColumn Prix = new TableColumn("Prix");
        c_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        TableColumn Description = new TableColumn("Description");
        c_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn Type = new TableColumn("Type");
        c_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn Updatetime = new TableColumn("Updatetime");
        c_uptime.setCellValueFactory(new PropertyValueFactory<>("updatetime"));
        TableColumn Adresse = new TableColumn("Adresse");
        c_adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        TableColumn Id = new TableColumn("Id");
        c_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn Id_Host = new TableColumn("Id_Host");
        c_idhost.setCellValueFactory(new PropertyValueFactory<>("id_host"));
        TableColumn Img_name = new TableColumn("Img_name");
        c_img.setCellValueFactory(new PropertyValueFactory<>("img_name"));

    }


    @FXML
    private void delete(ActionEvent event) {
        RoomService rs = new RoomService();
        Room rm = tableView.getSelectionModel().getSelectedItem();
          
          if (rm == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser l'évènement à supprimer !");
            alert.setContentText("Veuillez Sélectionner un évènement");
            alert.showAndWait();
    }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableView.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression Evènement");
            alert.setContentText("Voulez vous vraiment supprimer cet évènement");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                 
                    rs.remove(rm.getId());
                    System.out.println("++++++++++++++++++++"+rm.getId());
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.refresh();

                }
            });
          }
        
    }
    
    
    
    
  
    
    
    
    
    Room room = new Room();
   @FXML
    private void ShowOnClick() {

        try {

            Room rmm = (Room) tableView.getSelectionModel().getSelectedItem();
            rmm = tableView.getSelectionModel().getSelectedItem();
//             id=rmm.getId();
//             System.out.println("+++++++++++++"+id);
//             room = rm.getRoomById(id);
            texthost.setText(String.valueOf( rmm.getId_host()));
            txt_nom.setText(rmm.getName());
            txt_capacite.setText(String.valueOf(rmm.getCapacite()));
            txt_nbr_Lit.setText(String.valueOf(rmm.getNbr_lit()));
            txt_Prix.setText(String.valueOf(rmm.getPrix()));
            txt_description.setText(rmm.getDescription());
            txt_Type.setValue(rmm.getType());
            txt_img_name.setText(rmm.getImg_name());
            txt_updateTime.setText(rmm.getUpdatetime());
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
    private void updateR(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Voulez-vous vraiment modifier cette Room ?");
        Optional<ButtonType> result = alert.showAndWait();
       if (result.get() == ButtonType.OK) {
           
            System.out.println("++++++++"+room.getId());
       
        room.setName(txt_nom.getText());
        room.setId_host(Integer.parseInt( texthost.getText()));
        room.setAdresse(txt_adresse.getText());
        room.setCapacite(Integer.parseInt(txt_capacite.getText()));
        room.setDescription(txt_description.getText());
        room.setNbr_lit(Integer.parseInt(txt_nbr_Lit.getText()));
        room.setPrix(Integer.parseInt(txt_Prix.getText()));
        room.setType(txt_Type.getTypeSelector());
        room.setImg_name(txt_img_name.getText());
        room.setUpdatetime(txt_updateTime.getText());
        RoomService rms = new RoomService();
        rms.update(id , room);
           tableView.refresh();
     
            Notifications notififcationBuilder = Notifications.create()
                    .title("Confirmation !")
                    .text("Room " + "<" + room.getName() + ">" + " modifier avec succées !")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.CENTER);
            notififcationBuilder.showConfirm();
                  

    }
    }   

    @FXML
    private void Ajouter(ActionEvent event) {
        
        try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/AjoutFXML.fxml"));
            
             Scene scene1 = new Scene(page1);

            Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;
            stage.setScene(scene1);
            stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @FXML
    private void gohome(ActionEvent event) {
        
        try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/HomeHost.fxml"));
            
             Scene scene1 = new Scene(page1);

            Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;
            stage.setScene(scene1);
            stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }
    @FXML
    private void browseEvent(ActionEvent event) {
        try{
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//        FileChooser.ExtensionFilter extFilterTout = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("GPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String x = file.toPath().toAbsolutePath().toString();
        System.out.println(x);
                txt_img_name.setText(x.toString());

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
           imgVw.setImage(image);

        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }catch (Exception e){
            System.out.println("");
        }
        
    }

    
    public void LoadData() {
        Rservice us = new RoomService();
        data1 = FXCollections.observableArrayList();
        try {
            ResultSet r = us.recherche(txt_search2.getText());
            while (r.next()) {
                Room rm1 = new Room();
                rm1.setId(r.getInt("id"));
                rm1.setId_host( r.getInt("id_host"));
                rm1.setName(r.getString("name"));
                rm1.setCapacite(r.getInt("capacite"));
                rm1.setDescription(r.getString("description"));
                rm1.setNbr_lit(r.getInt("nbr_lit"));
                rm1.setAdresse(r.getString("adresse"));
                rm1.setImg_name(r.getString("img_name"));
                rm1.setPrix(r.getInt("prix"));
                rm1.setType(r.getString("type"));
                rm1.setUpdatetime(r.getString("updatetime"));  
                data1.add(rm1);
            }
            tableView.setItems(null);
            tableView.setItems(data1);            
        } catch (SQLException ex) {
            Logger.getLogger(AffichFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void rech(KeyEvent event) {
    
     
        RoomService es = new RoomService();
        LoadData();
        tableView.refresh();
    }

   
    
    public void mapInitialized() {
        System.out.println("here");
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(6);

        map = mapVie.createMap(mapOptions);

        map.addUIEventHandler(UIEventType.click, (jso) -> {
            System.out.println("click");
            JSObject ob = (JSObject) jso.getMember("latLng");
            System.out.println();
            Double lat = Double.valueOf(ob.call("lat").toString());
            Double lng = Double.valueOf(ob.call("lng").toString());
            
            GeocodingService s = new GeocodingService();
            s.reverseGeocode(lat, lng, (grs, gs) -> {
                if (gs.equals(gs.OK)) {
                    LatLong clickedLocation = new LatLong(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(clickedLocation);
                    markerOptions.animation(Animation.DROP);
                    if (marker != null) {
                        map.removeMarker(marker);
                        marker = new Marker(markerOptions);
                        map.addMarker(marker);
                    } else {
                        marker = new Marker(markerOptions);
                        map.addMarker(marker);

                    }
                    String adress = grs[0].getAddressComponents().get(1).getLongName() + ", " + grs[0].getAddressComponents().get(2).getLongName();
                    txt_adresse.setText(adress);
                    
                   
                 
                    
                    System.out.println(grs[0].getAddressComponents().get(1).getLongName() + ", " + grs[0].getAddressComponents().get(2).getLongName());

                }
            });

        });
    }
    
    
    
    

}
