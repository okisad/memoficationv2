package com.oktaysadoglu.memofication.server_services;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oktaysadoglu.memofication.server_services.pojo.AccessToken;
import com.oktaysadoglu.memofication.settings.AccessTokenPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class AuthenticationService {

    private OnTaskCompleted onTaskCompleted;

    private AppCompatActivity appCompatActivity;

    public AuthenticationService(OnTaskCompleted onTaskCompleted, AppCompatActivity appCompatActivity) {
        this.onTaskCompleted = onTaskCompleted;
        this.appCompatActivity = appCompatActivity;
    }

    public void setAccessToken(String token,String email,String platform){

        RestApiInterface restApiInterface = RestApiClient.getClient().create(RestApiInterface.class);

        Call<AccessToken> call = restApiInterface.getAccessToken(token,email,"9cf3dcd6-15ac-4f93-9524-98b832a80138",platform);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                AccessToken accessToken = response.body();

                if (accessToken != null){

                    AccessTokenPreferences.setAccessToken(appCompatActivity,accessToken.getToken());

                    onTaskCompleted.onTaskCompleted();

                }

            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

                Log.e("my",t.getMessage());

            }
        });

    }

}
