package com.example.ap2;

public class Player {
    private String name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int score;
    private String date;


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(String name, int score, String  date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }
}
