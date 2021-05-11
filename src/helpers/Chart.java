/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import models.Detailsmoney;

/**
 *
 * @author smartTicket
 */
public class Chart {

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> bc
            = new BarChart<String, Number>(xAxis, yAxis);

    public void initChart(StackPane stck, Detailsmoney data) {
        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data("Airtel", data.getAirtel()));
        series3.getData().add(new XYChart.Data("Orange", data.getOrange()));
        series3.getData().add(new XYChart.Data("Vodacom", data.getVodacom()));
        bc.getData().addAll(series3);
        stck.getChildren().clear();
        stck.getChildren().add(bc);

    }

    public void initChart2(StackPane stck, Detailsmoney data) {

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Orange");
        series4.getData().add(new XYChart.Data("Message", 256.34));
        series4.getData().add(new XYChart.Data("Traiter", 148.82));
        series4.getData().add(new XYChart.Data("En ettente", 10));

        XYChart.Series series5 = new XYChart.Series();
        series5.setName("2004");
        series5.getData().add(new XYChart.Data("Message", 71));
        series5.getData().add(new XYChart.Data("Traiter", 65));
        series5.getData().add(new XYChart.Data("En ettente", 1));

        XYChart.Series series6 = new XYChart.Series();
        series6.setName("2005");
        series6.getData().add(new XYChart.Data("Message", 12));
        series6.getData().add(new XYChart.Data("Traiter", 11));
        series6.getData().add(new XYChart.Data("En ettente", 2));
        bc.getData().addAll(series4, series5, series6);
        
        stck.getChildren().clear();
        stck.getChildren().add(bc);

    }

}
