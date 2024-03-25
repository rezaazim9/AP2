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

    public Brick(Label label, Rectangle rectangle, int count) {
        this.label = label;
        this.rectangle = rectangle;
        this.count = count;
    }
    static SecureRandom random = new SecureRandom();
    public static int num=9;
    public static void brickMaker(Group root) {
        k:
        for (int i = 0; i < 6; i++) {
            int j = random.nextInt(0, 5);
            Rectangle rectangle = new Rectangle(100, 50, Color.PERU);
            Label label = new Label();
            label.setFont(new Font(35));
            Brick brick = new Brick(label, rectangle, num);
            label.setText(STR."\{brick.count}");
            rectangle.setX(100 * j);
            rectangle.setY(-50);
            for (Brick brick1 : Game.brickList) {
                if (brick1.rectangle.getX() == rectangle.getX() &&brick1.rectangle.getY()==rectangle.getY()) {
                    continue k;
                }
            }
            label.setLayoutX(rectangle.getX() + 30);
            label.setLayoutY(rectangle.getY());
            Game.brickList.add(brick);
            root.getChildren().add(brick.rectangle);
            root.getChildren().add(brick.label);
        }
    }
}

