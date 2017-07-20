package com.catv.tetris.ui;

import java.awt.*;

/**
 * 按钮窗体
 */
public class ButtonBlockPanel extends BlockPanel {

    /**
     * 无参构造
     */
    public ButtonBlockPanel(){}

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public ButtonBlockPanel(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    
    public void paint(Graphics g) {
        this.paintBlockPanel(g);
    }
}
