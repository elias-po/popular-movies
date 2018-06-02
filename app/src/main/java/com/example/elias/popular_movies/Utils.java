package com.example.elias.popular_movies;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.elias.popular_movies.adapter.PosterRecyclerViewAdapter;
import com.example.elias.popular_movies.adapter.TrailerRecyclerViewAdapter;
import com.example.elias.popular_movies.model.Movie;
import com.example.elias.popular_movies.model.MoviesResponse;
import com.example.elias.popular_movies.model.Trailer;
import com.example.elias.popular_movies.model.TrailersResponse;
import com.example.elias.popular_movies.rest.ApiClient;
import com.example.elias.popular_movies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Utils {


    public static boolean isFavourite(){
        // TODO: check if ID is already added to favourites
        return false;
    }

    // Most of the retrofit-related code (throughout the app) is inspired by the guide on https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/

    public static void setPopularMoviesAdapter(final RecyclerView rv_reference){
        ApiInterface apiService =
                ApiClient.getClient(rv_reference.getContext()).create(ApiInterface.class);

        String api_key = rv_reference.getContext().getString(R.string.themoviedb_api_key);
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

    public static void setTopRatedMoviesAdapter(final RecyclerView rv_reference){
        ApiInterface apiService =
                ApiClient.getClient(rv_reference.getContext()).create(ApiInterface.class);

        String api_key = rv_reference.getContext().getString(R.string.themoviedb_api_key);
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

    public static void showMovieTitle(final TextView tv_reference, final int movie_id){
        ApiInterface apiService =
                ApiClient.getClient(tv_reference.getContext()).create(ApiInterface.class);

        String api_key = tv_reference.getContext().getString(R.string.themoviedb_api_key);
        Call<MoviesResponse> call = apiService.getPopularMovies(api_key);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Movies: " + movies);
                tv_reference.setText(movie_id);
                Log.d(TAG, "Adapter attached (onResponse)");
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    public static void setThumbnailsAdapter(final RecyclerView rv_reference, final int movie_id){
        ApiInterface apiService =
                ApiClient.getClient(rv_reference.getContext()).create(ApiInterface.class);

        String api_key = rv_reference.getContext().getString(R.string.themoviedb_api_key);
        Call<TrailersResponse> call = apiService.getMovieTrailers(movie_id, api_key);
        call.enqueue(new Callback<TrailersResponse>() {
            @Override
            public void onResponse(Call<TrailersResponse> call, Response<TrailersResponse> response) {
                int statusCode = response.code();
                List<Trailer> trailers = response.body().getTrailers();
                Log.d(TAG, "Number of trailers received: " + trailers.size());
                Log.d(TAG, "Movies: " + trailers.toString());
                rv_reference.setAdapter(new TrailerRecyclerViewAdapter(trailers));
                Log.d(TAG, "Adapter attached (onResponse)");
            }

            @Override
            public void onFailure(Call<TrailersResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
