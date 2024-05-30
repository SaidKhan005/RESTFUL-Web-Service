package com.restApi.RestFulResources.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;



@Component
public class UserDaoService {

	private static List <user> users = new ArrayList<>();
	private static int count = 0;
	static {
		users.add(new user (count++, "Adam", LocalDate.now().minusYears(30)));
		users.add(new user (count++, "Mike", LocalDate.now().minusYears(24)));
		users.add(new user (count++, "Susa", LocalDate.now().minusYears(54)));
	}
	
	public user save(user user) {
		user.setId(++count);
		users.add(user);
		return user;
	}
	
	public List<user> findAll(){
		return users;
	}
	
	public user findOne(int id) {

		Predicate<? super user> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
	}
	
	public void deleteById(int id) {

		Predicate<? super user> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
	
}
