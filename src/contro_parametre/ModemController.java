/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_parametre;

import com.jfoenix.controls.JFXButton;
import static helpers.Events.Events_Instance;
import static helpers.SharedPreference.Preferences;
import static helpers.rooters.CONFIG_MODEM_PARAMETRE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ModemController implements Initializable {

    @FXML
    private JFXButton bt_cAirtel32;
    @FXML
    private JFXButton bt_cAirtel321;
    @FXML
    private JFXButton bt_cAirtel3211;
    @FXML
    private Text comAirtel;
    @FXML
    private Text centreAirtel;
    @FXML
    private Text comOrange;
    @FXML
    private Text centreOrange;
    @FXML
    private Text comVoda;
    @FXML
    private Text centreVoda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initText();
        initDataText();
    }
    /**
     * Initializes the controller class.
     */
    public static String indexConfig = "";
    public static Text comAirtel_ = new Text();
    public static Text centreAirtel_ = new Text();
    public static Text comOrange_ = new Text();
    public static Text centreOrange_ = new Text();
    public static Text comVoda_ = new Text();
    public static Text centreVoda_ = new Text();

    /**
     * Initializes the controller class.
     */
    void initText() {
        comAirtel_ = comAirtel;
        centreAirtel_ = centreAirtel;

        comOrange_ = comOrange;
        centreOrange_ = centreOrange;

        comVoda_ = comVoda;
        centreVoda_ = centreVoda;
    }

    public static void initDataText() {
        comAirtel_.setText(Preferences().getModem_Airtel());
        centreAirtel_.setText(Preferences().getCentreMessagerie_Airtel());

        comOrange_.setText(Preferences().getModem_Orange());
        centreOrange_.setText(Preferences().getCentreMessagerie_Orange());

        comVoda_.setText(Preferences().getModem_Vodacom());
        centreVoda_.setText(Preferences().getCentreMessagerie_Vodacom());
    }

    @FXML
    private void configAirtel(MouseEvent event) throws IOException {
        indexConfig = "airtel";
        Events_Instance().Dialog(CONFIG_MODEM_PARAMETRE, 374, 421);
    }

    @FXML
    private void configOrange(MouseEvent event) throws IOException {
        indexConfig = "orange";
        Events_Instance().Dialog(CONFIG_MODEM_PARAMETRE, 374, 421);
    }

    @FXML
    private void configVoda(MouseEvent event) throws IOException {
        indexConfig = "vodacom";
        Events_Instance().Dialog(CONFIG_MODEM_PARAMETRE, 374, 421);
    }

}
