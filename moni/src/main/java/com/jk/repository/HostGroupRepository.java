package com.jk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.domain.Hostgroup;

public interface HostGroupRepository  extends JpaRepository<Hostgroup, Integer>{
	public Hostgroup findById(Integer id);

	public Hostgroup findByHostgroupid(Integer hostgroupid);
}
