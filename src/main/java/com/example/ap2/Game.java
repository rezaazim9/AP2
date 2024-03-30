package com.example.ap2;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {
    static Color ball_color = Color.RED;
    static SecureRandom random = new SecureRandom();
    Button mainMenu = new Button();
    static final List<Brick> brickList = new ArrayList<>();
    Button restart = new Button();
    Button back = new Button();
    Button pause = new Button();
    Button resume = new Button();
    static List<Ball> balls = new ArrayList<>();
    static double counter = 0;
    static boolean rand=false;
    static Label timeLabel = new Label();
    static Label scoreLabel = new Label();
    static double counterBrick = 0;
        static double weightTime=20;
    static double z = 0.01;
    static double x;
    static double y;
    static int score = 0;
    static double speedTime=20;
    static double time = 0;
    static boolean speed;
    static double orangeTime=0;
    static double blueTime=0;
    static boolean blue=false;
    static boolean orange=false;
    static Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<>() {
        boolean ballsMoving = false;



        @Override
        public void handle(ActionEvent actionEvent) {
            boolean minus2=false;
            time += 0.02;
            boolean weight=false;
             speed=false;
            speedTime+=0.02;
            weightTime+=0.02;
            orangeTime+=0.1;
            blueTime+=1;
            if (orange){
                SpecialItem.orange(orangeTime);

            }
             if (blue){
                SpecialItem.blue(blueTime);

            }
            if (speedTime<15){
                speed=true;
            }
            if (weightTime<15){
                weight=true;
            }
            timeLabel.setText(STR."\{time}");
            for (Ball ball : balls) {
                if (ball.isMoving) {
                    ballsMoving = true;
                    break;
                }
                ballsMoving = false;
            }
            for (Item item : Item.itemList) {
                item.circle.setLayoutY(item.circle.getLayoutY() + Brick.y);
                if (!ballsMoving) {
                    item.circle.setLayoutY((item.circle.getLayoutY() + 20));
                }
            }
            for (Brick brick : brickList) {
                brick.rectangle.setY(brick.rectangle.getY() + Brick.y);
                brick.label.setLayoutY(brick.label.getLayoutY() + Brick.x);
                if (!ballsMoving) {
                    brick.rectangle.setY(brick.rectangle.getY() + 20);
                    brick.label.setLayoutY(brick.label.getLayoutY() + 20);
                    balls.getFirst().isMoving = true;
                    Brick.x = x;
                    Brick.y = y;
                    z = 0.01;

                }
                if (brick.rectangle.getY() + brick.rectangle.getHeight() >= 550) {
                    timeline.stop();
                }
            }
            if (weight){
                minus2=true;
            }
            k:for (Ball i : balls) {
                for (Item item : Item.itemList) {
                    if (item.circle.isVisible()) {
                        if (checkItem(item, i)) {
                            if (item.circle.getFill() == Color.RED) {
                                Item.redItem();
                                break k;
                            } else if (item.circle.getFill() == Color.GOLD) {
                                speedTime = 0;
                            } else if (item.circle.getFill() == Color.GREEN) {
                                weightTime = 0;
                            } else if (item.circle.getFill() == Color.GRAY) {
                                rand = true;
                            }
                            break;
                        }
                    }
                }
                if (i.y == 0) {
                    i.circle.setLayoutY(balls.getFirst().circle.getLayoutY());
                }
                checkScene(i);
                for (Brick j : brickList) {
                    if (i.circle.getBoundsInParent().intersects(j.rectangle.getBoundsInParent())) {
                        if (!minus2) {
                            j.count--;
                        }
                        else {
                            j.count-=2;
                        }
                        j.label.setText(STR."\{j.count}");
                    }
                    if (counter >= balls.indexOf(i)) {
                        if (root.getChildren().contains(j.rectangle)) {
                            checkBrick(j, i);
                        }
                        i.circle.setLayoutY(i.circle.getLayoutY() + i.y);
                        i.circle.setLayoutX(i.circle.getLayoutX() + i.x);
                    }
                    if (j.count <= 0) {
                        if (j.rectangle.getFill()==Color.ORANGE){
                            orangeTime=0;
                            orange=true;
                        } if (j.rectangle.getFill()==Color.BLUE){
                            blueTime=0;
                            blue=true;
                        }
                        root.getChildren().remove(j.rectangle);
                        root.getChildren().remove(j.label);
                        brickList.remove(j);
                        score += j.count2;
                        break;
                    }
                }
                counterBrick -= z;
                counter += 0.01;
            }
            scoreLabel.setText(STR."\{score - (int) time}");
        }
    }));


    public static boolean aim = true;

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

    private static boolean checkItem(Item item, Ball ball) {
        if (ball.circle.getBoundsInParent().intersects(item.circle.getBoundsInParent())) {
            item.circle.setVisible(false);
            root.getChildren().remove(item.circle);
            return true;
        }
        return false;
    }

    public static void checkScene(Ball ball) {
        boolean right = ball.circle.getLayoutX() <= ball.circle.getRadius() + 1;
        boolean left = ball.circle.getLayoutX() >= 599 - ball.circle.getRadius();
        boolean bottom = ball.circle.getLayoutY() >= 550;
        boolean up = ball.circle.getLayoutY() <= ball.circle.getRadius();
        if ((up)) {
            ball.y *= -1;
        }
        if ((right || left)) {
            ball.x *= -1;
        }
        if (ball.y == 0) {
            ball.circle.setLayoutX(balls.getFirst().circle.getLayoutX());
            ball.circle.setLayoutY(balls.getFirst().circle.getLayoutY());
        }
        if (bottom) {
            ball.y *= 0;
            ball.x *= 0;
            if (balls.getFirst() == ball) {
                ball.circle.setLayoutY(549);
            }
            ball.isMoving = false;
            ball.circle.setLayoutY(balls.getFirst().circle.getLayoutY());
            ball.circle.setLayoutX(balls.getFirst().circle.getLayoutX());
        }

    }

    static Group root;

    public void game(Player player) {
        score = 0;
        x = Brick.x;
        y = Brick.y;
        Line line = new Line(0, 550, 600, 550);
        line.setStrokeWidth(5);
        root = new Group();
        root.getChildren().add(line);
        for (int i = 0; i < 9; i++) {
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
                orange=false;
                blue=false;
                player.setScore(score - (int) time);
                Main.objectMapper.writeValue(new File("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\players.json"), player);
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
        restart.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            orange=false;
            blue=false;
            player.setScore(score - (int) time);
            try {
                Main.objectMapper.writeValue(new File("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\players.json"), player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SceneSwitcher.game(new Player(player.getName(), 0, new Date().toString()));
        });
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
        timeLabel.setLayoutY(600);
        timeLabel.setLayoutX(500);
        timeLabel.setPrefHeight(50);
        timeLabel.setPrefWidth(100);
        timeLabel.setFont(new Font(35));
        timeLabel.setText(STR."\{time}");
        scoreLabel.setLayoutY(650);
        scoreLabel.setLayoutX(500);
        scoreLabel.setPrefHeight(50);
        scoreLabel.setPrefWidth(100);
        scoreLabel.setFont(new Font(35));
        scoreLabel.setText(STR."\{score}");
        back.setLayoutX(20);
        back.setLayoutY(630);
        back.setPrefWidth(150);
        back.setPrefHeight(40);
        back.setText("Back");
        back.setFont(new Font(18));
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, _ -> {
            try {
                orange=false;
                blue=false;
                player.setScore(score - (int) time);
                Main.objectMapper.writeValue(new File("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\players.json"), player);
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
        root.getChildren().add(timeLabel);
        root.getChildren().add(scoreLabel);
        Brick.brickMaker(root);
        Item.itemMaker(root);
        Scene scene = getScene(root, balls);
        Main.stage.setScene(scene);
        scene.setOnMouseMoved(e -> handleMouseMove( e.getX() , e.getY()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    static double deltaX;
    static double deltaY;
    static double length;
    static Line line;

    public void handleMouseMove(double x, double y) {
        randX= random.nextInt(-100,100);
        randY= random.nextInt(-100,100);
        root.getChildren().remove(line);
        if (aim && ballsNotMoving()) {
            deltaX = x - balls.getFirst().circle.getLayoutX();
            deltaY = y - balls.getFirst().circle.getLayoutY();
            if (rand) {
                deltaX += randX;
                deltaY += randY;
            }
            length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double extendLength = 700;
            double extendedX = balls.getFirst().circle.getLayoutX() + (deltaX / length) * extendLength;
            double extendedY = balls.getFirst().circle.getLayoutY() + (deltaY / length) * extendLength;
            line = new Line(balls.getFirst().circle.getLayoutX(), balls.getFirst().circle.getLayoutY(), extendedX, extendedY);
            line.setStroke(Color.RED);
            line.setStrokeWidth(3);
            for (Brick brick : brickList) {
                if (intersect(line, brick)) {
                    lineShrink(brick, line, deltaX, deltaY);
                }
            }
            root.getChildren().add(line);
        }
    }

    public boolean intersect(Line line, Brick brick) {
        Line up = new Line(brick.rectangle.getX(), brick.rectangle.getY(), brick.rectangle.getX() + brick.rectangle.getWidth(), brick.rectangle.getY());
        Line down = new Line(brick.rectangle.getX(), brick.rectangle.getY() + brick.rectangle.getHeight(), brick.rectangle.getX() + brick.rectangle.getWidth(), brick.rectangle.getY() + brick.rectangle.getHeight());
        Line right = new Line(brick.rectangle.getX() + brick.rectangle.getWidth(), brick.rectangle.getY(), brick.rectangle.getX() + brick.rectangle.getWidth(), brick.rectangle.getY() + brick.rectangle.getHeight());
        Line left = new Line(brick.rectangle.getX(), brick.rectangle.getY() + brick.rectangle.getHeight(), brick.rectangle.getX(), brick.rectangle.getY());
        boolean UP = up.contains(lineIntersect(up.getStartX(), up.getStartY(), up.getEndX(), up.getEndY(), line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
        boolean DOWN = down.contains(lineIntersect(down.getStartX(), down.getStartY(), down.getEndX(), down.getEndY(), line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
        boolean RIGHT = right.contains(lineIntersect(right.getStartX(), right.getStartY(), right.getEndX(), right.getEndY(), line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
        boolean LEFT = left.contains(lineIntersect(left.getStartX(), left.getStartY(), left.getEndX(), left.getEndY(), line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY()));
        return (DOWN && LEFT) || (UP && RIGHT) || (UP && LEFT) || (DOWN && RIGHT) || (LEFT && RIGHT) || (UP && DOWN);
    }

    public static Point2D lineIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double m = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (m == 0.0) {
            return new Point2D(0, 0);
        }
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / m;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / m;
        if (ua >= 0.0 && ua <= 1.0 && ub >= 0.0 && ub <= 1.0) {
            return new Point2D(x1 + ua * (x2 - x1), y1 + ua * (y2 - y1));
        }
        return new Point2D(0, 0);
    }


    public static void lineShrink(Brick brick, Line line, double x, double y) {
        length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        while (line.intersects(brick.rectangle.getBoundsInParent())) {
            line.setEndX(line.getEndX() - x / length);
            line.setEndY(line.getEndY() - y / length);
        }
    }

    private boolean ballsNotMoving() {
        for (Ball ball : balls) {
            if (ball.circle.getLayoutY() < 549) {
                return false;
            }
        }
        return true;
    }
    static int randX;
    static int randY;

    private Scene getScene(Group root, List<Ball> balls) {
        Scene scene = new Scene(root, 600, 750);
        scene.setOnMouseClicked(e -> {
            boolean ballsMoving;
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    for (Ball i : balls) {
                        if (balls.getFirst().circle.getLayoutY() < 549 && i != balls.getFirst() && (balls.getFirst().circle.getLayoutX() <= 570 || balls.getFirst().circle.getLayoutX() >= 25)) {
                            i.circle.setLayoutX(random.nextInt(20, 580));
                        } else {
                            i.circle.setLayoutX(balls.getFirst().circle.getLayoutX());
                        }
                        i.circle.setLayoutY(549);
                        i.x = 0;
                        i.y = 0;
                        i.isMoving = false;
                    }
                } else {
                    ballsMoving = false;
                    for (Ball i : balls) {
                        if (i.circle.getLayoutY() < 549) {
                            ballsMoving = true;
                            break;
                        }
                    }
                    if (!ballsMoving) {
                        counter = 0;
                        Ball newBall = new Ball(0, 0, new Circle(15), false);
                        newBall.circle.setLayoutX(balls.getFirst().circle.getLayoutX());
                        newBall.circle.setLayoutY(549);
                        newBall.circle.setFill(ball_color);
                        balls.add(newBall);
                        root.getChildren().add(newBall.circle);
                        for (Ball i : balls) {
                            if (!speed) {
                                i.y = 0.2 * (-i.circle.getLayoutY() + e.getY()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));
                                i.x = 0.2 * (-i.circle.getLayoutX() + e.getX()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));
                            }
                            else {
                                i.y = 0.4 * (-i.circle.getLayoutY() + e.getY()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));
                                i.x = 0.4 * (-i.circle.getLayoutX() + e.getX()) / (Math.pow(Math.pow(i.circle.getLayoutY() - e.getY(), 2) + Math.pow(i.circle.getLayoutX() - e.getX(), 2), (double) 1 / 2));

                            }
                            rand=false;
                            i.isMoving = true;
                            Brick.x = 0;
                            Brick.y = 0;
                            z = 0;
                        }

                    }
                }
            }
        });
        return scene;
    }

}
