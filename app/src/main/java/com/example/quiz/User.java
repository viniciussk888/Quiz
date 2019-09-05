package com.example.quiz;

public class User {
    private String uuid;
    private String username;
    private String profileUrl;
    private int score;
    private String codAluno;
    private String curso;

    public User() {
    }
    public User(String uuid, String username, String profileUrl, int score, String codAluno, String curso) {
        this.uuid = uuid;
        this.username = username;
        this.profileUrl = profileUrl;
        this.score = score;
        this.codAluno = codAluno;
        this.curso = curso;
    }
    public void setCodAluno(String codAluno) {
        this.codAluno = codAluno;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodAluno() {
        return codAluno;
    }

    public String getCurso() {
        return curso;
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
