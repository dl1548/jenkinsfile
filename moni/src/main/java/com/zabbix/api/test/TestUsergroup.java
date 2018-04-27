package com.zabbix.api.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

import com.jk.Enum.ExceptionCode;
import com.jk.domain.Result;
import com.zabbix.api.domain.base.HostGroup;
import com.zabbix.api.domain.base.Permission;
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
import com.zabbix.api.service.impl.UsergroupServiceImpl;

public class TestUsergroup extends TestCase {
	private static IUsergroupService usergroupService = new UsergroupServiceImpl();
	static {
		// 登录
		new Util().login();
	}

	public String testUserGroupCreate(String name,Integer userStatus,String[] read_write,String[] read_only,String[] deny,String auth) throws JSONException {
		// 数据准备
		UserGroupCreateRequest userGroupCreate = new UserGroupCreateRequest(auth);

		userGroupCreate.getParams().setName(name);
		userGroupCreate.getParams().setUsers_status(userStatus);
		List<Permission> rights = new ArrayList<>();
		if(read_write!=null){
			for(String rw:read_write){
				Permission permission = new Permission();
				permission.setId(rw);
				permission.setPermission(3);
				rights.add(permission);
			}
		}
		if(read_only!=null){
			for(String ro:read_only){
				Permission permission = new Permission();
				permission.setId(ro);
				permission.setPermission(2);
				rights.add(permission);
			}
		}
		if(deny!=null){
			for(String d:deny){
				Permission permission = new Permission();
				permission.setId(d);
				permission.setPermission(0);
				rights.add(permission);
			}
		}
		userGroupCreate.getParams().setRights(rights);
		JSONObject object=(JSONObject)usergroupService.userGroupCreate(userGroupCreate, auth);
		String id="";
		if(object.has("result")){
			id=object.getJSONObject("result").getJSONArray("usrgrpids").getString(0);
		}else if(object.has("error")){
			id=object.getJSONObject("error").getString("data");
		}
		return id;

	}

	public Object testUserGroupDelete(List<String> ids,String auth) {
		// 数据准备
		UserGroupDeleteRequest userGroupDelete = new UserGroupDeleteRequest(auth);

		userGroupDelete.getParams().addAll(ids);

		return usergroupService.userGroupDelete(userGroupDelete, auth);

	}

	public void testUserGroupExists(String auth) {
		// 数据准备
		UserGroupExistsRequest userGroupExists = new UserGroupExistsRequest(auth);

		List<String> ids = new ArrayList<String>();
		ids.add("testGroup10");
		userGroupExists.getParams().setName(ids);

		usergroupService.userGroupExists(userGroupExists, auth);

	}

	public void testUserGroupGetobjects(String auth) {
		// 数据准备
		UserGroupGetobjectsRequest userGroupGetobjects = new UserGroupGetobjectsRequest(auth);
		
		userGroupGetobjects.getParams().setName("test");
		
		
		usergroupService.userGroupGetobjects(userGroupGetobjects, auth);

	}

	public Object testUserGroupGet(String auth) {
		// 数据准备
		UserGroupGetRequest userGroupGet = new UserGroupGetRequest(auth);
		
		userGroupGet.getParams().setOutput("extends");
//		List<String> names = new ArrayList<String>();
//		names.add("Zabbix administrators");
//		names.add("Enabled debug mode");
//		userGroupGet.getParams().getFilter().setName(names);
		userGroupGet.getParams().setSelectUsers("users");
		return usergroupService.userGroupGet(userGroupGet, auth);

	}
	public Object userGroupGet(String auth) {
		// 数据准备
		UserGroupGetRequest userGroupGet = new UserGroupGetRequest(auth);
		userGroupGet.getParams().setOutput("extend");
		userGroupGet.getParams().setSelectUsers("extend");
		userGroupGet.getParams().setSelectRights("extends");
		
		return usergroupService.userGroupGet(userGroupGet, auth);

	}
	public static void main(String[] args,String auth) {
		TestUsergroup tu=new TestUsergroup();
		tu.userGroupGet(auth);
	}
	public void testUserGroupIsreadable(String auth) {
		// 数据准备
		UserGroupIsreadableRequest userGroupIsreadable = new UserGroupIsreadableRequest(auth);
//		List<String> ids = new ArrayList<>();
		userGroupIsreadable.getParams().add("12");
//		userGroupIsreadable.getParams().add("17");
		usergroupService.userGroupIsreadable(userGroupIsreadable, auth);

	}

	public void testUserGroupIswritable(String auth) {
		// 数据准备
		UserGroupIswritableRequest userGroupIswritable = new UserGroupIswritableRequest(auth);
		userGroupIswritable.getParams().add("12");
		usergroupService.userGroupIswritable(userGroupIswritable, auth);

	}

	public void testUserGroupMassadd(String auth) {
		// 数据准备
		UserGroupMassaddRequest userGroupMassadd = new UserGroupMassaddRequest(auth);
		
		userGroupMassadd.getParams().getUsrgrpids().add("25");
		Permission permission = new Permission();
		permission.setGroupid("16");
		permission.setPermission(3);		
		userGroupMassadd.getParams().getRights().add(permission);		
		permission = new Permission();
		permission.setGroupid("15");
		permission.setPermission(2);
		userGroupMassadd.getParams().getRights().add(permission);
		userGroupMassadd.getParams().getUserids().add("1");
		userGroupMassadd.getParams().getUserids().add("2");
		
		usergroupService.userGroupMassadd(userGroupMassadd, auth);

	}

	public void testUserGroupMassupdate(String auth) {
		// 数据准备
		UserGroupMassupdateRequest userGroupMassupdate = new UserGroupMassupdateRequest(auth);
		
		
		userGroupMassupdate.getParams().getUsrgrpids().add("25");
		Permission permission = new Permission();
		permission.setGroupid("16");
		permission.setPermission(3);		
		userGroupMassupdate.getParams().getRights().add(permission);		
		permission = new Permission();
		permission.setGroupid("15");
		permission.setPermission(3);
		userGroupMassupdate.getParams().getRights().add(permission);
		userGroupMassupdate.getParams().getUserids().add("1");
		
		usergroupService.userGroupMassupdate(userGroupMassupdate, auth);

	}

	public Result<String> testUserGroupUpdate(Integer groupid,String name, Integer userStatus,String[] read_write,String[] read_only,String[] deny,String auth) throws JSONException {
		// 数据准备
		UserGroupUpdateRequest userGroupUpdate = new UserGroupUpdateRequest(auth);
		userGroupUpdate.getParams().setUsrgrpid(groupid.toString());
		userGroupUpdate.getParams().setName(name);
		userGroupUpdate.getParams().setUsers_status(userStatus);
		List<Permission> rights=new ArrayList<>();
		if(read_write!=null){
			for(String rw:read_write){
				Permission permission = new Permission();
				permission.setId(rw);
				permission.setPermission(3);
				rights.add(permission);
			}
		}
		if(read_only!=null){
			for(String ro:read_only){
				Permission permission = new Permission();
				permission.setId(ro);
				permission.setPermission(2);
				
				rights.add(permission);
			}
		}
		if(deny!=null){
			for(String d:deny){
				Permission permission = new Permission();
				permission.setId(d);
				permission.setPermission(0);
				rights.add(permission);
			}	
		}
		userGroupUpdate.getParams().setRights(rights);
		JSONObject object=(JSONObject) usergroupService.userGroupUpdate(userGroupUpdate, auth);
		Result<String> result=new Result<>();
		if(object.has("result")){
			String usrgrpid=object.getJSONObject("result").getJSONArray("usrgrpids").getString(0);
			result.setCode(200);
			result.setMsg(ExceptionCode.successmsg);
			result.setData(usrgrpid);
		}else if(object.has("error")){
			result.setCode(500);
			result.setMsg(ExceptionCode.failedmsg);
			result.setData(object.getJSONObject("error").getJSONArray("debug").getJSONObject(0).getJSONArray("args").getString(1));
		}
		return result;

	}
}
