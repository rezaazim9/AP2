package com.example.ap2;

import java.io.IOException;

public class Setting {
    public void musicOff() {
        Main.mediaPlayer.pause();
    }

    public void musicOn() {
        Main.mediaPlayer.play();

    }

    public void aimOn() {
        Game.aim=true;
    }

    public void aimOff() {Game.aim=false;
    }

    public void saveOn() {
        History.save = true;
    }

    public void saveOff() {
        History.save = false;
    }

    public void back() throws IOException {
        SceneSwitcher.mainMenu();
    }
}
