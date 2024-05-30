package com.restApi.RestFulResources.Jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.restApi.RestFulResources.User.user;

public interface UserRepository extends JpaRepository<user, Integer>{
	
}


