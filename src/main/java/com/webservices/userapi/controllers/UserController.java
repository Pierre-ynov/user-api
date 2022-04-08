package com.webservices.userapi.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.userapi.daos.IUserDao;
import com.webservices.userapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController { // TODO : Gérer les responses autres que 200
	
	@Autowired
	IUserDao userDao;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getActiveUsers () {
		return ResponseEntity.ok(userDao.findAllActiveUsers());
	}
	
	@GetMapping("/users/admin")
	public ResponseEntity<List<User>> getAllUsers () {
		return ResponseEntity.ok(userDao.findAll());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById (@PathVariable String id) {
		return ResponseEntity.ok(userDao.findUserByID(id));
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser (@RequestBody ObjectNode jsonUser) {
		User user = new User(jsonUser);
		return ResponseEntity.ok(userDao.save(user));
	}
	
	@PostMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser (@RequestBody ObjectNode jsonUser, @PathVariable String id) {
		User user = getUserById(id).getBody();
		assert user != null;
		user.updateUser(jsonUser);
		user.setId(id);
		return ResponseEntity.ok(userDao.save(user));
	}
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<?> deleteUserById (@PathVariable String id) {
		User user = getUserById(id).getBody();
		assert user != null;
		user.deleteUser();
		userDao.save(user);
		return ResponseEntity.noContent().build();
	}
}
