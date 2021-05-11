/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contro_traitement;

import static apiActivity.TransactionActivity.TransActivity;
import static controllers.MainController._stckaMain;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Money;

/**
 * FXML Controller class
 *
 * @author smartTicket
 */
public class InfoController implements Initializable {
    
    @FXML
    private Text reseau;
    @FXML
    private Text reference;
    @FXML
    private Text numero;
    @FXML
    private Text montant;
    @FXML
    private CheckBox delivrer;
    @FXML
    private CheckBox used;
    @FXML
    private ImageView image;
    @FXML
    private Text btverify;
    @FXML
    private Text etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btverify_Info = btverify;
        image_Info = image;
        etat_Info = etat;
        used_Info = used;
        
        reseau.setText(Infomoney.getReseau());
        reference.setText(Infomoney.getReference());
        numero.setText(Infomoney.getNumero());
        montant.setText(Infomoney.getMontant() + "" + Infomoney.getDevise());
        delivrer.setSelected(Infomoney.isEtat());
        
        if (!"ARGENT NON UTILISE".equals(Infomoney.getCode())) {
            btverify.setDisable(true);
            btverify.setOpacity(0.3);
        }
        
    }
    public static Money Infomoney;
    public static Text btverify_Info, etat_Info;
    public static ImageView image_Info;
    public static CheckBox used_Info;
    
    @FXML
    private void verify(MouseEvent event) {
        btverify.setVisible(false);
        image.setVisible(true);
        _stckaMain.setDisable(true);
        TransActivity().FindReference(reference.getText());
        
    }
}
