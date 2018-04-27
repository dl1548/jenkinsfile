package com.jk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jk.domain.Media;


public interface MediaRepository  extends JpaRepository<Media, Integer>{
	public List<Media> deleteByUserid(String userid);
	public List<Media> findByUserid(String userid);
}
