package com.oktaysadoglu.memofication.dialogs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.oktaysadoglu.memofication.R;

/**
 * Created by oktaysadoglu on 20/02/2017.
 */

public class MainActivityExitDialog {

    public static void build(final AppCompatActivity appCompatActivity){


        AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity, R.style.AppDialogTheme);
        View view = LayoutInflater.from(appCompatActivity).inflate(R.layout.dialog_exit, null);

        AlertDialog alertDialog = builder
                .setView(view)
                .setTitle(R.string.dialog_exit_title)
                .setPositiveButton(R.string.dialog_exit_yes_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        appCompatActivity.startActivity(startMain);
                    }
                })
                .setNegativeButton(R.string.dialog_exit_no_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();


        alertDialog.show();

    }

}
