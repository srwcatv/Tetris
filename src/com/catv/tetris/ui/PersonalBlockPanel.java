package com.catv.tetris.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 个人面板设置
 */
public class PersonalBlockPanel extends BlockPanel {

    public PersonalBlockPanel(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    /**
     * 画面板
     * @param g 画笔
     */
    public void paint(Graphics g) {
        //画窗口
        this.paintBlockPanel(g);
        //画图片
        g.drawImage(Img.PERSONAL,this.x + BORDER,this.y+BORDER-1,321,136,null);
    }
}
