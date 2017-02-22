package com.oktaysadoglu.memofication.activities;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;
import com.oktaysadoglu.memofication.socialLogins.utils.FacebookIntegrationUtil;
import com.oktaysadoglu.memofication.socialLogins.utils.IntegrationUtil;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class MainActivityFacebook extends BaseActivity {

    private IntegrationUtil facebookIntegrationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookIntegrationUtil = new FacebookIntegrationUtil(this);

        SocialUser.setValues(Profile.getCurrentProfile().getName(),Profile.getCurrentProfile().getId(),Profile.getCurrentProfile().getProfilePictureUri(200,200),AccessToken.getCurrentAccessToken().getToken());

        Log.e("my", AccessToken.getCurrentAccessToken().getToken());

    }

    @Override
    protected void onStart() {
        super.onStart();

        facebookIntegrationUtil.setValuesToNavigation(this,getMainActivitySetupNavigationToolbar());

        facebookIntegrationUtil.setupLogout(null);

    }
}
