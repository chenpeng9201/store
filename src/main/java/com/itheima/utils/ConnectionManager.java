package com.itheima.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager{
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static Connection con;
	
	public static Connection getConnection() throws SQLException{
		con = local.get();
		if(con==null){
			local.set(C3P0Utils.getConnection());
			con = local.get();
		}
		return con;
	}
	
	public static void begin() throws SQLException{
		getConnection();
		con.setAutoCommit(false);
	}
	public static void commit() throws SQLException{
		con.commit();
	}
	public static void rollback() throws SQLException{
		con.rollback();
	}
	public static void close(){
		try {
			con.close();
			local.remove();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
