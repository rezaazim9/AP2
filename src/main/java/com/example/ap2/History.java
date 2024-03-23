package com.example.ap2;

import java.util.ArrayList;
import java.util.List;

public class History {
    static List<Player> playerList = new ArrayList<>();
    static List<Player> sortedPlayerlist = new ArrayList<>();
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
            if (i.score >= max) {
                max = i.score;
                highestScorePlayer = i;
            }
        }
        return max;
    }

    static public void sort() {
        List<Player> extra = new ArrayList<>(playerList);
        k:
        while (true) {
            int compare = highestScore(extra);
            for (Player i : extra) {
                if (i.score == compare) {
                    sortedPlayerlist.add(i);
                    if (extra.size() > 1) {
                        extra.remove(i);
                        break;
                    } else {
                        break k;
                    }
                }
            }
        }
    }
}
