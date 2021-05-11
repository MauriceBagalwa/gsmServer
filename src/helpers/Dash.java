/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static controllers.DashbordController.airtelDash;
import static controllers.DashbordController.orangeDash;
import static controllers.DashbordController.vodacomDash;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import models.Chartdata;

/**
 *
 * @author smartTicket
 */
public class Dash {

    CategoryAxis xAxi = new CategoryAxis();
    NumberAxis yAxi = new NumberAxis();
    BarChart<String, Number> bc;
    String message = "Message";
    String traiter = "traiter";
    String attente = "En ettente";
    Chartdata data1, data2, data3;

    public void initChart2(StackPane stck) {
        bc = new BarChart<>(xAxi, yAxi);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Airtel");
        series1.getData().add(new XYChart.Data("Message", airtelDash.getMessage()));
        series1.getData().add(new XYChart.Data("Money", airtelDash.getMoney()));
        series1.getData().add(new XYChart.Data("Traiter", airtelDash.getTraiter()));
        series1.getData().add(new XYChart.Data("En attente", airtelDash.getEn_attente()));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Orange");
        series2.getData().add(new XYChart.Data("Message", orangeDash.getMessage()));
        series2.getData().add(new XYChart.Data("Money", orangeDash.getMoney()));
        series2.getData().add(new XYChart.Data("Traiter", orangeDash.getTraiter()));
        series2.getData().add(new XYChart.Data("En attente", orangeDash.getEn_attente()));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Vodacom");
        series3.getData().add(new XYChart.Data("Message", vodacomDash.getMessage()));
        series3.getData().add(new XYChart.Data("Money", vodacomDash.getMoney()));
        series3.getData().add(new XYChart.Data("Traiter", vodacomDash.getTraiter()));
        series3.getData().add(new XYChart.Data("En attente", vodacomDash.getEn_attente()));

        XYChart.Series series4 = new XYChart.Series();
        series4.getData().add(new XYChart.Data("All Message", (vodacomDash.getMessage() + airtelDash.getMessage() + orangeDash.getMessage())));

        XYChart.Series series5 = new XYChart.Series();
        series5.getData().add(new XYChart.Data("All Money", (vodacomDash.getMoney() + airtelDash.getMoney() + orangeDash.getMoney())));

        XYChart.Series series6 = new XYChart.Series();
        series6.getData().add(new XYChart.Data("All Traiter", (vodacomDash.getTraiter() + airtelDash.getTraiter() + orangeDash.getTraiter())));

        bc.getData().addAll(series1, series2, series3, series4, series5, series6);

        stck.getChildren().clear();
        stck.getChildren().add(bc);

    }

}
