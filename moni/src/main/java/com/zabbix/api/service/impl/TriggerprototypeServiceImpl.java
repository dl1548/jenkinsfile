package com.zabbix.api.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zabbix.api.domain.base.TriggerPrototype;
import com.zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeCreateRequest.TriggerPrototypeCreateParams;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeDeleteRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeGetRequest;
import com.zabbix.api.domain.triggerprototype.TriggerPrototypeUpdateRequest;
import com.zabbix.api.service.ITriggerprototypeService;
import com.zabbix.util.FormatData;
public class TriggerprototypeServiceImpl implements ITriggerprototypeService {
	
	public Object triggerPrototypeCreate(TriggerPrototypeCreateRequest triggerPrototypeCreate,String auth){
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(triggerPrototypeCreate);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Object addTriggerPrototype(TriggerPrototype triggerPrototype,String auth){
		Object object = null;
		String desc = triggerPrototype.getDescription();
		String expr = triggerPrototype.getExpression();
		int prio = triggerPrototype.getPriority();
		int status = triggerPrototype.getStatus();
		if(desc!=null&&!"".equals(desc)&&expr!=null&&!"".equals(expr)){
			TriggerPrototypeCreateRequest prototypeCreateRequest = new TriggerPrototypeCreateRequest(auth);
			TriggerPrototypeCreateParams params = prototypeCreateRequest.getParams();
			params.setDescription(desc);
			params.setExpression(expr);
			params.setPriority(prio);
			params.setStatus(status);
			object = this.triggerPrototypeCreate(prototypeCreateRequest, auth);
		}
		return object;
	}
	
	public Object triggerPrototypeDelete(TriggerPrototypeDeleteRequest triggerPrototypeDelete,String auth){
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(triggerPrototypeDelete);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Object triggerPrototypeGet(TriggerPrototypeGetRequest triggerPrototypeGet,String auth){
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(triggerPrototypeGet);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public List<TriggerPrototype> triggerPrototypeGetToBean(TriggerPrototypeGetRequest triggerPrototypeGet,String auth) {
		List<TriggerPrototype> triggerPrototypes = null;
		JSONObject result= (JSONObject) this.triggerPrototypeGet(triggerPrototypeGet, auth);
		if (result.has("result")) {
			try {
				JSONArray array = result.getJSONArray("result");
				if(array!=null&&array.length()>0){
					triggerPrototypes = new ArrayList<TriggerPrototype>() ;
					for(int i = 0; i<array.length();i++){
						JSONObject jsonObject = array.getJSONObject(i);
						TriggerPrototype triggerPrototype = new TriggerPrototype();
						triggerPrototype.setTriggerid(jsonObject.getString("triggerid"));
						triggerPrototype.setComments(jsonObject.getString("comments"));
						triggerPrototype.setDescription(jsonObject.getString("description"));
						triggerPrototype.setExpression(jsonObject.getString("expression"));
						triggerPrototype.setPriority(jsonObject.getInt("priority"));
						triggerPrototype.setStatus(jsonObject.getInt("status"));
						triggerPrototype.setType(jsonObject.getInt("type"));
						triggerPrototype.setUrl(jsonObject.getString("url"));
						triggerPrototype.setTemplateid(jsonObject.getString("templateid"));
						triggerPrototypes.add(triggerPrototype);
					}
					return triggerPrototypes;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return triggerPrototypes;
	}
	
	
	public Object triggerPrototypeUpdate(TriggerPrototypeUpdateRequest triggerPrototypeUpdate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(triggerPrototypeUpdate);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
			if (rs.has("result")) {
				result = rs.get("result");
			}
			 else if (rs.has("error")) {
				result = rs.get("error");
			}
		}
		 catch (HttpException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		 catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
