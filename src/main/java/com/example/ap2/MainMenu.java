package com.example.ap2;

import java.io.IOException;

public class MainMenu {
    public void exit(){
        System.exit(0);
    }
    public void setting() throws IOException {
        SceneSwitcher.settingMenu();
    }
    public void gameMenu() throws IOException {
        SceneSwitcher.gameMenu();
    }
}
