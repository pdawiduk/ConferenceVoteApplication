package com.example.dawiduk.conferencevoteapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by dawiduk on 3-2-16.
 */
public class AgendaFetcher extends IntentService {

    private static final String ENDPOINT = "http://10.0.2.2:8080/RESTfulExample/rest/presentation/show";

    private static final String LOG_TAG = AgendaFetcher.class.getSimpleName();


    private List<Presentation> presentationList = new ArrayList<>();


    public AgendaFetcher() {
        super("AgendaFetcher");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(ENDPOINT)
                .build();

        try {
            okhttp3.Response response = client.newCall(request).execute();


         Presentation[] pres = gson.fromJson(response.body().string(),Presentation[].class);

            Log.d(LOG_TAG,pres[3].getPresentation());
            Toast toast = Toast.makeText(getApplicationContext(), presentationList.size(), Toast.LENGTH_SHORT);
                    toast.show();
        } catch (IOException e) {

            Toast toast = Toast.makeText(getApplicationContext(), "niedziala", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }
}






