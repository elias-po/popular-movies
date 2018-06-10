package com.example.elias.popular_movies.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.data.FavouriteContract;
import com.example.elias.popular_movies.model.Movie;

public class FavouriteMovieAdapter extends RecyclerView.Adapter {

    private Cursor favMovieCursor;
    private Context context;


    public FavouriteMovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster_rv_item, parent, false);
        this.context = parent.getContext();
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie;

        try{
            favMovieCursor.moveToPosition(position);
        } catch (Exception e){
            Log.d(FavouriteMovieAdapter.class.getSimpleName(), "EMPTY CURSOR");
            return;
        }

        Integer movie_id = favMovieCursor.getInt(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_MOVIE_ID));
        String title = favMovieCursor.getString(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_TITLE));
        String release_date = favMovieCursor.getString(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_RELEASE_DATE));
        String vote_avg = favMovieCursor.getString(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_VOTE_AVERAGE));
        String overview = favMovieCursor.getString(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_OVERVIEW));
        String poster_path = favMovieCursor.getString(favMovieCursor.getColumnIndex(FavouriteContract.FavMovieEntry.COLUMN_POSTER_URL));
        movie = new Movie(poster_path.substring(31, poster_path.length()), true, overview, release_date, null, movie_id, null,
                null, title, null, null, null, false, Double.valueOf(vote_avg));

        ((PosterViewHolder) holder).bindData(movie, context);
    }

    @Override
    public int getItemCount() {
        if(favMovieCursor == null)
            return 0;
        else
            return favMovieCursor.getCount();
    }

    public Cursor setCursor(Cursor c) {
        // check if this favMovieCursor is the same as the previous favMovieCursor (mCursor)
        if (favMovieCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = favMovieCursor;
        this.favMovieCursor = c; // new favMovieCursor value assigned

        //check if this is a valid favMovieCursor, then update the favMovieCursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

}
