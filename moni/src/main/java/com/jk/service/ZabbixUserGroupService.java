package com.jk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jk.Enum.ExceptionCode;
import com.jk.domain.Result;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.base.Permission;
import com.zabbix.api.domain.base.User;
import com.zabbix.api.domain.base.UserGroup;
import com.zabbix.api.test.TestUsergroup;

public class ZabbixUserGroupService {

	public static List<UserGroup> getUserGroup(String auth) throws JSONException{
		List<HostGroup> hglist=SettingService.hostGroupGet(auth);
		Iterator<HostGroup> iterator=hglist.iterator();
		Map<String,String> map=new HashMap<>();
		while(iterator.hasNext()){
			HostGroup hg=iterator.next();
			map.put(hg.getGroupid(), hg.getName());
		}
		TestUsergroup tug=new TestUsergroup();
		JSONObject rs=(JSONObject)tug.userGroupGet(auth);
		 JSONArray an = (JSONArray)rs.get("result");
		List<UserGroup> list=new ArrayList<>();
		for(int i=0;i<an.length();i++){
			UserGroup ug=new UserGroup();
			JSONObject jo=(JSONObject) an.get(i);
			System.out.println("主机组==="+jo.getString("name"));
			ug.setDebug_mode(Integer.valueOf(jo.getString("debug_mode")));
			ug.setUsrgrpid(jo.getString("usrgrpid"));
			ug.setName(jo.getString("name"));
			ug.setUsers_status(Integer.valueOf(jo.getString("users_status")));
			ug.setGui_access(Integer.valueOf(jo.getString("gui_access")));
			JSONArray array=jo.getJSONArray("rights");
			List<Permission> perlist=new ArrayList<>();
			for(int t=0;t<array.length();t++){
				JSONObject object=array.getJSONObject(t);
				Permission per = new Permission();
				String id = object.getString("id");
				String name = map.get(id);
				String permission = object.getString("permission");
				per.setGroupid(id);
				per.setName(name);
				per.setPermission(Integer.valueOf(permission));
				perlist.add(per);
			}
			ug.setRights(perlist);
			JSONArray ja=jo.getJSONArray("users");
			List<User> lis=new ArrayList<>();
			for(int j=0;j<ja.length();j++){
				User user=new User();
				JSONObject jj=(JSONObject)ja.get(j);
				String userid=jj.getString("userid");
				user.setUserid(userid);
				user.setName(jj.getString("alias"));
				lis.add(user);
			}
			ug.setUsers(lis);
			
			list.add(ug);
		}
		return list;
	}
	public static Result<String> deleteUserGroup(List<String> ids,String auth) throws JSONException{
		TestUsergroup tug=new TestUsergroup();
		JSONObject rs=(JSONObject)tug.testUserGroupDelete(ids, auth);
		Object errorInfo = null;
		Integer groupid;
		Result<String> result=new Result<>();
	if (rs.has("result")) {
		groupid = rs.getJSONObject("result").getJSONArray("usrgrpids").getInt(0);
		result.setCode(ExceptionCode.successcode);
		result.setMsg(ExceptionCode.successmsg);
		result.setData(groupid.toString());
	}
	 else if (rs.has("error")) {
		 errorInfo =  rs.getJSONObject("error").getJSONArray("debug").getJSONObject(0).getJSONArray("args").get(1);
		 result.setData((String)errorInfo);
		 result.setCode(ExceptionCode.failedcode);
	     result.setMsg(ExceptionCode.failedmsg);
	}
	return result;
}
}
