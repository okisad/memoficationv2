package com.oktaysadoglu.memofication.fragments.game_fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.BaseActivity;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<WordCard> wordCards = new ArrayList<>();
    private BaseActivity baseActivity;

    public SwipeDeckAdapter(/*List<WordCard> wordCards,*/ BaseActivity baseActivity) {

        Word w1 = new Word();
        w1.setId((long) 3);
        w1.setType("n");
        w1.setWord("log");
        w1.setMean("log");

        Word w2 = new Word();
        w2.setId((long) 4);
        w2.setType("v");
        w2.setWord("go");
        w2.setMean("gitmek");

        Word w3 = new Word();
        w3.setId((long) 5);
        w3.setType("v");
        w3.setWord("go");
        w3.setMean("gitmek");

        Word w4 = new Word();
        w4.setId((long) 6);
        w4.setType("v");
        w4.setWord("go");
        w4.setMean("gitmek");

        WordCard wordCard = new WordCard();

        wordCard.setMainWord(w1);
        wordCard.setFirstOptionWord(w1);
        wordCard.setSecondOptionWord(w2);
        wordCard.setThirdOptionWord(w3);
        wordCard.setFourthOptionWord(w4);

        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);
        wordCards.add(wordCard);



        setWordCards(wordCards);
        setBaseActivity(baseActivity);
    }

    @Override
    public int getCount() {
        return getWordCards().size();
    }

    @Override
    public Object getItem(int position) {
        return wordCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = getBaseActivity().getLayoutInflater();

            view = inflater.inflate(R.layout.card_word, parent, false);

        }

        setOrderText(view,position);

        List<Button> buttons = getButtons(view);

        fillWordAndChoices(view,position);

        setButtonsClickListeners(buttons,parent,position);

        return view;
    }

    private void fillWordAndChoices(View view,int position){

        TextView mainWordText = (TextView) view.findViewById(R.id.word_card_card_layout_main_word_text);

        Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
        Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
        Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
        Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);

        mainWordText.setText(getWordCards().get(position).getMainWord().getWord());

        firstOption.setText(getWordCards().get(position).getFirstOptionWord().getMean());
        secondOption.setText(getWordCards().get(position).getSecondOptionWord().getMean());
        thirdOption.setText(getWordCards().get(position).getThirdOptionWord().getMean());
        fourthOption.setText(getWordCards().get(position).getFourthOptionWord().getMean());

    }

    private List<Button> getButtons(View view){

        Button firstOption = (Button) view.findViewById(R.id.word_card_card_layout_first_button);
        Button secondOption = (Button) view.findViewById(R.id.word_card_card_layout_second_button);
        Button thirdOption = (Button) view.findViewById(R.id.word_card_card_layout_third_button);
        Button fourthOption = (Button) view.findViewById(R.id.word_card_card_layout_fourth_button);

        List<Button> buttonsBundle = new ArrayList<>();
        buttonsBundle.add(firstOption);
        buttonsBundle.add(secondOption);
        buttonsBundle.add(thirdOption);
        buttonsBundle.add(fourthOption);

        return buttonsBundle;

    }

    private void setButtonsClickListeners(List<Button> buttons,ViewGroup parent,int position){

        /*buttons.get(0).setOnClickListener(new FirstCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(1).setOnClickListener(new SecondCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(2).setOnClickListener(new ThirdCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(3).setOnClickListener(new FourthCardButtonClickListener(getGameActivity(),parent,getGameActivity().getCardStack(),buttons,getWordCards().get(position)));*/

    }

    private void setOrderText(View view,int position){

        TextView orderText = (TextView) view.findViewById(R.id.word_card_card_layout_order_text);

        orderText.setText((position+1)+"/50");

    }

    public List<WordCard> getWordCards() {
        return wordCards;
    }

    public void setWordCards(List<WordCard> wordCards) {
        this.wordCards = wordCards;
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
}