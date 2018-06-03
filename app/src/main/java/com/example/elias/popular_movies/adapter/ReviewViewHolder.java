package com.example.elias.popular_movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.elias.popular_movies.R;
import com.example.elias.popular_movies.model.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_author, tv_review;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        tv_author = (TextView) itemView.findViewById(R.id.tv_review_author);
        tv_review = (TextView) itemView.findViewById(R.id.tv_review_content);
    }

    public void bindData (final Review review){
        tv_author.setText(review.getAuthor());
        Log.d("TAG", "bind author " + review.getAuthor());
        tv_review.setText(review.getContent());
        Log.d("TAG", "bind content " + review.getContent());
    }

}
