package com.SocialMedia.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SocialMedia.user.Post;



public interface PostRepository extends JpaRepository<Post, Integer> {

}