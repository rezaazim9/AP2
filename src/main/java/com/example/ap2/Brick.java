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
    static double x = 0.2;
    static double y = 0.2;
    int count2;
    int special;

    public Brick(Label label, Rectangle rectangle, int count ,int count2,int special) {
        this.label = label;
        this.rectangle = rectangle;
        this.count = count;
        this.count2=count2;
        this.special=special;
    }
    static SecureRandom random = new SecureRandom();
    public static int num=0;
    public static void brickMaker(Group root) {
        for (int k=0;k<30;k++){
        for (int i = 0; i < 6; i++) {
            int j = random.nextInt(2);
            int h= random.nextInt(6);
            if (j == 1) {
                Rectangle rectangle = new Rectangle(100, 50, Color.PERU);
                if (h==1){
                    rectangle.setFill(Color.ORANGE);
                }
                if (h==2){
                    rectangle.setFill(Color.BLUE);
                }
                Label label = new Label();
                label.setFont(new Font(35));
                Brick brick = new Brick(label, rectangle,k+10+num,k+10+num,0);
                if (rectangle.getFill()==Color.BLUE){
                    brick.special=1;
                }else if (rectangle.getFill()==Color.ORANGE){
                    brick.special=2;
                }
                label.setText(STR."\{brick.count}");
                rectangle.setX(100 * i);
                rectangle.setY(-50*k-50);
                label.setLayoutX(rectangle.getX() + 30);
                label.setLayoutY(rectangle.getY());
                Game.brickList.add(brick);
                root.getChildren().add(brick.rectangle);
                root.getChildren().add(brick.label);
            }
        }
        }
    }
}

