package com.oktaysadoglu.memofication.server_services;

import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.server_services.pojo.AccessToken;
import com.oktaysadoglu.memofication.server_services.pojo.Version;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public interface RestApiInterface {

    @GET("rest/dictionary/getAllWords")
    Call<List<Word>> getAllWords();

    @GET("rest/dictionary/version")
    Call<Version> getUpdateVersion();

    @FormUrlEncoded
    @POST("rest/auth/getAccessToken")
    Call<AccessToken> getAccessToken(@Field("accessToken") String accessToken, @Field("id") String id, @Field("apiKey") String apiKey, @Header("platform") String platform);

}
