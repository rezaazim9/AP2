package com.example.ap2;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.security.SecureRandom;

public class Brick extends Node {
    Label label;
    int count;
    Rectangle rectangle;
    static double x = 0.1;
    static double y = 0.1;

    public Brick(Label label, Rectangle rectangle, int count) {
        this.label = label;
        this.rectangle = rectangle;
        this.count = count;
    }
    static SecureRandom random = new SecureRandom();
    public static int num=9;
    public static void brickMaker(Group root) {
        for (int i = 0; i < 6; i++) {
            int j = random.nextInt(2);
            if (j == 1) {
                Rectangle rectangle = new Rectangle(100, 50, Color.PERU);
                Label label = new Label();
                label.setFont(new Font(35));
                Brick brick = new Brick(label, rectangle, num);
                label.setText(STR."\{brick.count}");
                rectangle.setX(100 * i);
                rectangle.setY(-50);
                label.setLayoutX(rectangle.getX() + 30);
                label.setLayoutY(rectangle.getY());
                Game.brickList.add(brick);
                root.getChildren().add(brick.rectangle);
                root.getChildren().add(brick.label);
            }
        }
    }
}

