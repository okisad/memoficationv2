package com.oktaysadoglu.memofication.socialLogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.activities.MainActivityFacebook;
import com.oktaysadoglu.memofication.activities.MainActivityGoogle;
import com.oktaysadoglu.memofication.R;

/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class FacebookLoginUtil extends LoginUtil{

    private CallbackManager callbackManager;

    private AppCompatActivity appCompatActivity;

    public FacebookLoginUtil(AppCompatActivity appCompatActivity) {

        this.appCompatActivity = appCompatActivity;

    }

    public String getAccessToken() {

        if (!AccessToken.getCurrentAccessToken().getToken().equals(""))
            return AccessToken.getCurrentAccessToken().getToken();
        else
            return "";

    }


    @Override
    public void setup() {

        LoginButton loginButton = (LoginButton) appCompatActivity.findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.e("my","onsuccess");

                Intent intent = new Intent(appCompatActivity,MainActivityFacebook.class);

                appCompatActivity.startActivity(intent);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    public void setupCache() {

        if(Profile.getCurrentProfile() != null){

            Intent intent = new Intent(appCompatActivity,MainActivityFacebook.class);

            appCompatActivity.startActivity(intent);

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void setupLogout(GoogleApiClient googleApiClient) {

        Button button = (Button) appCompatActivity.findViewById(R.id.activity_main_navigation_view_logout_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(appCompatActivity,LoginActivity.class);

                appCompatActivity.startActivity(intent);

            }
        });

    }


}
