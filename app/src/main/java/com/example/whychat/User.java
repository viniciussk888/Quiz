package com.example.whychat;

public class User {
    private String uuid;
    private String username;
    private String profileUrl;
    private int score;

    public User() {
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User(String uuid, String username, String profileUrl, int score) {
        this.uuid = uuid;
        this.username = username;
        this.profileUrl = profileUrl;
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public int getScore() {return score; }
}
