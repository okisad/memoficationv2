package com.oktaysadoglu.memofication.services;

import android.util.Log;

import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class GetAllWords {

    public void getWord(int i){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Word> call = apiInterface.getMovieDetails(2);

        call.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                Word word = response.body();

                Log.e("my", word.toString());

            }

            @Override
            public void onFailure(Call<Word> call, Throwable t) {

            }
        });

    }

}
