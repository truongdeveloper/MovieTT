package com.example.moviett.ApiContainer;

import com.example.moviett.ApiMovieDetail.Result;

import java.io.Serializable;
import java.util.List;

public class SearchMovie implements Serializable {

    private boolean success;

    private List<SearchMovieItem> data;

    public SearchMovie(boolean success, List<SearchMovieItem> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<SearchMovieItem> getData() {
        return data;
    }

    public void setData(List<SearchMovieItem> data) {
        this.data = data;
    }
}
