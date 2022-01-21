package com.init.User.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.User.Model.User;
import com.init.User.Service.ServiceUser;

@RestController
@RequestMapping("/")
public class ControllerUser {

	@Autowired
	private ServiceUser service = new ServiceUser();

	// 1 Llistar Users retorna la llista de Users
	// (GET /users/).
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() {
		try {
			List<User> users = service.listUsers();
			return new  ResponseEntity<List<User>>(users, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	// 2 POST: /user : crea un User
	@PostMapping(value = "/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User newUser = service.createUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>( HttpStatus.CONFLICT);
		}
		
	}

	// 3 Delete:/user /{username}:Borra un User
	@DeleteMapping(value = "/user/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username) {
		try {
			String response = service.DeleteUser(username);
			return new ResponseEntity<String>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	// 4 Get/user/{username}: buscar usuario
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable String username) {
		try {
			Optional<User> user = service.getUser(username);
			return new ResponseEntity<Optional<User>> (user,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	
	}

	// 5 Post/user/{id}: actualizar usuario
	@PostMapping(value = "/user/{username}")
	public ResponseEntity<String> updateUsers(@PathVariable String username, @RequestBody User user) {
		try {
			String response = service.updateUser(username, user);

			return new ResponseEntity<String>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
}
