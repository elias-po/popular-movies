package com.example.elias.popular_movies;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.elias.popular_movies.Utils.isFavourite;
import static com.example.elias.popular_movies.Utils.setThumbnailsAdapter;

public class MovieDetailActivity extends AppCompatActivity {
    public static String api_key; // initialized in onCreate() from a string resource themoviedb_api_key

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
    Button movieFavBtn;
    RecyclerView trailer_thumbnails_rv;

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
        movieFavBtn = findViewById(R.id.add_to_fav_btn);

        movieTitleTv.setText(movie_title);
        movieReleaseDateTv.setText(movie_release_date.substring(0, 4));
        movieUserRatingTv.setText(movie_user_rating.substring(2,3).equals("0")
                ? movie_user_rating.substring(0,1) + "/10" : movie_user_rating + "/10");
        movieOverviewTv.setText(movie_overview);
        Picasso.with(this).load(movie_poster_url)
                .fit()
                .into(moviePosterIV);

        if(isFavourite()) {
            movieFavBtn.setText(R.string.fav_btn_remove);
        movieFavBtn.setBackgroundColor(Color.YELLOW);
        } else {
            movieFavBtn.setText(R.string.fav_btn_add);
            movieFavBtn.setBackgroundColor(Color.GRAY);
        }

        api_key = getString(R.string.themoviedb_api_key);

        if (api_key.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        //PosterRecyclerViewAdapter adapter = new PosterRecyclerViewAdapter(posterViewModels);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_thumbnails);
        this.trailer_thumbnails_rv = recyclerView;
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        setThumbnailsAdapter(trailer_thumbnails_rv, Integer.valueOf(movie_id));

        setTitle("Movie Details");

    }
}
