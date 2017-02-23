package com.oktaysadoglu.memofication.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.services.AuthenticationService;
import com.oktaysadoglu.memofication.services.OnTaskCompleted;
import com.oktaysadoglu.memofication.settings.AccessTokenPreferences;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;
import com.oktaysadoglu.memofication.socialLogins.utils.GooglePlusIntegrationUtil;
import com.oktaysadoglu.memofication.socialLogins.utils.IntegrationUtil;

public class MainActivityGoogle extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private IntegrationUtil googlePlusIntegrationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googlePlusIntegrationUtil = new GooglePlusIntegrationUtil(this);

        mGoogleApiClient = ((Memofication) getApplication()).getGoogleApiClient(this, this);

        AuthenticationService authenticationService = new AuthenticationService(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
                Log.e("mu","access token alışverisi okay");
                Log.e("mu",AccessTokenPreferences.getAccessToken(getApplicationContext()));
            }
        },this);

        authenticationService.setAccessToken(SocialUser.getAccessToken(),SocialUser.getEmail(),"google");

        Log.e("my","token : "+ SocialUser.getAccessToken());


    }

    @Override
    protected void onStart() {

        mGoogleApiClient.connect();

        super.onStart();

        googlePlusIntegrationUtil.setValuesToNavigation(this,getMainActivitySetupNavigationToolbar());

        googlePlusIntegrationUtil.setupLogout(mGoogleApiClient);
    }

    @Override
    protected void onStop() {

        mGoogleApiClient.disconnect();

        super.onStop();

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
