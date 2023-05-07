package com.example.moviett.ApiMovieDetail;

import java.io.Serializable;

public class CinemaMovie implements Serializable {
    private int idMovie;
    private String movieName;
    private String movieTheaterName;
    private String time;
    private int imgTheater;

    public CinemaMovie(int idMovie, String movieTheaterName, String time, int imgTheater) {
        this.idMovie = idMovie;
        this.movieTheaterName = movieTheaterName;
        this.time = time;
        this.imgTheater = imgTheater;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getImgTheater() {
        return imgTheater;
    }

    public void setImgTheater(int imgTheater) {
        this.imgTheater = imgTheater;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieTheaterName() {
        return movieTheaterName;
    }

    public void setMovieTheaterName(String movieTheaterName) {
        this.movieTheaterName = movieTheaterName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
