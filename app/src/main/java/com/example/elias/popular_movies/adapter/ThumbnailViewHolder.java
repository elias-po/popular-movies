package com.example.elias.popular_movies.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.elias.popular_movies.MovieDetailActivity;
import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Trailer;
import com.squareup.picasso.Picasso;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView thumbnail;
    private Trailer trailer;

    public ThumbnailViewHolder(final View itemView) {
        super(itemView);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail_image);
        itemView.setOnClickListener(this);
    }

    public void bindData (final Trailer trailer, Context context){
        String thumbnail_url = context.getResources().getString(R.string.thumbnail_base_url_yt)
                + trailer.getKey()
                + context.getResources().getString(R.string.thumbnail_hq_defualt);

        this.trailer = trailer;
        Picasso.with(itemView.getContext()).load(thumbnail_url).fit().into(thumbnail);
    }

    @Override
    public void onClick(View v) {
        Log.i("TAG", "You clicked thumbnail at position " + getAdapterPosition());
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(v.getContext().getString(R.string.trailer_uri_base_yt_app) + trailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(v.getContext().getString(R.string.trailer_uri_base_web) + trailer.getKey()));
        try {
            v.getContext().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            v.getContext().startActivity(webIntent);
        }
    }
}
