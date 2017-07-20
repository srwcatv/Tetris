package com.catv.tetris.ui;

import java.awt.*;

/**
 * 游戏主窗体
 */
public class LevelBlockPanel extends BlockPanel {

    /**
     * 无参构造
     */
    public LevelBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public LevelBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        this.paintBlockPanel(g);
        //左右居中显示等级文字图片
        drawImageLevelAtCenter(Img.levelImg, g);
        //显示数字
        drawImageNum(this.gameDto.getLevel(), g);
    }

    /**
     * 图片居中显示
     *
     * @param levelImg 要显示的图片
     * @param g       画笔
     */
    public void drawImageLevelAtCenter(Image levelImg, Graphics g) {
        g.drawImage(Img.levelImg, this.x + (this.width - Img.levelImg.getWidth(null) >> 1), this.y + (this.height - numImg_h - Img.levelImg.getHeight(null))/3, null);
    }

    /**
     * 显示数字图片
     *
     * @param num 显示的数字
     * @param g   画笔
     */
    public void drawImageNum(int num, Graphics g) {
        String newNum = String.valueOf(num);
        char[] chars = newNum.toCharArray();
        for (int index = chars.length - 1; index >= 0; index--) {
            //循环输出每一位数字
            g.drawImage(
                         Img.numImg,
                    this.x + this.width - (this.width - Img.levelImg.getWidth(null) >> 1) - (chars.length - index + 1) * numImg_w + numImg_w,
                    this.y + ((this.height - numImg_h - Img.levelImg.getHeight(null))/3 << 1) + Img.levelImg.getHeight(null),
                    this.x + this.width - (this.width - Img.levelImg.getWidth(null) >> 1) - (chars.length - index ) * numImg_w + numImg_w,
                    this.y + ((this.height - numImg_h - Img.levelImg.getHeight(null))/3 << 1) + Img.levelImg.getHeight(null) + numImg_h,
                    (chars[index] - '0') * numImg_w,
                    0,
                    ((chars[index] - '0') + 1) * numImg_w,
                    numImg_h,
                    null
            );
        }
    }
}
