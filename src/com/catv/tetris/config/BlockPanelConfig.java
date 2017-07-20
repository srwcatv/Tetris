package com.catv.tetris.config;

/**
 * 窗体配置数据
 */
public class BlockPanelConfig {

    /**
     * 窗体在框架左上角的x坐标
     */
    private int x;
    /**
     * 窗体在框架左上角的y坐标
     */
    private int y;
    /**
     * 窗体宽度
     */
    private int width;
    /**
     * 窗体高度
     */
    private int height;
    /**
     * 窗体类名称
     */
    private String className;

    /**
     *
     * @param x 窗体在框架左上角的x坐标
     * @param y 窗体在框架左上角的y坐标
     * @param width 窗体宽度
     * @param height 窗体高度
     * @param className 窗体类名称
     */
    public BlockPanelConfig(int x, int y, int width, int height, String className) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.className = className;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getClassName() {
        return className;
    }
}
