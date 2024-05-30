package com.restApi.RestFulResources.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	
	@GetMapping ("/users")
	public List <user> retrieveAllUsers(){  // list of user objects converted to json
		return service.findAll();
	}
	
	@GetMapping("/users/{id}") // using hateos to provide links to other resources
	public EntityModel <user> retriveUserById (@PathVariable int id){ //entity model wraps the user bean. This allows us not to make changes to the actual structure of the user object/component
		user user = service.findOne(id);
		
		EntityModel<user> entityModel = EntityModel.of(user); //create an entity model of type user
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers()); //create a link pointing to the method u want to execute 
		entityModel.add(link.withRel("all-users")); // add a reference / description to the link
		
		return entityModel;
	}
	
//	@GetMapping ("/users/{id}") // regular implementation
//	public user retriveUserById(@PathVariable int id) {
//		return service.findOne(id);
//	}
	
//	@PostMapping("/users") // simple method for a post request
//	public void createUser(@RequestBody user user) { //heavy work of conversions and even object creation done by the configurations
//		service.save(user);
//	}
	
	
	@PostMapping("/users") // Post method that returns a correct response code in this case 200 for created. and gives the uri of the resource created back
	public ResponseEntity <user> createUser (@Valid @RequestBody user user){ //ResponseEntity builds up the response code  //@valid uses the validation dependancy to check the user object fields are valid
		user savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //to the current uri
				.path("/{id}") // add an id variable
				.buildAndExpand(savedUser.getId()) //replace id variable with new user id
				.toUri(); // convert it to a uri
		return ResponseEntity.created(location).build(); //created response code
	}
	
	@DeleteMapping ("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		service.deleteById(id);
	}
}
