package com.oktaysadoglu.memofication.services.pojo;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class AccessToken {

    private String token;

    public AccessToken() {
    }

    public AccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
