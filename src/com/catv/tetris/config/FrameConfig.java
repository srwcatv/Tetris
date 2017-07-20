package com.catv.tetris.config;

/**
 * 游戏框架配置数据
 */
public class FrameConfig {
    /**
     * 框架宽度
     */
    private int width;
    /**
     * 框架高度
     */
    private int height;
    /**
     * 文字图片距离窗体左上角x,y距离
     */
    private int padding;
    /**
     * 窗体边框尺寸
     */
    private int border;

    /**
     * 游戏标题
     */
    private String title;

    /**
     * 构造函数
     *
     * @param width  框架宽度
     * @param height 框架高度
     */
    public FrameConfig(int width, int height, int padding, int border, String title) {
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.border = border;
        this.title = title;
    }

    /**
     * 获取框架宽度
     */
    public int getWidth() {
        return width;
    }

    /**
     * 获取框架高度
     */
    public int getHeight() {
        return height;
    }

    public int getPadding() {
        return padding;
    }

    public int getBorder() {
        return border;
    }

    public String getTitle() {
        return title;
    }
}
