package com.catv.tetris.dao;

import com.catv.tetris.entity.Player;

import java.util.List;

/**
 * 数据接口
 */
public interface Data {

    /**
     * 存储数据
     * @param player
     */
    void saveData(Player player);

    /**
     * 取出数据
     */
    List<Player> getData();
}
