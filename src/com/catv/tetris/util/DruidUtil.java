package com.catv.tetris.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 获取和关闭连接
 * @author CATV
 *
 */
public class DruidUtil {

	private static DataSource ds = null;//定义一个连接池变量
	private static Properties p = new Properties();//创建一个properties对象

	private DruidUtil() {
	}

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");//获取数据库连接配置文件输入流。
		try {
			p.load(in);//导入数据库连接配置文件。
			ds = DruidDataSourceFactory.createDataSource(p);//创建连接池对象。
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回连接对象。
	 * @return 返回连接对象
	 * @throws SQLException 抛出一个SQL异常给调用者处理
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	/**
	 * 关闭资源,如果为空则不关闭。
	 * 
	 * @param ps
	 *            关闭PrepareStatement资源
	 * @param rs
	 *            关闭ResultSet资源
	 */
	public static void close(PreparedStatement ps, ResultSet rs, Connection conn) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
