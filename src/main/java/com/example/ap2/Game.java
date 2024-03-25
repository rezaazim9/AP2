package com.example.ap2;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    static Color ball_color = Color.RED;
    Button mainMenu = new Button();
    static final List<Brick> brickList = new ArrayList<>();
    Button restart = new Button();
    Button back = new Button();
    Button pause = new Button();
    Button resume = new Button();
    static List<Ball> balls = new ArrayList<>();
    static double counter = 0;
    static double x = 0.1;
    static double y = 0.1;
    static double counterBrick = 0;
    static double z = 0.005;


    static Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<>() {
        boolean ballsMoving = false;

        @Override
        public void handle(ActionEvent actionEvent) {
            if (counterBrick <= 0) {
                Brick.num++;
                Brick.brickMaker(root);
                counterBrick = 30;
            }
            for (Ball ball : balls) {
                if (ball.isMoving) {
                    ballsMoving = true;
                    break;
                }
                ballsMoving = false;
            }
            for (Brick brick : brickList) {
                brick.rectangle.setY(brick.rectangle.getY() + y);
                brick.label.setLayoutY(brick.label.getLayoutY() + x);
                if (!ballsMoving) {
                    brick.rectangle.setY(brick.rectangle.getY() + 20);
                    brick.label.setLayoutY(brick.label.getLayoutY() + 20);
                    balls.getFirst().isMoving = true;
                    x = 0.2;
                    y = 0.2;
                    z = 0.005;

                }
                if (brick.rectangle.getY() + brick.rectangle.getHeight() >= 550) {
                    timeline.stop();
                }
            }

            for (Ball i : balls) {
                for (Brick j : brickList) {
                    if (i.circle.getBoundsInParent().intersects(j.rectangle.getBoundsInParent())) {
                        j.count--;
                        j.label.setText(STR."\{j.count}");
                    }
                    if (counter >= balls.indexOf(i)) {
                        if (root.getChildren().contains(j.rectangle)) {
                            checkBrick(j, i);
                        }
                        checkScene(i);
                        i.circle.setLayoutY(i.circle.getLayoutY() + i.y);
                        i.circle.setLayoutX(i.circle.getLayoutX() + i.x);
                    }
                    if (j.count == 0) {
                        root.getChildren().remove(j.rectangle);
                        root.getChildren().remove(j.label);
                    }
                }
                counterBrick -= z;
                counter += 0.005;
            }
        }
    }));

    public static void checkBrick(Brick brick, Ball ball) {
        if (ball.circle.getBoundsInParent().intersects(brick.rectangle.getBoundsInParent())) {
            if (ball.circle.getLayoutX() >= brick.rectangle.getX() && ball.circle.getLayoutX() <= brick.rectangle.getWidth() + brick.rectangle.getX()) {
                ball.y *= -1;
            } else if (ball.circle.getLayoutY() >= brick.rectangle.getY() && ball.circle.getLayoutY() <= brick.rectangle.getHeight() + brick.rectangle.getY()) {
                ball.x *= -1;
            } else {
                ball.y *= -1;
                ball.x *= -1;
            }
        }

    }

    public static void checkScene(Ball ball) {
        boolean right = ball.circle.getLayoutX() <= ball.circle.getRadius();
        boolean left = ball.circle.getLayoutX() >= 600 - ball.circle.getRadius();
        boolean bottom = ball.circle.getLayoutY() >= 550;
        boolean up = ball.circle.getLayoutY() <= ball.circle.getRadius();
        if ((up)) {
            ball.y *= -1;
        }
        if ((right || left)) {
            ball.x *= -1;
        }
        if (bottom) {
            ball.y = 0;
            ball.x = 0;
            if (balls.getFirst() == ball) {
                ball.circle.setLayoutY(549);
            }
            ball.isMoving = false;
            ball.circle.setLayoutY(balls.getFirst().circle.getLayoutY());
            ball.circle.setLayoutX(balls.getFirst().circle.getLayoutX());
        }

    }

    static Group root;

    public void game() {

        Line line = new Line(0, 550, 600, 550);
        line.setStrokeWidth(5);
        root = new Group();
        root.getChildren().add(line);
        for (int i = 0; i < 15; i++) {
            Ball ball = new Ball(0, 0, new Circle(15), false);
            ball.circle.setLayoutX(300);
            ball.circle.setLayoutY(549);
            ball.circle.setFill(ball_color);
            balls.add(ball);
        }
        for (Ball i : balls) {
            root.getChildren().add(i.circle);
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
        pause.setLayoutX(300);
        pause.setLayoutY(580);
        pause.setPrefWidth(150);
        pause.setPrefHeight(40);
        pause.setText("Pause");
        pause.setFont(new Font(18));
        pause.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> timeline.pause());
        resume.setLayoutX(300);
        resume.setLayoutY(630);
        resume.setPrefWidth(150);
        resume.setPrefHeight(40);
        resume.setText("Resume");
        resume.setFont(new Font(18));
        resume.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> timeline.play());
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
        root.getChildren().add(back);
        root.getChildren().add(restart);
        root.getChildren().add(mainMenu);
        root.getChildren().add(pause);
        root.getChildren().add(resume);
        Scene scene = getScene(root, balls);
        Main.stage.setScene(scene);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Scene getScene(Group root, List<Ball> balls) {
        Scene scene = new Scene(root, 600, 750);
        scene.setOnMouseClicked(e -> {
            boolean ballsMoving = false;
            for (Ball i : balls) {
                if (i.circle.getLayoutY() < 549) {
                    ballsMoving = true;
                    break;
                }
            }
            if (!ballsMoving) {
                counter = 0;
                for (Ball i : balls) {
                    i.y = 4 * (-i.circle.getLayoutY() + e.getY()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));
                    i.x = 4 * (-i.circle.getLayoutX() + e.getX()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));
                    i.isMoving = true;
                    x = 0;
                    y = 0;
                    z = 0;
                }
            }
        });
        return scene;
    }
}
