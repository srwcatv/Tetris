package com.catv.tetris.ui;

import com.catv.tetris.config.GameConfig;
import com.catv.tetris.util.FrameCenter;

import javax.swing.*;

/**
 * 游戏框架
 */
public class GameFrame extends JFrame{

    /**
     * 游戏窗体
     */
    private GamePanel gamePanel;

    /**
     * 构造函数
     */
    public GameFrame(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        initGame();
    }

    /**
     * 游戏初始化
     */
    public void initGame(){
        //获取游戏配置
        GameConfig config = GameConfig.getInstance();
        //设置标题
        this.setTitle(config.getFrameConfig().getTitle());
        //设置窗体大小
        this.setSize(config.getFrameConfig().getWidth(),config.getFrameConfig().getHeight());
        //设置窗体不可改变大小
        this.setResizable(false);
        //设置关闭窗口时关闭程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体屏幕居中
        FrameCenter.center(this);
        //设置默认panel
        this.setContentPane(gamePanel);
        //游戏窗口默认打开
        this.setVisible(true);
    }
}
