package com.oktaysadoglu.memofication.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.oktaysadoglu.memofication.database.mappers.Mapper;
import com.oktaysadoglu.memofication.database.repositories.Repository;
import com.oktaysadoglu.memofication.database.repositories.SqLiteRepository;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by oktaysadoglu on 01/03/2017.
 */

public class MemoficationDatabaseHelper extends SQLiteOpenHelper {

    private static MemoficationDatabaseHelper dictionaryBaseHelper;
    private static final int VERSION = 1;
    private static String DB_PATH = "";
    private static final String DATABASE_NAME = "memofication-db.db";
    private SQLiteDatabase database;

    private Context context;

    public static MemoficationDatabaseHelper getInstance(Context context){

        if (dictionaryBaseHelper == null){

            synchronized (MemoficationDatabaseHelper.class){

                if(dictionaryBaseHelper==null){

                    dictionaryBaseHelper = new MemoficationDatabaseHelper(context.getApplicationContext());

                }

            }

        }

        return dictionaryBaseHelper;

    }

    public Repository getRepository(Mapper mapper){

        return SqLiteRepository.getInstance(database,mapper);

    }

    private MemoficationDatabaseHelper(Context context){

        super(context,DATABASE_NAME,null,VERSION);

        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

        this.context = context;

    }

    public void createDataBase() throws IOException {

        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                copyDataBase();
                Log.e("my", "createDatabase database created");
            }
            catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException
    {
        InputStream mInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DATABASE_NAME;

        database = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        return database != null;

    }

    @Override
    public synchronized void close()
    {
        if(database != null)

            database.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
