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
import com.zabbix.api.domain.base.Template;
import com.zabbix.api.domain.template.TemplateCreateRequest;
import com.zabbix.api.domain.template.TemplateDeleteRequest;
import com.zabbix.api.domain.template.TemplateExistsRequest;
import com.zabbix.api.domain.template.TemplateGetRequest;
import com.zabbix.api.domain.template.TemplateGetobjectsRequest;
import com.zabbix.api.domain.template.TemplateIsreadableRequest;
import com.zabbix.api.domain.template.TemplateIswritableRequest;
import com.zabbix.api.domain.template.TemplateMassaddRequest;
import com.zabbix.api.domain.template.TemplateMassremoveRequest;
import com.zabbix.api.domain.template.TemplateMassupdateRequest;
import com.zabbix.api.domain.template.TemplateUpdateRequest;
import com.zabbix.api.service.ITemplateService;
import com.zabbix.util.FormatData;
public class TemplateServiceImpl implements ITemplateService {
	public Object create(TemplateCreateRequest create,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(create);
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
	public Object delete(TemplateDeleteRequest delete,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(delete);
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
	public Object exists(TemplateExistsRequest exists,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(exists);
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
	public Object getobjects(TemplateGetobjectsRequest getobjects,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(getobjects);
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
	public Object get(TemplateGetRequest get,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(get);
			putMethod.setRequestBody( FormatData.fromString(json));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			//System.out.println("json response:"+response);
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
	public List<Template> getTemplateToBean(TemplateGetRequest get,String auth){
		List<Template> templates = null;
		JSONObject result= (JSONObject) this.get(get, auth);
		if (result.has("result")) {
			JSONArray array;
			try {
				array = result.getJSONArray("result");
				if(array!=null&&array.length()>0){
					templates = new ArrayList<Template>();
					for(int i = 0; i<array.length();i++){
						JSONObject jsonObject = array.getJSONObject(i);
						Template template = new Template();
						template.setTemplateid(jsonObject.getString("templateid"));
						template.setHost(jsonObject.getString("host"));
						template.setName(jsonObject.getString("name"));
						templates.add(template);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}else if (result.has("error")) {
			return null;
		}
		return templates;
	}
	
	public Object isreadable(TemplateIsreadableRequest isreadable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(isreadable);
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
	public Object iswritable(TemplateIswritableRequest iswritable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(iswritable);
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
	public Object massadd(TemplateMassaddRequest massadd,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(massadd);
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
	public Object massremove(TemplateMassremoveRequest massremove,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(massremove);
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
	public Object massupdate(TemplateMassupdateRequest massupdate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(massupdate);
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
	public Object update(TemplateUpdateRequest update,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			String json = js.toJson(update);
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
