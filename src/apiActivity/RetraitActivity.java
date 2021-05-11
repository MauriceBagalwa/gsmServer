/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiActivity;

import apiInterface.Api;
import apiInterface.ApiInterface;
import static bd.Operations.Operation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static contro_traitement.Waitloading_simpleController.content_waitingSimple;
import static contro_traitement.Waitloading_simpleController.image_waitingSimple;
import static controllers.DonneeController.size;
import static controllers.MainController._stckaMain;
import static controllers.TraitementController.limit;
import static controllers.TraitementController.offset;
import static controllers.TraitementController.vbx_;
import helpers.Events;
import static helpers.Loading.Loading;
import static helpers.PieChartSample.Piechartsample;
import helpers.Variables;
import static helpers.Variables.chartStck_;
import static helpers.Variables.ext_Aindex;
import static helpers.Variables.getVar_instance;
import static controllers.RetraitController.initTextF;
import static helpers.Variables.listretrait_json;
import static helpers.rooters.RETRAIT_LOAD;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.Retrait;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author smartTicket
 */
public class RetraitActivity {

    ApiInterface api = Api.retrode().create(ApiInterface.class);
    public static List<Retrait> retraits = new ArrayList();
    static RetraitActivity retrait_;
    Retrait retrait;
//    int size;

    public static RetraitActivity retraitInstence() {
        if (retrait_ == null) {
            retrait_ = new RetraitActivity();
        }
        return retrait_;

    }

    /**
     *
     * @return @Methode retournant la taille de Liste des money à envoyé
     */
//    public List<Retrait> getRetraitClient() {
//        Call<List<Retrait>> call = api.getRetrait(200, 0);
//        retraits.clear();
//        call.enqueue(new Callback<List<Retrait>>() {
//            //respection de la reponse
//            @Override
//            public void onResponse(Call<List<Retrait>> call, Response<List<Retrait>> rspns) {
//                //test de la reponse si le statut est 200 donc tout s'est passer bien
//                if (rspns.code() == 200) {
//                    retraits = rspns.body();
//                } else {
//                    System.out.println("something wrong to server " + rspns.code());
//                }
//            }
//
//            //erreur de la requete
//            @Override
//            public void onFailure(Call<List<Retrait>> call, Throwable thrwbl) {
//                thrwbl.getMessage();
//                System.out.println("something wrong to server " + thrwbl.getMessage());
//            }
//        });
//        return retraits;
//    }
    public List<Retrait> Retrait() {
        api.getRetrait().enqueue(new Callback<List<Retrait>>() {
            @Override
            public void onResponse(Call<List<Retrait>> call, Response<List<Retrait>> rspns) {
                if (rspns.code() == 200) {

                    Variables.listretrait = rspns.body();
                    System.out.println("\nData: " + rspns.body());

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                getVar_instance().getEn_attente();
                                size = getVar_instance().retrait_en_attente.size();
                                Loading().ScrollOperation(vbx_, getVar_instance().retrait_en_attente, offset, limit, RETRAIT_LOAD);
                                ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
                                Events.dialog.close();
                                Piechartsample().OperationTovalide(chartStck_, getVar_instance().retrait_en_attente.size(),
                                        getVar_instance().retrait_valider.size());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            _stckaMain.setDisable(false);
                        }

                    });
                } else {
                    System.out.println("Erreur: " + rspns.message());
                    Platform.runLater(() -> {
                        image_waitingSimple.setVisible(false);
                        content_waitingSimple.setVisible(true);
                        _stckaMain.setDisable(false);
                    });
                }

            }

            @Override
            public void onFailure(Call<List<Retrait>> call, Throwable thrwbl) {
                Platform.runLater(() -> {
                    image_waitingSimple.setVisible(false);
                    content_waitingSimple.setVisible(true);
                    _stckaMain.setDisable(false);
                });
                System.out.println("Something went wrong....\n" + thrwbl.getMessage());
            }
        });
        return null;

    }

    public void DoRetrait(Retrait body, ImageView image, Button bt) {
        api.doretrait(body).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> rspns) {
                if (rspns.code() == 200) {
                    System.out.println("\nRetrait: " + rspns.body());
                    System.out.println("\nRetrait: " + rspns.body().get("status").getAsBoolean());
                    if (rspns.body().get("status").getAsBoolean()) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    getVar_instance().Error_Dialog("VALIDAION DU RETRAIT", rspns.body().get("message").getAsString());
                                    getVar_instance().changeEtatRetrait(body.getId());
                                    initTextF();
                                    getVar_instance().getEn_attente();
                                    size = getVar_instance().retrait_en_attente.size();
                                    Loading().ScrollOperation(vbx_, getVar_instance().retrait_en_attente, offset, limit, RETRAIT_LOAD);
                                    ext_Aindex.setText((offset + 1) + " à " + limit + " de " + size);
                                    Piechartsample().OperationTovalide(chartStck_, getVar_instance().retrait_en_attente.size(),
                                            getVar_instance().retrait_valider.size());
                                } catch (IOException ex) {
                                    Logger.getLogger(RetraitActivity.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });

                    } else {
                        Platform.runLater(() -> {
                            getVar_instance().Error_Dialog("VALIDAION DU RETRAIT", rspns.body().get("message").getAsString());
                        });
                    }
                } else {
                    System.out.println("Erreur: " + rspns.message());
                    getVar_instance().Error_Dialog("VALIDAION DU RETRAIT", "Une erreur est servenue lors de la validation.");
                }
                image.setVisible(false);
                _stckaMain.setDisable(false);
                bt.setVisible(true);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable thrwbl) {
                System.out.println("Erreur: " + thrwbl.getMessage());
                Platform.runLater(() -> {
                    getVar_instance().Error_Dialog("ERREUR DU SERVEUR", "Une erreur est servenue lors de la validation."
                            + "Veuillez verifier votre connexion internet puis ressayer.");
                    image.setVisible(false);
                    _stckaMain.setDisable(false);
                    bt.setVisible(true);
                });

            }
        }
        );

    }

    public static void main(String[] args) {
        retraitInstence().Retrait();
//        Retrait r = new Retrait("17", "92");
//        retraitInstence().DoRetrait(r);

    }
//"[Retrait{id=17, motif=RETRAIT D'ARGENT AGENCE, montant=100.0, numero=975236270, operateur=AIRTEL, dateEn=2021-03-05 08:30:00, refCompte=98, finished=false, nomAgent=Jack Renard},
//    Retrait{id=12, motif=RETRAIT D'ARGENT AGENCE, montant=12.0, numero=976064583, operateur=AIRTEL, dateEn=2021-02-28 11:11:00, refCompte=94, finished=false, nomAgent=wolfyy}, 
//    Retrait{id=11, motif=RETRAIT D'ARGENT AGENCE, montant=11.0, numero=976064583, operateur=AIRTEL, dateEn=2021-02-27 21:38:00, refCompte=94, finished=false, nomAgent=wolfyy}, 
//    Retrait{id=10, motif=RETRAIT D'ARGENT AGENCE, montant=11.0, numero=976064583, operateur=AIRTEL, dateEn=2021-02-27 21:38:00, refCompte=94, finished=false, nomAgent=wolfyy}, 
//    Retrait{id=9, motif=RETRAIT D'ARGENT AGENCE, montant=11.0, numero=976064583, operateur=AIRTEL, dateEn=2021-02-27 21:38:00, refCompte=94, finished=false, nomAgent=wolfyy}, 
}
