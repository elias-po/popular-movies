package com.example.elias.popularmovies_stage1;

import android.support.annotation.NonNull;


public class PosterViewModel {
    private String simpleText;

    public PosterViewModel(@NonNull final String simpleText) {
        setSimpleText(simpleText);
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }

    public String getSimpleText() {
        return simpleText;
    }
}
