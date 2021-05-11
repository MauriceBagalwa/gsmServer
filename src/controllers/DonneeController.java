/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static bd.Operations.Operation;
import com.jfoenix.controls.JFXButton;
import static contro_donnee.WithdateController.dateFlitre;
import static controllers.TraitementController.children;
import static controllers.TraitementController.limit;
import static controllers.TraitementController.limitI;
import static controllers.TraitementController.offset;
import static controllers.TraitementController.vbx_;
import helpers.Chart;
import helpers.Chart2;
import static helpers.Loading.Loading;
import static helpers.Variables.chartStck2_;
import static helpers.Variables.chartStck_;
import static helpers.Variables.ext_Aindex;
import static helpers.rooters.ALLMONEY;
import static helpers.rooters.FLITRE_ALL;
import static helpers.rooters.FLITRE_TODAY;
import static helpers.rooters.FLITRE_WITHDATE;
import static helpers.rooters.MONEY_OF_DAY;
import static helpers.rooters.MONEY_TRAITER_ALL;
import static helpers.rooters.MONEY_TRAITER_DEATILS;
import static helpers.rooters.MONEY_TRAITER_OF_DAY;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Detailsmoney;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class DonneeController implements Initializable {

    @FXML
    private AnchorPane pan111;
    @FXML
    private Label signal11111;
    @FXML
    private StackPane contentTraitement;
    @FXML
    private VBox vbx;
    @FXML
    private Label countMessage2;
    @FXML
    private Label signal1111111112;
    @FXML
    private AnchorPane pan11112;
    @FXML
    private Label signal1111111;
    @FXML
    private Label signal11111111;
    @FXML
    private Label signal111111111;
    @FXML
    private AnchorPane pan111122;
    @FXML
    private Label signal1111111113;
    @FXML
    private AnchorPane pan1111221;
    @FXML
    private Label signal1111111111;
    @FXML
    private Label signal11111111131;
    @FXML
    private AnchorPane pan1111;
    @FXML
    private AnchorPane pan11111;
    @FXML
    private StackPane stckPage;
    @FXML
    private AnchorPane pan111111;
    @FXML
    private Label up_;
    @FXML
    private Label down_;
    @FXML
    private Label filtre;
    @FXML
    private Label montant_airtel;
    @FXML
    private Label montant_Orange;
    @FXML
    private Label montant_Voda;
    @FXML
    private StackPane chartStck;
    @FXML
    private StackPane chartStck2;
    @FXML
    private Label countMessage1111;
    @FXML
    private TextField searchtxt;
    @FXML
    private JFXButton Aleft;
    @FXML
    private JFXButton Aright;
    @FXML
    private Label Aindex;
    @FXML
    private Label montant_Tot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            up_.setDisable(true);
            pageIndex = 0;
            ext_montant_airtel = montant_airtel;
            ext_montant_Orange = montant_Orange;
            ext_montant_Voda = montant_Voda;
            ext_montant_Tot = montant_Tot;
            ext_Aindex = Aindex;
            vbx_ = vbx;
            chartStck_ = chartStck;
            chartStck2_ = chartStck2;
            iniPage(pageIndex);

            children = 1;
            offset = 0;
            limit = 8;
            limitI = limit;
            index = 1;
            getData();
        } catch (Exception ex) {
            Logger.getLogger(DonneeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Variables
     */
    Detailsmoney detail;
    public static DonneeController donneeI;
    public static int pageIndex;
    Node node;
    public static Label ext_montant_airtel, ext_montant_Orange, ext_montant_Voda, ext_montant_Tot;
    String page[] = {FLITRE_TODAY, FLITRE_ALL, FLITRE_WITHDATE};
    public static List<Money> allDetailsMoney = new ArrayList<>();
    public static String sql = MONEY_OF_DAY;
    public static String sql2 = MONEY_TRAITER_OF_DAY, sql3;
    public static String date_ = "";
    public static float aiterlM, orangeM, vodaM;
    public static int index;
   

    /**
     * Methides & Fonctions
     *
     * @return
     */
    /*@1
     */
    public static DonneeController DonnerInstance() {
        if (donneeI == null) {
            donneeI = new DonneeController();
        }
        return donneeI;

    }

    /*@2
     */
    void iniPage(int index) throws IOException {
        stckPage.getChildren().clear();
        node = FXMLLoader.load(getClass().getResource(page[index]));
        stckPage.getChildren().add(node);
    }

    /*@3
     */
    public static void getData() throws Exception {
        switch (pageIndex) {
            case 0:
                sql = MONEY_OF_DAY;
                sql2 = MONEY_TRAITER_OF_DAY;
                index = 1;
                break;
            case 1:
                sql = ALLMONEY;
                sql2 = MONEY_TRAITER_ALL;
                index = 0;
                break;
            case 2:
                sql = "SELECT reseau,sum(`montant`) FROM `money` WHERE"
                        + " cast(`date` as date)='" + dateFlitre.getValue() + "' GROUP by reseau";
                sql2 = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`,  `message`, `date`, `etat`,`codereference` FROM `money` where cast(`date` as date)='" + dateFlitre.getValue() + "' order by `date` desc";
                date_ = "" + dateFlitre.getValue();
                index = 2;
                break;
        }
        ext_montant_airtel.setText("0.0");
        ext_montant_Orange.setText("0.0");
        ext_montant_Voda.setText("0.0");
        ext_montant_Tot.setText("0.0");
        aiterlM = 0;
        orangeM = 0;
        vodaM = 0;
        for (Money money : Operation().detailsMoney(sql)) {
            switch (money.getReseau().toLowerCase()) {
                case "airtel":
                    ext_montant_airtel.setText("" + money.getMontant());
                    aiterlM = money.getMontant();
                    break;
                case "orange":
                    ext_montant_Orange.setText("" + money.getMontant());
                    orangeM = money.getMontant();
                    break;
                case "vodacom":
                    ext_montant_Voda.setText("" + money.getMontant());
                    vodaM = money.getMontant();
                    break;
                default:
                    break;
            }
        }
        ext_montant_Tot.setText("" + (aiterlM + orangeM + vodaM));
        DonnerInstance().initData(sql2);
    }

    /*Evennement SceneBuilder
     */
    @FXML
    private void loadDetail(MouseEvent event) {
    }

    @FXML
    private void pageUp(MouseEvent event) throws IOException {
        down_.setDisable(false);
        pageIndex--;
        iniPage(pageIndex);
        if (pageIndex == 0) {
            up_.setDisable(true);
        }
    }

    @FXML
    private void pagedown(MouseEvent event) throws IOException {
        up_.setDisable(false);
        pageIndex++;
        iniPage(pageIndex);
        if (pageIndex == (page.length - 1)) {
            down_.setDisable(true);
        }
    }
    Chart chart;
    Chart2 chart2;
    public static int size;

    @FXML
    private void flitreData(MouseEvent event) throws Exception {
        getData();
    }

    @FXML
    private void left(MouseEvent event) throws IOException {
        if (offset > 0) {
            Aright.setDisable(false);
            offset = offset - limitI;
            limit = limit - limitI;
            Loading().ScrollOperation(vbx, allDetailsMoney, offset, limit, MONEY_TRAITER_DEATILS);
            if (offset == 0) {
                Aleft.setDisable(true);
            }
        }
        Aindex.setText((offset + 1) + " à " + limit + " de " + size);
    }

    @FXML
    private void right(MouseEvent event) throws IOException {
        if (limit < size) {
            Aleft.setDisable(false);
            offset = offset + limitI;
            limit = limit + limitI;
            Loading().ScrollOperation(vbx, allDetailsMoney, offset, limit, MONEY_TRAITER_DEATILS);
            Aindex.setText((offset + 1) + " à " + limit + " de " + size);
            if (limit >= size) {
                Aright.setDisable(true);
            }
        } 

    }

    @FXML
    private void searchTransaction(KeyEvent event) throws Exception {
        switch (pageIndex) {
            case 0:
                sql = MONEY_OF_DAY;
                sql3 = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`,  `message`, `date`, `etat`,`codereference` FROM `money` WHERE cast(`date` as date)=curdate() and `reference` like '%" + searchtxt.getText() + "%' or codereference like '%" + searchtxt.getText()
                        + "%' or `date` like '%" + searchtxt.getText() + "%' or montant like '%" + searchtxt.getText() + "%' order by `date` desc";
                index = 1;
                break;
            case 1:
                sql = ALLMONEY;
                sql3 = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`,  `message`, `date`, `etat`,`codereference` FROM `money` WHERE `reference` like '%" + searchtxt.getText() + "%' or codereference like '%" + searchtxt.getText()
                        + "%' or `date` like '%" + searchtxt.getText() + "%' or montant like '%" + searchtxt.getText() + "%' order by `date` desc";
                break;
            case 2:
                sql = "SELECT reseau,sum(`montant`) FROM `money` WHERE"
                        + " cast(`date` as date)='" + dateFlitre.getValue() + "' GROUP by reseau";
                sql3 = "select `reference`, `montant`, `numero`,`solde`, `devise`,`reseau`,  `message`, `date`, `etat`,`codereference` FROM `money` WHERE cast(`date` as date)='" + dateFlitre.getValue() + "' and `reference` like '%" + searchtxt.getText() + "%' or codereference like '%" + searchtxt.getText()
                        + "%' or `date` like '%" + searchtxt.getText() + "%' or montant like '%" + searchtxt.getText() + "%' order by `date` desc";
                break;
        }

        System.out.println("Request: " + sql3);
//        getData();
        initData(sql3);
    }

    public void initData(String sql) throws Exception {
        allDetailsMoney = Operation().AllMoney(sql);
        size = allDetailsMoney.size();
        Loading().ScrollOperation(vbx_, allDetailsMoney, offset, limit, MONEY_TRAITER_DEATILS);
        ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
        chart = new Chart();
        detail = new Detailsmoney(aiterlM, orangeM, vodaM);
        chart.initChart(chartStck_, detail);

        chart2 = new Chart2();
        chart2.initChart2(chartStck2_, Operation().getcharData("" + date_, index));
    }
}
