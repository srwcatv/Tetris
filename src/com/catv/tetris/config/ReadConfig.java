package com.catv.tetris.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * 窗体配置文件
 */
public class ReadConfig {
    /**
     * 申明一个document引用
     */
    private static Document document = null;
    private ReadConfig(){}
    static {
        try {
            //获取文档读取器
            SAXReader reader = new SAXReader();
            //读取配置文档
            document = reader.read("config/config.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得读取的配置文档
     * @return 返回读取的配置文档
     */
    public static Document getDocument(){
        return document;
    }
}
