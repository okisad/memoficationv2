package com.oktaysadoglu.memofication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.database.labs.MemoficationDBLab;
import com.oktaysadoglu.memofication.server_services.DictionaryService;
import com.oktaysadoglu.memofication.server_services.VersionOnTaskCompleted;
import com.oktaysadoglu.memofication.settings.UpdatePreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.test_button)
    Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);

        /*final AppCompatActivity appCompatActivity = this;*/

        testButton.setOnClickListener(/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoficationDBLab memoficationDBLab = MemoficationDBLab.getInstance(appCompatActivity);

                *//*memoficationDBLab.open();*//*

                Word word = new Word(Long.valueOf(1),"a ","fg","g");

                Word word2 = new Word(Long.valueOf(2),"b ","fg","g");

                List<Word> words = new ArrayList<Word>();
                words.add(word);
                words.add(word2);


                memoficationDBLab.addWords(words);

                List<Word> words1 = memoficationDBLab.getAllWords();

                System.out.println(words1.toString());

                memoficationDBLab.close();
            }
        }*/this);

    }

    @Override
    public void onClick(View view) {

        final AppCompatActivity appCompatActivity = this;

        UpdatePreferences.setVersionNumber(this,1);

        DictionaryService dictionaryService = new DictionaryService(new VersionOnTaskCompleted() {
            @Override
            public void onTaskCompleted(int i) {

                MemoficationDBLab dbLab = MemoficationDBLab.getInstance(appCompatActivity);

                dbLab.open(i);

                System.out.println("version "+ i);

                System.out.println(dbLab.getAllWords().toString());

                dbLab.close();

            }
        },this);

        dictionaryService.getVersionNumber();

    }
}
