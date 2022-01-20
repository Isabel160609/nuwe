package com.init.User.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class Controller {

	@Autowired
	private ServiceUser service = new ServiceUser();

	// 1 Llistar Users retorna la llista de Users
	// (GET /users/).
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() {

		List<User> users = service.listUsers();
		return ResponseEntity.ok(users);
	}

	// 2 POST: /user : crea un User
	@PostMapping(value = "/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User newUser = service.createUser(user);
		return ResponseEntity.ok(newUser);
	}

	// 3 Delete:/user /{username}:Borra un User
	@DeleteMapping(value = "/user/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username) {
		String response = service.DeleteUser(username);
		return ResponseEntity.ok(response);
	}

	// 4 Get/user/{username}: buscar usuario
	@GetMapping(value = "/users/{username}")
	public ResponseEntity<Optional<User>> getUsers(@PathVariable String username) {

		Optional<User> user = service.getUser(username);
		return ResponseEntity.ok(user);
	}

	// 4 Post/user/{id}: actualizar usuario
	@PostMapping(value = "/user/{username}")
	public ResponseEntity<String> updatetUsers(@PathVariable String username, @RequestBody User user) {

		String response = service.updateUser(username, user);

		return ResponseEntity.ok(response);
	}
}
