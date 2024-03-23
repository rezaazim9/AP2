package com.example.ap2;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    static Color ball_color = Color.RED;
    Button mainMenu = new Button();
    Button restart = new Button();
    Button back = new Button();
    static List<Circle> balls = new ArrayList<>();

    public void game() {
        Line line = new Line(0, 550, 600, 550);
        line.setStrokeWidth(5);
        Group root = new Group();
        root.getChildren().add(line);
        for (int i = 0; i < 10; i++) {
            Circle ball = new Circle(15);
            ball.setCenterX(300);
            ball.setCenterY(565);
            ball.setFill(ball_color);
            balls.add(ball);
        }
        for (Circle i : balls) {
            root.getChildren().add(i);
        }
        mainMenu.setLayoutX(20);
        mainMenu.setLayoutY(680);
        mainMenu.setPrefWidth(150);
        mainMenu.setPrefHeight(40);
        mainMenu.setText("Main Menu");
        mainMenu.setFont(new Font(18));
        mainMenu.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            try {
                SceneSwitcher.mainMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        restart.setLayoutX(20);
        restart.setLayoutY(580);
        restart.setPrefWidth(150);
        restart.setPrefHeight(40);
        restart.setText("Restart");
        restart.setFont(new Font(18));
        restart.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> SceneSwitcher.game());
        back.setLayoutX(20);
        back.setLayoutY(630);
        back.setPrefWidth(150);
        back.setPrefHeight(40);
        back.setText("Back");
        back.setFont(new Font(18));
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            try {
                SceneSwitcher.gameMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        /////////////////////////////////////////////
        Rectangle rectangle = new Rectangle(100, 50, Color.PERU);
        Brick brick = new Brick(10, rectangle);
        Label label = new Label(STR."\{brick.count}");
        label.setFont(new Font(35));
        label.setLayoutX(130);
        label.setLayoutY(100);
        rectangle.setX(100);
        rectangle.setY(100);
        root.getChildren().add(rectangle);
        root.getChildren().add(label);
        ///////////////////////////////////////////////////////
        root.getChildren().add(back);
        root.getChildren().add(restart);
        root.getChildren().add(mainMenu);
        Scene scene = new Scene(root, 600, 750);
        Main.stage.setScene(scene);
    }
}
