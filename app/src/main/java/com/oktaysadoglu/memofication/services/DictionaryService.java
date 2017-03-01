package com.oktaysadoglu.memofication.services;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.services.pojo.Version;
import com.oktaysadoglu.memofication.settings.UpdatePreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class DictionaryService {

    private OnTaskCompleted onTaskCompleted;

    private VersionOnTaskCompleted versionOnTaskCompleted;

    private AppCompatActivity appCompatActivity;

    public DictionaryService(OnTaskCompleted onTaskCompleted, AppCompatActivity appCompatActivity) {

        this.onTaskCompleted = onTaskCompleted;
        this.appCompatActivity = appCompatActivity;

    }

    public DictionaryService(VersionOnTaskCompleted versionOnTaskCompleted, AppCompatActivity appCompatActivity) {
        this.versionOnTaskCompleted = versionOnTaskCompleted;
        this.appCompatActivity = appCompatActivity;
    }

    public void getAllWord(){

        RestApiInterface restApiInterface = RestApiClient.getClient().create(RestApiInterface.class);

        Call<List<Word>> call = restApiInterface.getAllWords();

        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                List<Word> words = response.body();

                Memofication.words = words;

                for(int i = 0 ; i < words.size() ; i++){

                    /*System.out.println(i);*/

                    switch (words.get(i).getType()){
                        case "a":
                            Memofication.wordsA.add(words.get(i));
                            break;
                        case "adj.":
                            Memofication.wordsAdj.add(words.get(i));
                            break;
                        case "adv.":
                            Memofication.wordsAdv.add(words.get(i));
                            break;
                        case "conj.":
                            Memofication.wordsConj.add(words.get(i));
                            break;
                        case "inter.":
                            Memofication.wordsInter.add(words.get(i));
                            break;
                        case "n.":
                            Memofication.wordsN.add(words.get(i));
                            break;
                        case "prep.":
                            Memofication.wordsPrep.add(words.get(i));
                            break;
                        case "pron.":
                            Memofication.wordsPron.add(words.get(i));
                            break;
                        case "u":
                            Memofication.wordsU.add(words.get(i));
                            break;
                        case "v.":
                            Memofication.wordsV.add(words.get(i));
                            break;
                        case "x":
                            Memofication.wordsX.add(words.get(i));
                            break;


                    }

                }

                onTaskCompleted.onTaskCompleted();

            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {

                Log.e("my",t.getMessage());

            }
        });

    }

    public void getVersionNumber(){

        RestApiInterface restApiInterface = RestApiClient.getClient().create(RestApiInterface.class);

        Call<Version> call = restApiInterface.getUpdateVersion();

        call.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                Version version = response.body();

                if (version != null){

                    versionOnTaskCompleted.onTaskCompleted(version.getVersion());

                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {

                Log.e("UpdateService",t.getMessage());

            }
        });

    }

}
