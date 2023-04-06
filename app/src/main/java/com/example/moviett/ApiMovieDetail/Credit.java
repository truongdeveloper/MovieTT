package com.example.moviett.ApiMovieDetail;

import java.util.List;

public class Credit {
    private int id;
    private List<Cast> cast;

    public Credit(int id, List<Cast> cast) {
        this.id = id;
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
