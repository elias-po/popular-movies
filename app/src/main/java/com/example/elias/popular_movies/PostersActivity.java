package com.example.elias.popular_movies;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.elias.popular_movies.adapter.FavouriteMovieAdapter;
import com.example.elias.popular_movies.data.FavouriteContract;
import com.example.elias.popular_movies.model.Movie;
import com.example.elias.popular_movies.model.PosterViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.elias.popular_movies.Utils.*;


public class PostersActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    public static final String POSTERS_RV_KEY = "posters_rv_state";
    public static final String VIEW_OPTION_KEY = "movie_view_option";
    private static Parcelable posters_rv_state;
    private static int movie_view_option = 0; // 0 - most popular, 1 - highest rated, 2 - favourites

    public static boolean HIDE_STARS = true; // whether to show stars on the posters activity
    public static String api_key; // initialized in onCreate() from a string resource themoviedb_api_key
    private static final String TAG = PostersActivity.class.getSimpleName();
    private static final int TASK_LOADER_ID = 0;
    private RecyclerView recyclerView;
    private GridLayoutManager postersLayoutManager;

    private FavouriteMovieAdapter favouriteMovieAdapter;

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
        postersLayoutManager = new GridLayoutManager(this, numberOfColumns);
        recyclerView.setLayoutManager(postersLayoutManager);

        setProperAdapter(this.recyclerView); // sets a new adapter with according movies to recyclerView

        favouriteMovieAdapter = new FavouriteMovieAdapter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.INVISIBLE);

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);
    }

    private void setProperAdapter(RecyclerView rv){
        switch (movie_view_option){
            case 0:
                setPopularMoviesAdapter(this.recyclerView);
                break;
            case 1:
                setPopularMoviesAdapter(recyclerView);
                break;
            case 2:
                getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
                recyclerView.setAdapter(favouriteMovieAdapter);
                break;
            default:
                setPopularMoviesAdapter(this.recyclerView);
                break;
        }
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
            movie_view_option = 0;
            return true;
        }

        if (id == R.id.action_order_popular){
            setPopularMoviesAdapter(recyclerView);
            movie_view_option = 0;
            return true;
        }

        if (id == R.id.action_show_favourites){
            getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
            recyclerView.setAdapter(favouriteMovieAdapter);
            movie_view_option = 0;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        posters_rv_state = postersLayoutManager.onSaveInstanceState();
        outState.putParcelable(POSTERS_RV_KEY, posters_rv_state);
        outState.putInt(VIEW_OPTION_KEY, movie_view_option);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            posters_rv_state = savedInstanceState.getParcelable(POSTERS_RV_KEY);
            movie_view_option = savedInstanceState.getInt(VIEW_OPTION_KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // re-queries for all tasks
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);

        //setProperAdapter(recyclerView);

        if(posters_rv_state != null) {
            postersLayoutManager.onRestoreInstanceState(posters_rv_state);
        }
    }


    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            // Initialize a Cursor, this will hold all the task data
            Cursor favMoviesCursor = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (favMoviesCursor != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(favMoviesCursor);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                // Will implement to load data

                // Query and load all task data in the background; sort by priority
                // [Hint] use a try/catch block to catch any errors in loading data

                try {
                    return getContentResolver().query(FavouriteContract.FavMovieEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                             null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                favMoviesCursor = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        favouriteMovieAdapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        favouriteMovieAdapter.setCursor(null);
    }
}
