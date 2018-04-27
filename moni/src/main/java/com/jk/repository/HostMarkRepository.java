package com.jk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jk.domain.HostMark;
@Repository
@Transactional
public interface HostMarkRepository extends JpaRepository<HostMark,Integer>{
	public List<HostMark> findByHostid(Integer hostid);
	public List<HostMark> findByMark(String mark);
	public List<HostMark> deleteByHostid(Integer hostid);
	public List<HostMark> deleteByMark(String mark);
	@Query(value = "select h.hostid from hostmark h where  h.id=?1", nativeQuery = true)
    @Modifying
    List<HostMark> findHostidById(Integer id);
}
