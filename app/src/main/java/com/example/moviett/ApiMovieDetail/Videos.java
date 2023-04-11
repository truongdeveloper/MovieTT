package com.example.moviett.ApiMovieDetail;

import java.io.Serializable;
import java.util.List;

public class Videos implements Serializable {

    private int id;
    private List<ResultVideo> results;

    public Videos(int id, List<ResultVideo> results) {
        this.id = id;
        this.results = results;
    }

    public int getIdMovie() {
        return id;
    }

    public void setIdMovie(int id) {
        this.id = id;
    }

    public List<ResultVideo> getResults() {
        return results;
    }

    public void setResults(List<ResultVideo> results) {
        this.results = results;
    }
}
