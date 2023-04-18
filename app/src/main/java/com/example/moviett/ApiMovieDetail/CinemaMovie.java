package com.example.moviett.ApiMovieDetail;

public class CinemaMovie {
    private String movieName;
    private String movieTheaterName;
    private String time;
    private int imgTheater;

    public CinemaMovie(String movieName, String movieTheaterName, String time, int imgTheater) {
        this.movieName = movieName;
        this.movieTheaterName = movieTheaterName;
        this.time = time;
        this.imgTheater = imgTheater;
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
