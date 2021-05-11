/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import java.net.URL;
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
public class AttenteController implements Initializable {
    
    @FXML
    private AnchorPane pan112;
    @FXML
    private Text reseau;
    @FXML
    private Label num;
    @FXML
    private Label montant;
    @FXML
    private Label devise;
    @FXML
    private Text date;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseau.setText(moneyEn_attente.getReseau());
        num.setText(moneyEn_attente.getNumero());
        devise.setText(moneyEn_attente.getDevise());
        montant.setText("" + moneyEn_attente.getMontant());
        date.setText("" + moneyEn_attente.getDate());
        if (moneyEn_attente.getReseau().equals("Airtel")) {
            reseau.setStyle("-fx-fill:fb0101");
        }
    }
    
    @FXML
    private void loadDetail(MouseEvent event) {
    }
    public static Money moneyEn_attente;
}
