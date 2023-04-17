package com.example.moviett.ApiMovieDetail;

import com.example.moviett.ApiContainer.MovieApi;

import java.util.List;

public class CinemaMovie {
    private MovieApi movies;
    private List<String> movieTheaterName;
    private List<String> time;

    public CinemaMovie(MovieApi movies, List<String> movieTheaterName, List<String> time) {
        this.movies = movies;
        this.movieTheaterName = movieTheaterName;
        this.time = time;
    }

    public MovieApi getMovies() {
        return movies;
    }

    public void setMovies(MovieApi movies) {
        this.movies = movies;
    }

    public List<String> getMovieTheaterName() {
        return movieTheaterName;
    }

    public void setMovieTheaterName(List<String> movieTheaterName) {
        this.movieTheaterName = movieTheaterName;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
