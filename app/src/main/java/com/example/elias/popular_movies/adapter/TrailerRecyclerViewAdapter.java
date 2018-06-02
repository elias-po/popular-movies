package com.example.elias.popular_movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Trailer;

import java.util.List;

public class TrailerRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Trailer> trailers;
    private Context context;

    public TrailerRecyclerViewAdapter(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        this.context = parent.getContext();
        return new ThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ThumbnailViewHolder) holder).bindData(trailers.get(position), context);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.thumbnail_rv_item;
    }
}
