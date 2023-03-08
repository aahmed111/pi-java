package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.Client;
import edu.workshopjdbc3a48.entities.Transporteur;
import edu.workshopjdbc3a48.services.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class DashbordAdminController implements Initializable {

     @FXML
    private LineChart<String, Integer> chart;
    @FXML
    private PieChart pie;
    @FXML
    private Label numberClient;
    @FXML
    private Label numberTransporteur;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
         try {
             count();
             pieChart();
             lineChart();
         } catch (SQLException ex) {
             Logger.getLogger(DashbordAdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }

    public void lineChart() throws SQLException {
        ServiceUser su = new ServiceUser();
        Map<String, Integer> map = su.getUserByDuree();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Convertir chaque entrée de la Map en XYChart.Data
        List<XYChart.Data<String, Integer>> dataList = map.entrySet().stream()
                  .map(entry -> new XYChart.Data<String, Integer>(entry.getKey(), entry.getValue()))
                  .collect(Collectors.toList());
        // Trier les données par ordre décroissant de valeur
        Comparator<XYChart.Data<String, Integer>> comparator = Comparator.comparing(XYChart.Data::getYValue);
        Collections.sort(dataList, comparator.reversed());

        // Ajouter les 10 premières entrées triées dans la série
        for (int i = 0; i < 10 && i < dataList.size(); i++) {
            series.getData().add(dataList.get(i));
        }

        chart.getData().add(series);

    }

    public void pieChart() throws SQLException {
        ServiceUser su = new ServiceUser();
        Map<String, Integer> map = su.getListMaleFemale();

        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                  new PieChart.Data("Female", map.get("Female")),
                  new PieChart.Data("Male", map.get("Male"))
        );
        pie.setData(pieChart);
    }

    public void count() throws SQLException {
        ServiceUser su = new ServiceUser();
        List<Client> list = su.getAllClient();

        List<Transporteur> listT = su.getAllTransporteur();
        String numberT = String.valueOf(listT.size());
        String numberC = String.valueOf(list.size());
        numberTransporteur.setText(numberT);
        numberClient.setText(numberC);

    }
    
}
