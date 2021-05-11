/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_parametre;

import com.jfoenix.controls.JFXButton;
import static contro_parametre.ModemController.indexConfig;
import static helpers.Events.dialog;
import static helpers.SharedPreference.Preferences;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ConfigModemController implements Initializable {

    @FXML
    private TextField modem;
    @FXML
    private TextField bitTemporisation;
    @FXML
    private TextField centreMssg;
    @FXML
    private TextField pin;
    @FXML
    private JFXButton button;
    @FXML
    private TextField port;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        switch (indexConfig) {
            case "airtel": {
                System.out.println("Index: " + indexConfig);
                initText(Preferences().getPort_Airtel(), Preferences().getModem_Airtel(),
                        Preferences().getCentreMessagerie_Airtel());
                break;
            }
            case "orange": {
                System.out.println("Index: " + indexConfig);
                initText(Preferences().getPort_Orange(), Preferences().getModem_Orange(),
                        Preferences().getCentreMessagerie_Orange());
                break;
            }
            case "vodacom": {
                System.out.println("Index: " + indexConfig);
                initText(Preferences().getPort_Vodacom(), Preferences().getModem_Vodacom(),
                        Preferences().getCentreMessagerie_Vodacom());
                break;
            }

        }
    }

    void initText(String port, String modem, String centreMssg) {
        this.port.setText(port);
        this.modem.setText(modem);
        this.centreMssg.setText(centreMssg);
    }

    @FXML
    private void config(MouseEvent event) {
        try {

            switch (indexConfig) {
                case "airtel": {
                    Preferences().InitAirtelConfig(port.getText(), modem.getText(),
                            centreMssg.getText(), Integer.parseInt(bitTemporisation.getText()), pin.getText());
                    break;
                }
                case "orange": {
                    Preferences().InitOrange(port.getText(), modem.getText(),
                            centreMssg.getText(), Integer.parseInt(bitTemporisation.getText()), pin.getText());
                    break;
                }
                case "vodacom": {
                    Preferences().InitVodacom(port.getText(), modem.getText(),
                            centreMssg.getText(), Integer.parseInt(bitTemporisation.getText()), pin.getText());
                    break;
                }

            }
            dialog.close();
        } catch (Exception e) {

        }
    }

}
