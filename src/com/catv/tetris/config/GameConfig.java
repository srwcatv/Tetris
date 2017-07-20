package com.catv.tetris.config;

import org.dom4j.Document;
import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏配置
 */
public class GameConfig {

    /**
     * 配置一个单例游戏配置
     */
    private static GameConfig gameConfig = null;

    /**
     * 框架配置
     */
    private FrameConfig frameConfig;
    /**
     * 窗体配置
     */
    private List<BlockPanelConfig> panelConfigs = new ArrayList<>();

    /**
     * 方块配置
     */
    private GameBlockConfig blockConfig;

    /**
     * 系统配置
     */
    private SystemConfig systemConfig;

    /**
     * 初始化配置
     */
    private GameConfig() {
        //获取配置文档
        Document document = ReadConfig.getDocument();
        //获取文档根元素
        Element root = document.getRootElement();
        //获取窗体元素
        Element frame = root.element("frame");
        //获取方块元素
        Element gameBlock = root.element("gameBlock");
        //获取系统元素
        Element system = root.element("system");
        //设置窗体配置
        this.setPanelConfigs(frame);
        //设置框架配置
        this.setFrameConfig(frame);
        //设置方块配置
        this.setBlockConfig(gameBlock);
        //设置系统配置
        this.setSystemConfigs(system);
    }

    /**
     * 获取游戏配置实例
     *
     * @return 返回一个游戏配置实例
     */
    public static GameConfig getInstance() {
        if (gameConfig == null) {
            gameConfig = new GameConfig();
        }
        return gameConfig;
    }

    /**
     * 设置窗体配置
     *
     * @param frame 窗体元素
     */
    private void setFrameConfig(Element frame) {
        frameConfig = new FrameConfig(
                Integer.parseInt(frame.attributeValue("w")),
                Integer.parseInt(frame.attributeValue("h")),
                Integer.parseInt(frame.attributeValue("padding")),
                Integer.parseInt(frame.attributeValue("border")),
                frame.attributeValue("title")
        );
    }

    /**
     * 设置方块配置
     *
     * @param gameBlock 元素
     */
    public void setBlockConfig(Element gameBlock) {
        Map<Integer, List<Point>> allActBlock = new HashMap<>(7);
        List<Point> pointList = null;
        List<Element> actBlocks = gameBlock.elements("actBlock");
        int x, y;
        for (Element actBlock : actBlocks) {
            pointList = new ArrayList<>(4);
            List<Element> points = actBlock.elements("point");
            int type = Integer.parseInt(actBlock.attributeValue("type"));
            for (Element point : points) {
                x = Integer.parseInt(point.attributeValue("x"));
                y = Integer.parseInt(point.attributeValue("y"));
                pointList.add(new Point(x, y));
            }
            allActBlock.put(type, pointList);
        }
        blockConfig = new GameBlockConfig(Integer.parseInt(gameBlock.attributeValue("MIN_X")),
                Integer.parseInt(gameBlock.attributeValue("MAX_X")),
                Integer.parseInt(gameBlock.attributeValue("MIN_Y")),
                Integer.parseInt(gameBlock.attributeValue("MAX_Y")),
                allActBlock
        );
    }

    /**
     * 设置窗体配置
     *
     * @param system 系统元素
     */
    public void setSystemConfigs(Element system) {
        //初始化容器
        Map<Integer, Integer> plusScoreRule = new HashMap<>(4);
        //获取所有窗体元素
        List<Element> rules = system.elements("rule");
        //遍历获取所有窗体配置
        for (Element rule : rules) {
            //添加规则
            plusScoreRule.put(Integer.parseInt(rule.attributeValue("exp")), Integer.parseInt(rule.attributeValue("score")));
        }
        //实例化系统配置
        systemConfig = new SystemConfig(plusScoreRule);
    }

    /**
     * 设置窗体配置
     *
     * @param frame 窗体元素
     */
    public void setPanelConfigs(Element frame) {
        //获取所有窗体元素
        List<Element> panels = frame.elements("panel");
        //遍历获取所有窗体配置
        for (Element panel : panels) {
            panelConfigs.add(new BlockPanelConfig(
                    Integer.parseInt(panel.attributeValue("x")),
                    Integer.parseInt(panel.attributeValue("y")),
                    Integer.parseInt(panel.attributeValue("w")),
                    Integer.parseInt(panel.attributeValue("h")),
                    panel.attributeValue("className")
            ));
        }
    }

    /**
     * 获取框架配置
     *
     * @return
     */
    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    /**
     * 获窗体架配置
     *
     * @return
     */
    public List<BlockPanelConfig> getPanelConfigs() {
        return panelConfigs;
    }

    public GameBlockConfig getBlockConfig() {
        return blockConfig;
    }

    public SystemConfig getSystemConfig() {
        return systemConfig;
    }
}
