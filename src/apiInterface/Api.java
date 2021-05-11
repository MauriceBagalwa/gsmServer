/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author smartTicket
 */
public class Api {

    /**
     * @Url de l connexion avec la BD
     */
    private static String url = "https://api.smart-tick.com/api/v2/";
    
    private static Retrofit retro = null;

    public static Retrofit retrode() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
