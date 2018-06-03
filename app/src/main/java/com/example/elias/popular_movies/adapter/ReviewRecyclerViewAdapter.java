package com.example.elias.popular_movies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Review;

import java.util.List;

public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Review> reviews;

    public ReviewRecyclerViewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ReviewViewHolder) holder).bindData(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.review_rv_item;
    }
}
