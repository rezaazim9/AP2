package com.example.ap2;

import java.io.IOException;

public class Setting {
    public void musicOff(){
            Main.mediaPlayer.pause();
    }
    public void musicOn(){
            Main.mediaPlayer.play();

    }
    public void aimOn(){

    }
    public void aimOff(){

    }
    public void saveOn(){

    }
    public void saveOff(){

    }

    public  void  back() throws IOException {
        SceneSwitcher.mainMenu();
    }
}
