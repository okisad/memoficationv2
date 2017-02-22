package com.oktaysadoglu.memofication.services;

import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public interface ApiInterface {

    @GET("rest/dictionary/word")
    Call<Word> getMovieDetails(@Query("id") int id);

}
