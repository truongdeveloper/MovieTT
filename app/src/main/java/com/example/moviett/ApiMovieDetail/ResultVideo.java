package com.example.moviett.ApiMovieDetail;

import java.io.Serializable;

public class ResultVideo implements Serializable {

    private String name;
    private String key;
    private String site;
    private int size;
    private String type;
    private boolean official;
    private String id;

    public ResultVideo(String name, String key, String site, int size, String type, boolean official, String id) {
        this.name = name;
        this.key = key;
        this.site = site;
        this.size = size;
        this.type = type;
        this.official = official;
        this.id = id;
    }

    public String getNameTrailer() {
        return name;
    }

    public void setNameTrailer(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public String getIdTrailer() {
        return id;
    }

    public void setIdTrailer(String id) {
        this.id = id;
    }
}
