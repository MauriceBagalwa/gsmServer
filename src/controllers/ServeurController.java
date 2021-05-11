/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.octicons.OctIconView;
import static helpers.Activation.*;
import models.Session;
import static helpers.SharedPreference.Preferences;
import static helpers.Traitement.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.CserviceInfo;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ServeurController implements Initializable {

    @FXML
    private OctIconView etatAirtel;
    @FXML
    private Text etatConnect_Airtel;
    @FXML
    private Text modelAirtel;
    @FXML
    private Text gsmAirtel;
    @FXML
    private Text manufaturerAirtel;
    @FXML
    private Text serialNoAirtel;
    @FXML
    private Label signalAirtel;
    @FXML
    private JFXToggleButton toogleAirtel;
    @FXML
    private Label demarerAirtel;
    @FXML
    private ImageView imageAirtel;
    @FXML
    private Text labError;
    @FXML
    private Text txtError;
    @FXML
    private OctIconView etatOrange;
    @FXML
    private Text connectOrange;
    @FXML
    private ImageView imageOrange;
    @FXML
    private Text modelOrange;
    @FXML
    private Text gsmOrange;
    @FXML
    private Text manufaturerOrange;
    @FXML
    private Text serialNoOrange;
    @FXML
    private Label signalOrange;
    @FXML
    private JFXToggleButton toogleOrange;
    @FXML
    private Label demarerOrange;
    @FXML
    private Text labErrorOrange;
    @FXML
    private Text txtErrorOrange;
    @FXML
    private OctIconView etatVoda;
    @FXML
    private Text ConnectVoda;
    @FXML
    private ImageView imageVoda;
    @FXML
    private Text modelVoda;
    @FXML
    private Text gsmVoda;
    @FXML
    private Text manufaturerVoda;
    @FXML
    private Text serialVoda;
    @FXML
    private Label signalVoda;
    @FXML
    private JFXToggleButton toogleVoda;
    @FXML
    private Label demarerVoda;
    @FXML
    private Text labErrorVoda;
    @FXML
    private Text txtErrorVoda;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image = new Image(getClass().getResource("/image/i6.gif").toExternalForm());
        if (airtelSession != null && airtelSession.isConncet()) {
            etatAirtel.setFill(Color.web("#4b62c1"));
            initAirtelGSM_INFo(airtelSession);
            toogleAirtel.setSelected(true);
            toogleAirtel.setDisable(true);
            etatConnect_Airtel.setText("Connecté");
            if (Demarer_airtel != null && Demarer_airtel.isDemarer()) {
                demarerAirtel.setDisable(true);
            }
        }
        if (vodacomSession != null && vodacomSession.isConncet()) {
            etatVoda.setFill(Color.web("#4b62c1"));
            initVodacomGSM_INFo(vodacomSession);
            toogleVoda.setSelected(true);
            toogleVoda.setDisable(true);
            ConnectVoda.setText("Connecté");
            if (Demarer_vodacom != null && Demarer_vodacom.isDemarer()) {
                demarerVoda.setDisable(true);
            }
        }
        if (orangeSession != null && orangeSession.isConncet()) {
            etatOrange.setFill(Color.web("#4b62c1"));
            initOrange_INFo(orangeSession);
            toogleOrange.setSelected(true);
            toogleOrange.setDisable(true);
            connectOrange.setText("Connecté");
            if (Demarer_orange != null && Demarer_orange.isDemarer()) {
                demarerOrange.setDisable(true);
            } else {
                System.out.println("je ne suis avec vous...");
            }
        }
    }
    /**
     * @varianles.
     */
    Image image;
    CserviceInfo info_Airetl;
    CserviceInfo info_Orange;
    CserviceInfo info_Vodacom;

    public static String messageError;
    public static String messageErrorVoda;
    public static String messageErrorOrange;
    static LinkedList ListMessage_Airtel = new LinkedList();
    static LinkedList ListMessage_Orange = new LinkedList();
    static LinkedList ListMessage_Vodacom = new LinkedList();
    public String limiChar = "API of version 8.0.171 by JavaFX runtime of version API of version 8.0.171";
    Task<Void> actionEtat;

    /**
     * @Methodes d'initialisation des labels Erreurs.
     */
    void reniText(Text t1, Text t2) {
        t1.setText("");
        t2.setText("");
    }

    void reniText(Text t1, Text t2, String mssg) {
        t1.setText("Erreur :");
        t2.setText(" " + mssg);
    }

    /**
     * @Methodes d'initialisation du serveur Airtel.
     */
    void initAirtelGSM_INFo(Session data) {
        manufaturerAirtel.setText(data.getManufacturer());
        modelAirtel.setText(data.getModel());
        serialNoAirtel.setText(data.getSerialNo());
        gsmAirtel.setText(data.getSIMIMSI());
        signalAirtel.setText(data.getSignallevel());
    }

    /**
     * @Methodes d'initialisation du serveur Vodacom.
     */
    void initVodacomGSM_INFo(Session data) {
        manufaturerVoda.setText(data.getManufacturer());
        modelVoda.setText(data.getModel());
        serialVoda.setText(data.getSerialNo());
        gsmVoda.setText(data.getSIMIMSI());
        signalVoda.setText(data.getSignallevel());
    }

    /**
     * @Methodes d'initialisation du serveur Vodacom.
     */
    void initOrange_INFo(Session data) {
        manufaturerOrange.setText(data.getManufacturer());
        modelOrange.setText(data.getModel());
        serialNoOrange.setText(data.getSerialNo());
        gsmOrange.setText(data.getSIMIMSI());
        signalOrange.setText(data.getSignallevel());
    }

    @FXML
    private void activation(ActionEvent event) {
        Task<Void> action = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    imageAirtel.setImage(image);
                    toogleAirtel.setDisable(true);
                    reniText(labError, txtError);
                    info_Airetl = new CserviceInfo(Preferences().getPort_Airtel(), Preferences()
                            .getBitTemporisation_Airtel(), Preferences().getModem_Airtel(), "",
                            Preferences().getCentreMessagerie_Airtel(), Preferences().getPinSIM_Airtel());
                    Thread.sleep(1000);
                    if (activedAirtel(info_Airetl)) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                imageAirtel.setImage(null);
                                toogleAirtel.setDisable(true);
                                etatAirtel.setFill(Color.web("#4b62c1"));
                                etatConnect_Airtel.setText("Connecté");
                                initAirtelGSM_INFo(airtelSession);
                            }
                        });

                    } else {
                        imageAirtel.setImage(null);
                        toogleAirtel.setSelected(false);
                        toogleAirtel.setDisable(false);
                        etatAirtel.setFill(Color.web("#bdbcbf"));
                        if (messageError.length() > limiChar.length()) {
                            messageError = messageError.substring(0, limiChar.length());
                        }
                        txtError.setText(messageError);
                        reniText(labError, txtError, messageError);
                        Thread.sleep(2500);
                        reniText(labError, txtError);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(action).start();
    }

    @FXML
    private void demarerSAirtel(MouseEvent event) {
        try {
            if (airtelSession.isConncet()) {
                airtelBool = true;
                AirtelInaction(airtelService, ListMessage_Airtel);
                demarerAirtel.setDisable(true);
            } else {
                reniText(labError, txtError, "Serveur non conncté");
            }
        } catch (Exception e) {
            reniText(labError, txtError, "Serveur non conncté");
        }
    }

    @FXML
    private void activationVoda(ActionEvent event) {
        Task<Void> action = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    imageVoda.setImage(image);
                    toogleVoda.setDisable(true);
                    reniText(labErrorVoda, txtErrorVoda);
                    info_Vodacom = new CserviceInfo(Preferences().getPort_Vodacom(), Preferences()
                            .getBitTemporisation_Vodacom(), Preferences().getModem_Vodacom(), "",
                            Preferences().getCentreMessagerie_Vodacom(), Preferences().getPinSIM_Vodacom());
                    Thread.sleep(1000);
                    if (activedVodacom(info_Vodacom)) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                imageVoda.setImage(null);
                                toogleVoda.setDisable(true);
                                etatVoda.setFill(Color.web("#4b62c1"));
                                ConnectVoda.setText("Connecté");
                                initVodacomGSM_INFo(vodacomSession);
                            }
                        });
                    } else {
                        imageVoda.setImage(null);
                        toogleVoda.setSelected(false);
                        toogleVoda.setDisable(false);
                        etatVoda.setFill(Color.web("#bdbcbf"));
                        labErrorVoda.setText("Erreur:");
                        if (messageErrorVoda.length() > limiChar.length()) {
                            messageErrorVoda = messageErrorVoda.substring(0, limiChar.length());
                        }
                        txtErrorVoda.setText(messageErrorVoda);
                        Thread.sleep(2500);
                        reniText(labErrorVoda, txtErrorVoda);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(action).start();
    }

    private void demarerSVoda(MouseEvent event) {
        try {
            if (vodacomSession.isConncet()) {
                VodacomInaction(voadacomService, ListMessage_Vodacom);
                demarerVoda.setDisable(true);
            } else {
                reniText(labErrorVoda, txtErrorVoda, "Serveur non conncté");
            }
        } catch (Exception e) {
            reniText(labErrorVoda, txtErrorVoda, "Serveur non conncté");
        }
    }

    @FXML
    private void activationOrange(ActionEvent event) {
        Task<Void> action = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    imageOrange.setImage(image);
                    toogleOrange.setDisable(true);
                    reniText(labErrorOrange, txtErrorOrange);
                    info_Orange = new CserviceInfo(Preferences().getPort_Orange(), Preferences()
                            .getBitTemporisation_Orange(), Preferences().getModem_Orange(), "",
                            Preferences().getCentreMessagerie_Orange(), Preferences().getPinSIM_Orange());
                    System.out.println("\nData " + info_Orange.toString());
                    Thread.sleep(1000);
                    System.out.println("voila #2");
                    if (activedOrange(info_Orange)) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                imageOrange.setImage(null);
                                toogleOrange.setDisable(true);
                                etatOrange.setFill(Color.web("#4b62c1"));
                                connectOrange.setText("Connecté");
                                initOrange_INFo(orangeSession);
                            }
                        });
                    } else {
                        imageOrange.setImage(null);
                        toogleOrange.setSelected(false);
                        toogleOrange.setDisable(false);
                        etatOrange.setFill(Color.web("#bdbcbf"));
                        labErrorOrange.setText("Erreur:");
                        if (messageErrorOrange.length() > limiChar.length()) {
                            messageErrorOrange = messageErrorOrange.substring(0, limiChar.length());
                        }
                        txtErrorOrange.setText(messageErrorOrange);
                        Thread.sleep(2500);
                        reniText(labErrorOrange, txtErrorOrange);
                    }
                } catch (InterruptedException e) {
                    reniText(labErrorOrange, txtErrorOrange, e.getMessage());
                } catch (Exception e) {
                    reniText(labErrorOrange, txtErrorOrange, e.getMessage());
                }
                return null;
            }
        };
        new Thread(action).start();
    }

    @FXML
    private void demarerSOrange(MouseEvent event) {
        try {
            if (orangeSession.isConncet()) {
                OrangeIn_action(OrangeService, ListMessage_Orange);
                demarerOrange.setDisable(true);
            }
        } catch (Exception e) {
            reniText(labErrorOrange, txtErrorOrange, "Serveur non conncté");
        }
    }
}
