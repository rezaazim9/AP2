package com.example.ap2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class History implements Initializable {
    @FXML
    private TableColumn<Player, Integer> scoreTable;
    @FXML
    private TableColumn<Player, String> nameTable;
    @FXML
    private TableColumn<Player, String> dateTable;
    @FXML
    private TableView<Player> table;


    static ObservableList<Player> playerList = FXCollections.observableArrayList();
    static ObservableList<Player> sortedPlayerList = FXCollections.observableArrayList();
    static Player highestScorePlayer;
    static boolean save = true;

    public static void savePlayer(Player player) {
        if (save) {
            playerList.add(player);
        }
    }

    static public int highestScore(List<Player> list) {
        int max = 0;
        for (Player i : list) {
            if (i.getScore() >= max) {
                max = i.getScore();
                highestScorePlayer = i;
            }
        }
        return max;
    }
    static public void sort() {
        while (!playerList.isEmpty()) {
            int compare = highestScore(playerList);
            for (Player i : playerList) {
                if (i.getScore() == compare) {
                    sortedPlayerList.add(i);
                    playerList.remove(i);
                    break;
                }
            }
        }
    }

    public void back() throws IOException {
        SceneSwitcher.mainMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        History.sort();
        nameTable.setCellValueFactory(new PropertyValueFactory<>("Name"));
        scoreTable.setCellValueFactory(new PropertyValueFactory<>("Score"));
        dateTable.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table.getItems().clear();
        table.setItems(sortedPlayerList);
    }
}
