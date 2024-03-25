package com.example.ap2;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.Date;

public class GameMenu  {
    @FXML
    public TextArea name;

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
        History.savePlayer(new Player(name.getText(),0,new Date().toString()));
        SceneSwitcher.game();
    }
}
