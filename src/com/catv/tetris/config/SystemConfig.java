package com.catv.tetris.config;

import java.util.Map;

/**
 * 系统配置
 */
public class SystemConfig {

    /**
     * 奖励规则
     */
    private Map<Integer,Integer> plusScoreRule = null;

    /**
     * 构造器
     * @param plusScoreRule
     */
    public SystemConfig(Map<Integer,Integer> plusScoreRule){
        this.plusScoreRule = plusScoreRule;
    }

    public Map<Integer, Integer> getPlusScoreRule() {
        return plusScoreRule;
    }
}
