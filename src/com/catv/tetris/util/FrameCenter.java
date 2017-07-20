package com.catv.tetris.util;

import javax.swing.*;
import java.awt.*;

/**
 * 窗口居中
 */
public class FrameCenter {

    /**
     * 居中
     * @param frame 窗体
     */
    public static void center(JFrame frame){
        //设置窗体屏幕居中
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame.getWidth())/2;
        int y = (screenSize.height - frame.getHeight())/2;
        frame.setLocation(x,y);
    }
}
