package com.oktaysadoglu.memofication.settings;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class SettingsPreferences {

    private static final String NOTIFICATION_ON_OFF="notificationOnOff";

    private static final String NOTIFICATION_NUMBER = "numberOfNotification";

    private static String REVERSE = "reverse";

    public static boolean isReverse(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(REVERSE,false);

    }

    public static void setIsReverse(Context context,boolean isReverse){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(REVERSE,isReverse).apply();

    }

    public static boolean getNotificationOnOff(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(NOTIFICATION_ON_OFF,false);

    }

    public static void setNotificationOnOff(Context context,boolean onOff){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(NOTIFICATION_ON_OFF,onOff).apply();

    }

    public static int getNotificationNumber(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(NOTIFICATION_NUMBER,0);

    }

    public static void setNotificationNumber(Context context,int count){

        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(NOTIFICATION_NUMBER,count).apply();

    }

}
