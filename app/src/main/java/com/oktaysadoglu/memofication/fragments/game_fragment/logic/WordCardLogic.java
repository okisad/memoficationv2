package com.oktaysadoglu.memofication.fragments.game_fragment.logic;

import android.util.Log;

import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;

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

    public WordCardLogic(List<WordCard> wordCards) {
        this.wordCards = wordCards;
    }

    public List<WordCard> getWordCards(int level) {

        int startPoint = (level-1)*50 + 1;

        if (level <= 100) {

            List<Word> dicWords = Memofication.words;

            for (int i = startPoint ; i < startPoint+50 ; i++){

                try {

                    Word word = dicWords.get(i-1);

                    wordCards.add(prepareWordCardForWord(word));

                }catch (IndexOutOfBoundsException e){

                }


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

    private List<Word> getWordsListAccordingToType(String type) {

        List<Word> words;

        switch (type) {
            case "a":
                words = Memofication.wordsA;
                break;
            case "adj.":
                words = Memofication.wordsAdj;
                break;
            case "adv.":
                words = Memofication.wordsAdv;
                break;
            case "conj.":
                words = Memofication.wordsConj;
                break;
            case "n.":
                words = Memofication.wordsN;
                break;
            case "prep.":
                words = Memofication.wordsPrep;
                break;
            case "pron.":
                words = Memofication.wordsPron;
                break;
            case "v.":
                words = Memofication.wordsV;
                break;
            default:
                words = Memofication.words;
                break;
        }

        return words;
    }

    private List<Word> getWordsForChoices(Word meanWord,List<Word> words){

        List<Word> wordsForChoice = new ArrayList<>();

        Random generator = new Random();

        wordsForChoice.add(meanWord);

        int i = 1;

        aLoop:while (i < 4){

            int randomInt = generator.nextInt(words.size());

            Word candidateWord = words.get(randomInt);

            for (int m = 0 ; m < wordsForChoice.size() ; m++){

                if (wordsForChoice.get(m).getId().equals(candidateWord.getId()))
                    continue aLoop;
            }

            wordsForChoice.add(candidateWord);

            i++;

        }

        return wordsForChoice;

    }
}
