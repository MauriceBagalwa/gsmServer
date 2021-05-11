/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_parametre;

import com.jfoenix.controls.JFXButton;
import static helpers.Events.Events_Instance;
import static helpers.SharedPreference.Preferences;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class BdController implements Initializable {
    
    @FXML
    private TextField hostname;
    @FXML
    private TextField dbname;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label bd_;
    @FXML
    private Label utiilisateur_;
    @FXML
    private Label hostname_;
    @FXML
    private Label password_;
    @FXML
    private JFXButton btton_tester;
    @FXML
    private JFXButton bt_config;
    @FXML
    private Text getTaux;
    @FXML
    private TextField taux;
    @FXML
    private JFXButton bt_taux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
    }
    
    void initData() {
        hostname_.setText(Preferences().getHostname());
        bd_.setText(Preferences().getDatabase());
        utiilisateur_.setText(Preferences().getUser());
        password_.setText(Preferences().getPassword());
        getTaux.setText(Preferences().getTaux() + " CDF");
    }
    
    @FXML
    private void load(MouseEvent event) {
        hostname.setText(Preferences().getHostname());
        dbname.setText(Preferences().getDatabase());
        username.setText(Preferences().getUser());
        password.setText(Preferences().getPassword());
        
    }
    
    @FXML
    private void configDB(MouseEvent event) {
        try {
            Preferences().InitDataBase(hostname.getText(), dbname.getText(),
                    username.getText(), password.getText());
            Events_Instance().initFields(hostname, dbname, username, password);
            initData();
        } catch (Exception e) {
        }
    }
    float taux_;
    
    @FXML
    private void configtaux(MouseEvent event) {
        try {
            taux_ = Float.parseFloat(taux.getText());
            if (taux_ > 0) {
                Preferences().setTaux(taux_);
                getTaux.setText(Preferences().getTaux() + " CDF");
                taux.setText("");
            }
        } catch (Exception e) {
        }
    }
    
    @FXML
    private void tester(MouseEvent event) {
        
    }
    
}
