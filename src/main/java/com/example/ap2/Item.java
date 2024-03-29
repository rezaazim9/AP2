package com.example.ap2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static com.example.ap2.Brick.random;

public class Item {
    Circle circle;
    int item;

   static int h=2;

    public Item(Circle circle, int item) {
        this.circle = circle;
        this.item = item;
    }

    static List<Item> itemList = new ArrayList<>();

    public static void itemMaker(Group root) {
        for (int k = 0; k < 30; k++) {
            for (int i = 0; i < 11; i++) {
                 int j = random.nextInt(h);
                int h = random.nextInt(5);
                boolean isEmpty=true;
                for (Brick brick : Game.brickList) {
                    if ((brick.rectangle.getX()==100*i&&brick.rectangle.getY()==-50*k-100)) {
                        isEmpty=false;
                        break;
                    }
                }
                if (j==1&&isEmpty){
                    Circle circle = new Circle(10);
                    Item item1 = new Item(circle, h);
                    item1.circle.setLayoutX(100 * i+50 );
                    item1.circle.setLayoutY(-k * 50 - 75);
                    if (item1.item == 1) {
                        item1.circle.setFill(Color.RED);
                    } else if (item1.item == 2) {
                        item1.circle.setFill(Color.GOLD);
                    } else if (item1.item == 3) {
                        item1.circle.setFill(Color.GREEN);
                    } else if (item1.item == 0) {
                        item1.circle.setFill(Color.GRAY);
                    }
                    else if (item1.item==4){
                        item1.circle.setFill(Color.RED);
                    }
                    itemList.add(item1);
                    root.getChildren().add(item1.circle);
                }
            }
        }
    }
    public static void redItem(){
        Ball newBall = new Ball(0, 0, new Circle(15), false);
        newBall.circle.setLayoutX(Game.balls.getFirst().circle.getLayoutX());
        newBall.circle.setLayoutY(549);
        newBall.circle.setFill(Game.ball_color);
        Game.balls.add(newBall);
    }
}
