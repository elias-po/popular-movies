package com.example.elias.popular_movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Movie;

import java.util.List;

public class PosterRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Movie> movies;
    private Context context;

    public PosterRecyclerViewAdapter(List<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        this.context = parent.getContext();
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PosterViewHolder) holder).bindData(movies.get(position), context);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.poster_rv_item;
    }

}
