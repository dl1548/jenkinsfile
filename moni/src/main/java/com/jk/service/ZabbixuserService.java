package com.jk.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.jk.Enum.ExceptionCode;
import com.jk.domain.Media;
import com.jk.domain.Result;
import com.jk.domain.ZabbixUser;
import com.zabbix.api.domain.base.User;
import com.zabbix.api.domain.user.UserCreateRequest;
import com.zabbix.api.domain.user.UserDeleteRequest;
import com.zabbix.api.domain.user.UserGetRequest;
import com.zabbix.api.domain.user.UserUpdateRequest;
import com.zabbix.api.service.IUserService;
import com.zabbix.api.service.impl.UserServiceImpl;
import com.zabbix.util.FormatData;

public class ZabbixuserService {
    static IUserService iuserservice=	new UserServiceImpl();
    
	public Result<String> createuser(ZabbixUser user,String auth) throws JSONException{
		System.out.println(user.getAlias());
		System.out.println(user.getNickname());
		List<Media> user_medias = new ArrayList<>();;
		UserCreateRequest usercreaterequest=new UserCreateRequest(auth);
		usercreaterequest.getParams().setPasswd(user.getPasswordid());//密码
		usercreaterequest.getParams().setSurname(user.getNickname());//用户名
		usercreaterequest.getParams().setType(user.getType());//用户角色
		usercreaterequest.getParams().setAlias(user.getAlias());//姓名
		List<String> usrgrps=new ArrayList<>();
		if(null!=user.getGroupid()){
			String[] ids=user.getGroupid().split(",");
			for(String id:ids){
				usrgrps.add(id);
			}
		}
		usercreaterequest.getParams().setUsrgrps(usrgrps);
		if(user.getEmail()!=null && user.getEmail().equals("true")){
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("4");
			user_medias.add(Media);
		}
		if(user.getMessage()!=null && user.getEmail().equals("true")){
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("3");
			user_medias.add(Media);
		}
		if(user.getWechat()!=null && user.getEmail().equals("true")){ 
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("5");
			user_medias.add(Media);
		}
		usercreaterequest.getParams().setUser_medias(user_medias);
		JSONObject rs=(JSONObject)iuserservice.create(usercreaterequest, auth);
		Object errorInfo = null;
		Integer userid;
		Result<String> result=new Result<>();
		result.setCode(ExceptionCode.successcode);
		result.setMsg(ExceptionCode.successmsg);
		if (rs.has("result")) {
			userid = rs.getJSONObject("result").getJSONArray("userids").getInt(0);
			result.setData(userid.toString());
		}
		 else if (rs.has("error")) {
			 errorInfo =  rs.getJSONObject("error").getJSONArray("debug").getJSONObject(0).getJSONArray("args").get(1);
			 result.setData((String)errorInfo);
			 result.setCode(ExceptionCode.failedcode);
		     result.setMsg(ExceptionCode.failedmsg);
		}
		return result;
	}
	public String deleteuser(String id,String auth){
		UserDeleteRequest delete = new UserDeleteRequest(auth);
			delete.getParams().add(id);
		return iuserservice.delete(delete, auth).toString();
	}
	public List<User> queryUser(String name,String auth){
		UserGetRequest get = new UserGetRequest(auth);
		get.getParams().setSearchByAny(true);
		get.getParams().setOutput(name);
		return iuserservice.getUserBean(get, auth);
	}
	public ZabbixUser getAllUser(User user){
			ZabbixUser zuer=new ZabbixUser();
			System.out.println(user.getAlias());
			zuer.setUserid(null==user.getUserid()?"":user.getUserid());
			zuer.setAlias(null==user.getAlias()?"":user.getAlias());
			zuer.setNickname(null==user.getSurname()?"":user.getSurname());
			zuer.setPasswordid(null==user.getPasswd()?"":user.getPasswd());
			zuer.setType(null==user.getType()?0:user.getType());
			zuer.setGroupid(null==user.getGroups()?"":FormatData.grouplistToString(user.getGroups()));
			System.out.println("name="+user.getName()+"，id="+user.getUserid()+"，surname="+user.getSurname()+"，password="+user.getPasswd()+"，type="+user.getType());
			if(null!=user.getMideas()){
				Iterator<Media> ite=user.getMideas().iterator();
				while(ite.hasNext()){
					Media media=ite.next();
					zuer.setActive(null==media.getActive()?"":media.getActive());
					zuer.setSendto(null==media.getSendto()?"":media.getSendto());
					zuer.setSeverity(null==media.getSeverity()?"":media.getSeverity());
					zuer.setPeriod(null==media.getPeriod()?"":media.getPeriod());
					zuer.setMediatypeid(null==media.getMediatypeid()?"":media.getMediatypeid());
				}
			}
		return zuer;
	}
	public  String updateUserById(ZabbixUser user,String auth) throws JSONException{
		System.out.println(user.getPhone());
		UserUpdateRequest update=new UserUpdateRequest(auth);
		update.getParams().setAlias(user.getAlias());
		update.getParams().setSurname(user.getNickname());
		update.getParams().setPasswd(user.getPasswordid());
		update.getParams().setType(user.getType());
		update.getParams().setUserid(user.getUserid());
		List<String> usrgrps=new ArrayList<>();
		if(null!=user.getGroupid()){
			String[] ids=user.getGroupid().split(",");
			for(String id:ids){
				usrgrps.add(id);
			}
		}
		update.getParams().setUsrgrps(usrgrps);
		List<String> grps=new ArrayList<>();
		grps.add(user.getGroupid());
		update.getParams().setUsrgrps(grps);
		List<Media> user_medias=new ArrayList<>();
		System.out.println(user.getEmail());
		System.out.println(user.getMessage());
		System.out.println(user.getWechat());
		System.out.println(user.getActive());
		if(user.getEmail()!=null&&user.getEmail().equals("true")){
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("4");
			user_medias.add(Media);
		}
		if(user.getMessage()!=null&&user.getMessage().equals("true")){
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("3");
			user_medias.add(Media);
		}
		if(user.getWechat()!=null&&user.getWechat().equals("true")){
			Media Media=new Media();
			Media.setActive(user.getActive());//是否启用告警
			Media.setSendto(user.getSendto());//邮箱
			Media.setSeverity(user.getSeverity());//告警登记
			Media.setPeriod(user.getPeriod());//告警时间ss
			Media.setMediatypeid("5");
			user_medias.add(Media);
		}
		update.getParams().setMideas(user_medias);
		
		return iuserservice.update(update, auth).toString();
	}
}
