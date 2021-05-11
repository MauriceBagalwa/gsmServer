/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static apiActivity.TransactionActivity.TransActivity;
import static bd.Operations.Operation;
import static helpers.Events.Events_Instance;
import static helpers.rooters.DASHBORD;
import static helpers.rooters.DATA;
import static helpers.rooters.PARAMETRES_ROUTER;
import static helpers.rooters.RETRAIT;
import static helpers.rooters.SERVEUR;
import static helpers.rooters.SQL_EN_ATTENTE;
import static helpers.rooters.SQL_FOR_CODE;
import static helpers.rooters.TRAITEMENT;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Transation;
import models.DetailServer;
import models.Message;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane pan_dash;
    @FXML
    private Label lab_dashBord;
    @FXML
    private AnchorPane pan_reception;
    @FXML
    private Label lab_Reception;
    @FXML
    private AnchorPane pan_serveur;
    @FXML
    private Label lab_Server;
    @FXML
    private AnchorPane pan_parametre;
    @FXML
    private Label lab_Parametre;
    @FXML
    private StackPane mainContent;
    @FXML
    private StackPane stackPan;
    @FXML
    private Label lun;
    @FXML
    private Label mar;
    @FXML
    private Label merc;
    @FXML
    private Label jeu;
    @FXML
    private Label vend;
    @FXML
    private Label sam;
    @FXML
    private Label dim;
    @FXML
    private AnchorPane pan_donnee;
    @FXML
    private Label lab_donnees;
    @FXML
    private AnchorPane pan_retrait;
    @FXML
    private Label lab_retarit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            _stckaMain = stackPan;
            _mainContent = mainContent;
            isParametre = "other";
            ext_lun = lun;
            ext_mar = mar;
            ext_merc = merc;
            ext_jeu = jeu;
            ext_vend = vend;
            ext_sam = sam;
            ext_dim = dim;
            Events_Instance().colorPan("main", pan_dash, pan_donnee, pan_retrait, pan_reception, pan_serveur, pan_parametre);
            Events_Instance().setContent(mainContent, getClass().getResource(DASHBORD));
            HarrowTrans();
//            getCode();
//            Events.initCountMssg_of_Day(day, ext_lun, ext_mar, ext_merc, ext_jeu, ext_vend, ext_sam, ext_dim);
        } catch (Exception e) {
//            System.out.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Differentes Varianles.
     */
    public static MainController mainController;
    public static Label ext_lun, ext_mar, ext_merc, ext_jeu, ext_vend, ext_sam, ext_dim;
    public static StackPane _stckaMain, _mainContent;
    public Pane[] pan_Menu = {pan_dash, pan_reception, pan_serveur, pan_parametre};
    public static String isParametre;
    public static List<DetailServer> ListDetailserver = new ArrayList<>();
    public static boolean isDo = false, isactive;
    public static List<Message> getMessages = new ArrayList<>();
    public static int incrementMessage;
    public static Task<Integer> taskloadServer, taskdash;
    public static Task<Void> harrow, codeRef;
    public static List<Money> listMonney = new ArrayList<>();
    public static List<Transation> transactions = new ArrayList<>();
    public static List<Transation> listFor_Code = new ArrayList<>();
    public static int indexTrans, indexEmpty;
    public static int indexHarrow;
    public static boolean occuper = false, initDash = false, code_tentative = false;
    Calendar cal = new GregorianCalendar();
    int day = cal.get(Calendar.DAY_OF_MONTH) - 2;

    /**
     * Differentes Fonctin et Methodes.
     *
     * @return
     */
    public static MainController MainController() {
        if (mainController == null) {
            mainController = new MainController();
        }
        return mainController;
    }

    void HarrowTrans() {
        harrow = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Thread.sleep(400);
                    transactions.clear();
                    indexHarrow = 0;
                    indexTrans = 0;
                    transactions = Operation().Transactions(SQL_EN_ATTENTE);
                    System.out.println("#En attente... de " + transactions.size());
                    for (Transation trans : transactions) {
                        System.out.println("-#- Occuper: " + occuper);
                        if (!occuper) {
                            indexHarrow = indexTrans;
                            TransActivity().Transaction_Money(trans);

                            Thread.sleep(800);
                        }
                    }

                    Thread.sleep(970);
                }
            }
        };
        new Thread(harrow).start();
    }

    void getCode() {
        codeRef = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Thread.sleep(400);
                    listFor_Code.clear();
                    listFor_Code = Operation().Transactions(SQL_FOR_CODE);
                    for (Transation trans : listFor_Code) {
                        System.out.println("-#2- Tentative occuper( " + code_tentative + " )");
                        if (!code_tentative) {
                            TransActivity().FindReference(trans.getReference());
                            Thread.sleep(800);
                        }
                    }
                }
            }
        };
        new Thread(codeRef).start();
    }

    @FXML

    private void call_Dashbord(MouseEvent event) {
        try {
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_dashBord, lab_donnees, lab_retarit, lab_Reception, lab_Server, lab_Parametre);
            Events_Instance().colorPan("main", pan_dash, pan_reception, pan_donnee, pan_retrait, pan_serveur, pan_parametre);
            Events_Instance().setContent(mainContent, getClass().getResource(DASHBORD));
            isParametre = "dashBord";
            if (isactive) {
                taskloadServer.cancel();
                isactive = false;
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void call_Reception(MouseEvent event) {
        try {
            isactive = true;
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_Reception, lab_dashBord, lab_donnees, lab_retarit, lab_Server, lab_Parametre);
            Events_Instance().colorPan("main", pan_reception, pan_dash, pan_retrait, pan_serveur, pan_parametre, pan_donnee);
            Events_Instance().setContent(mainContent, getClass().getResource(TRAITEMENT));
            isParametre = "traitement";
        } catch (Exception e) {

        }
    }

    @FXML
    private void call_Serveur(MouseEvent event) {
        try {
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_Server, lab_dashBord, lab_retarit, lab_donnees, lab_Reception, lab_Parametre);
            Events_Instance().colorPan("main", pan_serveur, pan_reception, pan_dash, pan_retrait, pan_parametre, pan_donnee);
            Events_Instance().setContent(mainContent, getClass().getResource(SERVEUR));
            isParametre = "other";
            if (isactive) {
                taskloadServer.cancel();
                isactive = false;
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void call_Parametre(MouseEvent event) {
        try {
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_Parametre, lab_dashBord, lab_donnees, lab_retarit, lab_Reception, lab_Server);
            Events_Instance().colorPan("main", pan_parametre, pan_serveur, pan_retrait, pan_reception, pan_dash, pan_donnee);
            Events_Instance().setContent(mainContent, getClass().getResource(PARAMETRES_ROUTER));
            isParametre = "parametre";
            if (isactive) {
                taskloadServer.cancel();
                isactive = false;
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void call_Donnee(MouseEvent event) {
        try {
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_donnees, lab_Server, lab_dashBord, lab_retarit, lab_Reception, lab_Parametre);
            Events_Instance().colorPan("main", pan_donnee, pan_serveur, pan_retrait, pan_reception, pan_dash, pan_parametre);
            Events_Instance().setContent(mainContent, getClass().getResource(DATA));
            isParametre = "donnees";
            if (isactive) {
                taskloadServer.cancel();
                isactive = false;
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void call_Retrait(MouseEvent event) {
        try {
            System.out.println("#Retrait");
            Events_Instance().seleteLab("#a1a1a1", "fff", lab_retarit, lab_donnees, lab_Server, lab_dashBord, lab_Reception, lab_Parametre);
            Events_Instance().colorPan("main", pan_retrait, pan_donnee, pan_serveur, pan_reception, pan_dash, pan_parametre);
            Events_Instance().setContent(mainContent, getClass().getResource(RETRAIT));
            isParametre = "retrait";
            if (isactive) {
                taskloadServer.cancel();
                isactive = false;
            }
        } catch (Exception e) {

        }
    }

}
