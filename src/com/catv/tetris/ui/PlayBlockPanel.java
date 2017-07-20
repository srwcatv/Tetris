package com.catv.tetris.ui;

import com.catv.tetris.entity.GameActBlock;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 游戏主窗体
 */
public class PlayBlockPanel extends BlockPanel {

    /**
     * 单个块尺寸
     */
    private static final int ACTSIZE = 16;

    /**
     * 无参构造
     */
    public PlayBlockPanel() {
    }

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    public PlayBlockPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {
        //主窗体
        this.paintBlockPanel(g);
        //判断游戏是否开始
        if (this.gameDto.isStart()) {
            //获得坐标点
            List<Point> points = this.gameDto.getGameActBlock().getNowPoints();
            if (this.gameDto.isOpenShadow()) {
                //画阴影
                drawShadow(points, g);
            }
            //获得当前points的key
            int randomColor = this.gameDto.getGameActBlock().getRandomColor();
            //显示方块
            for (Point point : points) {
                drawImageBlock(point.x, point.y, randomColor, g);
            }
        }
        //画地图
        int actIndex = (new BigDecimal(this.gameDto.getLevel()).divide(new BigDecimal(8))).intValue() >= 1 ? (this.gameDto.getLevel() % 7) + 1 : this.gameDto.getLevel() % 8;
        if (!this.gameDto.isStart()) {
            actIndex = 8;
        }
        boolean[][] gameMap = this.gameDto.getGameMap();
        for (int x = 0; x < GameActBlock.MAX_X + 1; x++) {
            for (int y = 0; y < GameActBlock.MAX_Y + 1; y++) {
                if (gameMap[x][y]) {
                    drawImageMap(x, y, actIndex, g);
                }
            }
        }
        //暂停图片显示
        if (this.gameDto.isPause()) {
            drawPauseImage(g);
        }
    }

    /**
     * 暂停图片
     *
     * @param g
     */
    private void drawPauseImage(Graphics g) {
        g.drawImage(Img.pauseImg, this.x + BORDER + ((width - (BORDER << 1) - Img.pauseImg.getWidth(null)) >> 1), this.y + BORDER + ((height - (BORDER << 1) - Img.pauseImg.getHeight(null)) >> 1), null);
    }

    /**
     * 画方块
     *
     * @param pointX      方块在地图中左上角x坐标
     * @param pointY      方块在地图中左上角y坐标
     * @param randomColor 随机颜色
     * @param g           画笔
     */
    public void drawImageBlock(int pointX, int pointY, int randomColor, Graphics g) {
        g.drawImage(Img.actImage, pointX * ACTSIZE + x + BORDER, pointY * ACTSIZE + y + BORDER, pointX * ACTSIZE + x + ACTSIZE + BORDER, pointY * ACTSIZE + y + ACTSIZE + BORDER, (randomColor + 1) * ACTSIZE, 0, (randomColor + 2) * ACTSIZE, ACTSIZE, null);

    }

    /**
     * 画地图
     *
     * @param pointX 方块在地图中左上角x坐标
     * @param pointY 方块在地图中左上角y坐标
     * @param g      画笔
     */
    public void drawImageMap(int pointX, int pointY, int index, Graphics g) {
        g.drawImage(Img.actImage, pointX * ACTSIZE + x + BORDER, pointY * ACTSIZE + y + BORDER, pointX * ACTSIZE + x + ACTSIZE + BORDER, pointY * ACTSIZE + y + ACTSIZE + BORDER, index * ACTSIZE, 0, index * ACTSIZE + ACTSIZE, ACTSIZE, null);
    }

    /**
     * 画阴影
     *
     * @param points
     * @param g
     */
    public void drawShadow(List<Point> points, Graphics g) {
        //所有point的x坐标点
        List<Integer> pointXList = new ArrayList<>(points.size());
        //阴影左上角x坐标
        int shadowLX = 0;
        //阴影左上角y坐标
        int shadowY = this.y + BORDER;
        //阴影宽度
        int shadowW;
        //阴影高度
        int shadowH = this.height - (BORDER << 1);
        //循环得到所有的x坐标点
        for (Point point : points) {
            pointXList.add(point.x);
        }
        //根据最小坐标点得到阴影左上角x坐标
        shadowLX = this.x + Collections.min(pointXList) * ACTSIZE + BORDER;
        //根据最大最小x坐标点得到阴影宽度
        shadowW = (Collections.max(pointXList) - Collections.min(pointXList) + 1) * ACTSIZE;
        //画阴影
        g.drawImage(Img.shadowImg, shadowLX, shadowY, shadowW, shadowH, null);
    }
}
