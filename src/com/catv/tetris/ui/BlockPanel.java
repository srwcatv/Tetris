package com.catv.tetris.ui;

import com.catv.tetris.config.GameConfig;
import com.catv.tetris.dto.GameDto;
import com.catv.tetris.entity.Player;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 面板块
 */
abstract public class BlockPanel {

    /**
     * 升级所需消行数
     */
    public static final int LEVEL_UP = 20;

    /**
     * 槽值图片宽度
     */
    protected static int SLOT_IMG_W = Img.SLOTIMG.getWidth(null);

    /**
     * 文字相对窗体左上角x,y轴的距离
     */
    protected static final int PADDING = GameConfig.getInstance().getFrameConfig().getPadding();
    /**
     * 图片边框宽度
     */
    protected static final int BORDER = GameConfig.getInstance().getFrameConfig().getBorder();

    /**
     * 数字图片每个切片宽度
     */
    protected int numImg_w = Img.numImg.getWidth(null) / 10;

    /**
     * 数字图片切片高度
     */
    protected int numImg_h = Img.numImg.getHeight(null);

    /**
     * 图片宽度
     */
    protected static int IMAGE_WIDTH = Img.windowImg.getWidth(null);
    /**
     * 图片高度
     */
    protected static int IMAGE_HEIGHT = Img.windowImg.getHeight(null);
    /**
     * 血槽高度
     */
    protected static final int SLOTH = 32;

    /**
     * 窗口左上角x坐标
     */
    protected int x;
    /**
     * 窗口左上角y坐标
     */
    protected int y;
    /**
     * 窗口宽度
     */
    protected int width;
    /**
     * 窗口高度
     */
    protected int height;

    protected static final Font TEXTFONT = new Font("黑体", Font.BOLD, 20);


    /**
     * 无参构造
     */
    protected BlockPanel() {
    }

    /**
     * 数据源
     */
    protected GameDto gameDto;

    /**
     * 构造函数
     *
     * @param x      屏幕左上角x坐标
     * @param y      屏幕左上角y坐标
     * @param width  窗体宽度
     * @param height 窗体高度
     */
    protected BlockPanel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * 创建面板块
     *
     * @param g
     */
    protected void paintBlockPanel(Graphics g) {
        /**
         * 左上角
         */
        g.drawImage(Img.windowImg, x, y, x + this.BORDER, y + this.BORDER, 0, 0, this.BORDER, this.BORDER, null);
        /**
         * 上中
         */
        g.drawImage(Img.windowImg, x + this.BORDER, y, x + width - this.BORDER, y + this.BORDER, this.BORDER, 0, IMAGE_WIDTH - this.BORDER, this.BORDER, null);
        /**
         * 右上角
         */
        g.drawImage(Img.windowImg, x + width - this.BORDER, y, x + width, y + this.BORDER, IMAGE_WIDTH - this.BORDER, 0, IMAGE_WIDTH, this.BORDER, null);
        /**
         * 左中
         */
        g.drawImage(Img.windowImg, x, y + this.BORDER, x + this.BORDER, y + height - this.BORDER, 0, this.BORDER, this.BORDER, IMAGE_HEIGHT - this.BORDER, null);
        /**
         * 右中
         */
        g.drawImage(Img.windowImg, x + width - this.BORDER, y + this.BORDER, x + width, y + height - this.BORDER, IMAGE_WIDTH - this.BORDER, this.BORDER, IMAGE_WIDTH, IMAGE_HEIGHT - this.BORDER, null);
        /**
         * 左下角
         */
        g.drawImage(Img.windowImg, x, y + height - this.BORDER, x + this.BORDER, y + height, 0, IMAGE_HEIGHT - this.BORDER, this.BORDER, IMAGE_HEIGHT, null);
        /**
         * 下中
         */
        g.drawImage(Img.windowImg, x + this.BORDER, y + height - this.BORDER, x + width - this.BORDER, y + height, this.BORDER, IMAGE_HEIGHT - this.BORDER, IMAGE_WIDTH - this.BORDER, IMAGE_HEIGHT, null);
        /**
         * 右下角
         */
        g.drawImage(Img.windowImg, x + width - this.BORDER, y + height - this.BORDER, x + width, y + height, IMAGE_WIDTH - this.BORDER, IMAGE_HEIGHT - this.BORDER, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        /**
         * 中间位置
         */
        g.drawImage(Img.windowImg, x + this.BORDER, y + this.BORDER, x + width - this.BORDER, y + height - this.BORDER, this.BORDER, this.BORDER, IMAGE_WIDTH - BORDER, IMAGE_HEIGHT - BORDER, null);

    }

    /**
     * 画窗体
     *
     * @param g 画笔
     */
    abstract public void paint(Graphics g);

    /**
     * 画值槽
     *
     * @param slotImg 截取的图片
     * @param slotX   血条左上角x坐标
     * @param slotY   血条左上角y坐标
     * @param slotW   血条宽度
     * @param slotH   血条高度
     * @param p       当前消行数的比例
     * @param text    槽中显示的字符串
     * @param score
     * @param g       画笔
     */
    public void drawImgRect(Image slotImg, int slotX, int slotY, int slotW, int slotH, BigDecimal p, String text, String score, Graphics g) {
        //值槽外边框
        g.setColor(Color.BLACK);
        g.fillRect(slotX, slotY, slotW, slotH);
        //值槽内间隔
        g.setColor(Color.WHITE);
        g.fillRect(slotX + 2, slotY + 2, slotW - 4, slotH - 4);
        //值槽背景
        g.setColor(Color.BLACK);
        g.fillRect(slotX + 3, slotY + 3, slotW - 6, slotH - 6);
        //从颜色图片的那个位置开始切
        int slotImgX1;
        //切到哪里
        int slotImgX2;
        //显示的宽度
        int slotX2;
        //判断当前比例值是否大于等于1
        if (p.intValue() >= 1) {
            slotImgX1 = SLOT_IMG_W - 2;
            slotImgX2 = SLOT_IMG_W - 1;
            slotX2 = slotX + 3 + slotW - 6;
        } else {
            slotImgX1 = new BigDecimal(SLOT_IMG_W).multiply(p).intValue();
            slotImgX2 = slotImgX1 + 1;
            slotX2 = slotX + 3 + new BigDecimal(slotW - 6).multiply(p).intValue();
        }
        //开始画值槽
        g.drawImage(slotImg,
                slotX + 3,
                slotY + 3,
                slotX2,
                slotY + 3 + slotH - 6,
                slotImgX1,
                0,
                slotImgX2,
                slotImg.getHeight(null),
                null
        );
        //显示用户名
        g.setColor(Color.WHITE);
        g.setFont(TEXTFONT);
        g.drawString(text, slotX + 4, slotY + 23);
        //显示分数
        g.setColor(Color.WHITE);
        g.setFont(TEXTFONT);
        if (score != null) {
            char[] chars = score.toCharArray();
            for (int index = chars.length - 1; index >= 0; index--) {
                //循环输出每一位数字
                g.drawString(String.valueOf(chars[index]), slotX + slotW - (chars.length - index) * 12 - 3, slotY + 23);
            }
        }
    }

    /**
     * 显示值槽
     *
     * @param players 数据列表
     * @param slotX   值槽左上角x坐标
     * @param slotY   值槽左上角y坐标
     * @param spacing 值槽间隔
     * @param slotW   值槽宽度
     * @param g       画笔
     */
    public void showPlayerData(List<Player> players, int slotX, int slotY, int spacing, int slotW, Graphics g) {
        //数据显示
        for (int i = 0; i < players.size(); i++) {
            BigDecimal p = new BigDecimal(0);
            //判断用户分数是否大于0
            if (players.get(i).getScore() > 0) {
                //大于零做比值运算得出比例值
                p = new BigDecimal(this.gameDto.getNowScore()).divide(new BigDecimal(players.get(i).getScore()), 2, BigDecimal.ROUND_HALF_UP);
            }
            drawImgRect(Img.SLOTIMG, slotX, slotY + SLOTH * i + spacing * i, slotW, SLOTH, p, players.get(i).getName(), String.valueOf(players.get(i).getScore()), g);
        }
    }

    public void setGameDto(GameDto gameDto) {
        this.gameDto = gameDto;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return height;
    }

    public void setHeigth(int height) {
        this.height = height;
    }
}
