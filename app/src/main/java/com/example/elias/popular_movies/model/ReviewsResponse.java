package com.example.elias.popular_movies.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {
    @SerializedName("id")
    int id;
    @SerializedName("page")
    int page;
    @SerializedName("results")
    List<Review> reviews;
    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("total_results")
    int total_results;


    public ReviewsResponse(int id, int page, List<Review> reviews, int total_pages, int total_results) {
        this.id = id;
        this.page = page;
        this.reviews = reviews;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
