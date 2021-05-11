/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static apiActivity.RetraitActivity.retraitInstence;
import com.jfoenix.controls.JFXButton;
import static contro_traitement.Waitloading_simpleController.indexWaitingSimple;
import static controllers.DonneeController.size;
import static controllers.MainController._stckaMain;
import static controllers.MainController.indexEmpty;
import static controllers.TraitementController.children;
import static controllers.TraitementController.limit;
import static controllers.TraitementController.limitI;
import static controllers.TraitementController.offset;
import static controllers.TraitementController.vbx_;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static helpers.Events.Events_Instance;
import static helpers.Loading.Loading;
import helpers.Variables;
import static helpers.Variables.ext_Aindex;
import static helpers.Variables.getVar_instance;
import static helpers.Variables.listretrait_json;
import static helpers.rooters.RETRAIT_LOAD;
import static helpers.rooters.WAITLOADING_SIMPLE;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Retrait;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class RetraitController implements Initializable {

    @FXML
    private Label montant_Tot;
    @FXML
    private AnchorPane pAgent;
    @FXML
    private TextField txtagent;
    @FXML
    private AnchorPane pMontant;
    @FXML
    private TextField txtmontant;
    @FXML
    private ImageView imageV;
    @FXML
    private JFXButton btaction;
    @FXML
    private AnchorPane output;
    @FXML
    private Label labIndex;
    @FXML
    private AnchorPane pPsswd;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane pNumber;
    @FXML
    private TextField number;
    @FXML
    private TextField operateur;
    @FXML
    private JFXButton btcancel;
    @FXML
    private StackPane contentTraitement;
    @FXML
    private VBox vbx;
    @FXML
    private TextField searchtxt;
    @FXML
    private JFXButton Aleft;
    @FXML
    private JFXButton Aright;
    @FXML
    private Label Aindex;
    @FXML
    private StackPane stck_pie;
    @FXML
    private AnchorPane pan_dash1;
    @FXML
    private AnchorPane pan_dash3;
    @FXML
    private AnchorPane pan_dash31;
    @FXML
    private Label attente_size;
    @FXML
    private Label retirer_size;
    @FXML
    private FontAwesomeIconView indicator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            _stckaMain.setDisable(true);
            indexWaitingSimple = 0;
            vbx_ = vbx;
            txtagent_ = txtagent;
            txtmontant_ = txtmontant;
            number_ = number;
            operateur_ = operateur;
            password_ = password;
            children = 1;
            offset = 0;
            limit = 5;
            limitI = limit;
            size1 = attente_size;
            size2 = retirer_size;
            labMontant = montant_Tot;
            xI = 0;
            Variables.chartStck_ = stck_pie;
            Variables.ext_Aindex = Aindex;
            initData();

        } catch (IOException ex) {
            Logger.getLogger(RetraitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Task<Void> waitLoad;
    public static TextField txtagent_, txtmontant_, number_, operateur_;
    public static PasswordField password_;
    public static Label size1, size2, labMontant;

    public static void initTextF() {
        txtagent_.setText(null);
        txtmontant_.setText(null);
        number_.setText(null);
        operateur_.setText(null);
        password_.setText(null);

    }

    /**
     *
     * @throws IOException
     */
    void initData() throws IOException {
        waitLoad = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {

                    try {
                        _stckaMain.setDisable(true);
                        indexWaitingSimple = 0;
                        Events_Instance().Dialog(WAITLOADING_SIMPLE, 414, 210);
//                        if (index == 0) {
                        retraitInstence().Retrait();
//                        } else {
//                            size = getVar_instance().retrait_en_attente.size();
//                            Loading().ScrollOperation(vbx_, Operation().getRetrait(), offset, limit, RETRAIT_LOAD);
//                            ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
//                            Events.dialog.close();
//                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                return null;
            }
        };
        new Thread(waitLoad).start();
    }

//    void LoadDataDash() {
//        taskdash = new Task<Integer>() {
//            @Override
//            protected Integer call() throws Exception {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(1000);
//                            initData();
//                            _stckaMain.setDisable(false);
//                        } catch (Exception ex) {
//                            Logger.getLogger(DashbordController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                });
//                return null;
//            }
//        };
//        new Thread(taskdash).start();
//    }
    int xI;

    List<Retrait> json() {
        if (xI == 0) {
            return getVar_instance().retrait_en_attente;
        } else {
            return getVar_instance().retrait_valider;
        }
    }

    @FXML
    private void loadDetail(MouseEvent event) {
    }
    float solde = 10, montant;
    String mssg;
    boolean etat;
    Retrait r;

    private void getAction(MouseEvent event) throws InterruptedException {

    }

    @FXML
    private void searchTransaction(KeyEvent event) throws IOException {
        children = 1;
        offset = 0;
        limit = 5;
        Loading().ScrollOperation(vbx_, getVar_instance().search(json(), searchtxt.getText()), offset, limit, RETRAIT_LOAD);
        ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
    }

    @FXML
    private void left(MouseEvent event) throws IOException {
        if (offset > 0) {
            Aright.setDisable(false);
            offset = offset - limitI;
            limit = limit - limitI;
            Loading().ScrollOperation(vbx_, getVar_instance().retrait_en_attente, offset, limit, RETRAIT_LOAD);
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
            Loading().ScrollOperation(vbx_, getVar_instance().retrait_en_attente, offset, limit, RETRAIT_LOAD);
            ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
            if (limit >= size) {
                Aright.setDisable(true);
            }
        }
    }

    @FXML
    private void retirer(MouseEvent event) throws InterruptedException, Exception {
        Events_Instance().BorderColor(pAgent, pMontant, pPsswd, pNumber);
        if (Events_Instance().isFieldsempty(txtagent, txtmontant, password, number)) {
            Events_Instance().ErrorPan(indexEmpty, pAgent, pMontant, pPsswd, pNumber);
        } else {

//            if (airtelSession != null && airtelSession.isConncet()) {
//                try {
            if (password.getText().equals("1960")) {
                montant = Float.parseFloat(txtmontant.getText());
                btaction.setVisible(false);
                imageV.setVisible(true);
                _stckaMain.setDisable(true);

                r = new Retrait(listretrait_json.getId(), "92");
                retraitInstence().DoRetrait(r, imageV, btaction);
            } else {
                System.out.println("#Error Dialog");
                _stckaMain.setDisable(false);
                getVar_instance().Error_Dialog("Mot de passe incorretc", "Vous avez saisie un mot de passe qui est incorrect.");
            }

//                    if (Traitement.ReraitAirtel(number.getText(), montant, password.getText())) {
//                        initTextF();
//                        getVar_instance().Error_Dialog("Demande Retrait", "Votre demande est en attente...");
//                    } else {
//                        getVar_instance().Error_Dialog("Erreur", "Une erreur est survenue lors de la demande");
//                        _stckaMain.setDisable(false);
//                    }
//                } catch (NumberFormatException e) {
//                    Events_Instance().ErrorPan(0, pMontant);
//                }
//            } else {
//             
//            }
        }

    }

    @FXML
    private void cancel(MouseEvent event) {
        initTextF();
        Events_Instance().BorderColor(pAgent, pMontant, pPsswd, pNumber);
    }

    @FXML
    private void en_attente(MouseEvent event) throws IOException {
//        initData(0);
        xI = 0;
        size = getVar_instance().retrait_en_attente.size();
        indicator.setLayoutX(36);
        Loading().ScrollOperation(vbx_, getVar_instance().retrait_en_attente, offset, limit, RETRAIT_LOAD);
        ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
    }

    @FXML
    private void retirerData(MouseEvent event) throws IOException {
//        initData(1);
        xI = 1;
        size = getVar_instance().retrait_valider.size();
        indicator.setLayoutX(116);
        Loading().ScrollOperation(vbx_, getVar_instance().retrait_valider, offset, limit, RETRAIT_LOAD);
        ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
    }

    @FXML
    private void call_Dashbord(MouseEvent event) {
    }

}
