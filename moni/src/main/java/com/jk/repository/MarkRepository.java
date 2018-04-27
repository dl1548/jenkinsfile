package com.jk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jk.domain.Mark;
public interface MarkRepository extends JpaRepository<Mark, Integer>{
	public Mark findByName(String name);
}
