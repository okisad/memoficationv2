package com.oktaysadoglu.memofication;

import com.oktaysadoglu.memofication.fragments.game_fragment.logic.WordCardLogic;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;
import com.oktaysadoglu.memofication.services.GetAllWords;
import com.oktaysadoglu.memofication.services.OnTaskCompleted;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        final CountDownLatch signal = new CountDownLatch(1);

        GetAllWords getAllWords = new GetAllWords(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted() {
                System.out.println(Memofication.words.toString());

                for (int m = 1 ; m< 100 ; m++){

                    for (int i = 1 ; i < 101 ; i++){

                        WordCardLogic wordCardLogic = new WordCardLogic(new ArrayList<WordCard>());

                        List<WordCard> wordCards = wordCardLogic.getWordCards(i);

                        for (WordCard wordCard:wordCards) {

                            if(controlwordCards(wordCard)){

                            }else {

                                System.out.println("false");

                            }

                        }

                    }

                }

                System.out.println("bitti");


            }
        });

        getAllWords.getWord(0);



        signal.await();

    }

    private boolean controlwordCards(WordCard wordCard){

        for (int i = 0 ; i < 4 ; i++){

            long id = wordCard.getWords().get(i).getId();

            for (int j = i+1 ; j <4 ;j++){

                if (id == wordCard.getWords().get(j).getId()){

                    return false;

                }

            }

        }

        for (Word word : wordCard.getWords()){

            if (word == null){

                return false;

            }

        }

        long id = wordCard.getMainWord().getId();

        boolean control = false;

        for (Word word : wordCard.getWords()){

            if (id == word.getId()){

                if(!control){

                    control =true;
                }else {

                    control = false;

                }

            }

        }

        if (!control)
            return false;

        return true;

    }
}