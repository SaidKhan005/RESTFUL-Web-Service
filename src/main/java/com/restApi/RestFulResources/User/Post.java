package com.restApi.RestFulResources.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Integer postId;
	@Size(min=10)
	private String description;
	@ManyToOne(fetch = FetchType.LAZY ) //do not display user info with a post
	@JsonIgnore
	private user user;
	
	
	
	
	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	protected Post() {

	}

	public Integer getId() {
		return postId;
	}

	public void setId(Integer id) {
		this.postId = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [id=" + postId + ", description=" + description + "]";
	}


	
}
