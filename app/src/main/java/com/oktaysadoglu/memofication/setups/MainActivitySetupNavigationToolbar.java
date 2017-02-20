package com.oktaysadoglu.memofication.setups;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.fragments.FirstFragment;
import com.oktaysadoglu.memofication.fragments.SecondFragment;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class MainActivitySetupNavigationToolbar {

    private AppCompatActivity appCompatActivity;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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

        setupNavigationToolbar();

        setupDrawerContent();

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
