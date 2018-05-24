package com.example.elias.popularmovies_stage1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.elias.popularmovies_stage1.MovieDetailActivity;
import com.example.elias.popularmovies_stage1.model.PosterViewModel;
import com.example.elias.popularmovies_stage1.R;
import com.example.elias.popularmovies_stage1.model.Movie;
import com.squareup.picasso.Picasso;


public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView poster;
    private Movie movie;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster_image);
        itemView.setOnClickListener(this);
    }


    public void bindData(final Movie movie, Context context) {
        String poster_url = context.getResources().getString(R.string.image_base_url) + context.getResources().getString(R.string.image_size_default) +
                movie.getPosterPath();
        this.movie = movie;
        Picasso.with(itemView.getContext()).load(poster_url).fit().into(poster);
    }

    @Override
    public void onClick(View v) {
        Log.i("TAG", "You clicked cell at position " + getAdapterPosition());
        //getAdapterPosition();
        //TODO: launch MovieDetailActivity
        Intent intent = new Intent(v.getContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.movie_id, movie.getId());
        v.getContext().startActivity(intent);
    }
}
