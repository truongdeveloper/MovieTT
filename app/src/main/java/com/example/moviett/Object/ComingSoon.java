package com.example.moviett.Object;

import java.sql.Date;
import java.sql.Time;

public class ComingSoon {
    private int resourceId; // Id của ảnh trong thư mục drawable
    private String nameFilm; // Tên của phim
    private Date dateFilm; // Thời gian phát hành
    private int timeFilm; // Thời lượng phim (số phút)
    private String typeFilm; // Thể loại phim
    private String contentFilm; // Nội dung giới thiệu phim

    public ComingSoon(int resourceId, String nameFilm, Date dateFilm, int timeFilm, String typeFilm, String contentFilm) {
        this.resourceId = resourceId;
        this.nameFilm = nameFilm;
        this.dateFilm = dateFilm;
        this.timeFilm = timeFilm;
        this.typeFilm = typeFilm;
        this.contentFilm = contentFilm;
    }

    public ComingSoon(int resourceId, String nameFilm, String contentFilm) {
        this.resourceId = resourceId;
        this.nameFilm = nameFilm;
        this.contentFilm = contentFilm;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public Date getDateFilm() {
        return dateFilm;
    }

    public void setDateFilm(Date dateFilm) {
        this.dateFilm = dateFilm;
    }

    public int getTimeFilm() {
        return timeFilm;
    }

    public void setTimeFilm(int timeFilm) {
        this.timeFilm = timeFilm;
    }

    public String getTypeFilm() {
        return typeFilm;
    }

    public void setTypeFilm(String typeFilm) {
        this.typeFilm = typeFilm;
    }

    public String getContentFilm() {
        return contentFilm;
    }

    public void setContentFilm(String contentFilm) {
        this.contentFilm = contentFilm;
    }
}
