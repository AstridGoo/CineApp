package com.example.cineapp;

import java.util.Date;

public class Film {
    private String title;
    private String director;
    private String partners;
    private String place;
    private String seenDate;

    public Film(String title, String director, String partners, String place, String seenDate) {
        this.title = title;
        this.director = director;
        this.partners = partners;
        this.place = place;
        this.seenDate = seenDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(String seenDate) {
        this.seenDate = seenDate;
    }
}