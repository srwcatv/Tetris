package com.catv.tetris.service;

import com.catv.tetris.dao.Data;
import com.catv.tetris.dao.impl.Database;
import com.catv.tetris.dao.impl.DiskData;
import com.catv.tetris.dto.GameDto;
import com.catv.tetris.entity.GameActBlock;
import com.catv.tetris.entity.Player;
import com.catv.tetris.ui.BlockPanel;
import com.catv.tetris.util.AddEmptyPlayer;
import com.catv.tetris.util.DownSpeed;

import java.awt.*;
import java.util.List;

/**
 * 业务逻辑
 */
public class GameService {

    /**
     * 业务数据
     */
    private GameDto gameDto;

    /**
     * 磁盘数据
     */
    private Data dataDisk = null;

    /**
     * 数据库数据
     */
    private Data dataDb = null;

    /**
     * 无参构造
     */
    public GameService() {

    }

    /**
     * 单参构造函数
     *
     * @param gameDto
     */
    public GameService(GameDto gameDto) {
        this.gameDto = gameDto;
        //实例化磁盘数据来源
        this.dataDisk = new DiskData();
        //设置磁盘数据到dto
        this.gameDto.setDiskPlayers(getDiskData());
        //实例化数据库数据来源
        this.dataDb = new Database();
        //设置数据库数据到dto
        this.gameDto.setDbPlayers(getDatabase());
    }

    /**
     * 旋转
     */
    public void rotate() {
        if (!this.gameDto.isStart() || this.gameDto.isPause()) {
            return;
        }
        synchronized (this.gameDto) {
            this.gameDto.getGameActBlock().rotate(this.gameDto.getGameMap());
        }
    }

    /**
     * 左移
     */
    public void leftMove() {
        if (!this.gameDto.isStart() || this.gameDto.isPause()) {
            return;
        }
        synchronized (this.gameDto) {
            this.gameDto.getGameActBlock().move(-1, 0, this.gameDto.getGameMap());
        }
    }

    /**
     * 右移
     */
    public void rightMove() {
        if (!this.gameDto.isStart() || this.gameDto.isPause()) {
            return;
        }
        synchronized (this.gameDto) {
            this.gameDto.getGameActBlock().move(1, 0, this.gameDto.getGameMap());
        }
    }

    /**
     * 下移
     */
    public boolean downMove() {
        if (!this.gameDto.isStart() || this.gameDto.isPause()) {
            return true;
        }
        synchronized (this.gameDto) {
            //判断是否越界，如果越界，重新设置地图
            if (this.gameDto.getGameActBlock().move(0, 1, this.gameDto.getGameMap())) {
                return false;
            }
            boolean[][] gameMap = this.gameDto.getGameMap();
            List<Point> points = this.gameDto.getGameActBlock().getNowPoints();
            for (int index = 0; index < points.size(); index++) {
                gameMap[points.get(index).x][points.get(index).y] = true;
            }
            //消行获得经验值
            int exp = getExp(gameMap);
            //加分
            plusScore(exp);
            //刷新一个新的方块
            this.gameDto.setGameActBlock(new GameActBlock(this.gameDto.getGameActBlock().getNext()));
            //设置下一个方块
            this.gameDto.getGameActBlock().setNext(GameActBlock.getRandom().nextInt(GameActBlock.MAX_TYPE));
            //判断是否失败
            if (isFail(gameMap)) {
                //失败后处理
                this.gameDto.setStart(false);
            }
            return true;
        }
    }

    /**
     * 判断游戏是否失败
     *
     * @param gameMap
     * @return 布尔值
     */
    private boolean isFail(boolean[][] gameMap) {
        //遍历地图顶部坐标是否为true
        for (int x = 0; x < GameActBlock.MAX_X + 1; x++) {
            if (gameMap[x][0]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 加分升级
     * @param exp 经验值
     */
    private void plusScore(int exp) {
        if (exp > 0) {
            //判断是否满足升级条件
            if ((this.gameDto.getRemoveLine() + exp) >= BlockPanel.LEVEL_UP * (this.gameDto.getLevel() + 1)) {
                //满足升级条件当前等级+1
                this.gameDto.setLevel(this.gameDto.getLevel() + 1);
            }
            //计算下落速度
            this.gameDto.setSpeed(DownSpeed.getSpeed(this.gameDto.getLevel()));
            //增加当前消行数
            this.gameDto.setRemoveLine(this.gameDto.getRemoveLine() + exp);
            //分数增加并增加奖励分
            this.gameDto.setNowScore(this.gameDto.getNowScore() + exp * 10 + GameDto.getPlusScoreRule().get(exp));
        }
    }

    /**
     * 加分
     *
     * @param gameMap 地图
     * @return 返回消行数
     */
    private int getExp(boolean[][] gameMap) {
        int exp = 0;
        //遍历地图
        for (int y = 0; y < GameActBlock.MAX_Y + 1; y++) {
            //判断是否满足消行条件
            if (isCanRemoveLine(gameMap, y)) {
                //满足一次条件经验值+1
                exp++;
                //消行处理
                removeLine(gameMap, y);
            }
        }
        return exp;
    }

    /**
     * 消行处理
     *
     * @param row     当前要消除的行
     * @param gameMap 当前地图
     */
    private void removeLine(boolean[][] gameMap, int row) {
        for (int x = 0; x < GameActBlock.MAX_X + 1; x++) {
            for (int y = row; y > 0; y--) {
                gameMap[x][y] = gameMap[x][y - 1];
            }
        }
    }

    /**
     * 判断是否可以消行
     *
     * @return 布尔值
     */
    private boolean isCanRemoveLine(boolean[][] gameMap, int y) {
        int count = 0;
        for (int x = 0; x < GameActBlock.MAX_X + 1; x++) {
            if (gameMap[x][y]) {
                count++;
            }
            if (count == 20) {
                return true;
            }
        }
        return false;
    }

    /**
     * 功能键向左
     */
    public void keyFunLeft() {
        //暂停
        if (!this.gameDto.isStart()) {
            return;
        }
        this.gameDto.changePause();
    }

    /**
     * 功能键向上
     */
    public void keyFunUp() {
        //作弊
        this.plusScore(4);
    }

    /**
     * 功能键向右
     */
    public void keyFunRight() {
        //阴影开关
        this.gameDto.setOpenShadow();
    }

    /**
     * 功能键向下
     */
    public void keyFunDown() {
        //瞬间下落
        while (!this.downMove()) ;
    }

    /**
     * 保存到本地磁盘
     *
     * @param player 用户信息
     */
    public void saveDiskData(Player player) {
        this.dataDisk.saveData(player);
    }

    /**
     * 获得本地磁盘数据
     *
     * @return List<Player>
     */
    public List<Player> getDiskData() {
        List<Player> players = this.dataDisk.getData();
        AddEmptyPlayer.Add(players);
        return players;
    }

    /**
     * 保存到数据库
     *
     * @param player 用户信息
     */
    public void saveDatabase(Player player) {
        System.out.println("这是数据库保存");
        this.dataDb.saveData(player);
    }

    /**
     * 获得数据库数据
     *
     * @return List<Player>
     */
    public List<Player> getDatabase() {
        List<Player> players = this.dataDb.getData();
        return players;
    }

    /**
     * 开始按钮
     */
    public void start() {
        this.gameDto.initGameMap();
        //初始化一个方块对象
        GameActBlock gameActBlock = new GameActBlock(GameActBlock.getRandom().nextInt(GameActBlock.MAX_TYPE));
        //添加方块到数据源
        this.gameDto.setGameActBlock(gameActBlock);
        //游戏开始
        this.gameDto.setStart(true);
    }
}
