package com.oktaysadoglu.memofication.server_services;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.server_services.pojo.Version;

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

    private Context context;

    public DictionaryService(OnTaskCompleted onTaskCompleted, Context context) {

        this.onTaskCompleted = onTaskCompleted;
        this.context = context;

    }

    public DictionaryService(VersionOnTaskCompleted versionOnTaskCompleted, AppCompatActivity appCompatActivity) {
        this.versionOnTaskCompleted = versionOnTaskCompleted;
        this.context = context;
    }

    public void getAllWord(){

        RestApiInterface restApiInterface = RestApiClient.getClient().create(RestApiInterface.class);

        Call<List<Word>> call = restApiInterface.getAllWords();

        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                List<Word> words = response.body();

                Memofication.words = words;

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
