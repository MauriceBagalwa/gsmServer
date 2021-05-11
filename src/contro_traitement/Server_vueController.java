/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import animatefx.animation.FadeIn;
import static bd.Operations.Operation;
import static controllers.MainController.ListDetailserver;
import static controllers.MainController.isDo;
import static controllers.MainController._stckaMain;
import static controllers.MainController.getMessages;
import static controllers.MainController.taskloadServer;
import helpers.Events;
import static helpers.Events.BouncaAnnimet;
import static helpers.Events.Events_Instance;
import static helpers.Events.dialog;
import static helpers.Loading.Loading;
import static helpers.rooters.EN_ATTENTE;
import static helpers.rooters.SQL_ALLMESSAGE;
import static helpers.rooters.SQL_EN_ATTENTE;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Count;
import static models.Count.Count;
import models.Message;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class Server_vueController implements Initializable {

    @FXML
    private Label signal1;
    @FXML
    private Label signal11;
    @FXML
    private Label signal111;
    @FXML
    private AnchorPane pan11;
    @FXML
    private AnchorPane pan1111;
    @FXML
    private AnchorPane pan111;
    @FXML
    private Label txtTotal;
    @FXML
    private Label signal11111;
    @FXML
    private Label signal111111;
    @FXML
    private Label airtel_;
    @FXML
    private Label orange_;
    @FXML
    private Label vodacom_;
    @FXML
    private Label countMessage;
    private Text date1;
    private Text message1;
    private Text date2;
    private Text message2;
    @FXML
    private Label en_attente;
    @FXML
    private HBox hbx;
    @FXML
    private ImageView image;
    @FXML
    private VBox hbxMssg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
//            ext_Airtel = airtel_;
//            ext_Orange = orange_;
//            ext_Vodacom = vodacom_;
//            ext_message1 = message1;
//            ext_message2 = message2;
//            ext_date1 = date1;
//            ext_date2 = date2;
//            ext_countMessage = countMessage;
            img = new Image(getClass().getResource("/image/j3.gif").toExternalForm());
            LoadDataEn_attente();
        } catch (Exception ex) {
            Logger.getLogger(Server_vueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getMssg() {
        if (getMessages.size() > 0) {
            formatMssg = getMessages.get(0);
//            ext_message1.setText(formatMssg.getMessage());
//            ext_date1.setText(formatMssg.getDate());
//            Events.FadeInAnnimation(message1);
//            if (getMessages.size() == 2) {
//                formatMssg = getMessages.get(1);
//                ext_message1.setText(formatMssg.getMessage());
//                ext_date1.setText(formatMssg.getDate());
//                formatMssg = getMessages.get(0);
//                ext_message2.setText(formatMssg.getMessage());
//                ext_date2.setText(formatMssg.getDate());
//            } else {
//                formatMssg = getMessages.get(0);
//                ext_message2.setText(formatMssg.getMessage());
//                ext_date2.setText(formatMssg.getDate());
//            }
        }

    }

//    public static Label ext_Airtel, ext_Orange, ext_Vodacom, ext_countMessage;
//    public static Text ext_message1, ext_message2, ext_date1, ext_date2;
    Message formatMssg;
    FadeIn fadeIn;
    Image img;

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

    void initAfther(Label... lab) {
        lab[0].setText(Events_Instance().formatNumber(Count().getAirtel()));
        BouncaAnnimet(lab[0]);
        lab[1].setText(Events_Instance().formatNumber(Count().getOrange()));
        BouncaAnnimet(lab[1]);
        lab[2].setText(Events_Instance().formatNumber(Count().getVodacom()));
        BouncaAnnimet(lab[2]);
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
                            if (ListDetailserver.size() > 0) {
                                image.setImage(img);
                            }
//                            getMssg();
//                            hbxMssg.getChildren().clear();
//                            hbxMssg.getChildren().add(Loading().getTable(Operation().messageOfday()));
               
                            countMessage.setText(Events_Instance().formatNumber(Count().getAll()));
                            initAfther(airtel_, orange_, vodacom_);
                            Thread.sleep(200);
//                            en_attente.setText(Events_Instance().formatNumberPlus(Loading().LoadHBX(hbx,
//                                    Operation().getMessage_En_Attente(SQL_EN_ATTENTE))));
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
    private void loadDetail(MouseEvent event) throws Exception {

    }
    List<Integer> valuesCount = new ArrayList();
    List<Integer> value = new ArrayList();

}
