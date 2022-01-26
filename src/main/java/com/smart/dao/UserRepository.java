package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.*;

public interface UserRepository  extends JpaRepository<User, Integer>{
  
	@Query("select u  from User where u.email = :email")
	public User getUserByUserName(@Param("email") String email)  ;
		
	}
		
	

