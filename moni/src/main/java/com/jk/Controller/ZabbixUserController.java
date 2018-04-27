package com.jk.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jk.service.MD5Service;
import com.jk.service.ZabbixuserService;
import com.jk.domain.Media;
import com.jk.domain.Result;
import com.jk.domain.ZabbixUser;
import com.jk.repository.UserRepository;
import com.jk.repository.ZabbixUserRepository;
import com.zabbix.api.domain.base.User;
import com.zabbix.api.test.TestUser;
import com.zabbix.util.FormatData;

@RestController
public class ZabbixUserController {
	@Autowired
	ZabbixUserRepository zabbixuserrepository;
	
	@Autowired
    UserRepository userRepository;
	private static ZabbixuserService ZabbixuserService=new ZabbixuserService();
	/**
	 * 获取用户所有信息
	 * @return
	 */
	@PostMapping(value="/getUsers")
	public List<ZabbixUser> getUser(@RequestParam("auth")String auth){
		List<User> userList=new TestUser().testGetUserBean(auth);
		for (User user : userList) {
			ZabbixUser zuer=ZabbixuserService.getAllUser(user);//userBean to zabbixuser 
			ZabbixUser user2=zabbixuserrepository.findByUserid(user.getUserid());
			if(user2==null){
				zabbixuserrepository.save(zuer);
			}else{
				user2.setUserid(null==user.getUserid()?"":user.getUserid());
				user2.setAlias(null==user.getAlias()?"":user.getAlias());
				user2.setNickname(null==user.getSurname()?"":user.getSurname());
				user2.setPasswordid(null==user.getPasswd()?"":user.getPasswd());
				user2.setType(null==user.getType()?0:user.getType());
				user2.setGroupid(null==user.getGroups()?"":FormatData.grouplistToString(user.getGroups()));
				
				if(null!=user.getMideas()){
					Iterator<Media> ite=user.getMideas().iterator();
					while(ite.hasNext()){
						Media media=ite.next();
                       
						user2.setActive(null==media.getActive()?"":media.getActive());
						user2.setSendto(null==media.getSendto()?"":media.getSendto());
						user2.setSeverity(null==media.getSeverity()?"":media.getSeverity());
						user2.setPeriod(null==media.getPeriod()?"":media.getPeriod());
						user2.setMediatypeid(null==media.getMediatypeid()?"":media.getMediatypeid());
					}
			}
				zabbixuserrepository.save(user2);
		}
			}
		return zabbixuserrepository.findAll();
	}
	/**
	 * 创建用户
	 * @return
	 * @throws JSONException 
	 */
	@PostMapping(value="/createUser")
	@ResponseBody
	public List<String> create(@RequestBody ZabbixUser zuser,@RequestParam("auth")String auth) throws JSONException{
		
		Result<String> result=ZabbixuserService.createuser(zuser, auth);
		List<String> rs=new ArrayList<>();
		if(result.getCode()==200){
			Integer userid=Integer.valueOf(result.getData());
			zuser.setUserid(userid.toString());
			zabbixuserrepository.save(zuser);
			com.jk.domain.User user=new com.jk.domain.User();
			user.setUsername(zuser.getAlias());
			user.setPassword(MD5Service.getMD5(zuser.getPasswordid()));
			userRepository.save(user);
			rs.add("创建成功");
		}else{
			rs.add("创建失败!");
		}
		return rs;
	}
	/**
	 * 模糊查询用户名
	 * @param ali
	 * @return
	 */
	@PostMapping(value="/getUsersByName")
	public List<ZabbixUser> queryUser(@RequestParam("name") String alias){
		//ZabbixuserService.queryUser(name);
		return zabbixuserrepository.findByAliasLike(alias);
	}
	/**
	 * 根据id删除user
	 * @param ids
	 * @return 
	 */
	@PostMapping(value="/deleteUsersById")
	public List<String> deleteUsersById(@RequestParam("ids")Integer[] ids,@RequestParam("auth")String auth){
		List<String> list=new ArrayList<>();
		try {
			for(Integer id:ids){
				zabbixuserrepository.deleteByUserid(String.valueOf(id));
				String result=ZabbixuserService.deleteuser(id.toString(), auth);
				list.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据id修改user
	 * @param ids
	 * @return 
	 * @throws JSONException 
	 */
	@PostMapping(value="/updateUserById")
	@ResponseBody
	public String updateUserById(@RequestBody ZabbixUser user,@RequestParam("auth")String auth) throws JSONException{
		try {
			System.out.println(user.getId());
			zabbixuserrepository.save(user);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return ZabbixuserService.updateUserById(user, auth);
	}
	
}
