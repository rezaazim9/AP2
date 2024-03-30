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

    public static void game(Player player) {
        History.savePlayer(player);
        Game.time = 0;
        Game.orangeTime = 0;
        Game.blueTime = 0;
        Game.rand = false;
        if (Game.root != null) {
            Game.balls.clear();
            Game.counterBrick = 0;
            Game.counter = 0;
            Item.itemList.clear();
            Game.brickList.clear();
            if (GameMenu.choice == 1) {
                new GameMenu().easy();
            } else if (GameMenu.choice == 2) {
                new GameMenu().medium();
            } else {
                new GameMenu().hard();
            }
            Game.root.getChildren().removeAll();
        }
        new Game().game(player);
    }
}
