package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAccount {
	static String sql="select * from vm";
	static ConnectionPool pool;
	static Connection conn;
	static List<Map<String,String>> list=new ArrayList<Map<String,String>>();
	public static ResultSet getAccount(){
		pool =  ConnectionPool.getInstance();
		try {
			conn= pool.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			return ps.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pool.release(conn);
		}
		return null; 
	} 
}
