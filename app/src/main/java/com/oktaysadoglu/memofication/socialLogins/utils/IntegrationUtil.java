package com.oktaysadoglu.memofication.socialLogins.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.navigation.MainActivitySetupNavigationToolbar;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public abstract class IntegrationUtil {

    public abstract void setup();

    public abstract void setupCache();

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void setupLogout(final GoogleApiClient googleApiClient);

    public void setValuesToNavigation(AppCompatActivity appCompatActivity, MainActivitySetupNavigationToolbar mainActivitySetupNavigationToolbar){

        if (SocialUser.controlOfNull()){

            mainActivitySetupNavigationToolbar.setProfileImage(appCompatActivity,SocialUser.getPhotoUri());

        }

    }

}
