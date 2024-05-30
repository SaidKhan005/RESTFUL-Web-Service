package com.restApi.RestFulResources.User;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details") //name of the table
public class user {
	
	protected user(){ // jpa needs an empty constructor
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min =2, message="Name should have atleast 2 charecters") //for validation
	private String name;
	
	@Past(message = "Birth Date should be in the past") //check jakarta validation dependancy for all functionalities
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user") // a user can have many posts.. mapped to user field in posts class
	@JsonIgnore // do not display as a response for user calls
	private List<Post> posts;
	
	
	public user(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
