package com.example.ap2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class History implements Initializable {
    @FXML
    private TableColumn<Player ,Integer> scoreTable;
    @FXML
    private TableColumn<Player ,String> nameTable;
    @FXML
    private TableColumn<Player , Date> dateTable;
    @FXML
    private TableView<Player> table;


    static ObservableList<Player> playerList = FXCollections.observableArrayList();
    static ObservableList<Player> sortedPlayerList = FXCollections.observableArrayList() ;
    static Player highestScorePlayer;
    static boolean save;

    public void savePlayer(Player player) {
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
        ObservableList<Player> extra = FXCollections.observableArrayList(playerList);
        k:
        while (true) {
            if (playerList.isEmpty()){
                break ;
            }
            int compare = highestScore(extra);
            for (Player i : extra) {
                if (i.getScore()== compare) {
                    sortedPlayerList.add(i);
                        extra.remove(i);
                    if (extra.isEmpty()){
                        break k;
                    }
                        break;
                }
            }
        }
    }
    public  void  back() throws IOException {
        SceneSwitcher.mainMenu();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        History.sort();
        nameTable.setCellValueFactory(new PropertyValueFactory<>("Name"));
        scoreTable.setCellValueFactory(new PropertyValueFactory<>("Score"));
        dateTable.setCellValueFactory(new PropertyValueFactory<>("Date"));
      table.setItems(sortedPlayerList);

    }
}
