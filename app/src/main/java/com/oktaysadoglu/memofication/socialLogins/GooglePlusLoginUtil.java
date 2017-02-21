package com.oktaysadoglu.memofication.socialLogins;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.MainActivityGoogle;


/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class GooglePlusLoginUtil extends LoginUtil implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    public static int RC_SIGN_IN = 9001;

    private SignInButton googleSignInButton;

    private GoogleApiClient googleApiClient;

    private AppCompatActivity appCompatActivity;

    public GooglePlusLoginUtil(AppCompatActivity appCompatActivity){

        this.appCompatActivity = appCompatActivity;

    }

    @Override
    public void setup(){

        googleSignInButton = (SignInButton) appCompatActivity.findViewById(R.id.sign_in_button);

        googleSignInButton.setOnClickListener(this);

        GoogleSignInOptions gso = ((Memofication) appCompatActivity.getApplication()).getGoogleSignInOptions();

        googleApiClient = ((Memofication) appCompatActivity.getApplication()).getGoogleApiClient(appCompatActivity, this);
    }

    @Override
    public void setupLogout(final GoogleApiClient googleApiClient){


        Button button = (Button) appCompatActivity.findViewById(R.id.activity_main_navigation_view_logout_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Intent intent = new Intent(appCompatActivity,LoginActivity.class);
                        appCompatActivity.startActivity(intent);
                        appCompatActivity.finish();
                    }
                });
            }
        });

    }

    @Override
    public void setupCache(){
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            Log.d("my", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            Intent googleSignInIntent = new Intent(appCompatActivity, MainActivityGoogle.class);
            appCompatActivity.startActivity(googleSignInIntent);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //hideProgressDialog();
                    /*handleSignInResult(googleSignInResult);*/
                }


            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        Intent googleSignInIntent = new Intent(appCompatActivity, MainActivityGoogle.class);
        appCompatActivity.startActivity(googleSignInIntent);
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
        }
    }

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        appCompatActivity.startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    public void disconnectGoogleApiClient(){
        googleApiClient.disconnect();
    }

    public void connectGoogleApiClient(){
        googleApiClient.connect();
    }
}
