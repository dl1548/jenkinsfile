package com.zabbix.api.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.zabbix.util.FormatData;


public class Util {
	public static  String path;
	public static  String url;
	private static String loginName = "admin";
	private static String password = "zabbix";
	
 
	//读取设置连接池的属性文件
    private void readYml(){
        //记载配置文件
    	String osType = System.getProperties().getProperty("os.name").toLowerCase();//windows xp
//    	System.out.println(osType);
    	if(osType.startsWith("windows")){
    		  path = getClass().getResource("/").getPath().toString() + "\\application.yml";
    	}else{
    		path="/usr/local/tomcat/webapps/monitor/WEB-INF/classes/application.yml";//linux
    	}
    		
//		  System.out.println("path:"+path);
   }
	 private static  void getYml() {
		  
//		String relativelyPath=System.getProperty("user.dir");
//		String file=relativelyPath+"\\src\\main\\resources\\application.yml";//windows
		//String file=relativelyPath+"/src/main/resources/application.yml";//linux
//		//System.out.println(file1);
		System.out.println(path);
		BufferedReader read=null;
		FileReader filereader=null;
		File file=null;
		try {
			file=new File(path);
			filereader=new FileReader(file);
			read=new BufferedReader(filereader);
		    String s=null;
		    int index=0;
		
			while((s=read.readLine()) != null){
			    index++;
			    if(index==5){
			    	 System.out.println(s);
					 url=s.replaceAll("zabbix: ", "").replace(" ", "");
			    }
			       
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(read!=null)
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(filereader!=null)
				try {
					filereader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		System.out.println(url);
	}
	static{
		Util u=new Util();
		u.readYml();
		getYml();
		FormatData.API_URL = url;
	}
	/**
	 * 登录json
	 * 
	 * @return
	 */
	private static String loginJson(String loginName, String password) {
		JSONStringer js = new JSONStringer();
		try {
			js.object();
			js.key("jsonrpc").value("2.0");
			js.key("method").value("user.login");
			js.key("id").value(1);
			// js.key("auth").value(FormatData.auth);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("user", loginName);
			jsonObject.put("password", password);
			js.key("params").value(jsonObject);

			js.endObject();
		} catch (JSONException e) {
			return null;
		}
		return js.toString();
	}
	@SuppressWarnings("deprecation")
	public static HttpClient login() {
		try {
			HttpClient client = new HttpClient();
			PutMethod putMethod = new PutMethod(url);
			putMethod.setRequestHeader("Content-Type", "application/json-rpc");
			String jsonrpc = loginJson(loginName, password);
			JSONObject jsonObj = new JSONObject(jsonrpc);
			
			putMethod.setRequestBody(FormatData.fromString(jsonObj.toString()));
			String loginResponse = "";
			client.executeMethod(putMethod);
			loginResponse = putMethod.getResponseBodyAsString();
			JSONObject obj = new JSONObject(loginResponse);
			String sessionId = "";

			if (obj.has("result")) {
				sessionId = obj.getString("result");
				
				FormatData.auth = sessionId; //
				// init();

				// IUserGroupService ius = new UserGroupServiceImpl();
				// ius.getAllUserGroup(); 
				// IHostGroupService ihs = new HostGroupServiceImpl();
				// ihs.getAllHostGroup(); // 
				System.out.println(loginResponse);
				System.out.println(sessionId);
				return client;
			} else if (obj.has("error")) {
				sessionId = obj.getJSONObject("error").getString("data");
				throw new Exception(sessionId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
