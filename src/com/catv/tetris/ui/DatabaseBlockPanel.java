package com.catv.tetris.ui;

import com.catv.tetris.entity.Player;

import java.awt.*;
import java.util.List;

/**
 * 数据库窗体
 */
public class DatabaseBlockPanel extends BlockPanel {

    /**
     * 标题图片高度
     */
    private static final int DB_IMG_H = Img.DB_IMG.getHeight(null);

    /**
     * 无参构造
     */
    public DatabaseBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public DatabaseBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        paintBlockPanel(g);
        g.drawImage(Img.DB_IMG, x + PADDING, y + PADDING, null);
        //初始化值
        int spacing = (this.height - (PADDING << 1) - DB_IMG_H - SLOTH * 5) / 5;
        int slotX = this.x + PADDING;
        int slotY = this.y + PADDING + DB_IMG_H + spacing;
        int slotW = this.width - (PADDING << 1);
        //获得数据库用户数据
        List<Player> players = this.gameDto.getDbPlayers();
        //数据显示
        showPlayerData(players,slotX,slotY,spacing,slotW,g);
    }
}
