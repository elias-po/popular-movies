package com.example.elias.popularmovies_stage1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.elias.popularmovies_stage1.adapter.PosterRecyclerViewAdapter;
import com.example.elias.popularmovies_stage1.model.Movie;
import com.example.elias.popularmovies_stage1.model.MoviesResponse;
import com.example.elias.popularmovies_stage1.model.PosterViewModel;
import com.example.elias.popularmovies_stage1.rest.ApiClient;
import com.example.elias.popularmovies_stage1.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostersActivity extends AppCompatActivity {

    private static String api_key; // initialized in onCreate()
    private static final String TAG = PostersActivity.class.getSimpleName();
    private RecyclerView rv_reference;


    public static List<Movie> movies = null;

    private List<PosterViewModel> generateSimpleList() {
        List<PosterViewModel> posterViewModels = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            posterViewModels.add(new PosterViewModel("https://i.imgur.com/Q1rewMk.jpg"));
        }

        return posterViewModels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        api_key = getString(R.string.themoviedb_api_key);

        if (api_key.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        //PosterRecyclerViewAdapter adapter = new PosterRecyclerViewAdapter(posterViewModels);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posters);
        rv_reference = recyclerView;
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        showPopularMovies(); // sets a new adapter with according movies to rv_reference


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_posters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_top_rated) {
            showTopRatedMovies();
            return true;
        }

        if (id == R.id.action_order_popular){
            showPopularMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void showPopularMovies(){
        ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(api_key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Movies: " + movies);
                rv_reference.setAdapter(new PosterRecyclerViewAdapter(movies));
                Log.d(TAG, "Adapter attached (onResponse)");
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    protected void showTopRatedMovies(){
        ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(api_key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Movies: " + movies);
                rv_reference.setAdapter(new PosterRecyclerViewAdapter(movies));
                Log.d(TAG, "Adapter attached (onResponse)");
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

}
