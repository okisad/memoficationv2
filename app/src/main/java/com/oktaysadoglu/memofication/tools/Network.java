package com.oktaysadoglu.memofication.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by oktaysadoglu on 28/02/2017.
 */

public class Network {

    public static String HOST = "http://192.168.1.45:8080/";

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
