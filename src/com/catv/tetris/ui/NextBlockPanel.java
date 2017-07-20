package com.catv.tetris.ui;

import java.awt.*;

/**
 * 游戏主窗体
 */
public class NextBlockPanel extends BlockPanel {

    /**
     * 无参构造
     */
    public NextBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public NextBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        this.paintBlockPanel(g);
        //判断游戏是否开始
        if (this.gameDto.isStart()) {
            //获得下一个方块图片
            Image nextImg = Img.nextImage[this.gameDto.getGameActBlock().getNext()];
            drawImageNextAtCenter(nextImg, g);
        }
    }

    /**
     * 图片居中显示
     *
     * @param nextImg 要显示的图片
     * @param g       画笔
     */
    public void drawImageNextAtCenter(Image nextImg, Graphics g) {
        g.drawImage(nextImg, this.x + (this.width - nextImg.getWidth(null) >> 1), this.y + (this.height - nextImg.getHeight(null) >> 1), null);
    }
}
