package com.oktaysadoglu.memofication.server_services;

import com.oktaysadoglu.memofication.tools.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class RestApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60*2, TimeUnit.SECONDS)
                .connectTimeout(60*2, TimeUnit.SECONDS)
                .build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Network.HOST)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
