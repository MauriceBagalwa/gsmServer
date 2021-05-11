/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static bd.Operations.Operation;
import static bd.Operations.dashDetails;
import static controllers.MainController._stckaMain;
import static controllers.MainController.initDash;
import static controllers.MainController.taskdash;
import helpers.Dash;
import helpers.Events;
import static helpers.Events.Events_Instance;
import static helpers.Loading.Loading;
import static helpers.rooters.EN_ATTENTE;
import static helpers.rooters.SQL_MESSAGES_OFDAY;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.DashDetails;
import models.Message;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class DashbordController implements Initializable {

    @FXML
    private Label countMessage;
    @FXML
    private AnchorPane pan121121;
    @FXML
    private Label bM_airtel;
    @FXML
    private Label bT_airtel;
    @FXML
    private Label bA_airtel;
    @FXML
    private Label bM_orange;
    @FXML
    private Label bT_orange;
    @FXML
    private Label bA_orange;
    @FXML
    private Label bM_vodacom;
    @FXML
    private Label bT_vodacom;
    @FXML
    private Label bA_vodacom;
    @FXML
    private Label mssgAirtel;
    @FXML
    private Label traiterAirtel;
    @FXML
    private Label entAirtel;
    @FXML
    private Label mssgOrange;
    @FXML
    private Label traiterOrange;
    @FXML
    private Label entOrange;
    @FXML
    private Label mssgVodacom;
    @FXML
    private Label traiterVodacom;
    @FXML
    private Label entVodacom;
    @FXML
    private StackPane dashStck;
    @FXML
    private Text mss_;
    @FXML
    private Text money_;
    @FXML
    private Text traiter;
    @FXML
    private Text en_attente_;
    @FXML
    private Text mssgTop;
    @FXML
    private VBox lastmmssVBX;
    @FXML
    private Text mssg;
    @FXML
    private Text mssgTop1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initAllComponets();
            _stckaMain.setDisable(true);
            Events_Instance().Dialog(EN_ATTENTE, 185, 91);
            LoadDataDash();

        } catch (IOException ex) {
            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @Mes variables.
     */
    public static Label ex_bMairtel, ex_bTairtel, ex_bAairtel, ex_mssgAirtel, ex_traiterAirtel, ex_entAirtel;
    public static Label ex_bMorange, ex_bTorange, ex_bAorange, ex_mssgOrange, ex_traiterOrange, ex_entOrange;
    public static Label ex_bMvodacom, ex_bTvodacom, ex_bAvodacom, ex_mssgVodacom, ex_traiterVodacom, ex_entVodacom;
    public static DashDetails airtelDash, orangeDash, vodacomDash, top;
    public static StackPane dashStck_;
    public static VBox lastmmssVBX_;
    public static Text ext_mss, ext_money, ext_en_attente, ext_traiter, mssgTop_;
    public static List<Message> Lastmessage = new ArrayList<>();

    /**
     * @Init componets.
     */
    void initAllComponets() {
        dashStck_ = dashStck;
        /**
         * Airtel componet.
         */
        ex_bMairtel = bM_airtel;
        ex_bTairtel = bT_airtel;
        ex_bAairtel = bA_airtel;
        ex_mssgAirtel = mssgAirtel;
        ex_traiterAirtel = traiterAirtel;
        ex_entAirtel = entAirtel;
        /**
         * Orange componet.
         */
        ex_bMorange = bM_orange;
        ex_bTorange = bT_orange;
        ex_bAorange = bA_orange;
        ex_mssgOrange = mssgOrange;
        ex_traiterOrange = traiterOrange;
        ex_entOrange = entOrange;
        /**
         * Vodacom componet.
         */
        ex_bMvodacom = bM_vodacom;
        ex_bTvodacom = bT_vodacom;
        ex_bAvodacom = bA_vodacom;
        ex_mssgVodacom = mssgVodacom;
        ex_traiterVodacom = traiterVodacom;
        ex_entVodacom = entVodacom;
        mssgTop_ = mssgTop;
        /**
         * The top.
         */
        ext_mss = mss_;
        ext_money = money_;
        ext_en_attente = en_attente_;
        ext_traiter = traiter;

        lastmmssVBX_ = lastmmssVBX;
    }

    /**
     * Vodacom componet.
     */
    public static Dash dash_ = new Dash();

    public static void initDashBord() {
        try {
//            if (!initDash) {
            if (dashDetails != null) {
                System.out.println("Dash " + initDash);
                while (dashDetails == null) {

                }
                dashStck_.getChildren().clear();
                /**
                 * @1 initialisation des variables.
                 */
                airtelDash = dashDetails.get(0);
                orangeDash = dashDetails.get(1);
                vodacomDash = dashDetails.get(2);
            }
            /**
             * @2 Affichage de donnée.
             */
            ex_mssgAirtel.setText(Events_Instance().formatNumber(airtelDash.getMessage(), "Messages"));
            ex_traiterAirtel.setText(Events_Instance().formatNumber(airtelDash.getTraiter(), "Traiter"));
            ex_entAirtel.setText(Events_Instance().formatNumber(airtelDash.getEn_attente(), "En attente"));
            getChartLabel(airtelDash, ex_bMairtel, ex_bTairtel, ex_bAairtel);

            ex_mssgOrange.setText(Events_Instance().formatNumber(orangeDash.getMessage(), "Messages"));
            ex_traiterOrange.setText(Events_Instance().formatNumber(orangeDash.getTraiter(), "Traiter"));
            ex_entOrange.setText(Events_Instance().formatNumber(orangeDash.getEn_attente(), "En attente"));
            getChartLabel(orangeDash, ex_bMorange, ex_bTorange, ex_bAorange);

            ex_mssgVodacom.setText(Events_Instance().formatNumber(vodacomDash.getMessage(), "Messages"));
            ex_traiterVodacom.setText(Events_Instance().formatNumber(vodacomDash.getTraiter(), "Traiter"));
            ex_entVodacom.setText(Events_Instance().formatNumber(vodacomDash.getEn_attente(), "En attente"));
            getChartLabel(vodacomDash, ex_bMvodacom, ex_bTvodacom, ex_bAvodacom);

            Thread.sleep(1000);
            dash_.initChart2(dashStck_);
            theTop();
            Events.dialog.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dash
     * @param lab
     * @Methode qui calcule la valeur en %.
     */
    static int pMssg, pTraiter, pAttente;

    public static void getChartLabel(DashDetails dash, Label... lab) {

        pMssg = Pourcentage(dash.getMessage(), dash.getTotal());
        pTraiter = Pourcentage(dash.getTraiter(), dash.getTotal());
        pAttente = Pourcentage(dash.getEn_attente(), dash.getTotal());
        System.out.println(dash.getReseau() + "   Message: " + pMssg + "%" + "  Traiter: " + pTraiter + "%" + "  Attente: " + pAttente + "%\n");
        lab[0].setPrefWidth(PourcentageSizeLabel(pMssg, 237));
        lab[1].setPrefWidth(PourcentageSizeLabel(pTraiter, 237));
        lab[2].setPrefWidth(PourcentageSizeLabel(pAttente, 237));
    }

    static Integer Pourcentage(int nbr, int tot) {
        return nbr <= 0 ? 0 : ((nbr * 100) / tot) == 0 ? 1 : ((nbr * 100) / tot);
    }

    static Integer PourcentageSizeLabel(int pourcentage, int tot) {
        return pourcentage <= 0 ? 0 : (tot / 100) * pourcentage;
    }

    static void theTop() {
        top = null;
        if (airtelDash.getMoney() > orangeDash.getTraiter() && airtelDash.getTraiter() > vodacomDash.getTraiter()) {
            top = airtelDash;
        } else if (orangeDash.getMoney() > airtelDash.getTraiter() && orangeDash.getTraiter() > vodacomDash.getTraiter()) {
            top = orangeDash;
        } else if (vodacomDash.getMoney() > orangeDash.getTraiter() && vodacomDash.getTraiter() > airtelDash.getTraiter()) {
            top = vodacomDash;
        }
        if (top != null) {
            mssgTop_.setText("le reseau " + top.getReseau() + " est celui qui enregistre un grand trafique transaction depuis un certain temps");
            ext_mss.setText(Events_Instance().formatNumber(top.getMessage()));
            ext_traiter.setText(Events_Instance().formatNumber(top.getTraiter()));
            ext_money.setText(Events_Instance().formatNumber(top.getMoney()));
            ext_en_attente.setText(Events_Instance().formatNumber(top.getEn_attente()));
        }
    }

    void LoadDataDash() {
        taskdash = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            initDashBord();
                            Thread.sleep(400);
                            if (Lastmessage.size() <= 0) {
                                Lastmessage = Operation().LastmessageOfday(SQL_MESSAGES_OFDAY + " limit 3");
                            }
                            Loading().LoadVBX(lastmmssVBX_, Lastmessage);
                            _stckaMain.setDisable(false);
                        } catch (IOException | InterruptedException ex) {
                            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return null;
            }
        };
        new Thread(taskdash).start();
    }

    @FXML
    private void seeMore(MouseEvent event) {
        
    }
}
