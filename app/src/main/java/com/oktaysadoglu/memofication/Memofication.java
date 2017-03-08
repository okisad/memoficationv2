package com.oktaysadoglu.memofication;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.oktaysadoglu.memofication.database.helpers.MemoficationDatabaseHelper;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class Memofication extends Application{

    public static String CLIENT_ID = "624593840249-v0idjpte9edpog5khjaopc3ls6jr098d.apps.googleusercontent.com";

    public static List<Word> words = new ArrayList<>();

    public static List<Word> wordsA = new ArrayList<>();
    public static List<Word> wordsAdj = new ArrayList<>();
    public static List<Word> wordsAdv = new ArrayList<>();
    public static List<Word> wordsConj = new ArrayList<>();
    public static List<Word> wordsInter = new ArrayList<>();
    public static List<Word> wordsN = new ArrayList<>();
    public static List<Word> wordsPrep = new ArrayList<>();
    public static List<Word> wordsPron = new ArrayList<>();
    public static List<Word> wordsU = new ArrayList<>();
    public static List<Word> wordsV = new ArrayList<>();
    public static List<Word> wordsX = new ArrayList<>();

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    public AppCompatActivity activity;

    @Override
    public void onCreate() {
        super.onCreate();

        prepareDatabase();
    }

    private void prepareDatabase() {

        MemoficationDatabaseHelper dbHelper = MemoficationDatabaseHelper.getInstance(this);

        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public GoogleSignInOptions getGoogleSignInOptions(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestEmail()
                .build();
        return gso;
    }
    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity, GoogleApiClient.OnConnectionFailedListener listener){
        this.activity = activity;
        googleApiClient= new GoogleApiClient.Builder(this)
                .enableAutoManage(this.activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
                .build();
        return googleApiClient;
    }


}
