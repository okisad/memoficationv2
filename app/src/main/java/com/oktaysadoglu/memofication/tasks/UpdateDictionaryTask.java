package com.oktaysadoglu.memofication.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by oktaysadoglu on 08/03/2017.
 */

public class UpdateDictionaryTask extends AsyncTask<String,Integer,Boolean> {

    private Activity activity;
    private ProgressDialog progressDialog;

    public UpdateDictionaryTask(Activity activity,ProgressDialog progressDialog) {

        this.activity = activity;

        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        /*final int currentVersion = UpdatePreferences.getVersionNumber(activity);

        DictionaryService versionService = new DictionaryService(new VersionOnTaskCompleted() {
            @Override
            public void onTaskCompleted(int i) {
                final int remoteVersion = i;

                DictionaryService dictionaryService = new DictionaryService(new OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted() {
                        if (remoteVersion == currentVersion){

                            progressDialog.dismiss();

                        }else if (remoteVersion > currentVersion){

                            List<Word> words = Memofication.words;

                            SqLiteRepository memoficationDBLab = SqLiteRepository.getInstance(activity);

                            memoficationDBLab.open();

                            int progress = 1;

                            for (Word word : words){

                                memoficationDBLab.addWord(word);

                                int m = ((progress*100)/words.size());

                                publishProgress(m);

                                Log.e("yo",String.valueOf(m));

                                progress++;

                            }

                            progressDialog.dismiss();

                        }
                    }
                },activity);

                dictionaryService.getAllWord(progressDialog);
            }
        },activity);

        versionService.getVersionNumber(progressDialog);

        return true;*/

        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
