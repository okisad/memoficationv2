package com.oktaysadoglu.memofication.activities;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.oktaysadoglu.memofication.server_services.AuthenticationService;
import com.oktaysadoglu.memofication.server_services.OnTaskCompleted;
import com.oktaysadoglu.memofication.server_services.pojo.AnsweredWord;
import com.oktaysadoglu.memofication.server_services.pojo.User;
import com.oktaysadoglu.memofication.settings.AccessTokenPreferences;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;
import com.oktaysadoglu.memofication.socialLogins.utils.FacebookIntegrationUtil;

import java.util.ArrayList;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class MainActivityFacebook extends BaseActivity {

    private FacebookIntegrationUtil facebookIntegrationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookIntegrationUtil = new FacebookIntegrationUtil(this);

        SocialUser.setValues(Profile.getCurrentProfile().getName(),Profile.getCurrentProfile().getId(),Profile.getCurrentProfile().getProfilePictureUri(200,200),AccessToken.getCurrentAccessToken().getToken());

        user = new User(SocialUser.getEmail(),new ArrayList<AnsweredWord>());

        AuthenticationService authenticationService = new AuthenticationService(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
                Log.e("mu","access token alışverisi okay");
                Log.e("mu", AccessTokenPreferences.getAccessToken(getApplicationContext()));
            }
        },this);

        authenticationService.setAccessToken(SocialUser.getAccessToken(),SocialUser.getEmail(),"facebook");

        Log.e("my", AccessToken.getCurrentAccessToken().getToken());

        Log.e("my",AccessToken.getCurrentAccessToken().getUserId());

    }

    @Override
    protected void onStart() {
        super.onStart();

        facebookIntegrationUtil.setValuesToNavigation(this,getMainActivitySetupNavigationToolbar());

        facebookIntegrationUtil.setupLogout(null);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
