package com.mjw.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.mjw.common.util.JDBCUtils;

public class JdbcTest {
	
	@Test
	public void fun(){
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			
			String sql = "select * from tb_num";
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(sql);
			
			while(rs.next()){
				String id = rs.getString("id");
				int num = rs.getInt("num");
				System.out.println("id:"+id+"---"+"num:"+num);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != conn){
				try {
					conn.close();
				} catch (SQLException e) {
					conn = null;
				}
			}
			if(null != statement){
				try {
					statement.close();
				} catch (SQLException e) {
					statement = null;
				}
			}
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					rs = null;
				}
			}
		}
	}
	
	
	
	@Test
	public void fun1(){
		
	}
	
	public static void main(String[] args) {
		Connection conn = JDBCUtils.getConn();
		String sql = "select COLUMN_NAME,column_comment from INFORMATION_SCHEMA.Columns where table_name='tb_coupon_pro' and table_schema='frw_dev'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			StringBuffer sb = new StringBuffer();
			while(rs.next()){
				sb.append("pro_san_coupon."+rs.getString(1)+",");
			}
			System.out.println(sb);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
	}
	
}
