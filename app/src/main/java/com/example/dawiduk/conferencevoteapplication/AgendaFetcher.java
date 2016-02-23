package com.example.dawiduk.conferencevoteapplication;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.dawiduk.conferencevoteapplication.database.PresentationsDBstruct;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by dawiduk on 3-2-16.
 */
public class AgendaFetcher extends IntentService {

    private static List<ContentValues> contentList = new ArrayList<>();

    private static final String ENDPOINT = "http://10.18.121.115:80/RESTfulExample/rest/presentation/show";

    private static final String LOG_TAG = AgendaFetcher.class.getSimpleName();

    public AgendaFetcher() {
        super("AgendaFetcher");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        this.getContentResolver().delete(PresentationsDBstruct.PresentationsEntry.CONTENT_URI,null,null);
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            Presentation[] pres = gson.fromJson(response.body().string(), Presentation[].class);

            for (int i = 0; i < pres.length; i++) {
                ContentValues presentationvalues = new ContentValues();

                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.TABLE_PRESENTATION_ID, pres[i].getId());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTATION, pres[i].getPresentation());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_PRESENTER, pres[i].getPresenter());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_ROOM, pres[i].getRoom());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_START, pres[i].getStart());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_DESCRIPTION, pres[i].getDesc());
                presentationvalues.put(PresentationsDBstruct.PresentationsEntry.COLUMN_VOTES, pres[i].getVote());
                contentList.add(presentationvalues);

            }

            int inserted = 0;

            if (contentList.size() > 0) {

                ContentValues[] valuesArray = new ContentValues[contentList.size()];
                contentList.toArray(valuesArray);
                inserted = this.getContentResolver().bulkInsert(PresentationsDBstruct.PresentationsEntry.CONTENT_URI, valuesArray);
            }
            Log.d(LOG_TAG, "inserted to database = " + inserted);

        } catch (IOException e) {

            Toast toast = Toast.makeText(getApplicationContext(), "niedziala", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }
}






