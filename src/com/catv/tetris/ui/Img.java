package com.catv.tetris.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * 图片路径
 */
public class Img {

    private static Img IMG = null;
    /**
     * 皮肤根路径
     */
    public static final String PICPATH = "pic/";
    /**
     * 背景图集
     */
    protected static java.util.List<Image> BK_IMGS = null;

    /**
     * 玩家设置面板背景图
     */
    public static Image PLAYER_CFG_IMG = null;
    /**
     * 边框图片
     */
    public static Image windowImg = null;

    /**
     * 数字图片
     */
    public static Image numImg = null;

    /**
     * 消行血槽颜色
     */
    public static Image SLOTIMG = null;

    /**
     * 开始按钮图片
     */
    public static ImageIcon START_BTN_IMG = null;

    /**
     * 设置按钮图片
     */
    public static ImageIcon CFG_BTN_IMG = null;

    /**
     * 标题图片
     */
    public static Image DB_IMG = null;

    /**
     * 磁盘标题图片
     */
    public static Image DISK_IMG = null;

    /**
     * 块
     */
    public static Image levelImg = null;
    /**
     * 块
     */
    public static Image[] nextImage = null;

    /**
     * 美女图片
     */
    public static Image PERSONAL = null;


    /**
     * 块
     */
    public static Image actImage = null;

    /**
     * 下落方块同步阴影图
     */
    public static Image pauseImg = null;

    /**
     * 下落方块同步阴影图
     */
    public static Image shadowImg = null;


    /**
     * 分数标题
     */
    public static Image scoreImg = null;

    /**
     * 消行标题
     */
    public static Image rmLineImg = null;

    /**
     * 构造函数
     */
    private Img() {
    }


    public static Img getImg() {
        if (IMG == null) {
            IMG = new Img();
        }
        return IMG;
    }

    /**
     * 默认皮肤
     */
    static {
        setSkinPath("default");
    }

    /**
     * 设置皮肤
     *
     * @param path 皮肤路径
     */
    public static void setSkinPath(String path) {
        String skinPath = PICPATH + path;
        // 玩家设置面板背景图
        PLAYER_CFG_IMG = new ImageIcon(skinPath + "/window/player_cfg_bg.jpg").getImage();
        //边框图片路径
        windowImg = new ImageIcon(skinPath + "/window/Window.png").getImage();

        //数字图片
        numImg = new ImageIcon(skinPath + "/string/num.png").getImage();

        //消行血槽颜色
        SLOTIMG = new ImageIcon(skinPath + "/window/slot.png").getImage();

        //开始按钮图片
        START_BTN_IMG = new ImageIcon(skinPath + "/string/start.png");

        //设置按钮图片
        CFG_BTN_IMG = new ImageIcon(skinPath + "/string/config.png");

        //标题图片
        DB_IMG = new ImageIcon(skinPath + "/string/db.png").getImage();

        //磁盘标题图片
        DISK_IMG = new ImageIcon(skinPath + "/string/disk.png").getImage();

        //块
        levelImg = new ImageIcon(skinPath + "/string/level.png").getImage();

        //初始化数组
        nextImage = new Image[7];
        for (int index = 0; index < nextImage.length; index++) {
            nextImage[index] = new ImageIcon(skinPath + "/game/" + index + ".png").getImage();
        }

        //美女图片
        PERSONAL = new ImageIcon(skinPath + "/window/personal.jpg").getImage();


        //块
        actImage = new ImageIcon(skinPath + "/game/small_rect.png").getImage();

        //下落方块同步阴影图
        pauseImg = new ImageIcon(skinPath + "/string/pause.png").getImage();

        //下落方块同步阴影图
        shadowImg = new ImageIcon(skinPath + "/game/shadow.png").getImage();

        //分数标题
        scoreImg = new ImageIcon(skinPath + "/string/score.png").getImage();

        //消行标题
        rmLineImg = new ImageIcon(skinPath + "/string/remove_line.png").getImage();
        //初始化背景图集
        BK_IMGS = new ArrayList<>();
        File imgFile = new File(skinPath + "/background");
        File[] files = imgFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            try {
                BK_IMGS.add(new ImageIcon(file.getPath()).getImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
