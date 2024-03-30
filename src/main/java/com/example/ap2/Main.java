package com.example.ap2;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.*;

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
    static ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        try(
                FileReader reader = new FileReader("C:\\Users\\ostad\\IdeaProjects\\AP2\\src\\main\\resources\\com\\example\\ap2\\players.json");
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String currentLine;
            while((currentLine=bufferedReader.readLine()) != null) {
                  JsonNode logStorageNode =objectMapper.readTree(currentLine);
                  String name=logStorageNode.toString().substring(logStorageNode.toString().indexOf("name")+7,logStorageNode.toString().indexOf(",")-1);
                  String scoreString=logStorageNode.toString().substring(logStorageNode.toString().indexOf("score"));
                  int score=Integer.parseInt(scoreString.substring(scoreString.indexOf("score")+7,scoreString.indexOf(",")));
                  String date=logStorageNode.toString().substring(logStorageNode.toString().indexOf("date")+7,logStorageNode.toString().indexOf("2024")+4);
                History.playerList.add(new Player(name,score,date));
            }
        }
        launch();
    }
}