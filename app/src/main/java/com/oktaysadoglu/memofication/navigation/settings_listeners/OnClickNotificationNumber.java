package com.oktaysadoglu.memofication.navigation.settings_listeners;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.settings.SettingsPreferences;
import com.oktaysadoglu.memofication.tools.DialogTools;

/**
 * Created by oktaysadoglu on 22/02/2017.
 */

public class OnClickNotificationNumber implements View.OnClickListener {

    private AppCompatActivity appCompatActivity;

    public OnClickNotificationNumber(AppCompatActivity appCompatActivity) {

        this.appCompatActivity = appCompatActivity;

    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity, R.style.AppDialogTheme);

        View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.dialog_number_of_notification, null);

        final NumberPicker picker = (NumberPicker) view.findViewById(R.id.navigation_change_number_of_notification_dialog_number_picker);

        setPickerValues(picker,0,10);

        setDisplayView(picker);

        AlertDialog alertDialog = builder
                .setView(view)
                .setTitle(R.string.navigation_number_of_notification_dialog_title)
                .setPositiveButton(R.string.navigation_number_of_notification_dialog_ok_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SettingsPreferences.setNotificationNumber(appCompatActivity,picker.getValue());

                        int value = SettingsPreferences.getNotificationNumber(appCompatActivity);

                        Button notificationNumberButton = (Button) appCompatActivity.findViewById(R.id.activity_main_navigation_view_number_of_notification_button);

                        notificationNumberButton.setText(String.valueOf(value));
                    }
                })
                .setNegativeButton(R.string.navigation_number_of_notification_dialog_cancel_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();


        alertDialog.show();

    }

    private void setPickerValues(NumberPicker numberPicker,int min,int max){

        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        numberPicker.setValue(SettingsPreferences.getNotificationNumber(appCompatActivity));

    }

    private void setDisplayView(NumberPicker numberPicker){

        DialogTools dialogTools = new DialogTools();

        dialogTools.setNumberPickerTextColor(numberPicker, ContextCompat.getColor(appCompatActivity, R.color.grey_900));
        dialogTools.setDividerColor(numberPicker, ContextCompat.getColor(appCompatActivity, R.color.grey_700));

    }
}
