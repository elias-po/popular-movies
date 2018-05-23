package com.example.elias.popularmovies_stage1.rest;

import android.content.Context;

import com.example.elias.popularmovies_stage1.PostersActivity;
import com.example.elias.popularmovies_stage1.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BASE_URL;
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        BASE_URL = context.getString(R.string.movies_base_url);
        if(BASE_URL.isEmpty())
            BASE_URL = "http://api.themoviedb.org/3/";

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

