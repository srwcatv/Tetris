package com.catv.tetris.dao.impl;

import com.catv.tetris.dao.Data;
import com.catv.tetris.entity.Player;
import com.catv.tetris.util.AddEmptyPlayer;
import com.catv.tetris.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库数据
 */
public class Database implements Data {

    /**
     * 查询语句
     */
    private static final String queryList = "SELECT * from player ORDER BY score DESC LIMIT 0,5";

    /**
     * 插入语句
     */
    private static final String savePlayer = "insert into player (name,score) values(?,?)";

    /**
     * 保存数据
     *
     * @param player 保存的用户列表
     */
    public void saveData(Player player) {
        //获得数据库连接
        Connection connection = null;
        //获得预编译器
        PreparedStatement preparedStatement = null;
        try {
            connection = DruidUtil.getConnection();
            preparedStatement = connection.prepareStatement(savePlayer);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getScore());
            preparedStatement.executeUpdate();
            System.out.println("数据库保存完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(preparedStatement, null, connection);
        }
    }

    /**
     * 获得用户列表
     *
     * @return 返回分数最高的前五行用户
     */
    public List<Player> getData() {
        List<Player> players = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DruidUtil.getConnection();
            preparedStatement = connection.prepareStatement(queryList);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Player player = new Player();
                player.setName(resultSet.getString("name"));
                player.setScore(resultSet.getInt("score"));
                players.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(preparedStatement, resultSet, connection);
        }
        //填充集合列表到5个元素
        AddEmptyPlayer.Add(players);
        return players;
    }
}
