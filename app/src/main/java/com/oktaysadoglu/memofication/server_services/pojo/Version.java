package com.oktaysadoglu.memofication.server_services.pojo;

/**
 * Created by oktaysadoglu on 28/02/2017.
 */

public class Version {

    private String name;
    private int version;

    public Version() {
    }

    public Version(String name, int version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
