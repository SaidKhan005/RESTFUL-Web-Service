package com.SocialMedia.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.SocialMedia.user.User;



public interface UserRepository extends JpaRepository<User, Integer> {

}