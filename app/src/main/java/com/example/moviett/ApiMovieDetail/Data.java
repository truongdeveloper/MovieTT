package com.example.moviett.ApiMovieDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.intellij.lang.annotations.Language;

import java.util.Collection;
import java.util.List;

public class Data {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    List<Genres> genres;
    private int id;
    private String original_title;
    private String overview;
    // Ngày phát hành
    private String release_date;
    // Doanh thu
    private String title;
    private float vote_average;

    public Data(String backdropPath, List<Genres> genres, int id, String original_title, String overview, String release_date, String title, float vote_average) {
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
    }

    public String getBackdropPath() {
        return ("https://image.tmdb.org/t/p/w500" + backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}