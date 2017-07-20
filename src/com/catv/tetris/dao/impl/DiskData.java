package com.catv.tetris.dao.impl;

import com.catv.tetris.dao.Data;
import com.catv.tetris.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 磁盘数据
 */
public class DiskData implements Data {

    /**
     * 数据输出的文件
     */
    private static final String url = "data";

    /**
     * 保存数据
     *
     * @param player 存储数据列表
     */
    public void saveData(Player player) {
        List<Player> data = getData();
        data.add(player);
        int size = data.size();
        if (data.size() > 5) {
            Collections.sort(data);
            int indexOf = data.indexOf(player);
            if (indexOf > 4) {
                return;
            }
            List<Player> players = data.subList(5, size);
            File file = null;
            String newUrl = null;
            boolean delete = false;
            for (Player player1 : players) {
                newUrl = url + "\\" + player1.getName() + ".dat";
                System.out.println(newUrl);
                file = new File(newUrl);
                if (file.isFile() && file.exists()) {
                    while (!delete) {
                        System.gc();
                        delete = file.delete();
                    }
                }
            }
        }
        //创建对象输出流
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdir();
            }
            fos = new FileOutputStream(dir + "\\" + player.getName() + ".dat");
            //实例化对象输出流
            oos = new ObjectOutputStream(fos);
            //写入对象流数据
            oos.writeObject(player);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 取出数据
     *
     * @return 返回数据列表
     */
    public List<Player> getData() {
        //创建对象输入流
        ObjectInputStream ois = null;
        List<Player> players = new ArrayList<>();
        FileInputStream in = null;
        try {
            File file = new File(url);
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File file1 : files) {
                    in = new FileInputStream(file1.getPath());
                    //实例化对象输入流
                    ois = new ObjectInputStream(in);
                    //读取对象流数据
                    Player player = (Player) ois.readObject();
                    players.add(player);
                }
            }
            return players;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Data data = new DiskData();
       /* List<Player> players = data.getData();
        for (Player player : players) {
            System.out.println(player.getName() + ":" + player.getScore());
        }*/
        data.saveData(new Player("27", 9080));
        //data.getData();
    }
}
