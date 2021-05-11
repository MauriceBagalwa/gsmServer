/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiInterface;

import com.google.gson.JsonObject;
import java.util.List;
import model.Money;
import model.Results;
import model.Retrait;
import model.Transation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *
 * @author smartTicket
 */
public interface ApiInterface {

    @GET("/")
    Call<JsonObject> homepage();

    @GET("v1/transaction")
    Call<JsonObject> transactions();

    //requete avec la methode post
    @POST("serveur/transaction")
    Call<Results> addTransaction(@Body Transation transaction);

    @GET("serveur/search/referenceArgent/{reference}")
    Call<List<Money>> getReferenceArgent(@Path("reference") String reference);

    @GET("serveur/show/retrait/{limit}/{isAll}")
    Call<List<Retrait>> getRetrait2(@Path("limit") int limit, @Path("isAll") int isAll);

    @GET("admin/retrait/request/list")
    Call<List<Retrait>> getRetrait();

    @POST("admin/retrait/validate")
    Call<JsonObject> doretrait(@Body Retrait body);
}
