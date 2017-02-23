package com.oktaysadoglu.memofication.settings;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class AccessTokenPreferences {

    private static final String ACCESS_TOKEN="access_token";

    public static String getAccessToken(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getString(ACCESS_TOKEN,"");

    }

    public static void setAccessToken(Context context,String accessToken){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(ACCESS_TOKEN,accessToken).apply();

    }
}
