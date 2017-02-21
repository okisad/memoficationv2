package com.oktaysadoglu.memofication.activities;

import android.os.Bundle;

import com.oktaysadoglu.memofication.socialLogins.FacebookLoginUtil;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class MainActivityFacebook extends BaseActivity {

    private FacebookLoginUtil facebookLoginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookLoginUtil = FacebookLoginUtil.getInstance();

        facebookLoginUtil.setupLogout(this);

    }


}
