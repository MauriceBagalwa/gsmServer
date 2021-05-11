/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static controllers.RetraitController.initTextF;
import static controllers.RetraitController.number_;
import static controllers.RetraitController.operateur_;
import static controllers.RetraitController.txtagent_;
import static controllers.RetraitController.txtmontant_;
import static helpers.Events.Events_Instance;
import static helpers.Loading.Loading;
import static helpers.Variables.listretrait_json;
import static helpers.rooters.DETAILS_RETRAIT;
import static helpers.rooters.WAITLOADING_SIMPLE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Retrait;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class RetraitController implements Initializable {

    @FXML
    private Label montant;
    @FXML
    private Label devise;
    @FXML
    private Label refcompte;
    @FXML
    private Label number;
    @FXML
    private Label date;
    @FXML
    private CheckBox etat;
    @FXML
    private Label agent;
    @FXML
    private StackPane stck;
    @FXML
    private Label index;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        json = listretrait_json;
        if (json != null) {
            number.setText(json.getNumero());
            montant.setText(json.getOperateur());
            refcompte.setText("" + json.getRefCompte());
            date.setText(json.getDateEn());
            agent.setText(json.getNomAgent());

            if (json.isFinished() == 1) {
                etat.setSelected(true);
            }
            inDex = Loading().i;
        }
        iniaction();

    }
    Retrait json;
    int inDex;
    MenuItem item1 = new MenuItem("#Visualiser");
    MenuItem item2 = new MenuItem("#Faire l'opération");
    // Create ContextMenu
    ContextMenu contextMenu = new ContextMenu();

    void iniaction() {
        item1.setOnAction((e) -> {
            listretrait_json = json;
            try {
                Events_Instance().Dialog(DETAILS_RETRAIT, 390, 527);
            } catch (IOException ex) {
                Logger.getLogger(RetraitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        item2.setOnAction((e) -> {
            listretrait_json = json;
            txtagent_.setText(json.getNomAgent());
            txtmontant_.setText("" + json.getMontant());
            number_.setText(json.getNumero());
            operateur_.setText(json.getOperateur());
        });
        contextMenu.getItems().addAll(item1, item2);
        if(json.isFinished()==1){
            item2.setDisable(true);
        }
    }

    @FXML
    private void more(MouseEvent event) {
    }

    @FXML
    private void getData(MouseEvent event) throws IOException {
        initTextF();
        System.out.println("#Index: " + inDex);
//        Loading().selectedNode(index);
        contextMenu.show(stck, event.getScreenX(), event.getScreenY());
    }

}
