package com.oktaysadoglu.memofication.settings;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 28/02/2017.
 */

public class UpdatePreferences {

    private static final String VERSION="version";

    public static int getVersionNumber(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(VERSION,-1);

    }

    public static void setVersionNumber(Context context,int version){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(VERSION,version).apply();

    }

}
