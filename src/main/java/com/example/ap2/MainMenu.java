package com.example.ap2;

import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    public void exit(){
        System.exit(0);
    }
    public void setting() throws IOException {
        SceneSwitcher.settingMenu();
    }
}
