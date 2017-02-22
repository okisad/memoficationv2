package com.oktaysadoglu.memofication.navigation;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.FirstFragment;
import com.oktaysadoglu.memofication.fragments.SecondFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class MainActivitySetupNavigationToolbar implements View.OnClickListener{

    private AppCompatActivity appCompatActivity;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private TextView firstFragment;
    private TextView secondFragment;

    public MainActivitySetupNavigationToolbar(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
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

        firstFragment = (TextView) appCompatActivity.findViewById(R.id.nav_first_fragment);
        secondFragment = (TextView) appCompatActivity.findViewById(R.id.nav_second_fragment);

        firstFragment.setOnClickListener(this);
        secondFragment.setOnClickListener(this);

        setupNavigationToolbar();

    }

    private void setupNavigationToolbar(){
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

    }


    public void setProfileImage(AppCompatActivity appCompatActivity,Uri uri){

        ImageView profilePictureImage = (ImageView) appCompatActivity.findViewById(R.id.nav_header_profile_picture);

        Picasso.with(appCompatActivity)
                .load(uri.toString())
                .into(profilePictureImage);

    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.nav_first_fragment:
                fragment = new FirstFragment();
                break;
            case R.id.nav_second_fragment:
                fragment = new SecondFragment();
                break;
        }

        appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.flContent,fragment).commit();

        drawerLayout.closeDrawers();

    }
}
