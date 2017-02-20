package com.oktaysadoglu.memofication.socialLogins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.activities.MainActivity;
import com.oktaysadoglu.memofication.R;

/**
 * Created by oktaysadoglu on 17/02/2017.
 */

public class FacebookLoginUtil {

    public static int FACEBOOK_LOGIN = 1;

    private CallbackManager callbackManager;

    private static FacebookLoginUtil socailNetworkLogin = null;

    private FacebookLoginUtil() {
    }

    public static FacebookLoginUtil getInstance() {
        if (socailNetworkLogin == null){
            socailNetworkLogin = new FacebookLoginUtil();
            return socailNetworkLogin;
        }else
            return socailNetworkLogin;
    }

    public String getAccessToken() {

        if (!AccessToken.getCurrentAccessToken().getToken().equals(""))
            return AccessToken.getCurrentAccessToken().getToken();
        else
            return "";

    }

    public void setup(final AppCompatActivity appCompatActivity) {

        LoginButton loginButton = (LoginButton) appCompatActivity.findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.e("my","onsuccess");

                Intent intent = new Intent(appCompatActivity,MainActivity.class);

                intent.putExtra(LoginActivity.PLATFORM,FacebookLoginUtil.FACEBOOK_LOGIN);

                appCompatActivity.startActivity(intent);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void logoutProcess(final AppCompatActivity appCompatActivity){

        Button button = (Button) appCompatActivity.findViewById(R.id.activity_main_navigation_view_logout_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();

                Intent intent = new Intent(appCompatActivity,LoginActivity.class);

                appCompatActivity.startActivity(intent);

            }
        });

    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

}
