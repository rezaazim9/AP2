package com.example.ap2;

import javafx.scene.shape.Circle;

public class Ball {
    double x;
    double y;
    Circle circle;
    boolean isMoving;


    public Ball(double x, double y, Circle circle,boolean isMoving) {
        this.x = x;
        this.y = y;
        this.circle = circle;
        this.isMoving=isMoving;
    }

}
