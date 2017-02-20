package com.oktaysadoglu.memofication;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class Memofication extends Application{

    private static GoogleApiClient googleApiClient;

    private static GoogleSignInOptions googleSignInOptions;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static GoogleSignInOptions getGoogleSignInOptions(){

        if (googleSignInOptions == null){

            googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .requestIdToken("624593840249-ona410t6jts2u2unfbvjl2birqvk4aq0.apps.googleusercontent.com")
                    .build();

        }

        return googleSignInOptions;

    }

    public static void getGoogleApiClient(AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener){

        if (googleApiClient == null){

            googleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity,onConnectionFailedListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API,getGoogleSignInOptions())
                    .build();

        }

    }

    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }


}
