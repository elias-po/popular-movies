package com.example.elias.popularmovies_stage1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class PosterViewHolder extends RecyclerView.ViewHolder {
    private ImageView poster;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        poster = (ImageView) itemView.findViewById(R.id.poster_image);
    }

    public void bindData(final PosterViewModel viewModel, Context context) {
        //Picasso.with(context).load(viewModel.getUrl()).fit().into(poster);
        poster.setImageBitmap(BitmapFactory.decodeFile("~/Downloads/picasso_image.png"));
    }
}
