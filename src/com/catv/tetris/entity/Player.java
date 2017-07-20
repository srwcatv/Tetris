package com.catv.tetris.entity;

import java.io.Serializable;

/**
 * 玩家信息
 */
public class Player implements Serializable,Comparable {

    /**
     * 玩家姓名
     */
    private String name;

    /**
     * 玩家分数
     */
    private int score;

    /**
     * 无参构造
     */
    public Player() {
    }

    /**
     * 双参构造
     * @param name 姓名
     * @param score 分数
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        Player player = (Player) o;
        return player.getScore() - this.score;
    }
}
