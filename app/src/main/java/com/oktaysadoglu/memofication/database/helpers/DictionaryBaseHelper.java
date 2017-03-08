package com.oktaysadoglu.memofication.database.helpers;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.server_services.DictionaryService;
import com.oktaysadoglu.memofication.server_services.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 01/03/2017.
 */

public class DictionaryBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MemoficationBase.db";

    private ProgressDialog progressBar;

    private Context context;
    private static final String[] allColumns = {
            MemoficationDbSchema.DictionaryTable.Cols.ID,
            MemoficationDbSchema.DictionaryTable.Cols.NAME,
            MemoficationDbSchema.DictionaryTable.Cols.MEAN,
            MemoficationDbSchema.DictionaryTable.Cols.TYPE

    };

    private static final String CREATE_DICTIONARY_TABLE =
            "CREATE TABLE " + MemoficationDbSchema.DictionaryTable.NAME + " (" +
                    MemoficationDbSchema.DictionaryTable.Cols.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MemoficationDbSchema.DictionaryTable.Cols.ID + " NUMERIC, " +
                    MemoficationDbSchema.DictionaryTable.Cols.NAME + " TEXT, " +
                    MemoficationDbSchema.DictionaryTable.Cols.MEAN + " TEXT, " +
                    MemoficationDbSchema.DictionaryTable.Cols.TYPE + " TEXT " +
                    ")";

    public DictionaryBaseHelper(Context context,int version){

        super(context,DATABASE_NAME,null,version);

        this.context = context;

        /*progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);
        progressBar.setMessage("File downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();*/

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_DICTIONARY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MemoficationDbSchema.DictionaryTable.NAME);

        sqLiteDatabase.execSQL(CREATE_DICTIONARY_TABLE);

        /*updateDatabase();*/

    }

    public boolean addWords(SQLiteDatabase database,List<Word> words){

        for (Word word:words){

            ContentValues contentValues = new ContentValues();
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.ID,(int) (long) word.getId());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.NAME,word.getWord());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.MEAN,word.getMean());
            contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.TYPE,word.getType());

            long id = database.insert(MemoficationDbSchema.DictionaryTable.NAME,null,contentValues);

            final int m = (((int)(id)*100)/words.size());

            System.out.println(m);

            Handler progressBarHandler = new Handler();

            progressBarHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(m);
                }
            });

        }

        progressBar.dismiss();

        return true;

    }

    public List<Word> getAllWords(SQLiteDatabase database){

        String query = "SELECT  * FROM " + MemoficationDbSchema.DictionaryTable.NAME;

        Cursor cursor = database.rawQuery(query,null);

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

    private void updateDatabase(){

        DictionaryService dictionaryService = new DictionaryService(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
                List<Word> words = Memofication.words;

                SQLiteDatabase database = getWritableDatabase();

                addWords(database,words);

                System.out.println(getAllWords(database).toString());
            }
        },context);

        dictionaryService.getAllWord();

    }
}
