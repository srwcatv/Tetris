package com.catv.tetris.ui;

import com.catv.tetris.config.BlockPanelConfig;
import com.catv.tetris.config.GameConfig;
import com.catv.tetris.control.GameControl;
import com.catv.tetris.control.PlayerControl;
import com.catv.tetris.dto.GameDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel {

    /**
     * 游戏配置
     */
    private GameConfig gameConfig;

    /**
     * 操作面板块
     */
    private List<BlockPanel> blockPanels = null;

    /**
     * 开始按钮
     */
    private JButton startBtn = null;

    /**
     * 设置按钮
     */
    private JButton cfgBtn = null;

    /**
     * 游戏控制器
     */
    private GameControl control = null;

    /**
     * 初始化游戏配置
     *
     * @param gameControl
     * @param gameDto
     */
    public GamePanel(GameControl gameControl, GameDto gameDto) {
        this.control = gameControl;
        //获取游戏配置
        gameConfig = GameConfig.getInstance();
        //初始arrayList
        blockPanels = new ArrayList<BlockPanel>(gameConfig.getPanelConfigs().size());
        //设置布局管理器为自由布局
        this.setLayout(null);
        //初始化窗体
        initBlockPanel(gameDto);
        //初始化组件
        initComponent();
        //初始化按钮
        initButton();
    }

    /**
     * 初始化窗体
     *
     * @param gameDto
     */
    public void initBlockPanel(GameDto gameDto) {
        try {
            //获取窗体配置
            List<BlockPanelConfig> panelConfigs = gameConfig.getPanelConfigs();
            //利用反射配置所有窗体
            for (BlockPanelConfig panelConfig : panelConfigs) {
                Class clazz = Class.forName(panelConfig.getClassName());
                Constructor constructor = clazz.getConstructor(int.class, int.class, int.class, int.class);
                BlockPanel blockPanel = (BlockPanel) constructor.newInstance(
                        panelConfig.getX(),
                        panelConfig.getY(),
                        panelConfig.getWidth(),
                        panelConfig.getHeight()
                );
                blockPanel.setGameDto(gameDto);
                blockPanels.add(blockPanel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化组件
     */
    public void initComponent() {
        //TODO
    }

    /**
     * 初始化按钮
     */
    public void initButton() {
        //实例化开始按钮
        startBtn = new JButton(Img.START_BTN_IMG);
        //设置按钮属性
        startBtn.setBounds(811, 47, Img.START_BTN_IMG.getImage().getWidth(null), Img.START_BTN_IMG.getImage().getHeight(null));
        //开始按钮添加事件监听
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.start();
            }
        });
        //实例化配置按钮
        cfgBtn = new JButton(Img.CFG_BTN_IMG);
        //设置按钮属性
        cfgBtn.setBounds(967, 47, Img.CFG_BTN_IMG.getImage().getWidth(null), Img.CFG_BTN_IMG.getImage().getHeight(null));
        //配置按钮添加事件监听
        cfgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.getFrameConfig().setVisible(true);
            }
        });
        //添加到面板
        this.add(startBtn);
        this.add(cfgBtn);
    }

    /**
     * 打开或关闭按钮
     *
     * @param openOrOff 布尔值
     */
    public void switchButton(boolean openOrOff) {
        this.startBtn.setEnabled(openOrOff);
        this.cfgBtn.setEnabled(openOrOff);
    }

    /**
     * 键盘监听
     */
    public void setPlayerControl(PlayerControl playerControl) {
        addKeyListener(playerControl);
    }

    /**
     * 画窗体
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        //调用基类方法
        super.paintComponent(g);
        //画窗体
        for (BlockPanel blockPanel : blockPanels) {
            blockPanel.paint(g);
        }
        //获取窗口焦点
        this.requestFocusInWindow();
    }
}
