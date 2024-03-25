package com.example.ap2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneSwitcher {
    public static void settingMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Setting.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }

    public static void mainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }

    public static void gameMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GameMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }

    public static void history() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("History.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }

    public static void game() {
        if (Game.root!=null){
            Game.balls.clear();
            Game.counterBrick=0;
            Game.counter=0;
            Game.brickList.clear();
            if (GameMenu.choice==1) {
                Brick.num =9;
            }
           else  if (GameMenu.choice==2) {
                Brick.num =12;
            }
            else {
                Brick.num =15;
            }
            Game.root.getChildren().removeAll();
        }
        new Game().game();
    }
}
