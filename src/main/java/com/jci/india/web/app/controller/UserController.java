package com.jci.india.web.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jci.india.web.app.dto.UserDTO;
import com.jci.india.web.app.model.User;
import com.jci.india.web.app.services.UserDetailsServiceImpl;


import javassist.NotFoundException;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/users",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	UserDetailsServiceImpl userService;
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
		try {
			if(userService.save(userDTO)) {		
				return ResponseEntity.ok("User created successfully.");	
			}
              else {
            	  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Registration Failed.");
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping(value = "/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
		Optional<User> user = userService.findOne(id);
		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok().body(user.get());
	}
	
	@PutMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO userDTO) {
		User userUpdate = null;
		try {
			userUpdate = userService.update(userDTO, id);
		} catch (NotFoundException e) {
			LOGGER.error(e.getMessage(),e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok().body(userUpdate);
	}
	
	@DeleteMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		Optional<User> user = userService.findOne(id);
		if (!user.isPresent()) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("User Not Found");
		}

		userService.delete(user.get());
		return ResponseEntity.ok().body(user.get().getName() + "  Successfully Deleted");
	}


}
