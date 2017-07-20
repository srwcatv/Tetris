package com.catv.tetris.util;

/**
 * 下落速度计算
 */
public class DownSpeed {

    /**
     * 获得当前等级对应的速度
     *
     * @param nowLevel 等级
     * @return 返回速度
     */
    public static long getSpeed(int nowLevel) {
        return nowLevel >= 16 ? 100 : -40 * nowLevel + 740;

    }
}
