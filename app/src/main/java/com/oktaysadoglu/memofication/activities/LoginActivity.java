package com.oktaysadoglu.memofication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.socialLogins.FacebookLoginUtil;
import com.oktaysadoglu.memofication.socialLogins.GooglePlusLoginUtil;

public class LoginActivity extends AppCompatActivity {

    public static int RC_SIGN_IN = 1;

    public static String PLATFORM = "platform";


    private FacebookLoginUtil facebookLoginUtil;
    private GooglePlusLoginUtil googlePlusLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookLoginUtil.getInstance().setup(this);

        GooglePlusLoginUtil.getInstance().setup(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Intent intent = new Intent(this,MainActivity.class);

            intent.putExtra(PLATFORM,GooglePlusLoginUtil.GOOGLE_LOGIN);

            startActivity(intent);

        }else {

            FacebookLoginUtil.getInstance().getCallbackManager().onActivityResult(requestCode,resultCode,data);

        }

    }
}
