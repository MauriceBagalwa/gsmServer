/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import models.Chartdata;
import models.Detailsmoney;

/**
 *
 * @author smartTicket
 */
public class Chart2 {

    CategoryAxis xAxi = new CategoryAxis();
    NumberAxis yAxi = new NumberAxis();
    BarChart<String, Number> bc;
    String message = "Message";
    String traiter = "traiter";
    String attente = "En ettente";
    Chartdata data1, data2, data3;

    public void initChart2(StackPane stck, List<Chartdata> data) {
        bc = new BarChart<>(xAxi, yAxi);
        data1 = data.get(0);
        data2 = data.get(1);
        data3 = data.get(2);
        System.out.println(data1.toString());
        System.out.println(data2.toString());
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("Message", data1.getCount().getMessage()));
        series1.getData().add(new XYChart.Data("Money", data1.getCount().getAll_()));
        series1.getData().add(new XYChart.Data("Traiter", data1.getCount().getTraiter()));
        series1.getData().add(new XYChart.Data("Attente", data1.getCount().getEn_attente()));

        XYChart.Series series2 = new XYChart.Series();
        series2.getData().add(new XYChart.Data("Message", data2.getCount().getMessage()));
        series2.getData().add(new XYChart.Data("Money", data2.getCount().getAll_()));
        series2.getData().add(new XYChart.Data("Traiter", data2.getCount().getTraiter()));
        series2.getData().add(new XYChart.Data("Attente", data2.getCount().getEn_attente()));

        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data("Message", data3.getCount().getMessage()));
        series3.getData().add(new XYChart.Data("Money", data3.getCount().getAll_()));
        series3.getData().add(new XYChart.Data("Traiter", data3.getCount().getTraiter()));
        series3.getData().add(new XYChart.Data("Attente", data3.getCount().getEn_attente()));
        bc.getData().addAll(series1, series2, series3);

        stck.getChildren().clear();
        stck.getChildren().add(bc);

    }

}
