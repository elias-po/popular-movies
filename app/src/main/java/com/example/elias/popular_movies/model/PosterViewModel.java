package com.example.elias.popular_movies.model;

import android.support.annotation.NonNull;


public class PosterViewModel {
    private String url;

    public PosterViewModel(@NonNull final String url) {
        setUrl(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
