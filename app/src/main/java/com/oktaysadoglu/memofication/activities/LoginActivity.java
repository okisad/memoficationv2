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



    public static String PLATFORM = "platform";


    private FacebookLoginUtil facebookLoginUtil;
    private GooglePlusLoginUtil googlePlusLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googlePlusLoginUtil = GooglePlusLoginUtil.getInstance();

        googlePlusLoginUtil.setup(this);

        facebookLoginUtil = FacebookLoginUtil.getInstance();

        facebookLoginUtil.setup(this);

    }

    @Override
    protected void onStart() {
        googlePlusLoginUtil.connectGoogleApiClient();
        super.onStart();
        googlePlusLoginUtil.setupCache();

        facebookLoginUtil.controlUser(this);

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

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Intent googleSignInIntent = new Intent(LoginActivity.this, MainActivityGoogle.class);
            startActivity(googleSignInIntent);

        }else {

            FacebookLoginUtil.getInstance().getCallbackManager().onActivityResult(requestCode,resultCode,data);

        }

    }
}
