package com.oktaysadoglu.memofication.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.dialogs.MainActivityExitDialog;
import com.oktaysadoglu.memofication.fragments.game_fragment.GameFragment;
import com.oktaysadoglu.memofication.fragments.level_list_fragment.LevelListFragment;
import com.oktaysadoglu.memofication.navigation.MainActivitySetupNavigationToolbar;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    protected  MainActivitySetupNavigationToolbar mainActivitySetupNavigationToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivitySetupNavigationToolbar = new MainActivitySetupNavigationToolbar(this);

        actionBarDrawerToggle = mainActivitySetupNavigationToolbar.setup();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setStartFragment(LevelListFragment.newInstance());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setStartFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent,fragment).commit();

    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().findFragmentById(R.id.flContent) instanceof GameFragment){

            super.onBackPressed();

        }else {

            MainActivityExitDialog.build(this);

        }



    }

    public MainActivitySetupNavigationToolbar getMainActivitySetupNavigationToolbar() {
        return mainActivitySetupNavigationToolbar;
    }
}
