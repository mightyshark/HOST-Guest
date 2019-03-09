///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package api;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Side;
//import javafx.scene.Scene;
//import javafx.scene.chart.PieChart;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import services.RoomService;
//
//
///**
// *
// * @author hmila
// */
//public class Statistique extends Application{
//    
//private final ObservableList<PieChart.Data> user = FXCollections.observableArrayList();
//    private BorderPane root;
//    private PieChart pieChart;
//
//    @Override
//    public void start(Stage primaryStage) {
//        RoomService RS = new RoomService();
//       
//
//        primaryStage.setTitle("Pie Chart");
//        user.addAll(new PieChart.Data("nombre chambre", (RS.Calculer("chambre") * 100) / RS.Calculertotal()),
//                 new PieChart.Data("nombre maison", (RS.Calculer("maison") * 100) / RS.Calculertotal())
//                
//        );
//
//        root = new BorderPane();
//        Scene scene = new Scene(root, 600, 500);
//        pieChart = new PieChart();
//        pieChart.setData(user);
//        pieChart.setTitle("Les statistiques");
//        pieChart.setLegendSide(Side.BOTTOM);
//        pieChart.setLabelsVisible(true);
//        root.setCenter(pieChart);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }
//
//    
//
//
//}
