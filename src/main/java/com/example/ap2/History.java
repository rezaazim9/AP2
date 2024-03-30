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
import java.util.ArrayList;
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
    static boolean save = true;

    public static void savePlayer(Player player) {
        if (save) {
            playerList.add(player);
        }
    }
    static public int highestScore(List<Player> list) {
        int max=0;
        if (!list.isEmpty()) {
             max = list.getFirst().getScore();
        }
        for (Player i : list) {
            if (i.getScore() >= max) {
                max = i.getScore();
            }
        }
        return max;
    }
    static List<Player> extra=new ArrayList<>();
    static public void sort() {
        sortedPlayerList.clear();
        extra.addAll(playerList);
        while (!extra.isEmpty()) {
            int compare = highestScore(extra);
            for (Player i : extra) {
                if (i.getScore() == compare) {
                    sortedPlayerList.add(i);
                    extra.remove(i);
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
