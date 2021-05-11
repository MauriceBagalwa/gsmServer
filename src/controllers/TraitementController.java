/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static bd.Operations.Operation;
import com.jfoenix.controls.JFXButton;
import contro_traitement.Server_vueController;
import static controllers.MainController._stckaMain;
import static controllers.MainController.isDo;
import static controllers.MainController.taskloadServer;
import static helpers.Events.BouncaAnnimet;
import static helpers.Events.Events_Instance;
import static helpers.Events.dialog;
import static helpers.Loading.Loading;
import static helpers.rooters.EN_ATTENTE;
import static helpers.rooters.MESSAGE_RECEV;
import static helpers.rooters.SQL_ALLMESSAGE;
import static helpers.rooters.SQL_MESSAGES_OFDAY;
import static helpers.rooters.SQL_ALL_MESSAGE;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static models.Count.Count;
import models.Message;
import models.Count;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class TraitementController implements Initializable {

    @FXML
    private VBox vbx;
    private Label total;
    @FXML
    private Label sizeServer;
    @FXML
    private StackPane contentTraitement;
    @FXML
    private AnchorPane pan111;
    @FXML
    private Label airtel_;
    @FXML
    private Label orange_;
    @FXML
    private Label vodacom_;
    @FXML
    private Label signal11111;
    @FXML
    private VBox vbxMssg;
    @FXML
    private Label countMessage1111;
    @FXML
    private Label countMessage11;
    @FXML
    private Label countMessage;
    @FXML
    private Label countMessage21;
    @FXML
    private Label countMessage211;
    @FXML
    private Label countMessage2111;
    @FXML
    private Label countMessage2;
    @FXML
    private TextField searchtxt;
    @FXML
    private AnchorPane pan1111;
    @FXML
    private Label signal111111;
    @FXML
    private Label txtTotal_hors;
    @FXML
    private Label sizeServer11;
    @FXML
    private Label sizeServer1;
    @FXML
    private Label sizeServer12;
    @FXML
    private JFXButton Aleft;
    @FXML
    private JFXButton Aright;
    @FXML
    private Label Aindex;
    @FXML
    private Label allmssg;
    @FXML
    private Label refreshBt;
    @FXML
    private AnchorPane P_today;
    @FXML
    private AnchorPane P_all;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            countMessage_ = countMessage;
            sizeServer_ = sizeServer;
            Aindex_ = Aindex;
            hors_ = txtTotal_hors;
            vbxMssg_ = vbxMssg;
            vbx_ = vbx;
            ext_Airtel = airtel_;
            ext_Orange = orange_;
            ext_Vodacom = vodacom_;

            children = 1;
            offset = 0;
            limit = 9;
            limitI = limit;
            indexRefresh = 0;
            Loading().initServerActif(0, 0);
            LoadDataEn_attente();
        } catch (Exception ex) {
            Logger.getLogger(TraitementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @Variables
     */
    public static VBox vbx_, vbxMssg_;
    public static Label ext_Airtel, ext_Orange, ext_Vodacom, ext_countMessage;
    public static Label countMessage_, hors_, Aindex_, sizeServer_;
    public static int offset = 0, limit = 0, limitI = 0, children, bool = 0, indexRefresh;
    public static int sizeTraitementOrange = 0, sizeTraitement = 0, sizeMssg, sizeOfdata;
    public static List<Message> messages = new ArrayList<>();

    void initAfther(Label... lab) {
        lab[0].setText(Events_Instance().formatNumber(Count().getAirtel()));
        BouncaAnnimet(lab[0]);
        lab[1].setText(Events_Instance().formatNumber(Count().getOrange()));
        BouncaAnnimet(lab[1]);
        lab[2].setText(Events_Instance().formatNumber(Count().getVodacom()));
        BouncaAnnimet(lab[2]);
    }

    void inidata() throws IOException {
        Loading().ScrollOperation(vbxMssg, messages, offset, limit, MESSAGE_RECEV);
        sizeOfdata = messages.size();
        countMessage.setText(Events_Instance().formatNumber(sizeOfdata));
        initAfther(airtel_, orange_, vodacom_);
        Aindex_.setText((offset + 1) + " à " + limit + " de " + sizeOfdata);
        hors_.setText(Events_Instance().formatNumber(messages.size() - Count().getSomme()));
    }

    void inivalue() {
        try {
            Count c = new Count(0, 0, 0);
            Count().setAirtel(Operation().getCount("Airtel"));
            Count().setOrange(Operation().getCount("Orange"));
            Count().setVodacom(Operation().getCount("Vodacom"));
            Count().setAll(Operation().Other_getCount(SQL_ALLMESSAGE));

        } catch (Exception ex) {
            Logger.getLogger(Server_vueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dialog.close();
        _stckaMain.setDisable(false);
    }

    void LoadDataEn_attente() {
        taskloadServer = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!isDo) {
                                Events_Instance().Dialog(EN_ATTENTE, 165, 91);
                                _stckaMain.setDisable(true);
                                Thread.sleep(500);
                                isDo = true;
                                inivalue();
                            }
                            messages = Operation().messageOfday(SQL_MESSAGES_OFDAY);
                            inidata();
                        } catch (Exception ex) {
                            Logger.getLogger(Server_vueController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return null;
            }
        };
        new Thread(taskloadServer).start();
    }

    @FXML
    private void loadDetail(MouseEvent event) {
    }
    String sql;

    @FXML
    private void searchMessage(KeyEvent event) {
        try {
            sql = "SELECT `dateReception`,`numero`, `reseau`, `message` from message WHERE `numero` like '%" + searchtxt.getText() + "%' or reseau like '%" + searchtxt.getText()
                    + "%' or dateReception like '%" + searchtxt.getText() + "%'";
            messages = Operation().messageOfday(sql);
            inidata();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void left(MouseEvent event) throws IOException {
        if (offset > 0) {
            Aright.setDisable(false);
            offset = offset - limitI;
            limit = limit - limitI;
            Loading().ScrollOperation(vbxMssg, messages, offset, limit, MESSAGE_RECEV);
            if (offset == 0) {
                Aleft.setDisable(true);
            }
        }
        Aindex_.setText((offset + 1) + " à " + limit + " de " + sizeOfdata);
    }

    @FXML
    private void right(MouseEvent event) throws IOException {
        if (limit < sizeOfdata) {
            Aleft.setDisable(false);
            offset = offset + limitI;
            limit = limit + limitI;
            Loading().ScrollOperation(vbxMssg, messages, offset, limit, MESSAGE_RECEV);
            Aindex_.setText((offset + 1) + " à " + limit + " de " + sizeOfdata);
            if (limit >= sizeOfdata) {
                Aright.setDisable(true);
            }
        }

    }

    @FXML
    private void getAllmssg(MouseEvent event) throws IOException, Exception {
        P_all.setStyle("-fx-background-color:fff;");
        P_today.setStyle("-fx-background-color:transparent;");
        messages = Operation().messageOfday(SQL_ALL_MESSAGE);
        inidata();
        indexRefresh = 1;
    }

    @FXML
    private void mssgOfDay(MouseEvent event) throws Exception {
        P_today.setStyle("-fx-background-color:fff;");
        P_all.setStyle("-fx-background-color:transparent;");
        messages = Operation().messageOfday(SQL_MESSAGES_OFDAY);
        inidata();
        indexRefresh = 0;
    }

    @FXML
    private void refresh(MouseEvent event) throws Exception {
        System.out.println("IndexRefresh: " + indexRefresh);
        if (indexRefresh == 0) {
            messages = Operation().messageOfday(SQL_MESSAGES_OFDAY);
            inidata();
            indexRefresh = 1;
        } else {
            messages = Operation().messageOfday(SQL_ALL_MESSAGE);
            inidata();
        }
    }
}
