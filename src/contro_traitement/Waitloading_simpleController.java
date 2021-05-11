/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static apiActivity.RetraitActivity.retraitInstence;
import static bd.Operations.Operation;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class Waitloading_simpleController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton refrsh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image_waitingSimple = image;
        refrsh_waitingSimple = refrsh;
        content_waitingSimple = content;
    }
    public static ImageView image_waitingSimple;
    public static JFXButton refrsh_waitingSimple;
    public static AnchorPane content_waitingSimple;
    public static int indexWaitingSimple = -1;

    void refreshDataFor() throws Exception {
        switch (indexWaitingSimple) {
            case 0:
                retraitInstence().Retrait();
                break;
        }
    }

    @FXML
    private void refresh(MouseEvent event) throws Exception {
        content_waitingSimple.setVisible(false);
        image.setVisible(true);
        refreshDataFor();
    }

}
