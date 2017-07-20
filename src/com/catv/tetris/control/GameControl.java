package com.catv.tetris.control;

import com.catv.tetris.dto.GameDto;
import com.catv.tetris.entity.Player;
import com.catv.tetris.service.GameService;
import com.catv.tetris.ui.GameFrame;
import com.catv.tetris.ui.GamePanel;
import com.catv.tetris.ui.cfg.FrameConfig;
import com.catv.tetris.ui.cfg.SaveFrame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 游戏控制
 */
public class GameControl {
    /**
     * 窗体
     */
    private GamePanel gamePanel;

    /**
     * 业务逻辑
     */
    private GameService gameService;

    /**
     * 按键行为
     */
    private Map<Integer, String> actions = null;

    /**
     * 游戏设置窗口
     */
    private FrameConfig frameConfig = null;

    /**
     * 玩家控制器
     */
    private PlayerControl playerControl = null;

    /**
     * 游戏窗体
     */
    private GameFrame gameFrame = null;

    /**
     * 游戏线程
     */
    private Thread thread = null;

    /**
     * 数据源
     */
    private GameDto gameDto = null;

    /**
     * 得分窗口
     */
    private SaveFrame saveFrame = null;

    /**
     * 无参构造函数
     */
    public GameControl() {
        //创建数据源
        gameDto = new GameDto();
        //创建游戏窗体
        this.gamePanel = new GamePanel(this,gameDto);
        //创建业务逻辑对象
        this.gameService = new GameService(gameDto);
        //创建玩家控制器
        this.playerControl = new PlayerControl(this);
        //窗体中监听玩家输入
        this.gamePanel.setPlayerControl(playerControl);
        //初始化游戏设置窗口
        this.frameConfig = new FrameConfig(this);
        //初始化得分窗口
        this.saveFrame = new SaveFrame(this);
        //设置按键行为
        this.setActions();
        //创建游戏窗口
        this.gameFrame = new GameFrame(gamePanel);
    }

    /**
     * 按键行为控制
     *
     * @param keyCode 按键code值
     */
    public void actionByKeyCode(int keyCode) {
        try {
            if (this.actions.containsKey(keyCode)) {
                //获得方法对象
                Method method = this.gameService.getClass().getMethod(actions.get(keyCode));
                //调用方法
                method.invoke(this.gameService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        gamePanel.repaint();
    }

    public void setActions(){
        //初始化按键行为
        actions = new HashMap<>(4);
        //读取配置文件
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("config/playerCfg.dat"));
            actions = (HashMap) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始按钮
     */
    public void start() {
        //开始游戏
        this.gameService.start();
        //创建线程
        this.thread = new MyThread();
        //线程开始
        this.thread.start();
        //关闭开始设置按钮
        this.gamePanel.switchButton(false);
        //窗口数据重新加载
        this.initFrameData();
        //刷新画面
        this.gamePanel.repaint();
    }

    /**
     * 保存玩家信息
     * @param nickName 玩家昵称
     */
    public void savePlayer(String nickName) {
        //创建玩家对象
        Player player = new Player();
        //设置属性字段值
        player.setName(nickName);
        player.setScore(this.gameDto.getNowScore());
        //保存到数据库和本地
        this.gameService.saveDatabase(player);
        this.gameService.saveDiskData(player);
    }

    /**
     * 重新初始化窗口数据
     */
    public void initFrameData(){
        //重新加载用户数据
        this.gameDto.setDbPlayers(this.gameService.getDatabase());
        this.gameDto.setDiskPlayers(this.gameService.getDiskData());
        //初始化得分和等级
        this.gameDto.setNowScore(0);
        this.gameDto.setRemoveLine(0);
        this.gameDto.setLevel(0);
        this.gameDto.setSpeed(740);
        //初始化得分窗口输入框值
        this.saveFrame.getTextField().setText("");
        //刷新画面
        this.gamePanel.repaint();
    }

    /**
     * 主线程
     */
    private class MyThread extends Thread {
        public void run() {
            //刷新画面
            gamePanel.repaint();
            while (true) {
                //游戏失败后结束线程循环
                if (!gameDto.isStart()){
                    //失败后的操作
                    afterForFail();
                    break;
                }
                try {
                    Thread.sleep(gameDto.getSpeed());
                    //如果是暂停状态不执行下落
                    if (gameDto.isPause()){
                        continue;
                    }
                    //方块下落
                    gameService.downMove();
                    //刷新画面
                    gamePanel.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 画面刷新
     */
    public void repaint(){
        this.gamePanel.repaint();
        this.gamePanel.initButton();
    }

    /**
     * 失败后执行的操作
     */
    private void afterForFail() {
        //设置得分
        this.saveFrame.getScore().setText("您的得分:" + this.getGameDto().getNowScore());
        //打开得分窗口
        this.saveFrame.setVisible(true);
        //打开按钮开关
        this.gamePanel.switchButton(true);
    }


    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameDto getGameDto() {
        return gameDto;
    }
}
