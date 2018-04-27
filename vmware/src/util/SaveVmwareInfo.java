package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public class SaveVmwareInfo {
	static ConnectionPool pool;
	static Connection conn;
	public static void mapToSql(Map<String,Object> map,String tablename){
		String line="";
		String value="";
		int flag=1;
		for(String key:map.keySet()){
			if(map.size()<=flag){
				line+=key+")";
				value+="\""+map.get(key)+"\")";
				break;
			}
			line+=key+",";
			value+="\""+map.get(key)+"\",";
			flag++;
		}
		saveDate("replace into "+tablename+" ("+line+" values("+value);
	}
	public static void saveDate(String sql){
		System.out.println(sql);
		pool =  ConnectionPool.getInstance();
	    try {
			conn= pool.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pool.release(conn);
		} 
	}
}
