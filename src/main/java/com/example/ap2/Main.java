package com.example.ap2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bricks Breaker +");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}