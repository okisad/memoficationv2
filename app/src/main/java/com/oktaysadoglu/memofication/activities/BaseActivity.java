package com.oktaysadoglu.memofication.activities;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.dialogs.MainActivityExitDialog;
import com.oktaysadoglu.memofication.fragments.game_fragment.GameFragment;
import com.oktaysadoglu.memofication.fragments.level_list_fragment.LevelListFragment;
import com.oktaysadoglu.memofication.navigation.MainActivitySetupNavigationToolbar;
import com.oktaysadoglu.memofication.server_services.DictionaryService;
import com.oktaysadoglu.memofication.server_services.OnTaskCompleted;
import com.oktaysadoglu.memofication.server_services.VersionOnTaskCompleted;
import com.oktaysadoglu.memofication.server_services.pojo.User;
import com.oktaysadoglu.memofication.settings.UpdatePreferences;

/**
 * Created by oktaysadoglu on 21/02/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public static String TAG = "BaseActivity";

    ProgressDialog progress;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    protected  MainActivitySetupNavigationToolbar mainActivitySetupNavigationToolbar;

    public static User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivitySetupNavigationToolbar = new MainActivitySetupNavigationToolbar(this);

        actionBarDrawerToggle = mainActivitySetupNavigationToolbar.setup();

        updateDictionary(-1);
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

    private void updateDictionary(final int i) {

        final BaseActivity activity = this;

        if (UpdatePreferences.getVersionNumber(activity) == -1) {

            Log.e("my","-1");

            showProgress(activity);

            DictionaryService dictionaryService = new DictionaryService(new OnTaskCompleted() {
                @Override
                public void onTaskCompleted() {

                    Log.e(TAG, "getting words from server is completed");

                    UpdatePreferences.setVersionNumber(activity,i);

                    dismissProgress();

                }
            }, activity);

            dictionaryService.getAllWord();

        } else {

            Log.e("my","else ksımı");

            DictionaryService dictionaryService = new DictionaryService(new VersionOnTaskCompleted() {
                @Override
                public void onTaskCompleted(int i) {

                    int serverVersion = i;

                    if (serverVersion > UpdatePreferences.getVersionNumber(activity)) {

                        UpdatePreferences.setVersionNumber(activity, -1);

                        updateDictionary(i);

                    }

                }
            }, activity);

            dictionaryService.getVersionNumber();

        }

    }

    private void showProgress(AppCompatActivity appCompatActivity){

        progress = new ProgressDialog(appCompatActivity);
        progress.setMessage("Syncing :) ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();


    }

    private void dismissProgress(){

        progress.dismiss();

    }
}
