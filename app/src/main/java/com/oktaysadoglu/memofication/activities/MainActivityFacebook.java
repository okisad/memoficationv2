package com.oktaysadoglu.memofication.activities;

import android.os.Bundle;

import com.oktaysadoglu.memofication.socialLogins.FacebookLoginUtil;
import com.oktaysadoglu.memofication.socialLogins.LoginUtil;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class MainActivityFacebook extends BaseActivity {

    private LoginUtil facebookLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        facebookLoginUtil = new FacebookLoginUtil(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        facebookLoginUtil.setupLogout(null);

    }
}
