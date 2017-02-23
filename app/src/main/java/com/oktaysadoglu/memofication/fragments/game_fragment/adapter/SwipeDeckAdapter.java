package com.oktaysadoglu.memofication.fragments.game_fragment.adapter;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.BaseActivity;
import com.oktaysadoglu.memofication.fragments.game_fragment.GameFragment;
import com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners.FirstCardButtonClickListener;
import com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners.FourthCardButtonClickListener;
import com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners.SecondCardButtonClickListener;
import com.oktaysadoglu.memofication.fragments.game_fragment.card_listeners.ThirdCardButtonClickListener;
import com.oktaysadoglu.memofication.fragments.game_fragment.logic.WordCardLogic;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;
import com.oktaysadoglu.memofication.navigation.settings_listeners.OnClickListenerFragmentChange;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktaysadoglu on 10/05/16.
 */
public class SwipeDeckAdapter extends BaseAdapter {

    private List<WordCard> wordCards = new ArrayList<>();
    private BaseActivity baseActivity;
    private GameFragment gameFragment;

    public SwipeDeckAdapter(/*List<WordCard> wordCards,*/ BaseActivity baseActivity, int level, GameFragment gameFragment) {

        this.gameFragment = gameFragment;

        WordCardLogic wordCardLogic = new WordCardLogic(new ArrayList<WordCard>());

        setWordCards(wordCardLogic.getWordCards(level));
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

        buttons.get(0).setOnClickListener(new FirstCardButtonClickListener(getBaseActivity(),parent,gameFragment.getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(1).setOnClickListener(new SecondCardButtonClickListener(getBaseActivity(),parent,gameFragment.getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(2).setOnClickListener(new ThirdCardButtonClickListener(getBaseActivity(),parent,gameFragment.getCardStack(),buttons,getWordCards().get(position)));
        buttons.get(3).setOnClickListener(new FourthCardButtonClickListener(getBaseActivity(),parent,gameFragment.getCardStack(),buttons,getWordCards().get(position)));

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