/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static contro_traitement.MessageController.messageRecev;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import models.Message;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ReceptionController implements Initializable {

    @FXML
    private Text reseau;
    @FXML
    private Text numero;
    @FXML
    private Text date;
    @FXML
    private Text mssg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseau.setText(messageReception.getResau());
        numero.setText(messageReception.getNumero());
        date.setText(messageReception.getDate());
        mssg.setText(messageReception.getMessage());
    }
 public static Message messageReception;
}
