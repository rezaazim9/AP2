package com.example.ap2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Date;

public class GameMenu  {
    @FXML
    public TextArea name;
    static int choice=1;

    public void back() throws IOException {
        SceneSwitcher.mainMenu();
    }

    public void easy() {
        choice=1;
        Brick.num=9;
        Brick.x=0.1;
        Brick.y=0.1;
    }

    public void medium() {
        choice=2;
        Brick.num=12;
        Brick.x=0.4;
        Brick.y=0.4;
    }

    public void hard() {
        choice=3;
        Brick.num=15;
        Brick.x=0.8;
        Brick.y=0.8;
    }

    public void blue() {
        Game.ball_color = Color.BLUE;
    }

    public void red() {
        Game.ball_color = Color.RED;
    }

    public void green() {
        Game.ball_color = Color.LIGHTGREEN;
    }

    public void yellow() {
        Game.ball_color = Color.YELLOW;
    }

    public void start() {
        History.savePlayer(new Player(name.getText(),0,new Date().toString()));
        SceneSwitcher.game();
    }
}
