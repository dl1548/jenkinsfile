package com.jk.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.jk.domain.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	public User findByusername(String username);
}
