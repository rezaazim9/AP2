package com.example.ap2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    static Stage stage;
    static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bricks Breaker +");
        stage.setScene(scene);
        stage.getIcons().add(new Image(new File("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\icon.jpg").toURI().toString()));
        stage.show();
        Media media = new Media(new File("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\Music2.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.05);
        loop();
    }

    public void loop() {
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}