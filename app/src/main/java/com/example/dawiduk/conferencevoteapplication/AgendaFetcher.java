package com.example.dawiduk.conferencevoteapplication;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dawiduk on 3-2-16.
 */
public class AgendaFetcher  {

    private static final String ENDPOINT = "http://10.0.2.2:8080/RESTfulExample/rest/presentation";
    private static final String SHOW_METHOD = "show";
    private static final String CHANGE_VOTE="change";
    private static final String GET_TIME ="time";
    private static final String LOG_TAG = AgendaFetcher.class.getSimpleName();





  static   Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    static PresentationsInterface apiService =
            retrofit.create(PresentationsInterface.class);


    private  static List<Presentation> presentationList= new ArrayList<Presentation>();

public static void getPresentations(){

   PresentationsInterface api=retrofit.create(PresentationsInterface.class);
    Call<List<Presentation>> call = api.getPresentations();
    call.enqueue(new Callback<List<Presentation>>() {
        @Override
        public void onResponse(Response<List<Presentation>> response, Retrofit retrofit) {
            presentationList=response.body();
            Log.d(LOG_TAG,"udalo sie");
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(LOG_TAG,"nie udalo sie");
        }
    });


   // PresentationsInterface api =ServiceGenerator.createService(PresentationsInterface.class);

}
    }






