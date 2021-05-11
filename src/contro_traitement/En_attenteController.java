/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static bd.Operations.Operation;
import static helpers.Events.dialog;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
//import static controllers.MainController._stckaMain;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class En_attenteController implements Initializable {
    
    @FXML
    private Label signal111111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Operation().getDetailsServer();
            Thread.sleep(200);
//            while (dialog != null) {
//                System.out.println("Null...");
//                Thread.sleep(8000);
//            }
//            dialog.close();
            
        } catch (Exception ex) {
            Logger.getLogger(En_attenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
