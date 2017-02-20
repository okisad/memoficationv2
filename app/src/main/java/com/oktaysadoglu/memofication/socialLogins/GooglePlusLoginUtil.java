package com.oktaysadoglu.memofication.socialLogins;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.R;

/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class GooglePlusLoginUtil implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    public static int GOOGLE_LOGIN = 2;

    private SignInButton googleSignInButton;

    private Button googleLogOutButton;

    private AppCompatActivity appCompatActivity;

    private static GooglePlusLoginUtil socialGoogleLogin;

    private GooglePlusLoginUtil(){

    }

    public static GooglePlusLoginUtil getInstance(){

        if (socialGoogleLogin == null){
            socialGoogleLogin = new GooglePlusLoginUtil();
            return socialGoogleLogin;
        }else {
            return socialGoogleLogin;
        }

    }

    public void setup(AppCompatActivity appCompatActivity){

        this.appCompatActivity = appCompatActivity;

        googleSignInButton = (SignInButton) appCompatActivity.findViewById(R.id.sign_in_button);

        googleSignInButton.setOnClickListener(this);

        Memofication.getGoogleApiClient(appCompatActivity, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        });

        /*googleLogOutButton = (Button) appCompatActivity.findViewById(R.id.logout_button);*/

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            /*case R.id.logout_button:
                signOut();
                break;*/
        }
    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(Memofication.getGoogleApiClient());
        appCompatActivity.startActivityForResult(signInIntent, LoginActivity.RC_SIGN_IN);

    }
}
