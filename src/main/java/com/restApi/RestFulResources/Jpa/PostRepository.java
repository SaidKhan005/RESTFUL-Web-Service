package com.restApi.RestFulResources.Jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.restApi.RestFulResources.User.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
