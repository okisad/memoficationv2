package com.oktaysadoglu.memofication.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import com.oktaysadoglu.memofication.server_services.pojo.Entity;

/**
 * Created by oktaysadoglu on 08/03/2017.
 */

public interface Mapper <T,V>{

    T mapToContentValues(V entity);

    V mapToEntity(Cursor cursor);

    String getTablename();



}
