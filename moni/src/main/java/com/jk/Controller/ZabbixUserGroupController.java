package com.jk.Controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.domain.Result;
import com.jk.service.ZabbixUserGroupService;
import com.zabbix.api.domain.base.UserGroup;
import com.zabbix.api.test.TestUsergroup;
@RestController
public class ZabbixUserGroupController {
	/**
	 * 获取用户组所有信息
	 * @return
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@PostMapping(value="/getUserGroup")
	public List<UserGroup> getUserGroup(@RequestParam("auth")String auth) throws JSONException, JsonProcessingException, IOException{
		List<UserGroup> list = ZabbixUserGroupService.getUserGroup(auth);
		return list;
	} 
	/**
	 * 根据name创建用户组
	 * @throws JSONException 
	 * */
	@RequestMapping(value = "/createUserGroup", method = RequestMethod.POST)
	public String createUserGroup(@RequestParam("userGroupName")String name,
			@RequestParam("userStatus")Integer userStatus,
			@RequestParam(value="read_write",required=false) String[] read_write,
			@RequestParam(value="read_only",required=false) String[] read_only,
			@RequestParam(value="deny",required=false) String[] deny,@RequestParam("auth")String auth) throws JSONException{
		TestUsergroup tug=new TestUsergroup();
        String id=tug.testUserGroupCreate(name,userStatus,read_write,read_only,deny, auth);
        return id;
	} 
	/**
	 * 根据用户组id删除用户组
	 * @throws JSONException 
	 * */
	@RequestMapping(value = "/deleteUserGroup", method = RequestMethod.POST)
	public Result<String> deleteUserGroup(@RequestParam("usergroupid")List<String> ids,@RequestParam("auth")String auth) throws JSONException{
		Result<String> result=ZabbixUserGroupService.deleteUserGroup(ids, auth);
        return result;
	}
	/**
	 * 修改用户组信息
	 * parameter 1：用户组信息 2：权限信息 3：修改人id
	 * @throws JSONException 
	 * */
	@RequestMapping(value = "/updateUserGroup", method = RequestMethod.POST)
	public Result<String> updateUserGroup(@RequestParam("id")Integer id,
			@RequestParam("name")String name,
			@RequestParam("userStatus")Integer userStatus,
			@RequestParam(value="read_write",required=false) String[] read_write,
			@RequestParam(value="read_only",required=false) String[] read_only,
			@RequestParam(value="deny",required=false) String[] deny,@RequestParam("auth")String auth) throws JSONException{
		TestUsergroup tug=new TestUsergroup();
		Result<String> user=tug.testUserGroupUpdate(id,name,userStatus,read_write,read_only,deny, auth);
        return user;
	}
}
