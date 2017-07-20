package com.catv.tetris.entity;

import com.catv.tetris.config.GameConfig;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 移动块体
 */
public class GameActBlock {

    /**
     * x轴左边界
     */
    public static final int MIN_X = GameConfig.getInstance().getBlockConfig().getMin_x();
    /**
     * x轴右边界
     */
    public static final int MAX_X = GameConfig.getInstance().getBlockConfig().getMax_x();
    /**
     * y轴上边界
     */
    public static final int MIN_Y = GameConfig.getInstance().getBlockConfig().getMin_y();
    /**
     * y轴下边界
     */
    public static final int MAX_Y = GameConfig.getInstance().getBlockConfig().getMax_y();

    /**
     *
     * 坐标TYPE_0
     */
    /*
    private static List<Point> type_0 = null;
    *//**
     * 坐标TYPE_1
     *//*
    private static List<Point> type_1 = null;
    *//**
     * 坐标TYPE_2
     *//*
    private static List<Point> type_2 = null;
    *//**
     * 坐标TYPE_3
     *//*
    private static List<Point> type_3 = null;
    *//**
     * 坐标TYPE_4
     *//*
    private static List<Point> type_4 = null;
    *//**
     * 坐标TYPE_5
     *//*
    private static List<Point> type_5 = null;
    *//**
     * 坐标TYPE_6
     *//*
    private static List<Point> type_6 = null;*/
    /**
     * 所有方块
     */
    private static final Map<Integer, List<Point>> allActBlock = GameConfig.getInstance().getBlockConfig().getAllActBlock();
    /**
     * 当前下落方块的坐标
     */
    private List<Point> nowPoints = null;
    /**
     * 方块类型个数0 ~ 6
     */
    public static final int MAX_TYPE = 7;
    /**
     * 随机数生成器
     */
    private static Random random = null;
    /**
     * 随机颜色
     */
    private int randomColor;
    /**
     * 下一个方块
     */
    private int next;
    /**
     * 当前下落方块的key值
     */
    private int typeCode;
    /**
     * 初始化7种方块坐标
     */
    static {
        random = new Random();
        /*allActBlock = new HashMap<>(7);
        type_0 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(11, 0));
            add(new Point(11, 1));
        }};
        type_1 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(10, 1));
            add(new Point(11, 1));
        }};
        type_2 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(11, 0));
            add(new Point(10, 1));
        }};
        type_3 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(11, 0));
            add(new Point(12, 0));
        }};
        type_4 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(11, 0));
            add(new Point(9, 1));
        }};
        type_5 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 0));
            add(new Point(9, 1));
            add(new Point(10, 1));
        }};
        type_6 = new ArrayList<Point>(4) {{
            add(new Point(10, 0));
            add(new Point(9, 1));
            add(new Point(11, 0));
            add(new Point(10, 1));
        }};
        allActBlock.put(0, type_0);
        allActBlock.put(1, type_1);
        allActBlock.put(2, type_2);
        allActBlock.put(3, type_3);
        allActBlock.put(4, type_4);
        allActBlock.put(5, type_5);
        allActBlock.put(6, type_6);*/
    }

    /**
     * 初始坐标
     */
    public GameActBlock(int randomCode) {
        initPoint(randomCode);
    }

    /**
     * 初始坐标
     *
     * @param randomCode
     */
    public void initPoint(int randomCode) {
        this.typeCode = randomCode;
        //随机取出一种类型的方块
        List<Point> points = allActBlock.get(randomCode);
        //给当前的方块类型重新赋值
        this.nowPoints = new ArrayList<>(points.size());
        for (int index = 0; index < points.size(); index++) {
            nowPoints.add(new Point(points.get(index).x, points.get(index).y));
        }
        //生成随机颜色
        this.randomColor = this.random.nextInt(MAX_TYPE);
    }

    /**
     * 块移动
     *
     * @param moveX x轴偏移
     * @param moveY y轴偏移
     * @return 返回结果是否越界
     */
    public boolean move(int moveX, int moveY, boolean[][] gameMap) {
        //偏移
        for (int index = 0; index < nowPoints.size(); index++) {
            int newX = nowPoints.get(index).x + moveX;
            int newY = nowPoints.get(index).y + moveY;
            //判断是否越界
            if (isOverZone(newX, newY, gameMap)) {
                return false;
            }
        }
        for (int index = 0; index < nowPoints.size(); index++) {
            nowPoints.get(index).x += moveX;
            nowPoints.get(index).y += moveY;
        }
        return true;
    }

    /**
     * 旋转
     * 顺时针：
     * B.x = O.x - O.y + A.y
     * B.y = O.x + O.y - A.x
     *
     * @param gameMap
     */
    public void rotate(boolean[][] gameMap) {
        if (this.typeCode == 5){
            return;
        }
        for (int index = 1; index < nowPoints.size(); index++) {
            if (nowPoints.equals(allActBlock.get(5))){
                return;
            }
            int newX = nowPoints.get(0).x - nowPoints.get(0).y + nowPoints.get(index).y;
            int newY = nowPoints.get(0).x + nowPoints.get(0).y - nowPoints.get(index).x;
            //判断是否越界
            if (isOverZone(newX, newY, gameMap)) {
                return;
            }
        }
        for (int index = 1; index < nowPoints.size(); index++) {
            int newX = nowPoints.get(0).x - nowPoints.get(0).y + nowPoints.get(index).y;
            int newY = nowPoints.get(0).x + nowPoints.get(0).y - nowPoints.get(index).x;
            nowPoints.get(index).x = newX;
            nowPoints.get(index).y = newY;
        }
    }

    /**
     * 判断方块是超出边界
     *
     * @param newX x轴偏移
     * @param newY y轴偏移
     * @return false Or true
     */
    public boolean isOverZone(int newX, int newY, boolean[][] gameMap) {
        return newX < MIN_X || newX > MAX_X || newY < MIN_Y || newY > MAX_Y || gameMap[newX][newY];
    }

    public List<Point> getNowPoints() {
        return nowPoints;
    }

    public int getRandomColor() {
        return randomColor;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public static Random getRandom() {
        return random;
    }
}
