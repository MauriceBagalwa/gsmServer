/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static bd.Operations.*;
import helpers.Loading;
import static helpers.Loading.Loading;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.DetailServer;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class ServerController implements Initializable {

    @FXML
    private Text serverName;
    @FXML
    private Label signal;
    @FXML
    private AnchorPane pan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pan_ = pan;
        serverName.setText(detailServer.getName());
        signal.setText(detailServer.getSignal() + "%");
//        labIndex.setText("" + indexList);
        init();
    }
    public static DetailServer detailServer = new DetailServer();
    public static int indexList = 0;
    public static AnchorPane pan_;
    Calendar cal;
    String date;
    int y, m, d;

    void init() {
        cal = new GregorianCalendar();
        d = cal.get(Calendar.DAY_OF_MONTH);
        m = cal.get(Calendar.MONTH) + 1;
        y = cal.get(Calendar.YEAR);
        date = y + "-" + m + "-" + d;
    }

    @FXML
    private void loadDetail(MouseEvent event) {
        try {
//            Loading().initServerActif(1, Integer.parseInt(labIndex.getText()));
//            reseau_ = serverName.getText();
//            Loading().ScrollOperation(vbxMessage_, list(), offset, limit, children, LOADINGMESSAGE);
//            loaddetails(Operation().getDetails(serverName.getText(), date));
//            Loading().ScrollwithVbx(vbx_,sizeTraitement ,offset, limit, 3, LOADINGMESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Money> list() {
        System.out.println("::- Reseau: " + serverName.getText());
        if (serverName.getText().equals("Airtel")) {
            return MessageAirtelMoney;
        } else if (serverName.getText().equals("Orange")) {
            return MessageOrangeMoney;
        }
        return null;
    }
}
