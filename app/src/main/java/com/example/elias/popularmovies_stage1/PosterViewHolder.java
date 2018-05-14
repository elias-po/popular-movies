package com.example.elias.popularmovies_stage1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PosterViewHolder extends RecyclerView.ViewHolder {
    private TextView simpleTextView;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        simpleTextView = (TextView) itemView.findViewById(R.id.poster_text);
    }

    public void bindData(final PosterViewModel viewModel) {
        simpleTextView.setText(viewModel.getSimpleText());
    }
}
