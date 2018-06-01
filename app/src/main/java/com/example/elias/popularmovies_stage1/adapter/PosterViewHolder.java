package com.example.elias.popularmovies_stage1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.elias.popularmovies_stage1.MovieDetailActivity;
import com.example.elias.popularmovies_stage1.R;
import com.example.elias.popularmovies_stage1.model.Movie;
import com.squareup.picasso.Picasso;

import static com.example.elias.popularmovies_stage1.Utils.isFavourite;


public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView poster;
    private ImageView star_btn;
    private Movie movie;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster_image);
        star_btn = (ImageView) itemView.findViewById(R.id.star_btn);
        itemView.setOnClickListener(this);
    }


    public void bindData(final Movie movie, Context context) {
        String poster_url = context.getResources().getString(R.string.image_base_url) + context.getResources().getString(R.string.image_size_default) +
                movie.getPosterPath();
        this.movie = movie;
        Picasso.with(itemView.getContext()).load(poster_url).fit().into(poster);
        if(isFavourite())
            star_btn.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_on));
        else
            star_btn.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_star_big_off));
    }

    @Override
    public void onClick(View v) {
        Log.i("TAG", "You clicked cell at position " + getAdapterPosition());
        Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(MovieDetailActivity.movie_id, movie.getId().toString());
        extras.putString(MovieDetailActivity.movie_title, movie.getTitle());
        extras.putString(MovieDetailActivity.movie_release_date, movie.getReleaseDate());
        extras.putString(MovieDetailActivity.movie_user_rating, movie.getVoteAverage().toString());
        extras.putString(MovieDetailActivity.movie_overview, movie.getOverview());
        String poster_url = v.getResources().getString(R.string.image_base_url)
                + v.getResources().getString(R.string.image_size_default)
                + movie.getPosterPath();
        extras.putString(MovieDetailActivity.movie_poster_url, poster_url);
        intent.putExtras(extras);

        v.getContext().startActivity(intent);
    }
}
