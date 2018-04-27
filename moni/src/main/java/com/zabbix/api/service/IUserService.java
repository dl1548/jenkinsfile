package com.zabbix.api.service;
import java.util.List;

import com.zabbix.api.domain.base.User;
import com.zabbix.api.domain.user.UserAddmediaRequest;
import com.zabbix.api.domain.user.UserAuthenticateRequest;
import com.zabbix.api.domain.user.UserCreateRequest;
import com.zabbix.api.domain.user.UserDeleteMediaRequest;
import com.zabbix.api.domain.user.UserDeleteRequest;
import com.zabbix.api.domain.user.UserGetRequest;
import com.zabbix.api.domain.user.UserIsreadableRequest;
import com.zabbix.api.domain.user.UserIswritableRequest;
import com.zabbix.api.domain.user.UserLoginRequest;
import com.zabbix.api.domain.user.UserLogoutRequest;
import com.zabbix.api.domain.user.UserUpdateProfileRequest;
import com.zabbix.api.domain.user.UserUpdateRequest;
import com.zabbix.api.domain.user.UserUpdatemediaRequest;
/**
 * @ClassName: IUserService
 * @Description: 
 * @author
 * @date
 */
public interface IUserService {
	/**
	 * @Title: get
	 * @Description: 获得用户信息（json）
	 * @param get
	 * @return Object
	 * @throws
	 */
	public Object get(UserGetRequest get,String auth);
	
	/**
	 * @Title: login
	 * @Description: 用户登录
	 * @param login
	 * @return Object
	 * @throws
	 */
	public Object login(UserLoginRequest login,String auth);
	
	/**
	 * @Title: logout
	 * @Description: 用户退出
	 * @param logout
	 * @return Object
	 * @throws
	 */
	public Object logout(UserLogoutRequest logout,String auth);

	/**
	 * @Title: getUserBean
	 * @Description: 获得用户信息（bean）
	 * @param get void
	 * @throws
	 */
	public List<User> getUserBean(UserGetRequest get,String auth);
	
	public Object addmedia(UserAddmediaRequest addmedia,String auth);
	public Object authenticate(UserAuthenticateRequest authenticate,String auth);
	public Object create(UserCreateRequest create,String auth);
	public Object deleteMedia(UserDeleteMediaRequest deleteMedia,String auth);
	public Object delete(UserDeleteRequest delete,String auth);
	public Object isreadable(UserIsreadableRequest isreadable,String auth);
	public Object iswritable(UserIswritableRequest iswritable,String auth);
	public Object updatemedia(UserUpdatemediaRequest updatemedia,String auth);
	public Object updateProfile(UserUpdateProfileRequest updateProfile,String auth);
	public Object update(UserUpdateRequest update,String auth);
	public boolean addUser(User user,String auth);
	public boolean updateUser(User user,String auth);
	public boolean deleteUser(User user,String auth);
	public List<User> getUser(User user,String auth);
}
