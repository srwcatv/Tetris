package com.catv.tetris.ui;

import com.catv.tetris.entity.Player;

import java.awt.*;
import java.util.List;

/**
 * 本地数据库窗体
 */
public class DiskBlockPanel extends BlockPanel {

    /**
     * 磁盘标题图片高度
     */
    private static final int DISK_IMG_H = Img.DISK_IMG.getHeight(null);

    /**
     * 无参构造
     */
    public DiskBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public DiskBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        this.paintBlockPanel(g);
        g.drawImage(Img.DISK_IMG, x + PADDING, y + PADDING, null);
        //初始化值
        //值槽平均间隔
        int spacing = (this.height - (PADDING << 1) - DISK_IMG_H - SLOTH * 5) / 5;
        //值槽左上角x坐标
        int slotX = this.x + PADDING;
        //值槽左上角y坐标
        int slotY = this.y + PADDING + DISK_IMG_H + spacing;
        //值槽宽度
        int slotW = this.width - (PADDING << 1);
        //获得用户数据
        List<Player> players = this.gameDto.getDiskPlayers();
        //数据显示
        showPlayerData(players,slotX,slotY,spacing,slotW,g);
    }
}
