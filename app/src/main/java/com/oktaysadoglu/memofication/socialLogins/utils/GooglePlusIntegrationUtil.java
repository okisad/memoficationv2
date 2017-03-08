package com.oktaysadoglu.memofication.socialLogins.utils;

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
import com.oktaysadoglu.memofication.server_services.AuthenticationService;
import com.oktaysadoglu.memofication.server_services.OnTaskCompleted;
import com.oktaysadoglu.memofication.server_services.pojo.AnsweredWord;
import com.oktaysadoglu.memofication.server_services.pojo.User;
import com.oktaysadoglu.memofication.settings.AccessTokenPreferences;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;

import java.util.ArrayList;

import static com.oktaysadoglu.memofication.activities.BaseActivity.user;


/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class GooglePlusIntegrationUtil extends IntegrationUtil {

    public static String TAG = "GooglePlusIntegration";

    public static int RC_SIGN_IN = 9001;

    private SignInButton googleSignInButton;

    private GoogleApiClient googleApiClient;

    private AppCompatActivity appCompatActivity;

    public GooglePlusIntegrationUtil(AppCompatActivity appCompatActivity){

        this.appCompatActivity = appCompatActivity;

    }

    @Override
    public void setup(){

        googleSignInButton = (SignInButton) appCompatActivity.findViewById(R.id.sign_in_button);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        GoogleSignInOptions gso = ((Memofication) appCompatActivity.getApplication()).getGoogleSignInOptions();

        googleApiClient = ((Memofication) appCompatActivity.getApplication()).getGoogleApiClient(appCompatActivity, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Log.e(TAG,"google auth problem");
            }
        });
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
                        SocialUser.clearValues();
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

            GoogleSignInResult result = opr.get();

            targetActivity(result);

        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                }

            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

        targetActivity(result);

    }


    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);

        appCompatActivity.startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void targetActivity(GoogleSignInResult result){

        final Intent googleSignInIntent = new Intent(appCompatActivity, MainActivityGoogle.class);
        if (result.getSignInAccount() != null){

            SocialUser.setValues(result.getSignInAccount().getDisplayName(),result.getSignInAccount().getEmail(),result.getSignInAccount().getPhotoUrl(),result.getSignInAccount().getIdToken());

            user = new User(SocialUser.getEmail(),new ArrayList<AnsweredWord>());

        }

        AuthenticationService authenticationService = new AuthenticationService(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
                Log.e("mu","access token alışverisi okay");

                appCompatActivity.startActivity(googleSignInIntent);
            }
        },appCompatActivity);

        authenticationService.setAccessToken(SocialUser.getAccessToken(),SocialUser.getEmail(),"google");
    }

    public void disconnectGoogleApiClient(){
        googleApiClient.disconnect();
    }

    public void connectGoogleApiClient(){
        googleApiClient.connect();
    }
}
