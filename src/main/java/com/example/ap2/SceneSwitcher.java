package com.example.ap2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneSwitcher {
    public static void settingMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Setting.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }
    public static void mainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
    }
}
