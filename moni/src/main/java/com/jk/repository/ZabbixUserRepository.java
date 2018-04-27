package com.jk.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.ZabbixUser;

public interface ZabbixUserRepository  extends JpaRepository<ZabbixUser, Integer>{

	public List<ZabbixUser> findByAliasLike(String alias);
	
	public ZabbixUser findByUserid(String userid);

	@Transactional
	int deleteByUserid(String userid);
	//通过用户名密码查询用户
//    @Query(value = "replace into (active,deptid,jobid,alias,nickname,passwordid,period,phone,severity,mediatypeid,groupid,type,sendto) values ()", nativeQuery = true)
//    @Modifying
//    List<ZabbixUser> findUserByName(String username, String password);
}
