package com.oktaysadoglu.memofication.services;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.services.pojo.AccessToken;
import com.oktaysadoglu.memofication.settings.AccessTokenPreferences;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;

import java.util.List;

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

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<AccessToken> call = apiInterface.getAccessToken(token,email,"9cf3dcd6-15ac-4f93-9524-98b832a80138",platform);

        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                AccessToken accessToken = response.body();

                AccessTokenPreferences.setAccessToken(appCompatActivity,accessToken.getToken());

                onTaskCompleted.onTaskCompleted();
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

                Log.e("my",t.getMessage());

            }
        });

    }

}
