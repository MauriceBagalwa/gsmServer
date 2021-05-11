/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static controllers.MainController.ListDetailserver;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class TransactionController implements Initializable {

    @FXML
    private AnchorPane pan11;
    @FXML
    private VBox vbx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (ListDetailserver.size() > 0) {
            children = 4;
            offset = 0;
            limit = 8;
            try {
//                reseau = ListDetailserver.get(0).getName();
//                sql = "select reseau,numero,montant,devise,`date` FROM `money` WHERE reseau='" + reseau + "' and etat=false order by `date`";
//                listMonneyVu = Operation().getMessage_En_Attente(sql);
//                Loading().ScrollOperation(vbx, listMonneyVu, offset, limit, children, LOADINGMESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    static String reseau;
    public static String sql;
    public static int offset, limit, children;

    @FXML
    private void loadDetail(MouseEvent event) {
    }

}
