package com.oktaysadoglu.memofication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.oktaysadoglu.memofication.R;
import com.oktaysadoglu.memofication.database.helpers.MemoficationDatabaseHelper;
import com.oktaysadoglu.memofication.database.mappers.WordMapper;
import com.oktaysadoglu.memofication.database.repositories.Repository;
import com.oktaysadoglu.memofication.database.repositories.SqLiteRepository;
import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;

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

        testButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        MemoficationDatabaseHelper memoficationDatabaseHelper = MemoficationDatabaseHelper.getInstance(this);

        Repository repository = memoficationDatabaseHelper.getRepository(new WordMapper());

        Log.e("yo", repository.getEntities(MemoficationDbSchema.DictionaryTable.Cols.TYPE,"n.").toString());


    }
}
