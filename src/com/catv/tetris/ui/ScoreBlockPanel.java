package com.catv.tetris.ui;

import java.awt.*;
import java.math.BigDecimal;

/**
 * 游戏主窗体
 */
public class ScoreBlockPanel extends BlockPanel {

    /**
     * 无参构造
     */
    public ScoreBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public ScoreBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        //画窗口
        this.paintBlockPanel(g);
        //初始化值
        int slotX = this.x + (PADDING << 1);
        int slotY = this.y + Img.scoreImg.getHeight(null) + (PADDING << 2) + PADDING;
        int slotW = this.width - (PADDING << 2);
        //槽中显示的字符串
        String text = "下一級";
        //当前消除行数
        int removeLine = this.gameDto.getRemoveLine();
        //当消行数所占比例
        BigDecimal p = new BigDecimal(removeLine % 20).divide(new BigDecimal(LEVEL_UP));
        //显示分数标题
        g.drawImage(Img.scoreImg, slotX, this.y + PADDING, null);
        //显示分数
        drawImageNum((PADDING << 1), PADDING, this.gameDto.getNowScore(), g);
        //显示消行标题
        g.drawImage(Img.rmLineImg, slotX, this.y + Img.scoreImg.getHeight(null) + (PADDING << 1), null);
        //显示消行数
        drawImageNum((PADDING << 1), Img.scoreImg.getHeight(null) + (PADDING << 1), this.gameDto.getRemoveLine(), g);
        //显示值槽
        drawImgRect(Img.SLOTIMG,slotX,slotY,slotW,SLOTH,p,text, null, g);
    }

    /**
     * 显示数字图片
     *
     * @param num 显示的数字
     * @param g   画笔
     */
    public void drawImageNum(int x, int y, int num, Graphics g) {
        String newNum = String.valueOf(num);
        char[] chars = newNum.toCharArray();
        for (int index = chars.length - 1; index >= 0; index--) {
            //循环输出每一位数字
            g.drawImage(
                    Img.numImg,
                    this.x + this.width - x - (chars.length - index + 1) * numImg_w + numImg_w,
                    this.y + y,
                    this.x + this.width - x - (chars.length - index) * numImg_w + numImg_w,
                    this.y + y + numImg_h,
                    (chars[index] - '0') * numImg_w,
                    0,
                    ((chars[index] - '0') + 1) * numImg_w,
                    numImg_h,
                    null
            );
        }
    }
}
