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

    public SecondCardButtonClickListener(Context context, ViewGroup parent, SwipeDeck cardStack, List<Button> buttonsBundle, WordCard wordCard) {
        super(context,parent,cardStack,buttonsBundle,wordCard);

        setButton(buttonsBundle.get(1));

        setOrder(2);

    }

}
