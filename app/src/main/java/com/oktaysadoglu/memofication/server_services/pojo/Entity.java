package com.oktaysadoglu.memofication.server_services.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oktaysadoglu on 08/03/2017.
 */

public abstract class Entity {
    @SerializedName("_id")
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
