package com.example.ap2;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.security.SecureRandom;
import java.util.Random;

public class Brick extends Node {
    Label label;
    int count;
    Rectangle rectangle;

    public Brick(Label label, Rectangle rectangle,int count) {
        this.label = label;
        this.rectangle = rectangle;
        this.count=count;
    }
    static SecureRandom random = new SecureRandom();
    static int n = random.nextInt(0,5);
    public static void brickMaker(){
        Rectangle rectangle = new Rectangle(100, 50, Color.PERU);
        Label label = new Label();
        label.setFont(new Font(35));
        Brick brick = new Brick(label, rectangle,10);
        label.setText(STR."\{brick.count}");
        rectangle.setX(100*n);
        rectangle.setY(-50);
        label.setLayoutX(rectangle.getX()+30);
        label.setLayoutY(rectangle.getY());
        Game.brickList.add(brick);
    }
}
