package com.mjw.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

	public static final String DRIVERCLASS;
	public static final String URL;
	public static final String USERNAME;
	public static final String PASSWORD;
	
	static{
		Properties properties = new Properties();
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DRIVERCLASS = properties.getProperty("jdbc.driverClass");
		URL = properties.getProperty("jdbc.url");
		USERNAME = properties.getProperty("jdbc.username");
		PASSWORD = properties.getProperty("jdbc.password");
	}
	
	/**
	 * 加载驱动
	 */
	public static void loadDriver(){
		try {
			Class.forName(DRIVERCLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取链接
	 * @return
	 */
	public static Connection getConn(){
		loadDriver();
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 释放资源的代码
	 */
	public static void release(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	
	/**
	 * 释放资源的代码
	 */
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	
}
