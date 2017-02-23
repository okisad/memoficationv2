package com.oktaysadoglu.memofication.fragments.game_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.BaseActivity;
import com.oktaysadoglu.memofication.fragments.game_fragment.adapter.SwipeDeckAdapter;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.WordCard;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class GameFragment extends Fragment {

    @BindView(R.id.swipe_deck)
    SwipeDeck cardStack;

    private SwipeDeckAdapter swipeDeckAdapter;

    private ArrayList<WordCard> wordCards;

    private int level;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        level = getArguments().getInt("level");

        View view = inflater.inflate(R.layout.fragment_game,container,false);

        ButterKnife.bind(this,view);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        setSwipeDeckAdapter();
    }

    public void setSwipeDeckAdapter() {

        int startPoint = (level-1)*50 + 1;

        /*Memofication.getJobManager().addJobInBackground(new WriteWordCardJob(startPoint,startPoint+50));*/

        setSwipeDeckAdapter(new SwipeDeckAdapter((BaseActivity) getActivity(),level,this));

        cardStack.setAdapter(getSwipeDeckAdapter());

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
            }

            @Override
            public void cardSwipedRight(int position) {
            }

            @Override
            public void cardsDepleted() {
                Log.i("Main_Activity", "no more cards");
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }

        });


        cardStack.setLeftImage(R.id.left_image);

        cardStack.setRightImage(R.id.right_image);
    }

    public SwipeDeckAdapter getSwipeDeckAdapter() {
        return swipeDeckAdapter;
    }

    public void setSwipeDeckAdapter(SwipeDeckAdapter swipeDeckAdapter) {
        this.swipeDeckAdapter = swipeDeckAdapter;
    }

    public SwipeDeck getCardStack() {
        return cardStack;
    }
}
