package com.example.elias.popularmovies_stage1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elias.popularmovies_stage1.PosterViewHolder;
import com.example.elias.popularmovies_stage1.PosterViewModel;
import com.example.elias.popularmovies_stage1.R;

import java.util.ArrayList;
import java.util.List;

public class PosterRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<PosterViewModel> models;
    private Context context;

    public PosterRecyclerViewAdapter(List<PosterViewModel> models) {
        this.models = models;
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
        ((PosterViewHolder) holder).bindData(models.get(position), context);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.recyclerview_item;
    }

}
