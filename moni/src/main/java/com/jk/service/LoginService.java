package com.jk.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PutMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.zabbix.api.test.Util;
import com.zabbix.util.FormatData;

public class LoginService {

	@SuppressWarnings("deprecation")
	public static JSONObject login(String loginName,String password) {
	
			HttpClient client = new HttpClient();
			PutMethod putMethod = new PutMethod(Util.url);
			putMethod.setRequestHeader("Content-Type", "application/json-rpc");
			String jsonrpc = loginJson(loginName, password);
			JSONObject obj = new JSONObject();
			try {
				
			
			JSONObject jsonObj = new JSONObject(jsonrpc);
			
			putMethod.setRequestBody(FormatData.fromString(jsonObj.toString()));
			String loginResponse = "";
			client.executeMethod(putMethod);
			loginResponse = putMethod.getResponseBodyAsString();
			obj = new JSONObject(loginResponse);
			} catch (Exception e) {
				// TODO: handle exception
			}
		return obj;
	}
	private static String loginJson(String loginName, String password) {
		JSONStringer js = new JSONStringer();
		try {
			js.object();
			js.key("jsonrpc").value("2.0");
			js.key("method").value("user.login");
			js.key("id").value(1);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("user", loginName);
			jsonObject.put("password", password);
			jsonObject.put("userData", true);
			js.key("params").value(jsonObject);

			js.endObject();
		} catch (JSONException e) {
			return null;
		}
		return js.toString();
	}
}
