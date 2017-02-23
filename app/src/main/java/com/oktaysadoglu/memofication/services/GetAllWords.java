package com.oktaysadoglu.memofication.services;

import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class GetAllWords implements OnTaskCompleted{

    private OnTaskCompleted onTaskCompleted;

    public GetAllWords(OnTaskCompleted onTaskCompleted) {

        this.onTaskCompleted = onTaskCompleted;

    }

    public void getWord(int i){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Word>> call = apiInterface.getAllWords();

        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                List<Word> words = response.body();

                Memofication.words = words;

                for(int i = 0 ; i < words.size() ; i++){

                    System.out.println(i);

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

                /*Log.e("my", Memofication.words.toString());*/

            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {

                Log.e("my",t.getMessage());

            }
        });

    }

    @Override
    public void onTaskCompleted() {



    }
}
