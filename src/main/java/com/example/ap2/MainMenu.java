package com.example.ap2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;

public class MainMenu {
    @FXML
    private Label score = new Label();

    @FXML
    public void initialize() {
        score.setText(STR."\{History.highestScore(History.playerList)}");
    }

    public void exit() throws IOException {
        System.exit(0);
    }

    public void setting() throws IOException {
        SceneSwitcher.settingMenu();
    }

    public void gameMenu() throws IOException {
        SceneSwitcher.gameMenu();
    }

    public void history() throws IOException {
        SceneSwitcher.history();
    }
}
