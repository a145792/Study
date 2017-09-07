package com.mjw.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.mjw.common.util.JDBCUtils;

public class ConnPool {
	
	private static List<Connection> list = new ArrayList<>();
	
	static {
		for(int i = 0; i < 10; i++){
			list.add(JDBCUtils.getConn());
		}
	}
	
	public static void getConn(){
		
		
		
	}
}
