package com.catv.tetris.ui.cfg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 按键调试框
 */
public class TextCtrl extends TextField {

    /**
     * 按键编码
     */
    private int keyCode;

    /**
     * 按键对应执行的方法名
     */
    private String methodName;

    /**
     *
     * @param x 文本框左上角x坐标
     * @param y 文本框左上角y坐标
     * @param w 文本框宽度
     * @param h 文本框高度
     */
    public TextCtrl(int x,int y,int w,int h,String methodName){
        this.methodName = methodName;
        //初始化文本框
        this.setBounds(x,y,w,h);
        //添加事件监听
        this.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                //捕捉到的按键设置到对象中
                setKeyCode(e.getKeyCode());
                setText(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
        //文本框初始内容
        setText(KeyEvent.getKeyText(keyCode));
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
