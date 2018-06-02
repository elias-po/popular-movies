package com.example.elias.popular_movies.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersResponse {
    @SerializedName("id")
    int id;
    @SerializedName("results")
    List<Trailer> trailers;


    public TrailersResponse(int id, List<Trailer> trailers) {
        this.id = id;
        this.trailers = trailers;
    }

    public int getId() {
        return id;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
