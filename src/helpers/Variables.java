/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import static controllers.MainController._stckaMain;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Retrait;
import static controllers.DonneeController.size;
import static controllers.RetraitController.labMontant;
import static helpers.Events.Events_Instance;

/**
 *
 * @author smartTicket
 */
public class Variables {

    static Variables var;

    public static Variables getVar_instance() {
        if (var == null) {
            var = new Variables();
        }
        return var;

    }

    public static List<Retrait> listretrait = new ArrayList<>();
    public static StackPane chartStck_, chartStck2_;
    public static Label ext_Aindex, ext_size;
    public static VBox vbx_;
    public List<Retrait> retrait_en_attente = new ArrayList<>();
    public List<Retrait> retrait_valider = new ArrayList<>();
    float montant;

    public List<Retrait> getEn_attente() {
        System.out.println("Retrait: " + listretrait.toString());
        retrait_en_attente.clear();
        retrait_valider.clear();
        montant = 0;
        listretrait.forEach(retrait -> {
            System.out.println("#is: " + retrait.isFinished());
            if (retrait.isFinished() == 0) {
                retrait_en_attente.add(retrait);
                montant += retrait.getMontant();
            } else {
                retrait_valider.add(retrait);
            }
        });
        labMontant.setText("" + montant);
        return retrait_en_attente;
    }

    public void changeEtatRetrait(String id) {
        listretrait.forEach(retrait -> {
            System.out.println("#is: " + retrait.isFinished());
            if (id.equals(retrait.getId())) {
                retrait.setFinished(1);
            } else {
                System.out.println("id non trouver.");
            }
        });
    }

    List<Retrait> newjson = new ArrayList<>();

    public List<Retrait> search(List<Retrait> json, String txt) {
        newjson.clear();
        json.stream().filter(rslt -> (rslt.getNumero().contains(txt.toLowerCase())
                || rslt.getNomAgent().toLowerCase().contains(txt.toLowerCase())
                || rslt.getId().toLowerCase().contains(txt.toLowerCase())
                || rslt.getOperateur().toLowerCase().contains(txt.toLowerCase()))).forEachOrdered(rslt -> {
            newjson.add(rslt);
        });
        size = newjson.size();
        return newjson;
    }

    public static Retrait listretrait_json;

    JFXDialog dialog;
    JFXDialogLayout content = new JFXDialogLayout();
    JFXButton bt = new JFXButton("Okay");

    public void Error_Dialog(String title, String txt) {
        content.setHeading(new Text(title));
        content.setBody(new Text(txt));
        dialog = new JFXDialog(_stckaMain, content, JFXDialog.DialogTransition.CENTER, true);
        bt.setOnAction((e) -> {
            dialog.close();
        });
        content.setActions(bt);
        dialog.show();
    }
}
