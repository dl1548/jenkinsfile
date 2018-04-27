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
import com.zabbix.api.domain.base.Host;
import com.zabbix.api.domain.base.ItemPrototype;
import com.zabbix.api.domain.base.TriggerPrototype;
import com.zabbix.api.domain.itemprototype.ItemPrototypeCreateRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeDeleteRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeExistsRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeGetRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIsreadableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeIswritableRequest;
import com.zabbix.api.domain.itemprototype.ItemPrototypeUpdateRequest;
import com.zabbix.api.service.IItemprototypeService;
import com.zabbix.util.FormatData;
public class ItemprototypeServiceImpl implements IItemprototypeService {
	public Object itemPrototypeCreate(ItemPrototypeCreateRequest itemPrototypeCreate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeCreate);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	public Object itemPrototypeDelete(ItemPrototypeDeleteRequest itemPrototypeDelete,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeDelete);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	public Object itemPrototypeExists(ItemPrototypeExistsRequest itemPrototypeExists,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeExists);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	
	public Object itemPrototypeGet(ItemPrototypeGetRequest itemPrototypeGet,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeGet);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	public List<ItemPrototype> itemPrototypeGetToBean(ItemPrototypeGetRequest itemPrototypeGet,String auth) {
		JSONObject result = (JSONObject) this.itemPrototypeGet(itemPrototypeGet, auth);
		List<ItemPrototype> ItemPrototypes= null;
		if (result.has("result")) {
			JSONArray array;
			try {
				array = result.getJSONArray("result");
				if(array!=null&&array.length()>0){
					ItemPrototypes =new ArrayList<ItemPrototype>();
					for(int i = 0; i<array.length();i++){
						JSONObject jsonObject = array.getJSONObject(i);
						ItemPrototype itemPrototype = new ItemPrototype();
						itemPrototype.setItemid(jsonObject.getString("itemid"));
						itemPrototype.setDelay(jsonObject.getInt("delay"));
						itemPrototype.setKey_(jsonObject.getString("key_"));
						itemPrototype.setName(jsonObject.getString("name"));
						ItemPrototypes.add(itemPrototype);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ItemPrototypes;
	}
	
	public Object itemPrototypeIsreadable(ItemPrototypeIsreadableRequest itemPrototypeIsreadable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeIsreadable);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	public Object itemPrototypeIswritable(ItemPrototypeIswritableRequest itemPrototypeIswritable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeIswritable);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	public Object itemPrototypeUpdate(ItemPrototypeUpdateRequest itemPrototypeUpdate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(itemPrototypeUpdate);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			 rs = new JSONObject(response);
//			if (rs.has("result")) {
//				result = rs.get("result");
//			}
//			 else if (rs.has("error")) {
//				result = rs.get("error");
//			}
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
	
	
}
