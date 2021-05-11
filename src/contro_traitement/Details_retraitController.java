/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import static helpers.Events.dialog;
import static helpers.Variables.listretrait_json;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class Details_retraitController implements Initializable {

    @FXML
    private AnchorPane pusername;
    @FXML
    private Label reseau;
    @FXML
    private AnchorPane pusername1;
    @FXML
    private Label number;
    @FXML
    private Label montant;
    @FXML
    private AnchorPane pusername12;
    @FXML
    private Label date;
    @FXML
    private Label agent;
    @FXML
    private AnchorPane pusername11;
    @FXML
    private Label refCompte;
    @FXML
    private JFXCheckBox etat;
    @FXML
    private JFXTextArea motif;
    @FXML
    private JFXButton btaction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (listretrait_json != null) {
            agent.setText(listretrait_json.getNomAgent());
            refCompte.setText("" + listretrait_json.getRefCompte());
            if (listretrait_json.isFinished() == 1) {
                etat.setSelected(true);
            }
            reseau.setText(listretrait_json.getOperateur());
            number.setText(listretrait_json.getNumero());
            montant.setText(listretrait_json.getMontant() + "USD");
            date.setText(listretrait_json.getDateEn());
            motif.setText(listretrait_json.getMotif());
        }
    }

    @FXML
    private void cancel(MouseEvent event) {
        dialog.close();
    }

}
