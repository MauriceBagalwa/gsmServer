/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static helpers.rooters.EN_RECEPTION;
import static contro_traitement.ReceptionController.messageReception;
import static helpers.Events.Events_Instance;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Message;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class MessageController implements Initializable {

    @FXML
    private Label date;
    @FXML
    private Label num;
    @FXML
    private Label reseau;
    @FXML
    private Label mssg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setText(messageRecev.getDate().substring(0, messageRecev.getDate().lastIndexOf(":")));
        str = messageRecev.getNumero().trim();
        num.setText("  " + str);
        if (messageRecev.getResau().equals("orange")) {
//            reseau.setStyle("-fx-fill:Orange");
        }

        reseau.setText(messageRecev.getResau());
        mssg.setText(messageRecev.getMessage());
    }
    public static Message messageRecev;
    String str;

    @FXML
    private void show(MouseEvent event) throws IOException {
        messageReception = new Message(date.getText(), num.getText().trim(), reseau.getText(), mssg.getText());
        Events_Instance().Dialog(EN_RECEPTION, 337, 123);
    }
}
