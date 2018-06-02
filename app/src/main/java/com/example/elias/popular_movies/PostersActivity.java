package com.example.elias.popular_movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.elias.popular_movies.model.Movie;
import com.example.elias.popular_movies.model.PosterViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.elias.popular_movies.Utils.*;


public class PostersActivity extends AppCompatActivity {

    public static boolean HIDE_STARS = true; // whether to show stars on the posters activity
    public static String api_key; // initialized in onCreate() from a string resource themoviedb_api_key
    private static final String TAG = PostersActivity.class.getSimpleName();
    private RecyclerView recyclerView;

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
        this.recyclerView = recyclerView;
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        setPopularMoviesAdapter(this.recyclerView); // sets a new adapter with according movies to recyclerView


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.INVISIBLE);
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
            setTopRatedMoviesAdapter(recyclerView);
            return true;
        }

        if (id == R.id.action_order_popular){
            setPopularMoviesAdapter(recyclerView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
