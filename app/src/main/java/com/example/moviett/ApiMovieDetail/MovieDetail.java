package com.example.moviett.ApiMovieDetail;

import android.media.Image;

public class MovieDetail {

    // https://movie-pro.onrender.com/api/v1/movie/19995?language=vi
    private boolean success;
    private Data data;
    private Credit credits;
    private Similar similar;

    private Videos videos;

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
    public MovieDetail(Videos videos) {
        this.videos = videos;
    }

    public MovieDetail(boolean success, Data data, Credit credits, Similar similar) {
        this.success = success;
        this.data = data;
        this.credits = credits;
        this.similar = similar;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Credit getCredits() {
        return credits;
    }

    public void setCredits(Credit credits) {
        this.credits = credits;
    }

    public Similar getSimilar() {
        return similar;
    }

    public void setSimilar(Similar similar) {
        this.similar = similar;
    }
}
