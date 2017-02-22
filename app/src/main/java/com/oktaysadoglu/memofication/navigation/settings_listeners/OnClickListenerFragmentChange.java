package com.oktaysadoglu.memofication.navigation.settings_listeners;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.SecondFragment;
import com.oktaysadoglu.memofication.fragments.level_list_fragment.LevelListFragment;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class OnClickListenerFragmentChange implements View.OnClickListener{

    private AppCompatActivity appCompatActivity;

    private DrawerLayout drawerLayout;

    public OnClickListenerFragmentChange(AppCompatActivity appCompatActivity, DrawerLayout drawerLayout) {

        this.appCompatActivity = appCompatActivity;

        this.drawerLayout = drawerLayout;

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.nav_first_fragment:
                fragment = LevelListFragment.newInstance();
                break;
            case R.id.nav_second_fragment:
                fragment = new SecondFragment();
                break;
        }

        appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent,fragment).commit();

        drawerLayout.closeDrawers();
    }
}
