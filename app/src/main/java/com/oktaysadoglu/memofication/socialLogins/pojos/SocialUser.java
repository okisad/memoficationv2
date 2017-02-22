package com.oktaysadoglu.memofication.socialLogins.pojos;

import android.net.Uri;

import java.net.URI;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */
public class SocialUser {
    private static SocialUser ourInstance = new SocialUser();

    private static String name;
    private static String email;
    private static Uri photoUri;
    private static String accessToken;

    public static SocialUser getInstance() {
        return ourInstance;
    }

    private SocialUser() {
    }

    public static void setValues(String name,String email,Uri photo,String accessToken){

        SocialUser.name = name;
        SocialUser.email = email;
        SocialUser.photoUri = photo;
        SocialUser.accessToken = accessToken;

    }

    public static void clearValues(){

        SocialUser.name = null;
        SocialUser.email = null;
        SocialUser.photoUri = null;
        SocialUser.accessToken = null;

    }

    public static boolean controlOfNull(){

        if (SocialUser.accessToken == null || SocialUser.name == null || SocialUser.email == null || SocialUser.photoUri == null)
            return false;
        else
            return true;

    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static Uri getPhotoUri() {
        return photoUri;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setName(String name) {
        SocialUser.name = name;
    }

    public static void setEmail(String email) {
        SocialUser.email = email;
    }

    public static void setPhotoUri(Uri photoUri) {
        SocialUser.photoUri = photoUri;
    }

    public static void setAccessToken(String accessToken) {
        SocialUser.accessToken = accessToken;
    }
}
