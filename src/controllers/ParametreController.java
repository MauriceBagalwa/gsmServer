/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static bd.Operations.Operation;
import static controllers.DashbordController.Lastmessage;
import static controllers.DashbordController.lastmmssVBX_;
import static helpers.Events.Events_Instance;
import static helpers.Loading.Loading;
import static helpers.rooters.BD_PARAMETRE;
import static helpers.rooters.ESPACE_USER;
import static helpers.rooters.MODEM_PARAMETRE;
import static helpers.rooters.SQL_MESSAGES_OFDAY;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ParametreController implements Initializable {

    @FXML
    private Label lab_users;
    @FXML
    private Label lab_modem;
    @FXML
    private Label lab_BD;
    @FXML
    private StackPane comtentParametre;
    @FXML
    private AnchorPane pan_Users;
    @FXML
    private AnchorPane pan_Modem;
    @FXML
    private AnchorPane pan_Bd;
    @FXML
    private VBox lastmmssVBXparametre;
    @FXML
    private Text mssg;
    @FXML
    private Label countMessage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (Lastmessage.size() <= 0) {
                Lastmessage = Operation().messageOfday(SQL_MESSAGES_OFDAY + " limit 3");
            }
            Loading().LoadVBX(lastmmssVBXparametre, Lastmessage);
            Events_Instance().seleteLab("#797979", "#ceced6", lab_modem, lab_users, lab_BD);
            Events_Instance().colorPan("parametre", pan_Modem, pan_Users, pan_Bd);
            Events_Instance().setContent(comtentParametre, getClass().getResource(MODEM_PARAMETRE));
        } catch (Exception e) {
        }

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void callUsers(MouseEvent event) {
        Events_Instance().seleteLab("#797979", "#ceced6", lab_users, lab_modem, lab_BD);
        Events_Instance().colorPan("parametre", pan_Users, pan_Modem, pan_Bd);
        Events_Instance().setContent(comtentParametre, getClass().getResource(ESPACE_USER));
    }

    @FXML
    private void callModem(MouseEvent event) {
        Events_Instance().seleteLab("#797979", "#ceced6", lab_modem, lab_users, lab_BD);
        Events_Instance().colorPan("parametre", pan_Modem, pan_Users, pan_Bd);
        Events_Instance().setContent(comtentParametre, getClass().getResource(MODEM_PARAMETRE));
    }

    @FXML
    private void callBd(MouseEvent event) {
        Events_Instance().seleteLab("#797979", "#ceced6", lab_BD, lab_modem, lab_users);
        Events_Instance().colorPan("parametre", pan_Bd, pan_Modem, pan_Users);
        Events_Instance().setContent(comtentParametre, getClass().getResource(BD_PARAMETRE));
    }

}
