/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiActivity;

import apiInterface.Api;
import apiInterface.ApiInterface;
import static bd.Operations.Operation;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static contro_traitement.InfoController.btverify_Info;
import static contro_traitement.InfoController.etat_Info;
import static contro_traitement.InfoController.image_Info;
import static contro_traitement.InfoController.used_Info;
import static controllers.MainController._stckaMain;
import static controllers.MainController.code_tentative;
import static controllers.MainController.occuper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Money;
import model.Results;
import model.Transation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author smartTicket
 */
public class TransactionActivity {

    /**
     * @Variables
     */
    ApiInterface api = Api.retrode().create(ApiInterface.class);
    static TransactionActivity trans;
    Gson g = new Gson();
    boolean bool;

    /**
     * @Instance @return
     */
    public static TransactionActivity TransActivity() {
        if (trans == null) {
            trans = new TransactionActivity();
        }
        return trans;
    }

    /**
     * @return @add transaction
     */
    public boolean Transaction_() {

        api.transactions().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> rspns) {
                if (rspns.code() == 200) {
                    System.out.println(rspns.body());
                } else {
                    System.out.println("#Erreur: " + rspns.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable thrwbl) {
                System.out.println("something went wrong... " + thrwbl.getMessage());
            }
        });
        return bool;
    }
    String code, str;

    Transation transaction;
    Results resultat;
    //post data to the server, pour cette methode on a besoin 
    //d'un objet de la classe money qu'on envoyera au serveur via la methode post

    public void Transaction_Money(Transation transaction) {
        occuper = true;
        this.transaction = transaction;
        Call<Results> call = api.addTransaction(this.transaction);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> rspns) {
                if (rspns.code() == 200) {
                    resultat = rspns.body();
                    if (resultat.isStatus()) {
                        try {
                            System.err.println("saved succces");
                            Operation().changeEtat(transaction.getReference(), "");
                        } catch (Exception ex) {
                            Logger.getLogger(TransactionActivity.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.err.println(resultat.getMessage());
                    }
                } else {
                    System.err.println("something wrong to server");
                }
                occuper = false;
            }

            @Override
            public void onFailure(Call<Results> call, Throwable thrwbl) {
                thrwbl.getMessage();
                System.err.print("erreur " + thrwbl.getMessage());
                occuper = false;
            }
        });
    }

    public static List<Money> moneys;

    public void FindReference(String body) {
        moneys = null;
        code_tentative = true;
        System.out.println("-#3- Tentative...");
        Call<List<Money>> call = api.getReferenceArgent(body);
        call.enqueue(new Callback<List<Money>>() {
            @Override
            public void onResponse(Call<List<Money>> call, Response<List<Money>> rspns) {
                if (rspns.code() == 200) {
                    moneys = rspns.body();

                    if (!moneys.isEmpty()) {
                        try {
                            System.out.println("-#4B- Body2: " + moneys.toString());
                            etat_Info.setText(moneys.get(0).getEtat());
                            used_Info.setSelected(true);
                            if (!"ARGENT NON UTILISE".equals(moneys.get(0).getEtat())) {
                                Operation().changeEtat(moneys.get(0).getReference(), "1");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(TransactionActivity.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Non trouver");
                        etat_Info.setText("Reference invalid");
                    }
                } else {
                    System.out.println("Out : " + rspns.code() + " " + rspns.message());
                }
                initInfo();
            }

            @Override
            public void onFailure(Call<List<Money>> call, Throwable error) {
                System.out.println("Erro: " + error.getMessage());
                initInfo();
            }
        });
    }

    void initInfo() {
        _stckaMain.setDisable(false);
        image_Info.setVisible(false);
        btverify_Info.setVisible(true);
    }

    public static void main(String[] args) {
//        Money m=new Money("P140689VT35", 2.5, "+243999026241", "USD", date);
        TransActivity().FindReference("PP210130.1823.C01723");
    }
}
