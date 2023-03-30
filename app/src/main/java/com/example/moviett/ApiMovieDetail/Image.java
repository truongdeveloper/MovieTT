package com.example.moviett.ApiMovieDetail;

import java.util.List;

public class Image {
    //private List<T> backdrops;
    private int id;
    //private List<T> logos;
    private List<Poster> posters;

    public Image(int id, List<Poster> posters) {
        this.id = id;
        this.posters = posters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }
}
