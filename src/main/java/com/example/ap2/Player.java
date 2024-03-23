package com.example.ap2;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Player {
    private String name;
    private int score;
    private Date date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(String name, int score,Date date){
        this.name=name;
        this.score=score;
        this.date=date;
    }
}
