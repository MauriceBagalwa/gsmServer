/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static controllers.RetraitController.size1;
import static controllers.RetraitController.size2;
import static helpers.Events.Events_Instance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author smartTicket
 */
public class PieChartSample {

    static PieChartSample pie;

    public static PieChartSample Piechartsample() {
        if (pie == null) {
            pie = new PieChartSample();
        }
        return pie;
    }

    PieChart chart;
    Label caption;

    public void OperationTovalide(StackPane stck, int actif, int passif) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Retrait en attente", actif),
                        new PieChart.Data("Retrait effectuer", passif));
        chart = new PieChart(pieChartData);
        chart.setLabelLineLength(10);
        chart.setLegendVisible(false);
        chart.setPrefSize(362, 193);

        caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        chart.getData().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        });

        stck.getChildren().clear();
        stck.getChildren().add(chart);
        size1.setText(Events_Instance().formatNumber(actif));
        size2.setText(Events_Instance().formatNumber(passif));
    }

}
