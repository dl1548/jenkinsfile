package com.zabbix.api.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zabbix.api.domain.base.Permission;
import com.zabbix.api.domain.base.User;
import com.zabbix.api.domain.base.UserGroup;
import com.zabbix.api.domain.usergroup.UserGroupCreateRequest;
import com.zabbix.api.domain.usergroup.UserGroupDeleteRequest;
import com.zabbix.api.domain.usergroup.UserGroupExistsRequest;
import com.zabbix.api.domain.usergroup.UserGroupGetRequest;
import com.zabbix.api.domain.usergroup.UserGroupGetobjectsRequest;
import com.zabbix.api.domain.usergroup.UserGroupIsreadableRequest;
import com.zabbix.api.domain.usergroup.UserGroupIswritableRequest;
import com.zabbix.api.domain.usergroup.UserGroupMassaddRequest;
import com.zabbix.api.domain.usergroup.UserGroupMassupdateRequest;
import com.zabbix.api.domain.usergroup.UserGroupUpdateRequest;
import com.zabbix.api.service.IUsergroupService;
import com.zabbix.util.FormatData;
public class UsergroupServiceImpl implements IUsergroupService {
	public Object userGroupCreate(UserGroupCreateRequest userGroupCreate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupCreate));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupCreate)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	
	
	public Object userGroupDelete(UserGroupDeleteRequest userGroupDelete,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupDelete));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupDelete)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	
	
	public Object userGroupExists(UserGroupExistsRequest userGroupExists,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupExists));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupExists)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupGetobjects(UserGroupGetobjectsRequest userGroupGetobjects,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupGetobjects));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupGetobjects)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupGet(UserGroupGetRequest userGroupGet,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println("用户组post"+js.toJson(userGroupGet));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupGet)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println("用户组返回："+response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupIsreadable(UserGroupIsreadableRequest userGroupIsreadable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupIsreadable));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupIsreadable)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupIswritable(UserGroupIswritableRequest userGroupIswritable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupIswritable));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupIswritable)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupMassadd(UserGroupMassaddRequest userGroupMassadd,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupMassadd));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupMassadd)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupMassupdate(UserGroupMassupdateRequest userGroupMassupdate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupMassupdate));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupMassupdate)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
	public Object userGroupUpdate(UserGroupUpdateRequest userGroupUpdate,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(userGroupUpdate));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(userGroupUpdate)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			System.out.println(response);
			 rs = new JSONObject(response);
			System.out.println(rs);
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
