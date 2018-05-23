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
import com.example.elias.popularmovies_stage1.rest.ApiClient;
import com.example.elias.popularmovies_stage1.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostersActivity extends AppCompatActivity {

    private static String image_base_url;
    private static String image_size;
    private static String request_base_url = "http://api.themoviedb.org/3";
    private static String request_popular = "/movie/popular";
    private static String request_top_rated = "/movie/top_rated";
    private static String api_param;
    private static String api_key; // initialized in onCreate()
    private static String http_response;
    private static final String TAG = PostersActivity.class.getSimpleName();

    public static List<Movie> movies = null;

    private List<PosterViewModel> generateSimpleList() {
        List<PosterViewModel> posterViewModels = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            posterViewModels.add(new PosterViewModel("https://i.imgur.com/Q1rewMk.jpg"));
        }

        return posterViewModels;
    }


    private List<PosterViewModel> generatePosterList() {
        List<PosterViewModel> posterViewModels = new ArrayList<>();

        for (Movie movie : movies) {
            posterViewModels.add(new PosterViewModel(movie.getPosterPath()));
        }

        return posterViewModels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image_base_url = getString(R.string.image_base_url);
        image_size = getString(R.string.image_size_default);
        api_param = "?" + getString(R.string.api_param);
        api_key = getString(R.string.themoviedb_api_key);

        if (api_key.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        //requestMovies();
        //List<PosterViewModel> posterViewModels = generatePosterList();


        //List<PosterViewModel> posterViewModels = generateSimpleList();

        //PosterRecyclerViewAdapter adapter = new PosterRecyclerViewAdapter(posterViewModels);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_posters);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(api_key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Movies: " + movies);
                recyclerView.setAdapter(new PosterRecyclerViewAdapter(movies));
                Log.d(TAG, "Adapter attached (onResponse)");
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //recyclerView.setAdapter(adapter);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static String getImage_base_url() {
        return image_base_url;
    }

    public static String getImage_size() {
        return image_size;
    }

    public static String getApi_param() {
        return api_param;
    }

    public static String getApi_key() {
        return api_key;
    }
}
