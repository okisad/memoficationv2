package com.oktaysadoglu.memofication.database.repositories;

import android.app.ListActivity;

import com.oktaysadoglu.memofication.server_services.pojo.Entity;

import java.util.List;

/**
 * Created by oktaysadoglu on 08/03/2017.
 */

public interface Repository<T> {

    void add(T entity);

    void update(T entity);

    List<Entity> getEntities(String fieldName, Object value);

    void remove(T entity);

    List<T> getAllEntities();

}
