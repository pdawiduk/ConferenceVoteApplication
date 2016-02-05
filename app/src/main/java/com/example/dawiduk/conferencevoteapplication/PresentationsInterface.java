package com.example.dawiduk.conferencevoteapplication;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;


/**
 * Created by dawiduk on 3-2-16.
 */
public interface PresentationsInterface {

    @GET("/show")
  Call<List<Presentation>> getPresentations();


}
