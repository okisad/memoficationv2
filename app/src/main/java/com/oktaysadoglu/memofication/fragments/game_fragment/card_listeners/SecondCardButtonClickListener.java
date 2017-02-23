package com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;

import java.util.List;

/**
 * Created by oktaysadoglu on 11/05/16.
 */
public class SecondCardButtonClickListener extends CardButtonClickListener {

    private Button button;

    private Long mainId;

    private Long wordId;

    private WordCard wordCard;

    public SecondCardButtonClickListener(Context context, ViewGroup parent, SwipeDeck cardStack, List<Button> buttonsBundle, WordCard wordCard) {
        super(context,parent,cardStack,buttonsBundle,wordCard);

        setButton(buttonsBundle.get(1));

    }

    @Override
    public void onClick(View v) {

        Log.e("my",getWordCard().toString());

        setMainId(getWordCard().getMainWord().getId());

        setWordId(getWordCard().getWords().get(1).getId());

        Handler handler = new Handler();

        if (getMainId().equals(getWordId()))
            trueAnswerProcess(handler,getMainId(),getButton());
        else
            falseAnswerProcess(handler,getMainId(),getButton());

    }

    public Long getMainId() {
        return mainId;
    }


    public WordCard getWordCard() {
        return wordCard;
    }

    public void setWordCard(WordCard wordCard) {
        this.wordCard = wordCard;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }


}
