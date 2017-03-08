package com.oktaysadoglu.memofication.database.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.oktaysadoglu.memofication.database.mappers.Mapper;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.server_services.pojo.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by oktaysadoglu on 01/03/2017.
 */

public class SqLiteRepository implements Repository<Entity>{

    public static final String TAG = "SqLiteRepository";

    private static SqLiteRepository sqLiteRepository;

    private List<Word> words;

    private Context context;

    private SQLiteDatabase database;

    private Mapper<ContentValues,Entity> mapper;

    private String tableName;

    private static final String[] allColumns = {
            MemoficationDbSchema.DictionaryTable.Cols.ID,
            MemoficationDbSchema.DictionaryTable.Cols.NAME,
            MemoficationDbSchema.DictionaryTable.Cols.MEAN,
            MemoficationDbSchema.DictionaryTable.Cols.TYPE

    };

    private SqLiteRepository(SQLiteDatabase database,Mapper mapper) {

        this.database= database;

        this.mapper = mapper;

        tableName = mapper.getTablename();

    }

    public static SqLiteRepository getInstance(SQLiteDatabase database,Mapper mapper){

        if (sqLiteRepository == null){

            return new SqLiteRepository(database,mapper);
        }else {

            return sqLiteRepository;

        }
    }

    public void open(){

        Log.i(TAG,"database is opened");

    }

    public void close(){

        Log.i(TAG,"database is closed");

        database.close();

    }

    @Override
    public void add(Entity entity) {

        ContentValues contentValues = mapper.mapToContentValues(entity);

        database.insert(tableName,null,contentValues);

    }

    @Override
    public void update(Entity entity) {

        ContentValues contentValues = mapper.mapToContentValues(entity);

        database.update(tableName,contentValues,MemoficationDbSchema.DictionaryTable.Cols.ID+"="+contentValues.getAsLong(MemoficationDbSchema.DictionaryTable.Cols.ID),null);

    }

    @Override
    public List<Entity> getEntities(String fieldName, Object value) {

        List<Entity> words = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+tableName+" WHERE "+fieldName+"=";

        if (value instanceof Integer){

            selectQuery = selectQuery + value;

        }else if (value instanceof String){

            selectQuery = selectQuery + "'"+value+"'";

        }

        Cursor cursor = database.rawQuery(selectQuery,null);

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                while (cursor.isAfterLast() == false){

                    Entity entity = mapper.mapToEntity(cursor);
                    words.add(entity);
                    cursor.moveToNext();

                }

            }

        }

        return words;
    }

    @Override
    public void remove(Entity entity) {

        database.delete(tableName,"_id="+entity.getId(),null);

    }

    @Override
    public List<Entity> getAllEntities() {
        Cursor cursor = database.query(tableName,allColumns,null,null,null,null,null);

        List<Entity> words = new ArrayList<>();

        if (cursor.getCount() > 0){

            while (cursor.moveToNext()){

                words.add(mapper.mapToEntity(cursor));

            }

        }

        return words;
    }


}
