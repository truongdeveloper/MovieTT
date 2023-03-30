package com.example.moviett.ApiMovieDetail;

public class MovieDetail {

    // https://movie-pro.onrender.com/api/v1/movie/19995?language=vi
    private boolean success;
    private Data data;
    private Video videos;
    private Image images;
    private Credit credits;
    private Similar similar;

    public MovieDetail(boolean success, Data data, Video videos, Image images, Credit credits, Similar similar) {
        this.success = success;
        this.data = data;
        this.videos = videos;
        this.images = images;
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

    public Video getVideos() {
        return videos;
    }

    public void setVideos(Video videos) {
        this.videos = videos;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
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
