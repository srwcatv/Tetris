package com.catv.tetris.dto;

import com.catv.tetris.config.GameConfig;
import com.catv.tetris.entity.GameActBlock;
import com.catv.tetris.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 业务数据
 */
public class GameDto {

    /**
     * 数据库玩家数据
     */
    private List<Player> dbPlayers = new ArrayList<>();
    /**
     * 本地玩家数据
     */
    private List<Player> diskPlayers = new ArrayList<>();
    /**
     * 地图
     */
    private boolean[][] gameMap = new boolean[20][36];
    /**
     * 下落方块
     */
    private GameActBlock gameActBlock;
    /**
     * 等级
     */
    private int level;
    /**
     * 当前成绩
     */
    private int nowScore;
    /**
     * 消除的行
     */
    private int removeLine;

    /**
     * 是否开始
     */
    private boolean isStart = false;

    /**
     * 是否开启阴影
     */
    private boolean isOpenShadow = true;

    /**
     * 是否暂停
     */
    private boolean isPause = false;

    /**
     * 下落速度默认740
     */
    private long speed = 740;

    /**
     * 奖励分数规则
     */
    private static final Map<Integer,Integer> plusScoreRule = GameConfig.getInstance().getSystemConfig().getPlusScoreRule();

    public GameDto() {
        //初始化地图
        this.initGameMap();
    }

    /**
     * 初始化地图
     */
    public void initGameMap(){
        for (int x = 0;x <= GameActBlock.MAX_X;x++){
            for (int y = 0;y <= GameActBlock.MAX_Y;y++){
                gameMap[x][y] = false;
            }
        }
    }

    public List<Player> getDbPlayers() {
        return dbPlayers;
    }

    public void setDbPlayers(List<Player> dbPlayers) {
        this.dbPlayers = dbPlayers;
    }

    public List<Player> getDiskPlayers() {
        return diskPlayers;
    }

    public void setDiskPlayers(List<Player> diskPlayers) {
        this.diskPlayers = diskPlayers;
    }

    public boolean[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(boolean[][] gameMap) {
        this.gameMap = gameMap;
    }

    public GameActBlock getGameActBlock() {
        return gameActBlock;
    }

    public void setGameActBlock(GameActBlock gameActBlock) {
        this.gameActBlock = gameActBlock;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNowScore() {
        return nowScore;
    }

    public void setNowScore(int nowScore) {
        this.nowScore = nowScore;
    }

    public int getRemoveLine() {
        return removeLine;
    }

    public void setRemoveLine(int removeLine) {
        this.removeLine = removeLine;
    }

    public static Map<Integer, Integer> getPlusScoreRule() {
        return plusScoreRule;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isOpenShadow() {
        return isOpenShadow;
    }

    public void setOpenShadow() {
        this.isOpenShadow = !this.isOpenShadow;
    }

    public boolean isPause() {
        return isPause;
    }

    public void changePause() {
        this.isPause = !this.isPause;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }
}
