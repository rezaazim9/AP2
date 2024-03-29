package com.example.ap2;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.security.SecureRandom;

public class SpecialItem {
    static SecureRandom random = new SecureRandom();

    public static void orange(double time) {
        if (time < 50) {
            for (Ball ball : Game.balls) {
                int one = random.nextInt(256);
                int two = random.nextInt(256);
                int three = random.nextInt(256);
                ball.circle.setFill(Color.color((double) one / 256, (double) two / 256, (double) three / 256));
            }
            for (Brick brick : Game.brickList) {
                int one = random.nextInt(256);
                int two = random.nextInt(256);
                int three = random.nextInt(256);
                brick.rectangle.setFill(Color.color((double) one / 256, (double) two / 256, (double) three / 256));
            }
            for (Item item : Item.itemList) {
                int one = random.nextInt(256);
                int two = random.nextInt(256);
                int three = random.nextInt(256);
                item.circle.setFill(Color.color((double) one / 256, (double) two / 256, (double) three / 256));
            }
        } else {
            for (Ball ball : Game.balls) {

                ball.circle.setFill(Game.ball_color);
            }
            for (Brick brick : Game.brickList) {
                brick.rectangle.setFill(Color.PERU);
                if (brick.special == 1) {
                    brick.rectangle.setFill(Color.BLUE);
                }
                if (brick.special == 2) {
                    brick.rectangle.setFill(Color.ORANGE);
                }
            }
            for (Item item1 : Item.itemList) {
                if (item1.item == 1) {
                    item1.circle.setFill(Color.RED);
                } else if (item1.item == 2) {
                    item1.circle.setFill(Color.GOLD);
                } else if (item1.item == 3) {
                    item1.circle.setFill(Color.GREEN);
                } else if (item1.item == 0) {
                    item1.circle.setFill(Color.GRAY);
                } else if (item1.item == 4) {
                    item1.circle.setFill(Color.RED);
                }
            }
        }
    }

    public static void blue(double time) {
        if (time < 500) {
            for (Brick brick : Game.brickList) {
                if (time%100>50){
                    brick.rectangle.setHeight(brick.rectangle.getHeight()+0.25);
                    brick.rectangle.setWidth(brick.rectangle.getWidth()+0.5);
                    brick.label.setFont(new Font(brick.label.getFont().getSize()+0.5));

                }
                else {
                    brick.rectangle.setHeight(brick.rectangle.getHeight() - 0.25);
                    brick.rectangle.setWidth(brick.rectangle.getWidth() - 0.5);
                    brick.label.setFont(new Font(brick.label.getFont().getSize()-0.5));
                }
            }
        } else {
            for (Brick brick : Game.brickList) {
                brick.rectangle.setHeight(50);
                brick.rectangle.setWidth(100);
                brick.label.setFont(new Font(35));

            }
        }
    }
}
