package com.example.ap2;

import javafx.scene.paint.Color;

import java.io.IOException;

public class GameMenu {
    public void back() throws IOException {
        SceneSwitcher.mainMenu();
    }

    public void easy() {

    }

    public void medium() {

    }

    public void hard() {

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
        SceneSwitcher.game();
    }
}
