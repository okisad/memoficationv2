package com.oktaysadoglu.memofication.fragments.level_list_fragment;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.game_fragment.GameFragment;
import com.oktaysadoglu.memofication.tools.GetColorFilter;

/**
 * Created by oktaysadoglu on 05/02/16.
 */

public class LevelListRecyclerViewAdapter extends RecyclerView.Adapter<LevelListRecyclerViewAdapter.CardViewLevelListHolder> {

    private static int itemNumber = 100;

    private FragmentActivity mFragmentActivity;

    public LevelListRecyclerViewAdapter(FragmentActivity fragmentActivity) {

        mFragmentActivity = fragmentActivity;

    }

    @Override
    public CardViewLevelListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_list_card_view,parent,false);

        ImageView star1 = (ImageView) view.findViewById(R.id.card_view_level_list_first_star);
        ImageView star2 = (ImageView) view.findViewById(R.id.card_view_level_list_second_star);
        ImageView star3 = (ImageView) view.findViewById(R.id.card_view_level_list_third_star);

        int color = R.color.blue_grey_100;

        star1.setColorFilter(GetColorFilter.getColorFilter("#"+Integer.toHexString(ContextCompat.getColor(parent.getContext(),color))));
        star2.setColorFilter(GetColorFilter.getColorFilter("#"+Integer.toHexString(ContextCompat.getColor(parent.getContext(),color))));
        star3.setColorFilter(GetColorFilter.getColorFilter("#"+Integer.toHexString(ContextCompat.getColor(parent.getContext(),color))));

        return new CardViewLevelListHolder(view,mFragmentActivity);
    }

    @Override
    public void onBindViewHolder(CardViewLevelListHolder holder, int position) {

        holder.setTextView(position);

    }

    @Override
    public int getItemCount() {
        return itemNumber;
    }

    public static class CardViewLevelListHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;

        private int level;

        public CardViewLevelListHolder(View itemView, final FragmentActivity fragmentActivity) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent,new GameFragment()).commit();

                    /*Intent intent = new Intent(fragmentActivity, GameActivity.newClass(ReversePreferences.isReverse(fragmentActivity)));
                    intent.putExtra(INTENT_LEVEL,level);
                    fragmentActivity.startActivity(intent);*/

                }
            });

            mTextView = (TextView) itemView.findViewById(R.id.card_view_level_list_level_number);

        }

        public void setTextView(int position){

            level = (position+1);

            mTextView.setText(String.valueOf(level));

        }
    }

}
