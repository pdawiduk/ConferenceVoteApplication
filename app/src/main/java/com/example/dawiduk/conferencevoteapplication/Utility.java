package com.example.dawiduk.conferencevoteapplication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dawiduk on 25-2-16.
 */
public class Utility {
    private static final String LOG_TAG = Utility.class.getSimpleName();
    static int hour;

    public static int actualHour() throws IOException {

        final String ENDPOINT = "http://10.18.121.91:80/RESTfulExample/rest/presentation/time";
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(ENDPOINT)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Utility.hour = Integer.valueOf(response.body().string());

            }
        });
        Log.d(LOG_TAG, "actual hour download is " + hour);

        return hour;
    }
}
