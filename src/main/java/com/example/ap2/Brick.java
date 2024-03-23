package com.example.ap2;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Brick extends Node {
    int count;
    Rectangle rectangle;

    public Brick(int count, Rectangle rectangle) {
        this.count = count;
        this.rectangle = rectangle;
    }
}
