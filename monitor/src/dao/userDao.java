package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.el.StaticFieldELResolver;
import util.ConnectionPool;
import bean.User;

public class userDao {
   static	ResultSet rs;
   static PreparedStatement ps;
   static ConnectionPool pool=ConnectionPool.getInstance();
   static Connection conn;
  
    /*
     * 登录验证
     * 
     */
    public String findUsername(String user){ 
		
        //结果集对象  
    	String psw=null;
        try {    
        	conn = pool.getConnection();
            String sql = "select * from user where user=?";  
            // System.out.println("sql:"+sql);
            //System.out.println("conn:"+conn);
            ps=conn.prepareStatement(sql);
             ps.setString(1, user);  
             rs = ps.executeQuery();
            if(rs==null){  
                return null;  
            }  
            if(rs.next()){  
               psw=rs.getString("password");  
            }else{  
              psw=null;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  
			pool.release(conn);
        }  
        return psw;  
    }  
	/*
	 * 批量查询
	 */
	public ArrayList<User> getlist() throws SQLException{
		ArrayList<User> ar=new ArrayList<User>();//存储从数据库中取出来的数据  
        try{  
              String sql="select user,password from user";  
              ps=conn.prepareStatement(sql);  
              rs=ps.executeQuery();
              while(rs.next()){
            	  User u =new User();//封装数据  
                  u.setId(rs.getInt("id"));
                  u.setUser(rs.getString("user"));
                  u.setPassword(rs.getString("password"));  
                  ar.add(u);  
                    
                    
              }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{
            try{  
                if(rs!=null){  
                    rs.close();  
                }if(ps!=null){  
                    ps.close();  
                }if(conn!=null){  
                    conn.close();  
                }  
            }catch(Exception e2){  
                e2.printStackTrace();  
            }finally{
            	conn.close();
            }  
        } 
        return ar;//返回ar 
		
		
	}
}
