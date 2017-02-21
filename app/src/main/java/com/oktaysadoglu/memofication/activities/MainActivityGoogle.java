package com.oktaysadoglu.memofication.activities;

import android.support.annotation.NonNull;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.socialLogins.GooglePlusLoginUtil;
import com.oktaysadoglu.memofication.socialLogins.LoginUtil;

public class MainActivityGoogle extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;

    private LoginUtil googlePlusLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        googlePlusLoginUtil = new GooglePlusLoginUtil(this);

        mGoogleApiClient = ((Memofication) getApplication()).getGoogleApiClient(this, this);


    }

    @Override
    protected void onStart() {

        mGoogleApiClient.connect();

        super.onStart();

        googlePlusLoginUtil.setupLogout(mGoogleApiClient);
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
