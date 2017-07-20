package com.catv.tetris.ui;

import java.awt.*;

/**
 * 游戏主窗体
 */
public class BackGroundBlockPanel extends BlockPanel {

    /**
     * 无参构造
     */
    public BackGroundBlockPanel(){}

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public BackGroundBlockPanel(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    /**
     * 画窗体
     * @param g 画笔
     */
    public void paint(Graphics g) {
        //图片数量
        int size = Img.BK_IMGS.size();
        //当前等级
        int nowLevel = this.gameDto.getLevel();
        //根据等级和图片数量的余数更换图片
        int index = nowLevel%size;
        //画背景
        g.drawImage(Img.BK_IMGS.get(index),x,y,width,height,null);
    }
}
