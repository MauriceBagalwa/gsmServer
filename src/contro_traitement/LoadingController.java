/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class LoadingController implements Initializable {

    @FXML
    private AnchorPane pan;
    @FXML
    private Label date;
    private Text message;
    @FXML
    private Text ref;
    @FXML
    private Label montant;
    @FXML
    private Label devise;
    @FXML
    private Label reseau;
    @FXML
    private AnchorPane pan2;
    @FXML
    private Label num1;
    @FXML
    private Label num;
    @FXML
    private Label num12;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        message.setText(moneyLoading.getSms());
//        date.setText(dte.toString());
    }
    public static Money moneyLoading;
    Date dte = new Date();

    @FXML
    private void loadDetail(MouseEvent event) {
    }

}
