package com.example.ap2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class Game {
    static Color ball_color = Color.RED;
    Button mainMenu = new Button();
    private List<Brick> brickList = new ArrayList<>();
    Button restart = new Button();
    Button back = new Button();
    static List<Circle> balls = new ArrayList<>();
    double x;
    double y;
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Circle i = balls.getFirst();
            Brick brick1 = new Brick(10, new Rectangle(100, 50, Color.PERU));
            brick1.rectangle.setX(250);
            brick1.rectangle.setY(200);
            checkBrick(brick, i);
            checkScene(i);
            i.setLayoutY(i.getLayoutY() + y);
            i.setLayoutX(i.getLayoutX() + x);
        }
    }));


    public void checkBrick(Brick brick, Circle ball) {
        if (ball.getBoundsInParent().intersects(brick.rectangle.getBoundsInParent())) {
            if (ball.getLayoutX() >= brick.rectangle.getX() && ball.getLayoutX() <= brick.rectangle.getWidth() + brick.rectangle.getX()) {
                y *= -1;
            }else if(ball.getLayoutY() >= brick.rectangle.getY() && ball.getLayoutY() <= brick.rectangle.getHeight() + brick.rectangle.getY()){
                x *= -1;
            }
            else {
                y*=-1;
                x*=-1;
            }
        }

    }

    public void checkScene(Circle ball) {
        boolean right = ball.getLayoutX() <= ball.getRadius();
        boolean left = ball.getLayoutX() >= 600 - ball.getRadius();
        boolean bottom = ball.getLayoutY() >= 550;
        boolean up = ball.getLayoutY() <= ball.getRadius();
        if ((up || bottom)) {
            y *= -1;
        }
        if ((right || left)) {
            x *= -1;
        }
    }

    Brick brick;

    public void game() {
        Line line = new Line(0, 550, 600, 550);
        line.setStrokeWidth(5);
        Group root = new Group();
        root.getChildren().add(line);
        for (int i = 0; i < 10; i++) {
            Circle ball = new Circle(15);
            ball.setLayoutX(300);
            ball.setLayoutY(545);
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
        Rectangle rectangle = new Rectangle(100, 100, Color.PERU);
        brick = new Brick(10, rectangle);
        Label label = new Label(STR."\{brick.count}");
        label.setFont(new Font(35));
        label.setLayoutX(130);
        label.setLayoutY(100);
        rectangle.setX(250);
        rectangle.setY(200);
        root.getChildren().add(rectangle);
        root.getChildren().add(label);
        ///////////////////////////////////////////////////////
        root.getChildren().add(back);
        root.getChildren().add(restart);
        root.getChildren().add(mainMenu);
        Scene scene = new Scene(root, 600, 750);
        scene.setOnMouseClicked(e -> {
            y = 4 * (-balls.getFirst().getLayoutY() + e.getY()) / (Math.pow(Math.pow(balls.getFirst().getLayoutY() - e.getY(), 2) + Math.pow(balls.getFirst().getLayoutX() - e.getX(), 2), (double) 1 / 2));

            x = 4 * (-balls.getFirst().getLayoutX() + e.getX()) / (Math.pow(Math.pow(balls.getFirst().getLayoutY() - e.getY(), 2) + Math.pow(balls.getFirst().getLayoutX() - e.getX(), 2), (double) 1 / 2));
        });
        Main.stage.setScene(scene);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
