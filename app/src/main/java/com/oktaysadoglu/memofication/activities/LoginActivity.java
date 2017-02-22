package com.oktaysadoglu.memofication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.socialLogins.utils.FacebookIntegrationUtil;
import com.oktaysadoglu.memofication.socialLogins.utils.GooglePlusIntegrationUtil;

import static com.oktaysadoglu.memofication.socialLogins.utils.GooglePlusIntegrationUtil.RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity {

    private FacebookIntegrationUtil facebookIntegrationUtil;
    private GooglePlusIntegrationUtil googlePlusIntegrationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googlePlusIntegrationUtil = new GooglePlusIntegrationUtil(this);

        googlePlusIntegrationUtil.setup();

        facebookIntegrationUtil = new FacebookIntegrationUtil(this);

        facebookIntegrationUtil.setup();

    }

    @Override
    protected void onStart() {
        facebookIntegrationUtil.startTracking();
        googlePlusIntegrationUtil.connectGoogleApiClient();
        super.onStart();
        googlePlusIntegrationUtil.setupCache();
        facebookIntegrationUtil.setupCache();
    }

    @Override
    protected void onStop() {

        googlePlusIntegrationUtil.disconnectGoogleApiClient();

        super.onStop();

    }

    @Override
    protected void onDestroy() {
        facebookIntegrationUtil.stopTracking();

        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            googlePlusIntegrationUtil.onActivityResult(requestCode,resultCode,data);

        }else {

            facebookIntegrationUtil.onActivityResult(requestCode,resultCode,data);

        }

    }
}
