package com.example.elias.popularmovies_stage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elias.popularmovies_stage1.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static String movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}
