package com.zabbix.api.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.hostgroup.HostGroupCreateRequest;
import com.zabbix.api.domain.hostgroup.HostGroupDeleteRequest;
import com.zabbix.api.domain.hostgroup.HostGroupExistsRequest;
import com.zabbix.api.domain.hostgroup.HostGroupGetRequest;
import com.zabbix.api.domain.hostgroup.HostGroupGetobjectsRequest;
import com.zabbix.api.service.IHostgroupService;
import com.zabbix.api.service.impl.HostgroupServiceImpl;

public class TestHostgroup extends TestCase {
	private static IHostgroupService hostgroupService = new HostgroupServiceImpl();
	static {
		// 登录
		new Util().login();
	}
	public void testHostGroupCreate(String auth) {
		// 数据准备
		HostGroupCreateRequest hostGroupCreate = new HostGroupCreateRequest(auth);
		
		HostGroup hostGroup = new HostGroup();
		hostGroup.setName("");	
		hostGroupCreate.getParams().add(hostGroup);
//		
//		hostGroup = new HostGroup();
//		hostGroup.setName("testHost2a");
//		hostGroupCreate.getParams().add(hostGroup);
		
		JSONObject rs = (JSONObject) hostgroupService.hostGroupCreate(hostGroupCreate, auth);
		if (rs.has("result")) {
			try {
				JSONObject result =(JSONObject) rs.get("result");
				JSONArray ids  = (JSONArray) result.get("groupids");
				String id = (String) ids.get(0);
				System.out.println(id);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 else if (rs.has("error")) {
		    try {
		       	JSONObject result = (JSONObject) rs.get("error");
		    	String errormessage = (String) result.get("data");
		    	System.out.println(errormessage);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void testHostGroupExist(String auth){
		HostGroupExistsRequest request = new HostGroupExistsRequest(auth);
		List<String> nameList = new ArrayList<String>();
		nameList.add("");
		request.getParams().setName(nameList);
		Boolean result = (Boolean)hostgroupService.hostGroupExists(request, auth);
	}

	public void testHostGroupDelete(String auth) {
		// 数据准备
		HostGroupDeleteRequest hostGroupDelete = new HostGroupDeleteRequest(auth);
		
		hostGroupDelete.getParams().add("18");
		
		hostgroupService.hostGroupDelete(hostGroupDelete, auth);

	}

	public void testHostGroupExists(String auth) {
		// 数据准备
		HostGroupExistsRequest hostGroupExists = new HostGroupExistsRequest(auth);
		
//		hostGroupExists.getParams().getGroupid().add("191");
		hostGroupExists.getParams().getName().add("windows");
//		hostGroupExists.getParams().getName().add("testHost3");
		
		boolean result = (Boolean) hostgroupService.hostGroupExists(hostGroupExists, auth);
		
		System.out.println(result);
	}

	public void testHostGroupGetobjects(String auth) {
		// 数据准备
		HostGroupGetobjectsRequest hostGroupGetobjects = new HostGroupGetobjectsRequest(auth);
		
		hostGroupGetobjects.getParams().getName().add("testHost3");
		
		hostgroupService.hostGroupGetobjects(hostGroupGetobjects, auth);

	}

	public List<HostGroup> testHostGroupGet(String auth) {
		
		// 数据准备
		HostGroupGetRequest hostGroupGet = new HostGroupGetRequest(auth);
//		String[] host={"hostid","name"};
		hostGroupGet.getParams().setOutput("extend");
		hostGroupGet.getParams().setSelectHosts("name");
//		hostGroupGet.getParams().setName("Templates");
//		hostGroupGet.getParams().getGroupids().add("2");
		
		
		return hostgroupService.hostGroupGet(hostGroupGet, auth);

	}
	public static void main(String[] args,String auth) {
		TestHostgroup th=new TestHostgroup();
		
		th.testHostGroupGet(auth);
	}
	public void testgetHostGroupBean(String auth) {
		// 数据准备
		HostGroupGetRequest hostGroupGet = new HostGroupGetRequest(auth);
		
		hostGroupGet.getParams().setOutput("extend");
		List<String> names = new ArrayList<String>();
		names.add("windows");
		names.add("Linux servers");
		hostGroupGet.getParams().getFilter().setName(names);
//		hostGroupGet.getParams().setSelectHosts("extend");
//		hostGroupGet.getParams().setName("Templates");
//		hostGroupGet.getParams().getGroupids().add("1");
		
		List<HostGroup> groups = hostgroupService.getHostGroupBean(hostGroupGet, auth);
		System.out.println(groups.size());
	}
 public String getHostGroup(String auth){
	 HostGroupGetRequest get =new HostGroupGetRequest(auth);
	 String[] host={"hostid","name"};
	 get.getParams().setOutput("extend");
	 get.getParams().setSelectHosts("extend");
	 
	 return hostgroupService.getHostGroup(get, auth).toString();
 }
	
}