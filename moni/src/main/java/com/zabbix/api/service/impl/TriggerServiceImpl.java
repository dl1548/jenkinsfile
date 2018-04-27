package com.zabbix.api.service.impl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zabbix.api.domain.base.Trigger;
import com.zabbix.api.domain.trigger.TriggerAdddependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest;
import com.zabbix.api.domain.trigger.TriggerCreateRequest.TriggerCreateParams;
import com.zabbix.api.domain.trigger.TriggerDeleteRequest;
import com.zabbix.api.domain.trigger.TriggerDeletedependenciesRequest;
import com.zabbix.api.domain.trigger.TriggerExistsRequest;
import com.zabbix.api.domain.trigger.TriggerGetRequest;
import com.zabbix.api.domain.trigger.TriggerGetobjectsRequest;
import com.zabbix.api.domain.trigger.TriggerIsreadableRequest;
import com.zabbix.api.domain.trigger.TriggerIswritableRequest;
import com.zabbix.api.domain.trigger.TriggerUpdateRequest;
import com.zabbix.api.service.ITriggerService;
import com.zabbix.util.FormatData;
public class TriggerServiceImpl implements ITriggerService {
	public Object adddependencies(TriggerAdddependenciesRequest adddependencies,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
			System.out.println(js.toJson(adddependencies));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(adddependencies)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
			//System.out.println(response);
			 rs = new JSONObject(response);
			//System.out.println(rs);
			//System.out.println(rs.length());
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
	public Object create(TriggerCreateRequest create,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
	//		System.out.println(js.toJson(create));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(create)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
	//		System.out.println(response);
			 rs = new JSONObject(response);
//			System.out.println(rs);
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
	
	public Object addTrigger(Trigger trigger,String auth){
		Object object = null;
		String desc = trigger.getDescription();
		String expr = trigger.getExpression();
		int prior = trigger.getPriority();
		int status = trigger.getStatus();
		if(desc!=null&&!"".equals(desc)&&expr!=null&&!"".equals(expr)){
			TriggerCreateRequest createRequest = new TriggerCreateRequest(auth);
			TriggerCreateParams params = createRequest.getParams();
			params.setDescription(desc);
			params.setExpression(expr);
			params.setStatus(status);
			params.setPriority(prior);
			object = this.create(createRequest, auth);
		}
		return object;
	}
	
	public Object deletedependencies(TriggerDeletedependenciesRequest deletedependencies,String auth){
		
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
	//		System.out.println(js.toJson(deletedependencies));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(deletedependencies)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
	//		System.out.println(response);
			 rs = new JSONObject(response);
	//		System.out.println(rs);
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
	public Object delete(TriggerDeleteRequest delete,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
	//		System.out.println(js.toJson(delete));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(delete)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
	//		System.out.println(response);
			 rs = new JSONObject(response);
	//		System.out.println(rs);
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
	public Object exists(TriggerExistsRequest exists,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
		//	System.out.println(js.toJson(exists));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(exists)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
		//	System.out.println(response);
			 rs = new JSONObject(response);
		//	System.out.println(rs);
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
	public Object getobjects(TriggerGetobjectsRequest getobjects,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
		//	System.out.println(js.toJson(getobjects));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(getobjects)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
		//	System.out.println(response);
			 rs = new JSONObject(response);
		//	System.out.println(rs);
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
	

	public  List<Trigger> get(TriggerGetRequest get,String auth){
		 List<Trigger> list = new ArrayList<Trigger>();
		FormatData fd=new FormatData();
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		try {
		//	System.out.println("请求 request="+js.toJson(get));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(get)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
		//	System.out.println("返回 response="+response);
			ObjectMapper mapper=new ObjectMapper();
			JsonNode node = mapper.readTree(response);
			if(node.has("result")){
				ArrayNode an = (ArrayNode)node.get("result");
				for(JsonNode s:an){
					String date = null;
					Integer priority = null;
					Trigger trigger=new Trigger();
					if(null!=s.get("lastchange"))
					 date=s.get("lastchange").toString().replaceAll("\"", "");
					if(null!=s.get("lastchange")&& !date.isEmpty()){
						trigger.setLastchange(date);	
					}
					
					if(null!=s.get("groups")&&s.get("groups").size()>0)
					trigger.setTriggerid(s.get("groups").get(0).toString());//此处将主机组放入触发器id临时字段
					if(null!=s.get("hosts")&&s.get("hosts").size()>0)
					trigger.setComments(s.get("hosts").get(0).toString());//此处将主机放入comments临时字段
					if(null!=s.get("description")){
						if((s.get("description").toString().indexOf("{HOST.NAME}"))!=-1){
							trigger.setDescription(s.get("description").toString().replaceAll("\\{HOST.NAME\\}",((ArrayNode)s.get("hosts")).get(0).get("host").toString()).replaceAll("\"", ""));
						}else{
							trigger.setDescription(s.get("description").toString().replaceAll("\"", ""));
						}
							
					}
//						
					if(null!=s.get("priority"))
					priority=Integer.parseInt(s.get("priority").toString().replaceAll("\"", ""));
					if(null!=s.get("groups")){
						Iterator<JsonNode> elements=s.get("groups").getElements();
						while(elements.hasNext()){
							JsonNode jsonnod=elements.next();
							trigger.getGroups().add(jsonnod.get("groupid").asInt());
						}
						}
					if(null!=s.get("hosts")){
						Iterator<JsonNode> elements=s.get("hosts").getElements();
						while(elements.hasNext()){
							JsonNode jsonnod=elements.next();
							trigger.getHosts().add(jsonnod.get("hostid").asInt());
						}
					}
					if(null!=s.get("error")){
						trigger.setError(s.get("error").toString());
					}
					trigger.setPriority(priority);
					list.add(trigger);
					
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Object isreadable(TriggerIsreadableRequest isreadable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
		//	System.out.println(js.toJson(isreadable));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(isreadable)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
		//	System.out.println(response);
			 rs = new JSONObject(response);
		//	System.out.println(rs);
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
	public Object iswritable(TriggerIswritableRequest iswritable,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
		//	System.out.println(js.toJson(iswritable));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(iswritable)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
		//	System.out.println(response);
			 rs = new JSONObject(response);
		//	System.out.println(rs);
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
	public Object update(TriggerUpdateRequest update,String auth){
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		try {
	//		System.out.println(js.toJson(update));
			putMethod.setRequestBody( FormatData.fromString(js.toJson(update)));
			client.executeMethod(putMethod);
			String response = putMethod.getResponseBodyAsString();
	//		System.out.println(response);
			 rs = new JSONObject(response);
	//		System.out.println(rs);
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
	public Object getObj(TriggerGetRequest get,String auth) {
		Object result = null;
		Gson js = new Gson();
		HttpClient client = new HttpClient();
		PostMethod putMethod = new PostMethod(FormatData.API_URL);
		putMethod.setRequestHeader("Content-Type", "application/json-rpc");
		JSONObject rs =null;
		//	System.out.println("+++++++"+js.toJson(get));
			try {
				putMethod.setRequestBody( FormatData.fromString(js.toJson(get)));
				client.executeMethod(putMethod);
				String response = putMethod.getResponseBodyAsString();
//				System.out.println(response);
				 rs = new JSONObject(response);
	//			System.out.println(rs);
//				if (rs.has("result")) {
//					result = rs.get("result");
//				}
//				 else if (rs.has("error")) {
//					result = rs.get("error");
//				}
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
	public List<Trigger> getTriggerBean(TriggerGetRequest get,String auth) {
		JSONObject result = (JSONObject) this.get(get, auth);
		List<Trigger> triggers = null;
		if (result.has("result")) {
			try {
				JSONArray array = result.getJSONArray("result");
				if(array!=null&&array.length()>0){
					triggers = new ArrayList<Trigger>();
					for(int i=0;i<array.length();i++){
						JSONObject triggerjson = array.getJSONObject(i);
						Trigger trigger =new Trigger();
						trigger.setComments(triggerjson.getString("comments"));
						trigger.setDescription(triggerjson.getString("description"));
						trigger.setError(triggerjson.getString("error"));
						trigger.setExpression(triggerjson.getString("expression"));
						trigger.setFlags(triggerjson.getInt("flags"));
						trigger.setLastchange(triggerjson.getString("lastchange"));
						trigger.setPriority(triggerjson.getInt("priority"));
						trigger.setStatus(triggerjson.getInt("status"));
						trigger.setTemplateid(triggerjson.getString("templateid"));
						trigger.setTriggerid(triggerjson.getString("triggerid"));
						trigger.setType(triggerjson.getInt("type"));
						trigger.setUrl(triggerjson.getString("url"));
						trigger.setValue(triggerjson.getInt("value"));
						trigger.setValue_flags(triggerjson.getInt("value_flag"));
						triggers.add(trigger);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				return triggers;
			}
		}else if (result.has("error")) {
			return triggers;
		 }
		return triggers;
	}
}
