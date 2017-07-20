package com.catv.tetris.ui.cfg;

import com.catv.tetris.control.GameControl;
import com.catv.tetris.util.FrameCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 保存窗口
 */
public class SaveFrame extends JFrame {

    /**
     * 确认按钮
     */
    private JButton okBtn = null;

    /**
     * 分数标签
     */
    private JLabel score = null;

    /**
     * 文本框
     */
    private JTextField textField = null;

    /**
     * 错误消息
     */
    private JLabel errMsg = null;

    /**
     * 游戏控制器
     */
    private GameControl control = null;

    public SaveFrame(GameControl gameControl) {
        //标题
        this.setTitle("保存得分");
        //获得游戏控制器
        this.control = gameControl;
        //设置边界布局
        this.setLayout(new BorderLayout());
        //窗口不可拉伸
        this.setResizable(false);
        //窗口尺寸
        this.setSize(260, 140);
        //窗口事件监听
        /*this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //点击右上角的X刷新面板
                control.getGamePanel().repaint();
            }
        });*/
        //创建面板
        this.createPanel();
        //窗口居中
        FrameCenter.center(this);
    }

    /**
     * 确认按钮添加点击事件
     */
    public void addBtnListener(){
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  nickName = textField.getText();
                if (nickName.length() > 20 || nickName == null || "".equals(nickName)){
                    errMsg.setText("昵称过长或不能为空!");
                    return;
                }
                control.savePlayer(textField.getText());
                setVisible(false);
            }
        });
    }

    /**
     * 创建面板
     */
    private void createPanel() {
        //创建北部面板
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //创建分数标签
        score = new JLabel();
        //设置字体
        //score.setFont(new Font("黑体",Font.BOLD,16));
        //添加分数标签到北部面板
        north.add(score);
        //创建中部面板
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //创建昵称输入框
        textField = new JTextField(10);
        //前缀标签
        JLabel prefix = new JLabel("你的名字:");
        //设置字体
        //prefix.setFont(new Font("黑体",Font.BOLD,16));
        //错误消息标签
        errMsg = new JLabel();
        errMsg.setForeground(Color.RED);
        //添加前缀到中间面板
        center.add(prefix);
        //添加输入框到中间面板
        center.add(textField);
        //添加错误消息到面板
        center.add(errMsg);
        //创建南部面板
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //创建确认按钮
        okBtn = new JButton("确认");
        //添加事件监听
        this.addBtnListener();
        //添加确认按钮到南部面板
        south.add(okBtn);
        //添加到窗口中
        this.add(north,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        this.add(south,BorderLayout.SOUTH);
    }

    public JLabel getScore() {
        return score;
    }

    public JTextField getTextField() {
        return textField;
    }
}
