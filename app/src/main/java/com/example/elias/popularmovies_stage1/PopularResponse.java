package com.example.elias.popularmovies_stage1;

public class PopularResponse {
    Integer page;
    Movie[] results;
    Integer total_results;
    Integer total_pages;

    public Integer getPage() {
        return page;
    }

    public Movie[] getResults() {
        return results;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setResults(Movie[] results) {
        this.results = results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }
}
