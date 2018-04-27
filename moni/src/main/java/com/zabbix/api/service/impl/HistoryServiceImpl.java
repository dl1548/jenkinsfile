package com.zabbix.api.service.impl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zabbix.api.domain.base.IntegerHistory;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.util.FormatData;
/**
 * @ClassName: HistoryServiceImpl
 * @Description: 
 * @author 
 * @date 
 */
public class HistoryServiceImpl implements IHistoryService {
	
	/*
	 * Title: get
	 * Description: 获取历史信息（json）
	 * @param get
	 * @return Object
	 * @see com.zabbix.api.service.IHistoryService#get(com.zabbix.api.domain.history.HistoryGetRequest)
	 */
	public Object get(HistoryGetRequest get,String auth){
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(get);
			//System.out.println("请求+++"+json);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
			// System.out.println("return:"+rs);
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	//{"params":{"history":3,"hostids":["10187"],"itemids":["27240"],"time_from":1522648780,"time_till":1522652380,"output":"extend"},"jsonrpc":"2.0","method":"history.get","auth":"9acce8c35a13496c16b7e7a4ca3653fd","id":1}
	public static void main(String[] args) {
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json1="{\"params\":{\"history\":3,\"hostids\":[\"10187\"],\"itemids\":[\"27240\"],\"time_from\":1522648780,\"time_till\":1522652380,\"output\":\"extend\"},\"jsonrpc\":\"2.0\",\"method\":\"history.get\",\"auth\":\"9acce8c35a13496c16b7e7a4ca3653fd\",\"id\":1}";
			String json2="{\"params\":{\"history\":3,\"hostids\":[\"10187\"],\"itemids\":[\"27240\"],\"time_from\":1522643056,\"time_till\":1522646656,\"output\":\"extend\"},\"jsonrpc\":\"2.0\",\"method\":\"history.get\",\"auth\":\"4ad9aee02fb026f445b8ca1b25aee5ec\",\"id\":1}";
			String json3="{\"params\":{\"history\":3,\"hostids\":[\"10187\"],\"itemids\":[\"27887\"],\"time_from\":1522651261,\"time_till\":1522654861,\"sortfield\":[\"clock\"],\"sortorder\":[\"DESC\"]},\"jsonrpc\":\"2.0\",\"method\":\"history.get\",\"auth\":\"1d7d4aaf4e96da03ae065ad395d78f0e\",\"id\":1}";
		//	System.out.println("请求+++"+json3);
			putMethod.setRequestBody( FormatData.fromString(json3));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
		//	 System.out.println("return:"+rs);
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Title: getHistoryToBean
	 * Description: 获取历史信息（bean）
	 * @param get
	 * @return List<IntegerHistory>
	 * @see com.zabbix.api.service.IHistoryService#getHistoryToBean(com.zabbix.api.domain.history.HistoryGetRequest)
	 */
	@Override
	public List<IntegerHistory> getHistoryToBean(HistoryGetRequest get,String auth) throws UnsupportedEncodingException {
		Gson js = new Gson();
		String response=null;
		JSONObject result=new JSONObject();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
			String json = js.toJson(get);
		//	System.out.println("请求+++"+json);
			putMethod.setRequestBody( FormatData.fromString(json));
			try {
				client.executeMethod(putMethod);
				response = putMethod.getResponseBodyAsString();
				result = new JSONObject(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	//	System.out.println("返回"+result);
		List<IntegerHistory> histories = null;
		try {
			if (result.has("result")&&result.getJSONArray("result")!=null) {
				try {
					JSONArray array = result.getJSONArray("result");
					if(array!=null&&array.length()>0){
						histories = new ArrayList<IntegerHistory>();
						for(int i=0;i<array.length();i++){
							JSONObject object = array.getJSONObject(i);
							IntegerHistory his = new IntegerHistory();
							his.setClock(object.getString("clock"));
							his.setItemid(object.getString("itemid"));
							his.setNs(object.getInt("ns"));
							his.setValue(object.getString("value"));
							if (object.has("history")) {
								his.setHistory(object.getInt("history"));	
							}
							
							histories.add(his);
							
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
					return null;
				}
				return histories;
			}else if (result.has("error")) {
				return null;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return histories;
	}
}
