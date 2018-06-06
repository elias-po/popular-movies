package com.example.elias.popular_movies.model;
import com.google.gson.annotations.SerializedName;

public class Review {
    public static int totalHeight = 0;

    @SerializedName("id")
    String id;
    @SerializedName("author")
    String author;
    @SerializedName("content")
    String content;
    @SerializedName("url")
    String url;


    public Review(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
