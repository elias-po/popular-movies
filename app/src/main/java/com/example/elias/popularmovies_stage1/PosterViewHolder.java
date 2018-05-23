package com.example.elias.popularmovies_stage1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elias.popularmovies_stage1.model.Movie;
import com.squareup.picasso.Picasso;


public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView poster;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster_image);
        itemView.setOnClickListener(this);
    }

    public void bindData(final PosterViewModel viewModel, Context context) {
        Picasso.with(itemView.getContext()).load(viewModel.getUrl()).fit().into(poster);
        //poster.setImageResource(R.drawable.ic_launcher_foreground);
    }

    public void bindData(final Movie movie, Context context) {
        String poster_url = context.getResources().getString(R.string.image_base_url) + context.getResources().getString(R.string.image_size_default) +
                movie.getPosterPath();

        Picasso.with(itemView.getContext()).load(poster_url).fit().into(poster);
        //poster.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public void onClick(View v) {
        Log.i("TAG", "You clicked cell at position " + getAdapterPosition());
        //getAdapterPosition();
        //TODO: launch MovieDetailActivity
    }
}
