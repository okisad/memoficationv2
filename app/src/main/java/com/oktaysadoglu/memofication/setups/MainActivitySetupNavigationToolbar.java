package com.oktaysadoglu.memofication.setups;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.oktaysadoglu.memofication.Memofication;
import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.activities.LoginActivity;
import com.oktaysadoglu.memofication.fragments.FirstFragment;
import com.oktaysadoglu.memofication.fragments.SecondFragment;
import com.oktaysadoglu.memofication.socialLogins.FacebookLoginUtil;
import com.oktaysadoglu.memofication.socialLogins.GooglePlusLoginUtil;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class MainActivitySetupNavigationToolbar {

    private AppCompatActivity appCompatActivity;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Button logoutButton;
    private int platform;

    public MainActivitySetupNavigationToolbar(AppCompatActivity appCompatActivity,int platform) {
        this.appCompatActivity = appCompatActivity;
        this.platform = platform;
    }

    public ActionBarDrawerToggle setup(){
        bindItems();
        return actionBarDrawerToggle;
    }

    private void bindItems(){

        toolbar = (Toolbar) appCompatActivity.findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) appCompatActivity.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) appCompatActivity.findViewById(R.id.nvView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        setupNavigationToolbar();

        setupDrawerContent();

        setLogoutButton();

    }

    private void setLogoutButton(){

        logoutButton = (Button) appCompatActivity.findViewById(R.id.activity_main_navigation_view_logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (platform == GooglePlusLoginUtil.GOOGLE_LOGIN){

                    Auth.GoogleSignInApi.signOut(Memofication.getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            Log.e("my","signout");
                        }
                    });

                }else if (platform == FacebookLoginUtil.FACEBOOK_LOGIN){

                    LoginManager.getInstance().logOut();

                    Intent intent = new Intent(appCompatActivity,LoginActivity.class);

                    appCompatActivity.startActivity(intent);

                }

            }
        });

    }

    private void setupNavigationToolbar(){
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

    }

    private void setupDrawerContent() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){

        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.nav_first_fragment:
                fragment = FirstFragment.newInstance();
                break;
            case R.id.nav_second_fragment:
                fragment = SecondFragment.newInstance();
                break;
            case R.id.nav_third_fragment:
                break;
        }

        appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent,fragment).commit();

        menuItem.setChecked(true);

        appCompatActivity.setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();

    }
}
