package com.example.moviett.ApiContainer;


import java.util.List;

public class ListMovie {

    private boolean success;
    private List<MovieApi> trandingMovies;
    private List<MovieApi> nowPlayingMovies;
    private List<MovieApi> topRatedMovies;
    private List<MovieApi> trandingtv;
    private List<MovieApi> topRatedTv;
    private List<MovieApi> upcoming;

    public ListMovie(boolean success, List<MovieApi> randingMovies, List<MovieApi> nowPlayingMovies, List<MovieApi> topRatedMovies, List<MovieApi> trandingtv, List<MovieApi> topRatedTv, List<MovieApi> upcoming) {
        this.success = success;
        this.trandingMovies = trandingMovies;
        this.nowPlayingMovies = nowPlayingMovies;
        this.topRatedMovies = topRatedMovies;
        this.trandingtv = trandingtv;
        this.topRatedTv = topRatedTv;
        this.upcoming = upcoming;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<MovieApi> getRandingMovies() {
        return trandingMovies;
    }

    public void setRandingMovies(List<MovieApi> randingMovies) {
        this.trandingMovies = randingMovies;
    }

    public List<MovieApi> getNowPlayingMovies() {
        return nowPlayingMovies;
    }

    public void setNowPlayingMovies(List<MovieApi> nowPlayingMovies) {
        this.nowPlayingMovies = nowPlayingMovies;
    }

    public List<MovieApi> getTopRatedMovies() {
        return topRatedMovies;
    }

    public void setTopRatedMovies(List<MovieApi> topRatedMovies) {
        this.topRatedMovies = topRatedMovies;
    }

    public List<MovieApi> getTrandingtv() {
        return trandingtv;
    }

    public void setTrandingtv(List<MovieApi> trandingtv) {
        this.trandingtv = trandingtv;
    }

    public List<MovieApi> getTopRatedTv() {
        return topRatedTv;
    }

    public void setTopRatedTv(List<MovieApi> topRatedTv) {
        this.topRatedTv = topRatedTv;
    }

    public List<MovieApi> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<MovieApi> upcoming) {
        this.upcoming = upcoming;
    }
}