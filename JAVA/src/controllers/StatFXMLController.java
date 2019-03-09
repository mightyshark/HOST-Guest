/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.RoomService;

/**
 * FXML Controller class
 *
 * @author hmila
 */
public class StatFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
     PieChart   pieChart = new PieChart();
    
    private final ObservableList<PieChart.Data> type = FXCollections.observableArrayList();
    @FXML
    private Button btn_prec;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    RoomService PS = new RoomService();
       type.addAll(new PieChart.Data("Nombre des Chambres", (PS.Calculer("chambre") * 100) / PS.Calculertotal()),
                 new PieChart.Data("Nombre des Maisons", (PS.Calculer("maison") * 100) / PS.Calculertotal())
                 
                
        );
        pieChart.setData(type);
        pieChart.setTitle("Les statistiques");
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setLabelsVisible(true);
    }    

    @FXML
    private void retour(ActionEvent event) {
   
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
    
}
