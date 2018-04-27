package com.jk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.domain.HostProcess;


public interface ProcessRepository extends JpaRepository<HostProcess, Integer>{

	public List<HostProcess> findByBusinessname(String businessname);
}
