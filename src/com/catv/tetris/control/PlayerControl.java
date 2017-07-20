package com.catv.tetris.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 玩家控制
 */
public class PlayerControl extends KeyAdapter {

    /**
     * 游戏控制
     */
    private GameControl gameControl;

    /**
     * 无参构造
     */
    public PlayerControl(){
    }

    /**
     * 单参构造
     * @param gameControl
     */
    public PlayerControl(GameControl gameControl){
        this.gameControl = gameControl;
    }

    /**
     * 监听键盘事件
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
        this.gameControl.actionByKeyCode(e.getKeyCode());
    }
}
