/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static contro_traitement.InfoController.Infomoney;
import static helpers.Events.Events_Instance;
import static helpers.rooters.INFO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class MoneytraiterController implements Initializable {

    @FXML
    private Label num;
    @FXML
    private Label reseau;
    @FXML
    private Label montant;
    @FXML
    private Label reference;
    @FXML
    private CheckBox etat;
    @FXML
    private Label devise;
    @FXML
    private Label code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        money = moneyTraiter;
        reference.setText(moneyTraiter.getReference());
        reseau.setText(moneyTraiter.getReseau());
        num.setText(moneyTraiter.getNumero());
        montant.setText("" + moneyTraiter.getMontant());
        code.setText(moneyTraiter.getCode());
        etat.setSelected(moneyTraiter.isEtat());
        devise.setText(moneyTraiter.getDevise());
    }
    public static Money moneyTraiter;
    Money money;

    @FXML
    private void more(MouseEvent event) {

    }

    @FXML
    private void getInfo(MouseEvent event) throws IOException {
        Infomoney = money;
        Events_Instance().Dialog(INFO, 378, 163);
    }

}
