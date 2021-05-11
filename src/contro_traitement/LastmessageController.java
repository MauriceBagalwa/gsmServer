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
import javafx.scene.text.Text;
import models.Message;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class LastmessageController implements Initializable {

    @FXML
    private Text reseau;
    @FXML
    private Text numero;
    @FXML
    private Text mssg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseau.setText(lastMessage.getResau());
        numero.setText(lastMessage.getNumero().trim());
        mssg.setText(lastMessage.getMessage());
    }
    public static Message lastMessage;
}
