package com.example.elias.popularmovies_stage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static String movie_id = "MOVIE_ID";
    public static String movie_title = "MOVIE_TITLE";
    public static String movie_release_date = "MOVIE_RELEASE_DATE";
    public static String movie_user_rating = "MOVIE_USER_RATING";
    public static String movie_overview = "MOVIE_OVERVIEW";
    public static String movie_poster_url = "MOVIE_POSTER_URL";

    TextView movieTitleTv;
    TextView movieReleaseDateTv;
    TextView movieUserRatingTv;
    TextView movieOverviewTv;
    ImageView moviePosterIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        Bundle extras = intent.getExtras();

        movie_id = extras.getString(movie_id);
        movie_title = extras.getString(movie_title);
        movie_release_date = extras.getString(movie_release_date);
        movie_user_rating = extras.getString(movie_user_rating);
        movie_overview = extras.getString(movie_overview);
        movie_poster_url = extras.getString(movie_poster_url);

        movieTitleTv = findViewById(R.id.tv_movie_title);
        movieReleaseDateTv = findViewById(R.id.tv_movie_release_date);
        movieUserRatingTv = findViewById(R.id.tv_user_rating);
        movieOverviewTv = findViewById(R.id.tv_movie_overview);
        moviePosterIV = findViewById(R.id.iv_poster);

        movieTitleTv.setText(movie_title);
        movieReleaseDateTv.setText(movie_release_date.substring(0, 4));
        movieUserRatingTv.setText(movie_user_rating.substring(2,3).equals("0")
                ? movie_user_rating.substring(0,1) + "/10" : movie_user_rating + "/10");
        movieOverviewTv.setText(movie_overview);
        Picasso.with(this).load(movie_poster_url)
                .fit()
                .into(moviePosterIV);

        setTitle("Movie Details");

    }
}
