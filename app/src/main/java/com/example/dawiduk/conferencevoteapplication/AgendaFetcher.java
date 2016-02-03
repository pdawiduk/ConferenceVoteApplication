package com.example.dawiduk.conferencevoteapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by dawiduk on 3-2-16.
 */
public class AgendaFetcher {

    private static final String URL= "http://localhost:8080/RESTfulExample/rest/presentation/";
    private static final String SHOW_METHOD = "show";
    private static final String CHANGE_VOTE="change";
    private static final String GET_TIME ="time";

    private List<Presentation> presentationList= new ArrayList<Presentation>();

    public void fetchAgenda(){
        Retrofit client = new Retrofit.Builder()
                .baseUrl(URL +SHOW_METHOD)
                .build();
    }


}

