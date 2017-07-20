package com.catv.tetris.ui.cfg;

import com.catv.tetris.control.GameControl;
import com.catv.tetris.ui.Img;
import com.catv.tetris.util.FrameCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 玩家配置
 */
public class FrameConfig extends JFrame {


    /**
     * 确认按钮
     */
    private JButton btnOk = new JButton("确认");
    /**
     * 取消按钮
     */
    private JButton btnCancle = new JButton("取消");
    /**
     * 应用按钮
     */
    private JButton btnApply = new JButton("应用");

    /**
     * 按键调试框
     */
    private TextCtrl[] textCtrls = new TextCtrl[8];

    /**
     * 配置文件
     */
    private static final String CFGFILE = "config/playerCfg.dat";

    /**
     * 错误消息
     */
    private JLabel errorMsg = new JLabel();

    /**
     * 皮肤面板
     */
    private JPanel skinPanel = null;

    /**
     * 列表框
     */
    private JList skinList = null;

    /**
     * 数据列表
     */
    private DefaultListModel dataList = new DefaultListModel();

    /**
     * 预览图片面板
     */
    private JPanel viewPanel = null;

    /**
     * 方法名数组
     */
    private static final String[] METHODNAMES = new String[]{
            "rightMove", "rotate", "leftMove", "downMove",
            "keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown"
    };

    /**
     * 游戏控制器
     */
    private GameControl control;

    /**
     * 预览图片
     */
    private Image[] skinViews = null;

    /**
     * 构造器初始化窗口
     *
     * @param gameControl 游戏控制器
     */
    public FrameConfig(GameControl gameControl) {
        //设置标题
        this.setTitle("设置");
        //获得游戏控制器
        this.control = gameControl;
        //设置边界布局
        this.setLayout(new BorderLayout());
        //初始化按键输入框
        initKeyText();
        //添加主面板
        this.add(createMainPanel(), BorderLayout.CENTER);
        //添加按钮面板
        this.add(createButtonPanel(), BorderLayout.SOUTH);
        //窗口不可拉伸
        this.setResizable(false);
        //窗口尺寸
        this.setSize(629, 329);
        //窗口事件监听
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //点击右上角的X刷新面板
                control.getGamePanel().repaint();
            }
        });
        //窗口居中
        FrameCenter.center(this);
    }

    /**
     * 初始化按键输入框
     */
    public void initKeyText() {
        //读取配置文件
        ObjectInputStream ois = null;
        Map<Integer, String> keyCodes = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(CFGFILE));
            keyCodes = (HashMap) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //文本框左上角x坐标
        int x = 6;
        //文本框左上角y坐标
        int y = 38;
        //文本框宽度
        int w = 64;
        //文本框高度
        int h = 20;
        //添加到数组中
        for (int index = 0; index < textCtrls.length; index++) {
            //当索引等于4初始化x,y坐标
            if (index == 4) {
                x = 549;
                y = 40;
            }
            textCtrls[index] = new TextCtrl(x, y, w, h, METHODNAMES[index]);
            for (Map.Entry<Integer, String> entry : keyCodes.entrySet()) {
                if (textCtrls[index].getMethodName().equals(entry.getValue())) {
                    textCtrls[index].setKeyCode(entry.getKey());
                }

            }
            y = y + 30;
        }
        /*for (Map.Entry<Integer, String> entry : keyCodes.entrySet()) {
            for (TextCtrl ctrl : textCtrls) {
                if (ctrl.getMethodName().equals(entry.getValue())){
                    ctrl.setKeyCode(entry.getKey());
                }
            }
        }*/
    }

    /**
     * 创建按钮面板
     *
     * @return
     */
    private JPanel createButtonPanel() {
        //实例化面板，流式布局
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //确认按钮添加事件监听
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeConfig2File();
                setVisible(false);
                control.setActions();
                control.getGamePanel().repaint();
            }
        });
        //添加错误提示信息
        this.errorMsg.setForeground(Color.RED);
        jPanel.add(this.errorMsg);
        //面板添加确认按钮
        jPanel.add(this.btnOk);
        //取消按钮添加事件监听
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                control.getGamePanel().repaint();
            }
        });
        //面板添加取消按钮
        jPanel.add(this.btnCancle);
        //应用按钮添加事件监听
        btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeConfig2File();
                control.setActions();
                control.repaint();
            }
        });
        //面板添加应用按钮
        jPanel.add(this.btnApply);
        return jPanel;
    }

    /**
     * 写入配置到文件中
     */
    private void writeConfig2File() {
        //按键和方法名映射
        Map<Integer, String> keyCodes = new HashMap<>(8);
        //开启输出流
        ObjectOutputStream oos = null;
        try {
            for (int index = 0; index < textCtrls.length; index++) {
                int keyCode = textCtrls[index].getKeyCode();
                if (keyCode == 0) {
                    this.errorMsg.setText("错误的按键");
                    return;
                }
                keyCodes.put(textCtrls[index].getKeyCode(), textCtrls[index].getMethodName());
            }
            if (keyCodes.size() != 8) {
                this.errorMsg.setText("按键重复");
                return;
            }
            Img.setSkinPath(dataList.get(skinList.getSelectedIndex()).toString());
            oos = new ObjectOutputStream(new FileOutputStream(CFGFILE));
            oos.writeObject(keyCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建主面板,选项卡面板
     *
     * @return
     */
    private JTabbedPane createMainPanel() {
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.add("玩家设置", createControlCfg());
        jTabbedPane.add("皮肤设置", createSkinCfg());
        return jTabbedPane;
    }

    /**
     * 创建皮肤设置面板
     *
     * @return 面板
     */
    private JPanel createSkinCfg() {
        //初始化皮肤面板
        skinPanel = new JPanel(new BorderLayout());
        //获得文件流
        File file = new File("pic/");
        if (file.isDirectory()) {
            //获得目录下所有文件
            File[] files = file.listFiles();
            //初始化预览图片数组
            skinViews = new Image[files.length];
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    dataList.addElement(files[i].getName());
                    skinViews[i] = new ImageIcon(files[i].getPath() + "\\view.png").getImage();
                }
            }
        }
        //初始化列表框
        this.skinList = new JList(dataList);
        this.skinList.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
        //选项索引
        this.skinList.setSelectedIndex(0);
        //图片预览面板初始化
        this.viewPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                //预览图片左上角x坐标
                int x = (skinPanel.getWidth() - skinList.getWidth() - skinViews[skinList.getSelectedIndex()].getWidth(null)) >> 1;
                //预览图片左上角y坐标
                int y = 0;
                g.drawImage(skinViews[skinList.getSelectedIndex()], x, y, null);
            }
        };
        //添加到皮肤面板
        skinPanel.add(new JScrollPane(skinList), BorderLayout.WEST);
        skinPanel.add(viewPanel, BorderLayout.CENTER);
        return skinPanel;
    }

    /**
     * 创建玩家设置面板
     *
     * @return 面板
     */
    private JPanel createControlCfg() {
        JPanel jPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(Img.PLAYER_CFG_IMG, 0, 0, Img.PLAYER_CFG_IMG.getWidth(null), Img.PLAYER_CFG_IMG.getHeight(null), null);
            }
        };
        //设置布局管理器
        jPanel.setLayout(null);
        //添加按键输入框
        for (TextCtrl textCtrl : textCtrls) {
            jPanel.add(textCtrl);
        }
        return jPanel;
    }
}
