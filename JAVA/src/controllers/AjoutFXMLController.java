/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import services.RoomService;
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
import entities.Host;
import entities.Room;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class AjoutFXMLController implements Initializable ,  MapComponentInitializedListener{

    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_capacite;
    @FXML
    private JFXTextField txt_nbr_Lit;
    @FXML
    private JFXTextField txt_Prix;
    @FXML
    private JFXComboBox<String> cmb_Type;
    @FXML
    private JFXTextField txt_adresse;
    @FXML
    private JFXTextField txt_updateTime;
    @FXML
    private JFXTextField txt_img_name;
    @FXML
    private JFXTextArea txt_description;
    @FXML
    private JFXButton browse_btn;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imgVw1;
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    private Marker marker;

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_Type.getItems().add("Chambre");
        cmb_Type.getItems().add("Maison");
        mapView.addMapInializedListener(this);


    }

   
    @FXML
    private void AjouterEvent(ActionEvent event) {

         if     (
                 
                 txt_nom.getText().trim().isEmpty()
                || txt_adresse.getText().trim().isEmpty()
                || txt_nom.getText().trim().isEmpty()
                || txt_capacite.getText().trim().isEmpty()
                || txt_description.getText().trim().isEmpty()
                || txt_nbr_Lit.getText().trim().isEmpty()
                || txt_Prix.getText().isEmpty()
                || txt_img_name.getText().isEmpty()
                || txt_updateTime.getText().isEmpty()
                 ) 
        
         
         
         
         
         {
            Notifications notififcationBuilder = Notifications.create()
                    .title("Erreur !")
                    .text("Veuillez remplir tout les champs !")
                    .graphic(null)
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.CENTER);

            notififcationBuilder.showError();}
        
         else {     
             HostService hs = new HostService();
            Host h = hs.getIdHost(LoginFXMLController.getIdsession());
          
        Room r = new Room();
        r.setId_host(h.getId());
        r.setName(txt_nom.getText());
        r.setAdresse(txt_adresse.getText());
        r.setCapacite(Integer.parseInt(txt_capacite.getText()));
        r.setDescription(txt_description.getText());
        r.setNbr_lit(Integer.parseInt(txt_nbr_Lit.getText()));
        r.setPrix(Integer.parseInt(txt_Prix.getText()));
        r.setType(cmb_Type.getValue());
        r.setImg_name(txt_img_name.getText());
        r.setUpdatetime(txt_updateTime.getText());
        RoomService rm = new RoomService();
       
        rm.add(r);
        Notifications notififcationBuilder = Notifications.create()
                .title("Confirmation !")
                .text("Room ajouter avec succées !")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.CENTER);
        notififcationBuilder.showConfirm();

    
         
          try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/AffichFXML.fxml"));
            
             Scene scene1 = new Scene(page1);

            Stage stage = (Stage)   ((Node) event.getSource()).getScene().getWindow() ;
            stage.setScene(scene1);
            stage.show();
            
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }}

    @FXML
    private void annuler(ActionEvent event) {
    
    try {

            Parent page1;
                page1 = FXMLLoader.load(getClass().getResource("/views/AffichFXML.fxml"));
            
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
            imageview.setImage(image);

        } catch (IOException ex) {
            Logger.getLogger(AjoutFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }catch (Exception e){
            System.out.println("");
        }
        
    }

    
    
    
    @Override
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

        map = mapView.createMap(mapOptions);

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
 
    
    







