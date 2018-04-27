package com.jk.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class LoginUtil {
	public static String url = "http://192.168.1.200/zabbix/api_jsonrpc.php";
	private static String loginName = "admin";
	private static String password = "zabbix";
//	static{
//		FormatData.API_URL = url;
//	}
	
	@SuppressWarnings("deprecation")
	public static HttpClient login() {
		try {
			HttpClient client = new HttpClient();
			PutMethod putMethod = new PutMethod(url);
			putMethod.setRequestHeader("Content-Type", "application/json-rpc");
			String jsonrpc = loginJson(loginName, password);
			JSONObject jsonObj = new JSONObject(jsonrpc);
			
			putMethod.setRequestBody(fromString(jsonObj.toString()));
			String loginResponse = "";
			client.executeMethod(putMethod);
			loginResponse = putMethod.getResponseBodyAsString();
			JSONObject obj = new JSONObject(loginResponse);
			String sessionId = "";

			if (obj.has("result")) {
				sessionId = obj.getString("result");
				
				//FormatData.auth = sessionId; 
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
	public static InputStream fromString(String str)
			throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("UTF-8");
		return new ByteArrayInputStream(bytes);
	}

}
