package com.restApi.RestFulResources.Jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.restApi.RestFulResources.User.Post;
import com.restApi.RestFulResources.User.user;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJpaResource(UserRepository repository,PostRepository postRepository) {
		this.userRepository = repository;
		this.postRepository = postRepository;
	}
	
	
	@GetMapping ("/jpa/users")
	public List <user> retrieveAllUsers(){  // list of user objects converted to json
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}") 
	public EntityModel <user> retriveUserById (@PathVariable int id){ 
		Optional<user> user = userRepository.findById(id); // find by id now
		
		EntityModel<user> entityModel = EntityModel.of(user.get()); // user.get added
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrieveAllUsers());  
		entityModel.add(link.withRel("all-users")); 
		
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
	
	
	@PostMapping("/jpa/users") // Post method that returns a correct response code in this case 200 for created. and gives the uri of the resource created back
	public ResponseEntity <user> createUser (@Valid @RequestBody user user){ //ResponseEntity builds up the response code  //@valid uses the validation dependancy to check the user object fields are valid
		user savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //to the current uri
				.path("/{id}") // add an id variable
				.buildAndExpand(savedUser.getId()) //replace id variable with new user id
				.toUri(); // convert it to a uri
		return ResponseEntity.created(location).build(); //created response code
	}
	
	@DeleteMapping ("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	// get all posts for a user
	@GetMapping ("/jpa/users/{id}/posts")
	public List<Post> retrivePostsForUser(@PathVariable int id) {
		Optional<user> user = userRepository.findById(id);
		return user.get().getPosts();
	}
	
	// create a post for user
	@PostMapping ("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<user> user = userRepository.findById(id);
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()  //to the current uri
				.path("/{id}") // add an id variable
				.buildAndExpand(savedPost.getId()) //replace id variable with new post id
				.toUri(); // convert it to a uri
		return ResponseEntity.created(location).build();
	}
	
	//get a specific post and link to all posts for that user
	@GetMapping("/jpa/users/{id}/posts/{post_id}")
	public EntityModel<Post> retrivePostById (@PathVariable int post_id, @PathVariable int id){ 
		Optional<Post> post = postRepository.findById(post_id); // find by id now
		
		EntityModel<Post> entityModel = EntityModel.of(post.get()); // user.get added
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).retrivePostsForUser(id));  
		entityModel.add(link.withRel("all-user-posts")); 
		
		return entityModel;
	}
	
}
