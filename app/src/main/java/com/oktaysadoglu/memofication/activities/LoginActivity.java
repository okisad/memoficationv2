package com.oktaysadoglu.memofication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.socialLogins.FacebookLoginUtil;
import com.oktaysadoglu.memofication.socialLogins.GooglePlusLoginUtil;

import static com.oktaysadoglu.memofication.socialLogins.GooglePlusLoginUtil.RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity {

    private FacebookLoginUtil facebookLoginUtil;
    private GooglePlusLoginUtil googlePlusLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googlePlusLoginUtil = new GooglePlusLoginUtil(this);

        googlePlusLoginUtil.setup();

        facebookLoginUtil = new FacebookLoginUtil(this);

        facebookLoginUtil.setup();

    }

    @Override
    protected void onStart() {
        googlePlusLoginUtil.connectGoogleApiClient();
        super.onStart();
        googlePlusLoginUtil.setupCache();
        facebookLoginUtil.setupCache();
    }

    @Override
    protected void onStop() {

        googlePlusLoginUtil.disconnectGoogleApiClient();

        super.onStop();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            googlePlusLoginUtil.onActivityResult(requestCode,resultCode,data);

        }else {

            facebookLoginUtil.onActivityResult(requestCode,resultCode,data);

        }

    }
}
