package com.catv.tetris.config;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 方块配置
 */
public class GameBlockConfig {

    private int min_x;
    private int max_x;
    private int min_y;
    private int max_y;

    private Map<Integer, List<Point>> allActBlock = null;

    public GameBlockConfig(int min_x, int max_x, int min_y, int max_y, Map<Integer, List<Point>> allActBlock) {
        this.min_x = min_x;
        this.max_x = max_x;
        this.min_y = min_y;
        this.max_y = max_y;
        this.allActBlock = allActBlock;
    }

    public int getMin_x() {
        return min_x;
    }

    public int getMax_x() {
        return max_x;
    }

    public int getMin_y() {
        return min_y;
    }

    public int getMax_y() {
        return max_y;
    }

    public Map<Integer, List<Point>> getAllActBlock() {
        return allActBlock;
    }
}
