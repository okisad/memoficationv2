package com.oktaysadoglu.memofication.navigation;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.navigation.settings_listeners.OnCheckedChangeNotificationStatus;
import com.oktaysadoglu.memofication.navigation.settings_listeners.OnClickListenerFragmentChange;
import com.oktaysadoglu.memofication.navigation.settings_listeners.OnClickNotificationNumber;
import com.oktaysadoglu.memofication.socialLogins.pojos.SocialUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class MainActivitySetupNavigationToolbar{

    private AppCompatActivity appCompatActivity;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.nvView)
    public NavigationView navigationView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @BindView(R.id.nav_first_fragment)
    public TextView firstFragmentTextView;
    @BindView(R.id.nav_second_fragment)
    public TextView secondFragmentTextView;
    @BindView(R.id.navigation_notification_on_off_switch)
    public Switch notificationOnOffSwitch;
    @BindView(R.id.activity_main_navigation_view_number_of_notification_button)
    public Button notificationNumberButton;

    public MainActivitySetupNavigationToolbar(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public ActionBarDrawerToggle setup(){
        bindItems();
        return actionBarDrawerToggle;
    }

    private void bindItems(){

        ButterKnife.bind(this,appCompatActivity);

        actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        setListeners();

        setupNavigationToolbar();

    }

    private void setListeners(){

        firstFragmentTextView.setOnClickListener(new OnClickListenerFragmentChange(appCompatActivity,drawerLayout));

        secondFragmentTextView.setOnClickListener(new OnClickListenerFragmentChange(appCompatActivity,drawerLayout));

        notificationOnOffSwitch.setOnCheckedChangeListener(new OnCheckedChangeNotificationStatus());

        notificationNumberButton.setOnClickListener(new OnClickNotificationNumber(appCompatActivity));

    }

    private void setupNavigationToolbar(){
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

    }


    public void setProfileImage(AppCompatActivity appCompatActivity){

        ImageView profilePictureImage = (ImageView) appCompatActivity.findViewById(R.id.nav_header_profile_picture);

        Picasso.with(appCompatActivity)
                .load(SocialUser.getPhotoUri().toString())
                .into(profilePictureImage);

    }

    public void setProfileName(AppCompatActivity appCompatActivity){

        TextView profileNameTextView = (TextView) appCompatActivity.findViewById(R.id.nav_header_profile_name);

        profileNameTextView.setText(SocialUser.getName());

    }

}
