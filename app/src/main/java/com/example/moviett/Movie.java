package com.example.moviett;

public class Movie {
    private String srcImg;
    private String title;
    private float vote;

    public Movie( String srcImg, String title, float vote) {
        this.srcImg = srcImg;
        this.title = title;
        this.vote = vote;
    }

    public String getSrcImg() {
        return srcImg;
    }

    public void setSrcImg(String srcImg) {
        this.srcImg = srcImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }
}