package com.oktaysadoglu.memofication.database.mappers;

import android.content.ContentValues;
import android.database.Cursor;

import com.oktaysadoglu.memofication.database.schema.MemoficationDbSchema;
import com.oktaysadoglu.memofication.fragments.game_fragment.pojo.Word;
import com.oktaysadoglu.memofication.server_services.pojo.Entity;

/**
 * Created by oktaysadoglu on 08/03/2017.
 */

public class WordMapper implements Mapper<ContentValues,Entity> {

    @Override
    public ContentValues mapToContentValues(Entity entity) {

        ContentValues contentValues = new ContentValues();

        Word word = Word.class.cast(entity);

        contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.ID,word.getId());
        contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.NAME,word.getWord());
        contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.MEAN,word.getMean());
        contentValues.put(MemoficationDbSchema.DictionaryTable.Cols.TYPE,word.getType());

        return contentValues;
    }

    @Override
    public Entity mapToEntity(Cursor cursor) {

        Word word = new Word();

        word.setId(cursor.getInt(0));
        word.setWord(cursor.getString(1));
        word.setMean(cursor.getString(2));
        word.setType(cursor.getString(3));

        return word;
    }

    @Override
    public String getTablename() {
        return MemoficationDbSchema.DictionaryTable.NAME;
    }
}
