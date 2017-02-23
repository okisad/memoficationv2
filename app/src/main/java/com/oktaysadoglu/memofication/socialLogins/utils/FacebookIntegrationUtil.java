package com.oktaysadoglu.memofication.socialLogins.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.activities.MainActivityFacebook;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;

import java.util.Arrays;

/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class FacebookIntegrationUtil extends IntegrationUtil {

    public static String TAG = "FacebookIntegrationUtil";

    private CallbackManager callbackManager;

    private AppCompatActivity appCompatActivity;

    private AccessTokenTracker accessTokenTracker;

    private ProfileTracker profileTracker;

    public FacebookIntegrationUtil(AppCompatActivity appCompatActivity) {

        this.appCompatActivity = appCompatActivity;

    }


    @Override
    public void setup() {

        LoginButton loginButton = (LoginButton) appCompatActivity.findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(appCompatActivity,MainActivityFacebook.class);

                appCompatActivity.startActivity(intent);

                Log.i(TAG,"Login is successful");

            }

            @Override
            public void onCancel() {

                Log.i(TAG,"Login is cancelled");

            }

            @Override
            public void onError(FacebookException error) {

                Log.e(TAG,error.getMessage());

            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                Log.i(TAG,"access token is changed");


            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                Log.i(TAG,"profile is changed");

                if (currentProfile != null)
                    SocialUser.setValues(currentProfile.getName(),currentProfile.getId(),currentProfile.getProfilePictureUri(200,200),AccessToken.getCurrentAccessToken().getToken());

            }
        };

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

                SocialUser.clearValues();

                LoginManager.getInstance().logOut();

                Intent intent = new Intent(appCompatActivity,LoginActivity.class);

                appCompatActivity.startActivity(intent);

            }
        });

    }


    public void startTracking(){

        profileTracker.startTracking();
        accessTokenTracker.startTracking();

    }

    public void stopTracking(){

        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();

    }


}
