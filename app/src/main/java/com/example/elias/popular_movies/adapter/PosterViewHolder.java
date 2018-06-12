package com.example.elias.popular_movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.elias.popular_movies.MovieDetailActivity;
import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Movie;
import com.squareup.picasso.Picasso;

import static com.example.elias.popular_movies.PostersActivity.HIDE_STARS;
import static com.example.elias.popular_movies.Utils.isFavourite;


public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView poster;
    private ImageView star_btn;
    private Movie movie;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster_image);
        star_btn = (ImageView) itemView.findViewById(R.id.star_btn);
        if(HIDE_STARS) {
            itemView.setOnClickListener(this);
        } else {
            poster.setOnClickListener(this);
            star_btn.setOnClickListener(this);
        }
    }


    public void bindData(final Movie movie, Context context) {
        String poster_url = context.getResources().getString(R.string.image_base_url) + context.getResources().getString(R.string.image_size_default) +
                movie.getPosterPath();
        this.movie = movie;
        Picasso.with(itemView.getContext()).load(poster_url).fit().into(poster);
        if(HIDE_STARS){
            star_btn.setVisibility(View.INVISIBLE);
        } else {
            if (isFavourite(context))
                star_btn.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_on));
            else
                star_btn.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_off));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == poster.getId() || HIDE_STARS) {
            Log.i("TAG", "You clicked cell at position " + getAdapterPosition());
            Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);

            Bundle extras = new Bundle();
            extras.putString(MovieDetailActivity.MOVIE_ID_KEY, movie.getId().toString());
            extras.putString(MovieDetailActivity.MOVIE_TITLE_KEY, movie.getTitle());
            extras.putString(MovieDetailActivity.MOVIE_RELEASE_DATE_KEY, movie.getReleaseDate());
            extras.putString(MovieDetailActivity.MOVIE_USER_RATING_KEY, movie.getVoteAverage().toString());
            extras.putString(MovieDetailActivity.MOVIE_OVERVIEW_KEY, movie.getOverview());
            String poster_url = v.getResources().getString(R.string.image_base_url)
                    + v.getResources().getString(R.string.image_size_default)
                    + movie.getPosterPath();
            extras.putString(MovieDetailActivity.MOVIE_POSTER_URL_KEY, poster_url);
            intent.putExtras(extras);

            v.getContext().startActivity(intent);
        } else {
            Log.i("TAG", "You clicked STAR at position " + getAdapterPosition());
        }
    }
}
