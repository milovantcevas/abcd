package com.example.abcd;

public class Event {
    private int id;
    private String data;
    private String title;
    private String description;
    private String clubId;

    public Event(){

    }

    public Event(int id, String data, String title, String description, String clubId) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.description = description;
        this.clubId = clubId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
