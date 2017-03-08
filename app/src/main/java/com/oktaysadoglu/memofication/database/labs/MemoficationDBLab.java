package com.oktaysadoglu.memofication.database.labs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.oktaysadoglu.memofication.database.helpers.DictionaryBaseHelper;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 01/03/2017.
 */

public class MemoficationDBLab {

    public static final String TAG = "MemoficationDBLab";

    private static MemoficationDBLab memoficationDBLab;

    private List<Word> words;

    private Context context;

    private SQLiteDatabase database;

    private static final String[] allColumns = {
            MemoficationDbSchema.DictionaryTable.Cols.ID,
            MemoficationDbSchema.DictionaryTable.Cols.NAME,
            MemoficationDbSchema.DictionaryTable.Cols.MEAN,
            MemoficationDbSchema.DictionaryTable.Cols.TYPE

    };

    private MemoficationDBLab(Context context) {

        this.context = context;

        words = new ArrayList<>();

    }

    public static MemoficationDBLab getInstance(Context context){

        if (memoficationDBLab == null){

            return new MemoficationDBLab(context);
        }else {

            return memoficationDBLab;

        }
    }

    public void open(int v){

        Log.i(TAG,"database is opened");

        database = new DictionaryBaseHelper(this.context,v).getWritableDatabase();

    }

    public void close(){

        Log.i(TAG,"database is closed");

        database.close();

    }

    public boolean addWords(List<Word> words){

        for (Word word:words){

            ContentValues contentValues = new ContentValues();
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.ID,(int) (long) word.getId());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.NAME,word.getWord());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.MEAN,word.getMean());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.TYPE,word.getType());

            long id = database.insert(MemoficationDbSchema.DictionaryTable.NAME,null,contentValues);

            return true;

        }

        return false;

    }

    public List<Word> getAllWords(){

        Cursor cursor = database.query(MemoficationDbSchema.DictionaryTable.NAME,allColumns,null,null,null,null,null);

        List<Word> words = new ArrayList<>();

        if (cursor.getCount() > 0){

            while (cursor.moveToNext()){

                Word word = new Word();
                word.setId(cursor.getLong(cursor.getColumnIndex(MemoficationDbSchema.DictionaryTable.Cols.ID)));
                word.setWord(cursor.getString(cursor.getColumnIndex(MemoficationDbSchema.DictionaryTable.Cols.NAME)));
                word.setMean(cursor.getString(cursor.getColumnIndex(MemoficationDbSchema.DictionaryTable.Cols.MEAN)));
                word.setType(cursor.getString(cursor.getColumnIndex(MemoficationDbSchema.DictionaryTable.Cols.TYPE)));

                words.add(word);

            }

        }

        return words;

    }



}
