package com.catv.tetris.util;

import com.catv.tetris.entity.Player;

import java.util.List;

/**
 * 填充到5个尺寸
 */
public class AddEmptyPlayer {

    private AddEmptyPlayer() {
    }

    public static void Add(List<Player> players) {
        int size = players.size();
        if (size < 5) {
            for (int i = 0; i < 5 - size; i++) {
                players.add(new Player("No Data", 0));
            }
        }
    }
}
