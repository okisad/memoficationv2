package com.oktaysadoglu.memofication.socialLogins;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public abstract class LoginUtil {

    public abstract void setup();

    public abstract void setupCache();

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);

    public abstract void setupLogout(final GoogleApiClient googleApiClient);


}
