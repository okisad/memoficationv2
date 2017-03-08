package com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oktaysadoglu on 25/04/16.
 */
public abstract class CardButtonClickListener implements View.OnClickListener {

    public static int SWIPE_CARD_SPEED = 500;

    public static int SWIPE_TRUE_CARD_DELAY = 500;

    public static int SWIPE_FALSE_CARD_DELAY = 1250;

    private WordCard wordCard;

    private List<Button> buttonsBundle;

    private SwipeDeck cardStack;

    private ViewGroup parent;

    private Context context;

    private int mainId;

    private int wordId;

    private int order;

    private Button button;

    public CardButtonClickListener(Context context, ViewGroup parent, SwipeDeck cardStack, List<Button> buttonsBundle, WordCard wordCard) {

        setWordCard(wordCard);

        setButtonsBundle(buttonsBundle);

        setParent(parent);

        setContext(context);

        setCardStack(cardStack);

    }

    public void onClick(View v){

        Log.e("my",getWordCard().toString());

        setMainId(getWordCard().getMainWord().getId());

        setWordId(getWordCard().getWords().get(getOrder()).getId());

        Handler handler = new Handler();

        if (getMainId() == getWordId())
            trueAnswerProcess(handler,getMainId(),getButton());
        else
            falseAnswerProcess(handler,getMainId(),getButton());

    }

    protected Button getButtonOfTrueAnswer(List<Button> buttonsBundle) {

        Map<Integer, Button> lookup = new HashMap<>();

        List<Word> words = getWordCard().getWords();

        lookup.put((int) (long) words.get(0).getId(), buttonsBundle.get(0));
        lookup.put((int) (long) words.get(1).getId(), buttonsBundle.get(1));
        lookup.put((int) (long) words.get(2).getId(), buttonsBundle.get(2));
        lookup.put((int) (long) words.get(3).getId(), buttonsBundle.get(3));

        return lookup.get((int) (long) getWordCard().getMainWord().getId());

    }

    protected void trueAnswerProcess(Handler handler, int mainId, Button button) {

        /*Memofication.getJobManager().addJobInBackground(new ProcessTrueAnswerWordCardJob(mainId));*/

        paintButtons(true, button);

        swipeCardAccordingToAnswer(true, handler);

    }

    protected void falseAnswerProcess(Handler handler, int mainId, Button button) {

        /*Memofication.getJobManager().addJobInBackground(new ProcessFalseAnswerWordCardJob(mainId));*/

        paintButtons(false, button);

        swipeCardAccordingToAnswer(false, handler);

    }

    private void paintButtons(boolean answer, Button button) {

        Button buttonOfTrueAnswer = getButtonOfTrueAnswer(getButtonsBundle());

        if (answer)
            button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.word_card_true_choice_background));
        else {

            button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.word_card_false_choice_background));

            if (buttonOfTrueAnswer != null) {

                buttonOfTrueAnswer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.word_card_true_choice_background));

            }

        }

    }

    private void swipeCardAccordingToAnswer(final boolean answer, Handler handler) {

        enableDisableViewGroup(getParent(), false);

        if (answer) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCardStack().swipeTopCardLeft(SWIPE_CARD_SPEED);
                    enableDisableViewGroup(getParent(), true);
                }
            }, SWIPE_TRUE_CARD_DELAY);

        } else {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCardStack().swipeTopCardRight(SWIPE_CARD_SPEED);
                    enableDisableViewGroup(getParent(), true);
                }
            }, SWIPE_FALSE_CARD_DELAY);

        }

    }

    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }

    public WordCard getWordCard() {
        return wordCard;
    }

    public void setWordCard(WordCard wordCard) {
        this.wordCard = wordCard;
    }

    public List<Button> getButtonsBundle() {
        return buttonsBundle;
    }

    public void setButtonsBundle(List<Button> buttonsBundle) {
        this.buttonsBundle = buttonsBundle;
    }

    public SwipeDeck getCardStack() {
        return cardStack;
    }

    public void setCardStack(SwipeDeck cardStack) {
        this.cardStack = cardStack;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
