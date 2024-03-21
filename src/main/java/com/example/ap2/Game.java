package com.example.ap2;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class Game {
    static Color ball_color=Color.RED;
    static List<Circle> balls=new ArrayList<>();

    public static void game(){
        Line line=new Line(0,550,600,550);
        line.setStrokeWidth(5);
        Group root=new Group();
        root.getChildren().add(line);
        for (int i = 0; i < 10; i++) {
            Circle ball = new Circle(10);
            ball.setCenterX(300);
            ball.setCenterY(560);
            ball.setFill(ball_color);
            balls.add(ball);
        }
        for (Circle i:balls){
            root.getChildren().add(i);
        }
        Scene scene=new Scene(root,600,750);
        Main.stage.setScene(scene);
    }
}
