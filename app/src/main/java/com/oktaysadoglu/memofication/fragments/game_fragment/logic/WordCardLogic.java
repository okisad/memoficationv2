package com.oktaysadoglu.memofication.fragments.game_fragment.logic;

import android.app.Activity;
import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.database.helpers.MemoficationDatabaseHelper;
import com.oktaysadoglu.memofication.database.mappers.WordMapper;
import com.oktaysadoglu.memofication.database.repositories.Repository;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;
import com.oktaysadoglu.memofication.server_services.pojo.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by oktaysadoglu on 23/02/2017.
 */

public class WordCardLogic {

    public static String TAG = "WordCardLogic";

    private List<WordCard> wordCards;

    private Activity activity;

    private Repository<Entity> repository;

    public WordCardLogic(List<WordCard> wordCards, Activity activity) {
        this.wordCards = wordCards;
        this.activity = activity;
    }

    public List<WordCard> getWordCards(int level) {

        int startPoint = (level-1)*50 + 1;

        if (level <= 100) {

            MemoficationDatabaseHelper helper = MemoficationDatabaseHelper.getInstance(activity);

            repository = helper.getRepository(new WordMapper());

            List<Word> words = new ArrayList<>();

            for (int i = startPoint ; i < startPoint+50 ; i++){

                Word word = Word.class.cast(repository.getEntities(MemoficationDbSchema.DictionaryTable.Cols.ID,i).get(0));

                words.add(word);

                wordCards.add(prepareWordCardForWord(word));

            }

            return wordCards;


        } else {

            Log.e(TAG, "level must be lower than 101");

            return new ArrayList<WordCard>();

        }

    }

    private WordCard prepareWordCardForWord(Word word) {

        WordCard wordCard = new WordCard();

        wordCard.setWords(findSameTypeWordForChoice(word));

        wordCard.shuffle();

        wordCard.setMainWord(word);

        return wordCard;
    }

    private List<Word> findSameTypeWordForChoice(Word word) {

        List<Word> wordsForChoice = getWordsForChoices(word,getWordsListAccordingToType(word.getType()));

        return wordsForChoice;
    }

    private List<Entity> getWordsListAccordingToType(String type) {

        List<Entity> words = repository.getEntities(MemoficationDbSchema.DictionaryTable.Cols.TYPE,type);

        return words;
    }

    private List<Word> getWordsForChoices(Word meanWord,List<Entity> words){

        List<Word> wordsForChoice = new ArrayList<>();

        Random generator = new Random();

        wordsForChoice.add(meanWord);

        int i = 1;

        aLoop:while (i < 4){

            int randomInt = generator.nextInt(words.size());

            Word candidateWord = Word.class.cast(words.get(randomInt));

            for (int m = 0 ; m < wordsForChoice.size() ; m++){

                if (wordsForChoice.get(m).getId() == candidateWord.getId())
                    continue aLoop;
            }

            wordsForChoice.add(candidateWord);

            i++;

        }

        return wordsForChoice;

    }
}
