package com.jk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jk.domain.HostL;

@Repository
public interface HostRepository extends JpaRepository<HostL, Integer>{

	public HostL findById(Integer id);
	public HostL findByName(String name);
	@Query(value = "SELECT TYPE,COUNT(TYPE) FROM host GROUP BY TYPE", nativeQuery = true)
    public List<String> countList();
	public List<HostL> findByTypeLike(String type);
}
